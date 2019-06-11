package com.android.team.moshey.models.entities.tickets;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Objects;

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
    @ColumnInfo(name = "date")
    private String date;

    @Ignore
    public MyTicket() {
    }

    public MyTicket(long _id, String ticketId, String from, String to, String date) {
        this._id = _id;
        this.ticketId = ticketId;
        this.from = from;
        this.to = to;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    @Override
    public boolean equals(Object vo) {
        if (this == vo) return true;
        if (vo == null || getClass() != vo.getClass()) return false;
        MyTicket vvMyTicket = (MyTicket) vo;
        return get_id() == vvMyTicket.get_id() &&
                getTicketId().equals(vvMyTicket.getTicketId()) &&
                getFrom().equals(vvMyTicket.getFrom()) &&
                getTo().equals(vvMyTicket.getTo()) &&
                getDate().equals(vvMyTicket.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(get_id(), getTicketId(), getFrom(), getTo(), getDate());
    }
}
