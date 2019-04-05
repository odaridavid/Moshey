package com.android.team.moshey.ui.adapters.callback;

/**
 * Created By blackcoder
 * On 04/04/19
 **/
public interface IBookTicketCallback {

    /**
     * Called When a ticket is being booked
     */
    void bookTrainTicket(String to,String from);
}
