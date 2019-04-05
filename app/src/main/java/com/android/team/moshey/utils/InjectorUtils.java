package com.android.team.moshey.utils;

import android.content.Context;

import com.android.team.moshey.models.MosheyRepository;
import com.android.team.moshey.models.db.TicketsDatabase;
import com.android.team.moshey.models.network.FirebaseDataSource;
import com.android.team.moshey.ui.viewmodels.BookingViewModelFactory;
import com.android.team.moshey.ui.viewmodels.MosheyViewModelFactory;

/**
 * Created By blackcoder
 * On 03/04/19
 **/
//Inject Required Classes, (Dependency Injection)
public class InjectorUtils {

    /**
     * Builds instance of app data repository that keeps track of network and local source
     */
    private static MosheyRepository provideRepository(Context context) {
        TicketsDatabase database = TicketsDatabase.getInstance(context.getApplicationContext());
        ThreadAppExecutors executors = ThreadAppExecutors.getInstance();
        FirebaseDataSource vFirebaseDataSource = FirebaseDataSource.getInstance();
        return MosheyRepository.getInstance(database.mTicketsDao(), vFirebaseDataSource, executors);
    }


    public static BookingViewModelFactory provideDetailViewModelFactory(Context context) {
        MosheyRepository repository = provideRepository(context.getApplicationContext());
        return new BookingViewModelFactory(repository);
    }

    public static MosheyViewModelFactory provideMosheyViewModelFactory(Context context) {
        MosheyRepository repository = provideRepository(context.getApplicationContext());
        return new MosheyViewModelFactory(repository);
    }

}
