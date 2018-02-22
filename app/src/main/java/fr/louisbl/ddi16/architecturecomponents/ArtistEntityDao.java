package fr.louisbl.ddi16.architecturecomponents;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by louisbl on 22/02/18.
 */

@Dao
public interface ArtistEntityDao {
    @Insert(onConflict = REPLACE)
    void save(ArtistEntity artistEntity);

    @Query("SELECT * FROM artist_table WHERE id = :artistId")
    LiveData<ArtistEntity> load(String artistId);
}
