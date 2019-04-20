package com.android.team.moshey.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.team.moshey.R;
import com.android.team.moshey.models.entities.tickets.AvailableTicket;
import com.android.team.moshey.ui.adapters.callback.IBookTicketCallback;
import com.android.team.moshey.ui.adapters.callback.IFirestoreAdapterCallback;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created By blackcoder
 * On 03/04/19
 **/
public final class BookTicketFirestoreAdapter extends FirestoreRecyclerAdapter<AvailableTicket, BookTicketFirestoreAdapter.BookTicketViewHolder> {
    private IFirestoreAdapterCallback mFirestoreAdapterCallback;
    private IBookTicketCallback mBookTicketCallback;

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     */
    public BookTicketFirestoreAdapter(@NonNull FirestoreRecyclerOptions<AvailableTicket> options, IFirestoreAdapterCallback firestoreAdapterCallback, IBookTicketCallback bookTicketCallback) {
        super(options);
        mFirestoreAdapterCallback = firestoreAdapterCallback;
        mBookTicketCallback = bookTicketCallback;
    }

    @Override
    protected void onBindViewHolder(@NonNull BookTicketViewHolder bookTicketViewHolder, int i, @NonNull AvailableTicket availableTicket) {
        bookTicketViewHolder.tvFrom.setText(availableTicket.getFrom());
        bookTicketViewHolder.tvTo.setText(availableTicket.getTo());
        bookTicketViewHolder.tvTicketsLeft.setText(String.valueOf(availableTicket.getLeft()));
        if (!(availableTicket.getLeft() > 0)) {
            bookTicketViewHolder.mBtnBookTicket.setEnabled(false);
            Context vContext = bookTicketViewHolder.itemView.getContext();
            int disabledColor = vContext.getResources().getColor(R.color.mtrl_on_primary_disabled);
            bookTicketViewHolder.mBtnBookTicket.setBackgroundColor(disabledColor);
        }
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
        mFirestoreAdapterCallback.onAdapterDataChanged(super.getItemCount());
        notifyDataSetChanged();
    }

    class BookTicketViewHolder extends RecyclerView.ViewHolder {

        final TextView tvFrom, tvTo, tvTicketsLeft;
        final Button mBtnBookTicket;

        BookTicketViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFrom = itemView.findViewById(R.id.text_view_from);
            tvTo = itemView.findViewById(R.id.text_view_to);
            tvTicketsLeft = itemView.findViewById(R.id.text_view_ticket_remaining);
            mBtnBookTicket = itemView.findViewById(R.id.button_book_ticket);
            mBtnBookTicket.setOnClickListener(v -> mBookTicketCallback.bookTrainTicket(tvTo.getText().toString(), tvFrom.getText().toString()));
        }
    }
}



