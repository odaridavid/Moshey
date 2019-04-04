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

//    private final LiveData<List<MyTicket>> mMyTickets;
    private FirestoreRecyclerOptions<AvailableTicket> mFirestoreRecyclerOptions;
    private MosheyRepository mMosheyRepository;

    BookingViewModel(MosheyRepository mosheyRepository) {
//        mMyTickets = mosheyRepository.getTickets();
        mFirestoreRecyclerOptions = mosheyRepository.getAvailableTicketsRemote();
        mMosheyRepository = mosheyRepository;
    }

    /**
     * Loads data from my tickets table
     *
     * @return {@link LiveData} list of observable tickets
     */
//    public LiveData<List<MyTicket>> getMyTickets() {
//        return mMyTickets;
//    }

    /**
     * @return {@link FirestoreRecyclerOptions<AvailableTicket>} Firestore Query Object to be used by adapter to get realtime events
     */
    public FirestoreRecyclerOptions<AvailableTicket> getFirestoreRecyclerOptions() {
        return mFirestoreRecyclerOptions;
    }

    /**
     * Pass Ticket to Repository to be added to database
     *
     * @param myTicket ticket booked by user
     */
    public void bookTicket(MyTicket myTicket) {
        mMosheyRepository.bookTicket(myTicket);
    }
}
