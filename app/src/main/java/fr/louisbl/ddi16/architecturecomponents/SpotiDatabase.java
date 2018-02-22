package fr.louisbl.ddi16.architecturecomponents;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by louisbl on 22/02/18.
 */

@Database(entities = {ArtistEntity.class}, version = 2)
public abstract class SpotiDatabase extends RoomDatabase {
    public abstract ArtistEntityDao artistEntityDao();

    private static SpotiDatabase sInstance;

    static SpotiDatabase getDb(final Context context) {
        if (sInstance == null) {
            synchronized (SpotiDatabase.class) {
                if (sInstance == null) {
                    sInstance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            SpotiDatabase.class,
                            "spoti_database")
                        .fallbackToDestructiveMigration()
                        .build();

                }
            }
        }

        return sInstance;
    }
}
