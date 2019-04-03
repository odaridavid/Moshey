package com.android.team.moshey.ui;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.team.moshey.R;
import com.android.team.moshey.databinding.ActivityBookingBinding;
import com.android.team.moshey.models.entities.AvailableTicket;
import com.android.team.moshey.ui.viewmodels.BookingViewModel;
import com.android.team.moshey.ui.viewmodels.BookingViewModelFactory;
import com.android.team.moshey.utils.InjectorUtils;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class BookingActivity extends AppCompatActivity {

    private BookingViewModel mBookingViewModel;
    private ActivityBookingBinding mBookingBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBookingBinding = DataBindingUtil.setContentView(this, R.layout.activity_booking);
        Toolbar vToolbar = mBookingBinding.bookingAppBar;
        vToolbar.setTitle(getString(R.string.book_title));
        setSupportActionBar(vToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        }
        BookingViewModelFactory factory = InjectorUtils.provideDetailViewModelFactory(this.getApplicationContext());
        mBookingViewModel = ViewModelProviders.of(this, factory).get(BookingViewModel.class);
        observeTickets();
    }

    public void observeTickets() {
        mBookingViewModel.getAvailableTickets().observe(this, availableTickets -> {
            if (availableTickets != null)
                bindUI(availableTickets);
        });
    }

    private void bindUI(List<AvailableTicket> availableTickets) {
        if (!availableTickets.isEmpty()) {
            RecyclerView vRecyclerView = mBookingBinding.availableTicketsRecycler;
            vRecyclerView.setAdapter(new BookTicketAdapter(availableTickets));
            vRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        } else
            Toast.makeText(BookingActivity.this, getString(R.string.tickets_unavailable), Toast.LENGTH_SHORT).show();
    }
}
