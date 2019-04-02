package com.android.team.moshey.ui;

import android.os.Bundle;
import android.widget.Toast;

import com.android.team.moshey.R;
import com.android.team.moshey.databinding.ActivityBookingBinding;
import com.android.team.moshey.models.entities.AvailableTicket;
import com.android.team.moshey.viewmodels.BookingViewModel;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

public class BookingActivity extends AppCompatActivity {

    private BookingViewModel mBookingViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityBookingBinding vBookingBinding = DataBindingUtil.setContentView(this, R.layout.activity_booking);
        Toolbar vToolbar = vBookingBinding.bookingAppBar;
        vToolbar.setTitle(getString(R.string.book_title));
        setSupportActionBar(vToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        }
        mBookingViewModel = ViewModelProviders.of(this).get(BookingViewModel.class);
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

        }
        Toast.makeText(BookingActivity.this, getString(R.string.tickets_unavailable), Toast.LENGTH_SHORT).show();
    }
}
