package com.android.team.moshey.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.android.team.moshey.R;
import com.android.team.moshey.databinding.ActivityMosheyBinding;
import com.android.team.moshey.models.entities.MyTicket;
import com.android.team.moshey.ui.adapters.MyTicketsAdapter;
import com.android.team.moshey.ui.viewmodels.MosheyViewModel;
import com.android.team.moshey.ui.viewmodels.MosheyViewModelFactory;
import com.android.team.moshey.utils.InjectorUtils;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MosheyActivity extends AppCompatActivity {

    private MosheyViewModel mMosheyViewModel;
    private RecyclerView mViewTicketsListRecycler;
    private TextView mTextViewNoTickets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMosheyBinding vActivityMosheyBinding = DataBindingUtil.setContentView(this, R.layout.activity_moshey);
        setSupportActionBar(vActivityMosheyBinding.mosheyAppBar);
        mViewTicketsListRecycler = vActivityMosheyBinding.viewTicketsListRecycler;
        mTextViewNoTickets = vActivityMosheyBinding.textViewNoTickets;
        MosheyViewModelFactory vMosheyViewModelFactory = InjectorUtils.provideMosheyViewModelFactory(this);
        mMosheyViewModel = ViewModelProviders.of(this, vMosheyViewModelFactory).get(MosheyViewModel.class);
        observeTickets();
    }

    private void observeTickets() {
        mMosheyViewModel.getMyTickets().observe(this, myTickets -> {
            if (!(myTickets.size() > 0)) {
                mViewTicketsListRecycler.setVisibility(View.GONE);
                mTextViewNoTickets.setVisibility(View.VISIBLE);
            } else setupRecyclerView(myTickets);
        });
    }

    private void setupRecyclerView(List<MyTicket> myTicketList) {
        mViewTicketsListRecycler.setVisibility(View.VISIBLE);
        mTextViewNoTickets.setVisibility(View.GONE);
        MyTicketsAdapter vMyTicketsAdapter = new MyTicketsAdapter(myTicketList);
        mViewTicketsListRecycler.setHasFixedSize(true);
        mViewTicketsListRecycler.setLayoutManager(new LinearLayoutManager(this));
        mViewTicketsListRecycler.setAdapter(vMyTicketsAdapter);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_book:
                startActivity(new Intent(MosheyActivity.this, BookingActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
