package com.android.team.moshey.ui.viewmodels;

import com.android.team.moshey.models.MosheyRepository;
import com.android.team.moshey.models.entities.AvailableTicket;

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

    BookingViewModel(MosheyRepository mosheyRepository) {
        mAvailableTickets = mosheyRepository.getTickets();
    }

    /**
     * Loads data from available tickets table
     *
     * @return {@link LiveData} list of observable tickets
     */
    public LiveData<List<AvailableTicket>> getAvailableTickets() {
        return mAvailableTickets;
    }
}
