package com.android.team.moshey.viewmodels;

import com.android.team.moshey.models.entities.AvailableTicket;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * Created By blackcoder
 * On 03/04/19
 **/
public class BookingViewModel extends ViewModel {

    //    Data in Booking View,lifecycle aware
    private MutableLiveData<List<AvailableTicket>> mAvailableTickets;

    public BookingViewModel() {
        mAvailableTickets = new MutableLiveData<>();
    }

    public MutableLiveData<List<AvailableTicket>> getAvailableTickets() {
        return mAvailableTickets;
    }

    public void setAvailableTickets(MutableLiveData<List<AvailableTicket>> availableTickets) {
        mAvailableTickets = availableTickets;
    }
}
