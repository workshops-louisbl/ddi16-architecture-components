package fr.louisbl.ddi16.architecturecomponents;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.SpotifyService;

/**
 * Created by louisbl on 21/02/18.
 */

public class SpotifyApiWrapper {

    private static volatile SpotifyApiWrapper sInstance;
    private final SpotifyApi api;

    private SpotifyApiWrapper() {
        if (sInstance != null) {
            throw new RuntimeException("Use getInstance()");
        }
        api = new SpotifyApi();
    }

    public static SpotifyApiWrapper getInstance() {
        if (sInstance == null) {
            synchronized (SpotifyApiWrapper.class) {
                if (sInstance == null) sInstance = new SpotifyApiWrapper();
            }
        }

        return sInstance;
    }

    public void setToken(String token) {
        api.setAccessToken(token);
    }

    public SpotifyService getService() {
        return api.getService();
    }
}
