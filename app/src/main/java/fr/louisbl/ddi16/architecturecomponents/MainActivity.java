package fr.louisbl.ddi16.architecturecomponents;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.spotify.sdk.android.authentication.AuthenticationClient;
import com.spotify.sdk.android.authentication.AuthenticationRequest;
import com.spotify.sdk.android.authentication.AuthenticationResponse;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private static final String CLIENT_ID = "00b7d581f8ef4bb184ee94d055659762"; // FIXME
    private static final int REQUEST_CODE = 1001;
    private static final String REDIRECT_URI = "ddi16testapp://callback"; // FIXME


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

    }

    private void showArtistDetail() {
        ArtistDetailFragment artistDetailFragment = new ArtistDetailFragment();
        Bundle args = new Bundle();
        args.putString(ArtistDetailFragment.ARTIST_ID_KEY, "3YGigudQiWDb5NdJOC5StS");
        artistDetailFragment.setArguments(args);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, artistDetailFragment).commit();
    }

    @OnClick(R.id.button_login)
    void onLoginClick(Button button) {
        AuthenticationRequest.Builder builder =
                new AuthenticationRequest.Builder(CLIENT_ID, AuthenticationResponse.Type.TOKEN, REDIRECT_URI);

        builder.setScopes(new String[]{"streaming"});
        AuthenticationRequest request = builder.build();

        AuthenticationClient.openLoginActivity(MainActivity.this, REQUEST_CODE, request);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        // Check if result comes from the correct activity
        if (requestCode == REQUEST_CODE) {
            AuthenticationResponse response = AuthenticationClient.getResponse(resultCode, intent);
            if (response.getType() == AuthenticationResponse.Type.TOKEN) {
                Log.d("AccessToken", response.getAccessToken());
                SpotifyApiWrapper.getInstance().setToken(response.getAccessToken());
                showArtistDetail();
            }
        }
    }

}
