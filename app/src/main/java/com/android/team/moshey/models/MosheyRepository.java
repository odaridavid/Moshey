package com.android.team.moshey.models;

import android.util.Log;

import com.android.team.moshey.models.db.dao.MyTicketsDao;
import com.android.team.moshey.models.entities.AvailableTicket;
import com.android.team.moshey.models.entities.MyTicket;
import com.android.team.moshey.models.network.FirebaseDataSource;
import com.android.team.moshey.utils.ThreadAppExecutors;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import java.util.List;

import androidx.lifecycle.LiveData;

/**
 * Created By blackcoder
 * On 03/04/19
 **/
public class MosheyRepository {
    private FirebaseDataSource mFirebaseDataSource;
    private static final Object LOCK = new Object();
    private static MosheyRepository sMosheyRepository;
    private final MyTicketsDao mMyTicketsDao;
    private ThreadAppExecutors mThreadAppExecutors;

    private MosheyRepository(FirebaseDataSource firebaseDataSource, MyTicketsDao myTicketsDao, ThreadAppExecutors threadAppExecutors) {
        mFirebaseDataSource = firebaseDataSource;
        mMyTicketsDao = myTicketsDao;
        mThreadAppExecutors = threadAppExecutors;
    }

    public synchronized static MosheyRepository getInstance(MyTicketsDao myTicketsDao, FirebaseDataSource firebaseDataSource, ThreadAppExecutors threadAppExecutors) {
        if (sMosheyRepository == null) {
            synchronized (LOCK) {
                sMosheyRepository = new MosheyRepository(firebaseDataSource, myTicketsDao, threadAppExecutors);
            }
        }
        return sMosheyRepository;
    }

    /**
     * Gets a list of all booked tickets
     *
     * @return {@link LiveData} Observable List of tickets
     */
    public LiveData<List<MyTicket>> getTickets() {
        return mMyTicketsDao.getMyTickets();
    }

    public FirestoreRecyclerOptions<AvailableTicket> getAvailableTicketsRemote() {
        return mFirebaseDataSource.availableTicketQuery();
    }

    /**
     * Book a ticket process by adding it to local db,decrementing firestore field of available tickets and
     * adding it to booked collection with unique ticket id
     *
     * @param myTicket object with details pertaining the ticket
     */
    public void bookTicket(MyTicket myTicket) {
        mThreadAppExecutors.diskIO().execute(() -> {
            mMyTicketsDao.insertTicket(myTicket);
            Log.d("Booking", "Ticket Booked");
        });
        mThreadAppExecutors.networkIO().execute(() -> {
            mFirebaseDataSource.bookTicket(myTicket);
            mFirebaseDataSource.registerTicket(myTicket.getTicketId());
//          TODO  Make payment
        });
    }
}
