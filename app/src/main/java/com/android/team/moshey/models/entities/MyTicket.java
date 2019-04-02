package com.android.team.moshey.models.entities;

import com.android.team.moshey.models.ITicket;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created By blackcoder
 * On 01/04/19
 **/

@Entity(tableName = "my_tickets")
public final class MyTicket implements ITicket {

    @PrimaryKey(autoGenerate = true)
    private long _id;
    @ColumnInfo(name = "ticket_id")
    private String ticketId;
    @ColumnInfo(name = "from")
    private String from;
    @ColumnInfo(name = "to")
    private String to;

    public MyTicket(long _id, String ticketId, String from, String to) {
        this._id = _id;
        this.ticketId = ticketId;
        this.from = from;
        this.to = to;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    @Override
    public String getFrom() {
        return from;
    }

    @Override
    public void setFrom(String from) {
        this.from = from;
    }

    @Override
    public String getTo() {
        return to;
    }

    @Override
    public void setTo(String to) {
        this.to = to;
    }


    @Override
    public String toString() {
        return "MyTicket{" +
                "ticketId='" + ticketId + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                '}';
    }
}
