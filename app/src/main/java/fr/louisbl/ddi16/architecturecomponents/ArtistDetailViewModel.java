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
    private String artistId;
    private MutableLiveData<Artist> artist = new MutableLiveData<>();
    private String token = "";

    public void init(String artistId) {
        this.artistId = artistId;
        SpotifyApi api = new SpotifyApi();
        api.setAccessToken(token);

        SpotifyService spotify = api.getService();

        spotify.getArtist("3YGigudQiWDb5NdJOC5StS", new SpotifyCallback<Artist>() {
            @Override
            public void failure(SpotifyError spotifyError) {

            }

            @Override
            public void success(Artist artistData, Response response) {
                Log.d("Artist", artistData.name);
                artist.setValue(artistData);
            }
        });
    }

    public LiveData<Artist> getArtist() {
        return artist;
    }
}
