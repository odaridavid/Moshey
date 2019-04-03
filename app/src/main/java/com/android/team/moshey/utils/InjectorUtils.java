package com.android.team.moshey.utils;

import android.content.Context;

import com.android.team.moshey.models.FirebaseDataSource;
import com.android.team.moshey.models.MosheyRepository;
import com.android.team.moshey.models.db.TicketsDatabase;
import com.android.team.moshey.ui.viewmodels.BookingViewModelFactory;

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
        FirebaseDataSource vFirebaseDataSource =
                FirebaseDataSource.getInstance(context.getApplicationContext());
        return MosheyRepository.getInstance(database.mAvailableTicketsDao(), vFirebaseDataSource);
    }

    public static FirebaseDataSource provideNetworkDataSource(Context context) {
        return FirebaseDataSource.getInstance(context.getApplicationContext());
    }

    /**
     * Links repository with view model to listen and observe for changes
     */
    public static BookingViewModelFactory provideDetailViewModelFactory(Context context) {
        MosheyRepository repository = provideRepository(context.getApplicationContext());
        return new BookingViewModelFactory(repository);
    }

}
