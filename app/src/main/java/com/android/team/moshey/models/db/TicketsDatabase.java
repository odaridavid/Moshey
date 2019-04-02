package com.android.team.moshey.models.db;

import android.content.Context;

import com.android.team.moshey.models.entities.MyTicket;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import static com.android.team.moshey.utils.Constants.TICKETS_DATABASE_LABEL;

/**
 * Created By blackcoder
 * On 02/04/19
 **/
@Database(entities = {MyTicket.class}, version = 1, exportSchema = false)
public abstract class TicketsDatabase extends RoomDatabase {
    public abstract TicketsDao mTicketsDao();

    private static final Object LOCK = new Object();
    private static volatile TicketsDatabase sInstance;

    public static TicketsDatabase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                if (sInstance == null) {
                    sInstance = Room
                            .databaseBuilder(
                                    context.getApplicationContext(),
                                    TicketsDatabase.class,
                                    TICKETS_DATABASE_LABEL)
                            .setTransactionExecutor(new ThreadExecutor())
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return sInstance;
    }
}
