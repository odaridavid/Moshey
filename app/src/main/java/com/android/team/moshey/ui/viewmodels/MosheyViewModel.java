package com.android.team.moshey.ui.viewmodels;

import com.android.team.moshey.models.MosheyRepository;
import com.android.team.moshey.models.entities.MyTicket;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

/**
 * Created By blackcoder
 * On 05/04/19
 **/
public class MosheyViewModel extends ViewModel {
    private final LiveData<List<MyTicket>> mMyTickets;

    MosheyViewModel(MosheyRepository mosheyRepository) {
        mMyTickets = mosheyRepository.getTickets();
    }

    /**
     * Loads data from my tickets table
     *
     * @return {@link LiveData} list of observable tickets
     */
    public LiveData<List<MyTicket>> getMyTickets() {
        return mMyTickets;
    }
}
