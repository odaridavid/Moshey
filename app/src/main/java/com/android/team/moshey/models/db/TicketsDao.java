package com.android.team.moshey.models.db;

import com.android.team.moshey.models.entities.MyTicket;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

/**
 * Created By blackcoder
 * On 02/04/19
 **/
@Dao
public interface TicketsDao {

    @Query("SELECT * FROM my_tickets")
    List<MyTicket> getAllTickets();

    @Insert(onConflict = OnConflictStrategy.FAIL)
    void insertTicket();
}
