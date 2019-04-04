package com.android.team.moshey.models.db;

import android.content.Context;

import com.android.team.moshey.models.db.dao.AvailableTicketsDao;
import com.android.team.moshey.models.db.dao.MyTicketsDao;
import com.android.team.moshey.models.entities.AvailableTicket;
import com.android.team.moshey.models.entities.MyTicket;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import static com.android.team.moshey.utils.ConstantUtils.TICKETS_DATABASE_LABEL;

/**
 * Created By blackcoder
 * On 02/04/19
 **/
@Database(entities = {MyTicket.class, AvailableTicket.class}, version = 3, exportSchema = false)
public abstract class TicketsDatabase extends RoomDatabase {
    public abstract MyTicketsDao mTicketsDao();

    public abstract AvailableTicketsDao mAvailableTicketsDao();

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
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return sInstance;
    }
}
