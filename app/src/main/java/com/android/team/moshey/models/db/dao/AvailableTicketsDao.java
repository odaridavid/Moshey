package com.android.team.moshey.models.db.dao;

import com.android.team.moshey.models.entities.AvailableTicket;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

/**
 * Created By blackcoder
 * On 03/04/19
 **/
@Dao
public interface AvailableTicketsDao {

    /**
     * Get Locally Cached Available Tickets
     *
     * @return {@link LiveData} an observable lifecycle aware list of tickets
     */
    @Query("SELECT * FROM available_tickets")
    LiveData<List<AvailableTicket>> getAllAvailableTickets();

    /**
     * Saves Loaded Tickets to the database
     *
     * @param availableTickets Tickets to insert
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveAvailableTickets(List<AvailableTicket> availableTickets);
}
