package com.android.team.moshey.models.services;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

import androidx.annotation.NonNull;

/**
 * Created By blackcoder
 * On 03/04/19
 **/
public final class MosheyFirebaseService extends JobService {
    @Override
    public boolean onStartJob(@NonNull JobParameters job) {
        jobFinished(job, false);
        return true;
    }

    @Override
    public boolean onStopJob(@NonNull JobParameters job) {
        return true;
    }
}
