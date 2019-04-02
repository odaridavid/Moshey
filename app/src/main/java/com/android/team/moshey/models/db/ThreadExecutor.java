package com.android.team.moshey.models.db;


import java.util.concurrent.Executor;

/**
 * Created By blackcoder
 * On 03/04/19
 **/
public final class ThreadExecutor implements Executor {
    @Override
    public void execute(Runnable command) {
        new Thread(command).start();
    }
}
