package com.android.team.moshey;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created By blackcoder
 * On 20/04/19
 **/
public final class MosheyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }
}
