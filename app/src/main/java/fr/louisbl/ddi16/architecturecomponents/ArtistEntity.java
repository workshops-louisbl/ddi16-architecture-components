package fr.louisbl.ddi16.architecturecomponents;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import kaaes.spotify.webapi.android.models.Artist;

/**
 * Created by louisbl on 21/02/18.
 */

@Entity(tableName = "artist_table")
public class ArtistEntity {
    @PrimaryKey
    @NonNull
    private String id;
    private String name;

    public ArtistEntity() {
    }

    public ArtistEntity(Artist artist) {
        setId(artist.id);
        setName(artist.name);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
