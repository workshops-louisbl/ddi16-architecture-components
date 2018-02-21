package fr.louisbl.ddi16.architecturecomponents;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.SpotifyCallback;
import kaaes.spotify.webapi.android.SpotifyError;
import kaaes.spotify.webapi.android.SpotifyService;
import kaaes.spotify.webapi.android.models.Artist;
import retrofit.client.Response;

/**
 * Created by louisbl on 21/02/18.
 */

public class ArtistDetailViewModel extends ViewModel {
    private LiveData<Artist> artist;
    private ArtistRepository artistRepo = new ArtistRepository();

    public void init(String artistId) {
        artist = artistRepo.getArtistById(artistId);
    }

    public LiveData<Artist> getArtist() {
        return artist;
    }
}
