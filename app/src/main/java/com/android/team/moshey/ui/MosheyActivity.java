package com.android.team.moshey.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.android.team.moshey.R;
import com.android.team.moshey.databinding.ActivityMosheyBinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

public class MosheyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMosheyBinding vActivityMosheyBinding = DataBindingUtil.setContentView(this, R.layout.activity_moshey);
        Toolbar supportToolbar = vActivityMosheyBinding.mosheyAppBar;
        setSupportActionBar(supportToolbar);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
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
