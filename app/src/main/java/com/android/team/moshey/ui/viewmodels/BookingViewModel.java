package com.android.team.moshey.ui.viewmodels;

import com.android.team.moshey.models.MosheyRepository;
import com.android.team.moshey.models.entities.AvailableTicket;
import com.android.team.moshey.models.entities.MyTicket;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import androidx.lifecycle.ViewModel;

/**
 * Created By blackcoder
 * On 03/04/19
 **/
public class BookingViewModel extends ViewModel {

    private FirestoreRecyclerOptions<AvailableTicket> mFirestoreRecyclerOptions;
    private MosheyRepository mMosheyRepository;

    BookingViewModel(MosheyRepository mosheyRepository) {
        mFirestoreRecyclerOptions = mosheyRepository.getAvailableTicketsRemote();
        mMosheyRepository = mosheyRepository;
    }

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
