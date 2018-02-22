package fr.louisbl.ddi16.architecturecomponents;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

/**
 * Created by louisbl on 21/02/18.
 */

public class ArtistDetailViewModel extends AndroidViewModel {
    private ArtistRepository artistRepo;

    public ArtistDetailViewModel(Application application) {
        super(application);
        artistRepo = new ArtistRepository(application);
    }

    public LiveData<ArtistEntity> getArtist(String artistId) {
        return artistRepo.getArtistById(artistId);
    }
}
