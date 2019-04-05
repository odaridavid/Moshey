package com.android.team.moshey.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.team.moshey.R;
import com.android.team.moshey.models.entities.MyTicket;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created By blackcoder
 * On 05/04/19
 **/
public class MyTicketsAdapter extends RecyclerView.Adapter<MyTicketsAdapter.MyTicketsViewHolder> {

    private List<MyTicket> mMyTickets;

    public MyTicketsAdapter(List<MyTicket> myTickets) {
        mMyTickets = myTickets;
    }

    @NonNull
    @Override
    public MyTicketsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ticket, parent, false);
        return new MyTicketsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyTicketsViewHolder holder, int position) {

        if (mMyTickets != null) {
            MyTicket ticktet = mMyTickets.get(position);
            holder.tvFrom.setText(ticktet.getFrom());
            holder.tvTo.setText(ticktet.getTo());
            holder.tvTicketId.setText(ticktet.getTicketId());
            holder.date.setText(ticktet.getDate());
        }
    }

    @Override
    public int getItemCount() {
        return mMyTickets.size();
    }

    class MyTicketsViewHolder extends RecyclerView.ViewHolder {
        TextView tvFrom, tvTo, tvTicketId, date;

        MyTicketsViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFrom = itemView.findViewById(R.id.text_view_my_from);
            tvTo = itemView.findViewById(R.id.text_view_my_to);
            tvTicketId = itemView.findViewById(R.id.text_view_ticket_id);
            date = itemView.findViewById(R.id.text_view_ticket_date_booked);
        }
    }
}
