package com.android.team.moshey.ui.viewmodels;

import com.android.team.moshey.models.MosheyRepository;
import com.android.team.moshey.models.entities.AvailableTicket;
import com.android.team.moshey.models.entities.MyTicket;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

/**
 * Created By blackcoder
 * On 03/04/19
 **/
public class BookingViewModel extends ViewModel {

    //    Data in Booking View,lifecycle aware
    private final LiveData<List<AvailableTicket>> mAvailableTickets;
    private FirestoreRecyclerOptions<AvailableTicket> mFirestoreRecyclerOptions;

    BookingViewModel(MosheyRepository mosheyRepository) {
        mAvailableTickets = mosheyRepository.getTickets();
        mFirestoreRecyclerOptions = mosheyRepository.getAvailableTicketsRemote();
    }

    /**
     * Loads data from available tickets table
     *
     * @return {@link LiveData} list of observable tickets
     */
    public LiveData<List<AvailableTicket>> getAvailableTickets() {
        return mAvailableTickets;
    }

    /**
     * @return {@link FirestoreRecyclerOptions<AvailableTicket>} Firestore Query Object to be used by adapter to get realtime events
     */
    public FirestoreRecyclerOptions<AvailableTicket> getFirestoreRecyclerOptions() {
        return mFirestoreRecyclerOptions;
    }

    public void bookTicket(MyTicket myTicket) {
        //                TODO Make Payments
//                TODO Decrement from firestore

    }
}
