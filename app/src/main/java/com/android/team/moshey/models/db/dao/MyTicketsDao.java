package com.android.team.moshey.models.db.dao;

import com.android.team.moshey.models.entities.MyTicket;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

/**
 * Created By blackcoder
 * On 02/04/19
 **/
@Dao
public interface MyTicketsDao {

    /**
     * Load Personally Booked Tickets
     *
     * @return {@link LiveData} an observable lifecycle aware list of tickets
     */
    @Query("SELECT * FROM my_tickets")
    LiveData<List<MyTicket>> getMyTickets();

    /**
     * On Booking and payment made add a ticket to local database
     *
     * @param myTicket booked ticket
     */
    @Insert()
    void insertTicket(MyTicket myTicket);
}
