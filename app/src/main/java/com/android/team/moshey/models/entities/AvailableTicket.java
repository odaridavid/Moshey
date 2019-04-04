package com.android.team.moshey.models.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

/**
 * Created By blackcoder
 * On 02/04/19
 **/
@Entity(tableName = "available_tickets", indices = {@Index(value = {"from", "to"}, unique = true)})
public class AvailableTicket implements ITicket {
    @PrimaryKey(autoGenerate = true)
    private long _id;
    @ColumnInfo(name = "from")
    private String from;
    @ColumnInfo(name = "to")
    private String to;
    @ColumnInfo(name = "left")
    private int left;

    @Ignore
    public AvailableTicket() {
    }

    public AvailableTicket(long _id, String from, String to, int left) {
        this._id = _id;
        this.from = from;
        this.to = to;
        this.left = left;
    }


    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
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

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    @NonNull
    @Override
    public String toString() {
        return "AvailableTicket{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", left=" + left +
                '}';
    }
}
