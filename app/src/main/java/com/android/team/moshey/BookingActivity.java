package com.android.team.moshey;

import android.os.Bundle;

import com.android.team.moshey.databinding.ActivityBookingBinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

public class BookingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityBookingBinding vBookingBinding = DataBindingUtil.setContentView(this, R.layout.activity_booking);
    }
}
