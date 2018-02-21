package fr.louisbl.ddi16.architecturecomponents;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

/**
 * Created by louisbl on 21/02/18.
 */

public class ArtistDetailViewModel extends ViewModel {
    private LiveData<ArtistEntity> artist;
    private ArtistRepository artistRepo = new ArtistRepository();

    public void init(String artistId) {
        artist = artistRepo.getArtistById(artistId);
    }

    public LiveData<ArtistEntity> getArtist() {
        return artist;
    }
}
