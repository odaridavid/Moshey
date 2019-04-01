package com.android.team.moshey;

import android.os.Bundle;

import com.android.team.moshey.databinding.ActivityMosheyBinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

public class MosheyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMosheyBinding vActivityMosheyBinding = DataBindingUtil.setContentView(this, R.layout.activity_moshey);
    }
}
