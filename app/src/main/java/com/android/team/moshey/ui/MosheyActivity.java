package com.android.team.moshey.ui;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.team.moshey.R;
import com.android.team.moshey.databinding.ActivityMosheyBinding;
import com.android.team.moshey.models.entities.tickets.MyTicket;
import com.android.team.moshey.ui.adapters.MyTicketsAdapter;
import com.android.team.moshey.ui.viewmodels.MosheyViewModel;
import com.android.team.moshey.ui.viewmodels.MosheyViewModelFactory;
import com.android.team.moshey.utils.InjectorUtils;
import com.android.team.moshey.utils.ThreadAppExecutors;
import com.android.team.moshey.views.MosheyImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.List;

import static com.android.team.moshey.utils.ConstantUtils.MAIN_VIEW_PHOTO_URL;

public class MosheyActivity extends AppCompatActivity {

    private MosheyViewModel mMosheyViewModel;
    private RecyclerView mViewTicketsListRecycler;
    private TextView mTextViewNoTickets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        testMpesaToken();
        ActivityMosheyBinding vActivityMosheyBinding = DataBindingUtil.setContentView(this, R.layout.activity_moshey);
        setSupportActionBar(vActivityMosheyBinding.mosheyAppBar);
        CollapsingToolbarLayout vCollapsingToolbarLayout = vActivityMosheyBinding.collapsingToolbar;
        vCollapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.white));
        vCollapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(android.R.color.white));
        mViewTicketsListRecycler = vActivityMosheyBinding.viewTicketsListRecycler;
        mTextViewNoTickets = vActivityMosheyBinding.textViewNoTickets;
        MosheyViewModelFactory vMosheyViewModelFactory = InjectorUtils.provideMosheyViewModelFactory(this);
        mMosheyViewModel = ViewModelProviders.of(this, vMosheyViewModelFactory).get(MosheyViewModel.class);
        MosheyImageView vMosheyImageView = vActivityMosheyBinding.mosheyImageViewCustom;
        ThreadAppExecutors.getInstance().diskIO().execute(() -> {
            RequestBuilder<Drawable> vRequestBuilder = Glide.with(MosheyActivity.this).load(MAIN_VIEW_PHOTO_URL);
            runOnUiThread(() -> vRequestBuilder.into(vMosheyImageView));
        });
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
            case R.id.action_about:
                startActivity(new Intent(MosheyActivity.this, AboutUsActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

//    public void testMpesaToken() {
//        MpesaService vMpesaService = NetworkAdapter.getRetrofitInstance().create(MpesaService.class);
//        vMpesaService
//                .getAuthToken("Basic " + ConstantUtils.getCredentials())
//                .enqueue(new Callback<AuthResponse>() {
//                    @Override
//                    public void onResponse(@NonNull Call<AuthResponse> call, @NonNull Response<AuthResponse> response) {
//                        Log.d("Mpesa Response ", response.body().toString());
//                    }
//
//                    @Override
//                    public void onFailure(Call<AuthResponse> call, Throwable t) {
//                        Log.e("Mpesa Error ", t.getMessage());
//                    }
//                });
//    }
}
