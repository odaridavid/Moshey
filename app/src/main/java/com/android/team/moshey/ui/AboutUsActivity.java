package com.android.team.moshey.ui;

import android.os.Bundle;

import com.android.team.moshey.R;
import com.android.team.moshey.databinding.ActivityAboutUsBinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

public class AboutUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityAboutUsBinding vActivityAboutUsBinding = DataBindingUtil.setContentView(this, R.layout.activity_about_us);
        setSupportActionBar(vActivityAboutUsBinding.aboutUsAppBar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
}
