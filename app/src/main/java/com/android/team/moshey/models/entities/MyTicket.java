package com.android.team.moshey.models.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

/**
 * Created By blackcoder
 * On 01/04/19
 **/

@Entity(tableName = "my_tickets", indices = {@Index(value = {"ticket_id"}, unique = true)})
public class MyTicket implements ITicket {

    @PrimaryKey(autoGenerate = true)
    private long _id;
    @ColumnInfo(name = "ticket_id")
    private String ticketId;
    @ColumnInfo(name = "from")
    private String from;
    @ColumnInfo(name = "to")
    private String to;

    @Ignore
    public MyTicket() {
    }

    public MyTicket(long _id, String ticketId, String from, String to) {
        this._id = _id;
        this.ticketId = ticketId;
        this.from = from;
        this.to = to;
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
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


    @NonNull
    @Override
    public String toString() {
        return "MyTicket{" +
                "ticketId='" + ticketId + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                '}';
    }
}
