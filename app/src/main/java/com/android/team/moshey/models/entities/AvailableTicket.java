package com.android.team.moshey.models.entities;

import com.android.team.moshey.models.ITicket;

import androidx.annotation.NonNull;

/**
 * Created By blackcoder
 * On 02/04/19
 **/
public class AvailableTicket implements ITicket {
    private String from;
    private String to;
    private int left;

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
