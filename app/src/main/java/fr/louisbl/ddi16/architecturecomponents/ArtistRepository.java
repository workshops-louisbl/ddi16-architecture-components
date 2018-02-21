package fr.louisbl.ddi16.architecturecomponents;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import java.util.HashMap;

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

    private static final HashMap<String, LiveData<ArtistEntity>> simpleCache = new HashMap<>();

    public LiveData<ArtistEntity> getArtistById(String artistId) {
        LiveData<ArtistEntity> cached = simpleCache.get(artistId);
        if (cached != null) return cached;

        final MutableLiveData<ArtistEntity> data = new MutableLiveData<>();
        simpleCache.put(artistId, data);

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
                // data.setValue() _must_ be called from main Thread
                // data.postValue() always run on the main Thread
                data.postValue(new ArtistEntity(artist));
            }
        });

        return data;
    }
}
