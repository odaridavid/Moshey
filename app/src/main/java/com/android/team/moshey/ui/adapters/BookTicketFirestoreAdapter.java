package com.android.team.moshey.ui.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.team.moshey.R;
import com.android.team.moshey.models.entities.AvailableTicket;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created By blackcoder
 * On 03/04/19
 **/
public final class BookTicketFirestoreAdapter extends FirestoreRecyclerAdapter<AvailableTicket, BookTicketViewHolder> {
    private IFirestoreAdapterCallback mCallback;

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public BookTicketFirestoreAdapter(@NonNull FirestoreRecyclerOptions<AvailableTicket> options, IFirestoreAdapterCallback callback) {
        super(options);
        mCallback = callback;
    }

    @Override
    protected void onBindViewHolder(@NonNull BookTicketViewHolder bookTicketViewHolder, int i, @NonNull AvailableTicket availableTicket) {
        Log.d("Adapter:", availableTicket.toString());
        bookTicketViewHolder.tvFrom.setText(availableTicket.getFrom());
        bookTicketViewHolder.tvTo.setText(availableTicket.getTo());
        bookTicketViewHolder.tvTicketsLeft.setText(String.valueOf(availableTicket.getLeft()));
    }

    @NonNull
    @Override
    public BookTicketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.tickets_available, parent, false);
        return new BookTicketViewHolder(v);
    }

    @Override
    public void onDataChanged() {
        super.onDataChanged();
        mCallback.onAdapterDataChanged(super.getItemCount());
        notifyDataSetChanged();
    }
}

class BookTicketViewHolder extends RecyclerView.ViewHolder {

    TextView tvFrom, tvTo, tvTicketsLeft;

    BookTicketViewHolder(@NonNull View itemView) {
        super(itemView);
        tvFrom = itemView.findViewById(R.id.text_view_from);
        tvTo = itemView.findViewById(R.id.text_view_to);
        tvTicketsLeft = itemView.findViewById(R.id.text_view_ticket_remaining);
        Button vBtnBookTicket = itemView.findViewById(R.id.button_book_ticket);
    }
}

