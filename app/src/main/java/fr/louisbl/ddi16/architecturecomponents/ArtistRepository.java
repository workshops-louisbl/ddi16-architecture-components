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
    private String token = "BQCdeCKVmIFPU_MMxYrzA68btZ3vVQuKIg8VuDybs37ldUif17QkQvXNZcmGjlILn02ydusSQt37Ex6j5ZF-MzxcCXTSMjAjMIX1aKGddRcAkgLhJgUa6x1XR9q8xjep8XnzPeYXcJW0rtnkbw";

    public LiveData<Artist> getArtistById(String artistId) {
        final MutableLiveData<Artist> data = new MutableLiveData<>();
        SpotifyApi api = new SpotifyApi();
        api.setAccessToken(token);

        SpotifyService spotify = api.getService();

        spotify.getArtist(artistId, new SpotifyCallback<Artist>() {
            @Override
            public void failure(SpotifyError spotifyError) {

            }

            @Override
            public void success(Artist artist, Response response) {
                Log.d("Artist", artist.name);
                data.postValue(artist);
            }
        });

        return data;
    }
}
