package com.android.team.moshey.models;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.android.team.moshey.models.entities.AvailableTicket;
import com.android.team.moshey.models.services.MosheyFirebaseService;
import com.android.team.moshey.models.services.MosheySyncIntentService;
import com.android.team.moshey.utils.ThreadAppExecutors;
import com.firebase.jobdispatcher.Constraint;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.Trigger;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import static com.android.team.moshey.utils.ConstantUtils.COLLECTION_TICKETS;
import static com.android.team.moshey.utils.ConstantUtils.TICKETS_SYNC_JOB_TAG;

/**
 * Created By blackcoder
 * On 03/04/19
 **/
public class FirebaseDataSource {
    private Context mContext;
    private final MutableLiveData<List<AvailableTicket>> mAvailableTickets;
    private static final Object LOCK = new Object();
    private static FirebaseDataSource sFirebaseDataSource;
    private FirebaseFirestore mFirebaseFirestoreDb;
    private static final int SYNC_INTERVAL_HOURS = 6;
    private static final int SYNC_INTERVAL_SECONDS = (int) TimeUnit.HOURS.toSeconds(SYNC_INTERVAL_HOURS);
    private static final int SYNC_FLEXTIME_SECONDS = SYNC_INTERVAL_SECONDS / 6;
    private ThreadAppExecutors mThreadAppExecutors;

    private FirebaseDataSource(Context context, ThreadAppExecutors executors) {
        mThreadAppExecutors = executors;
        mContext = context;
        mAvailableTickets = new MutableLiveData<>();
        mFirebaseFirestoreDb = FirebaseFirestore.getInstance();
    }

    LiveData<List<AvailableTicket>> getAvailableTickets() {
        return mAvailableTickets;
    }

    public synchronized static FirebaseDataSource getInstance(Context context, ThreadAppExecutors executors) {
        if (sFirebaseDataSource == null) {
            synchronized (LOCK) {
                sFirebaseDataSource = new FirebaseDataSource(context.getApplicationContext(), executors);
            }
        }
        return sFirebaseDataSource;
    }

    /**
     * Starts Intent Service to update data
     */
    void startFetchForAvailableTickets() {
        Intent intentToFetch = new Intent(mContext, MosheySyncIntentService.class);
        mContext.startService(intentToFetch);
    }

    /**
     * Fetch Data from Firebase Firestore Database and update obervers with post value method
     */
    public void fetchTickets() {
        List<AvailableTicket> ticketList = new ArrayList<>();
        mThreadAppExecutors.networkIO().execute(() ->
                mFirebaseFirestoreDb
                        .collection(COLLECTION_TICKETS)
                        .get()
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                QuerySnapshot lQueryDocumentSnapshots = task.getResult();
                                List<DocumentSnapshot> lDocuments = lQueryDocumentSnapshots.getDocuments();
                                if (lDocuments.size() > 0) {
                                    Log.d("Tickets:", String.valueOf(lDocuments.size()));
                                    for (DocumentSnapshot vDocumentSnapshot : lDocuments) {
                                        AvailableTicket vAvailableTicket = vDocumentSnapshot.toObject(AvailableTicket.class);
                                        ticketList.add(vAvailableTicket);
                                    }
                                    mAvailableTickets.postValue(ticketList);
                                }
                            } else Log.d("Firebase Ticket Fetch ", "Failure");
                        })
                        .addOnFailureListener(e -> Log.d("Firebase Ticket Fetch ", e.getMessage())));
    }

    public void scheduleRecurringFetchTicketsSync() {
        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(mContext));
        Job syncTicketsJob = dispatcher.newJobBuilder()
                .setService(MosheyFirebaseService.class)
                .setTag(TICKETS_SYNC_JOB_TAG)
                .setConstraints(Constraint.ON_ANY_NETWORK)
                .setLifetime(Lifetime.FOREVER)
                .setRecurring(true)
                .setTrigger(Trigger.executionWindow(
                        SYNC_INTERVAL_SECONDS,
                        SYNC_INTERVAL_SECONDS + SYNC_FLEXTIME_SECONDS))
                .setReplaceCurrent(true)
                .build();
        // Schedule the Job with the dispatcher
        dispatcher.schedule(syncTicketsJob);
    }
}
