package fr.louisbl.ddi16.architecturecomponents;

import kaaes.spotify.webapi.android.models.Artist;

/**
 * Created by louisbl on 21/02/18.
 */

public class ArtistEntity {
    public String name;

    public ArtistEntity(Artist artist) {
        this.name = artist.name;
    }
}
