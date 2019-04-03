package com.android.team.moshey.models.services;

import android.app.IntentService;
import android.content.Intent;

import com.android.team.moshey.models.FirebaseDataSource;
import com.android.team.moshey.utils.InjectorUtils;

import androidx.annotation.Nullable;

/**
 * Created By blackcoder
 * On 03/04/19
 **/
public final class MosheySyncIntentService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     */
    public MosheySyncIntentService() {
        super("MosheySyncIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        FirebaseDataSource networkDataSource =
                InjectorUtils.provideNetworkDataSource(this.getApplicationContext());
        networkDataSource.fetchTickets();
    }
}
