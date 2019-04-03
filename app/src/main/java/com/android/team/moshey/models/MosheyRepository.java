package com.android.team.moshey.models;

import android.util.Log;

import com.android.team.moshey.models.db.dao.AvailableTicketsDao;
import com.android.team.moshey.models.entities.AvailableTicket;
import com.android.team.moshey.utils.ThreadAppExecutors;

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
    private final AvailableTicketsDao mAvailableTicketsDao;
    private boolean mInitialized = false;


    private MosheyRepository(FirebaseDataSource firebaseDataSource, AvailableTicketsDao availableTicketsDao, ThreadAppExecutors threadAppExecutors) {
        mFirebaseDataSource = firebaseDataSource;
        mAvailableTicketsDao = availableTicketsDao;
        LiveData<List<AvailableTicket>> vAvailableTickets = mFirebaseDataSource.getAvailableTickets();
        vAvailableTickets.observeForever(availableTickets -> threadAppExecutors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                mAvailableTicketsDao.saveAvailableTickets(availableTickets);
                Log.d("Available Tickets", availableTickets.toString());
            }
        }));
    }

    public synchronized static MosheyRepository getInstance(AvailableTicketsDao availableTicketsDao, FirebaseDataSource firebaseDataSource, ThreadAppExecutors threadAppExecutors) {
        if (sMosheyRepository == null) {
            synchronized (LOCK) {
                sMosheyRepository = new MosheyRepository(firebaseDataSource, availableTicketsDao, threadAppExecutors);
            }
        }
        return sMosheyRepository;
    }

    /**
     * Initialise Fetch Service from firebase
     */
    private void startFetchTickets() {
        mFirebaseDataSource.startFetchForAvailableTickets();
    }

    private synchronized void initializeData() {
        // Only perform initialization once per app lifetime. If initialization has already been
        // performed, we have nothing to do in this method.
        if (mInitialized) return;
        mInitialized = true;
        startFetchTickets();
    }

    public LiveData<List<AvailableTicket>> getTickets() {
        initializeData();
        return mAvailableTicketsDao.getAllAvailableTickets();
    }

}
