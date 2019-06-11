package com.android.team.moshey;

import android.app.Application;

import androidx.appcompat.app.AppCompatDelegate;

import com.squareup.leakcanary.LeakCanary;

import timber.log.Timber;

public final class MosheyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
        Timber.plant();
//        TODO Setup AppCompatDelegate With The Mode to Use
//
//          Q and newer let users select between if using preference settings:
//          - Dark - MODE_NIGHT_YES
//          - Light - MODE_NIGHT_NO
//          - System Default - MODE_NIGHT_FOLLOW_SYSTEM
//
//          P and earlier
//          - Battery Saver over System Default(Only Exists on Q and Earlier) - MODE_NIGHT_AUTO_BATTERY)
//
//          MODE_NIGHT_AUTO -> DEPRECATED
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
    }
}
