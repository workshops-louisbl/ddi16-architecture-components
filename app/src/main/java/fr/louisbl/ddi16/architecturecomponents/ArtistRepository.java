package fr.louisbl.ddi16.architecturecomponents;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
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

public class ArtistRepository {

    public LiveData<ArtistEntity> getArtistById(String artistId) {
        final MutableLiveData<ArtistEntity> data = new MutableLiveData<>();
        SpotifyApi api = new SpotifyApi();
        api.setAccessToken(MainActivity.TOKEN);

        SpotifyService spotify = api.getService();

        spotify.getArtist(artistId, new SpotifyCallback<Artist>() {
            @Override
            public void failure(SpotifyError spotifyError) {
                Log.e("Artist", spotifyError.getMessage());
            }

            @Override
            public void success(Artist artist, Response response) {
                Log.d("Artist", artist.name);
                data.postValue(new ArtistEntity(artist));
            }
        });

        return data;
    }
}
