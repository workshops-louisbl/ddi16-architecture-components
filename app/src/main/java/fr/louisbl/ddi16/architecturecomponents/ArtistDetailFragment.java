package fr.louisbl.ddi16.architecturecomponents;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArtistDetailFragment extends Fragment {


    public static final String ARTIST_ID_KEY = "ARTIST_ID_KEY";
    private ArtistDetailViewModel viewModel;
    private TextView nameTextView;

    public ArtistDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_artist_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        nameTextView = view.findViewById(R.id.artist_detail_name);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        String artistId = getArguments().getString(ARTIST_ID_KEY);

        // Instantiate a ViewModel tied to the lifecycle of the fragment
        viewModel = ViewModelProviders.of(this).get(ArtistDetailViewModel.class);
        viewModel.init(artistId);

        viewModel.getArtist().observe(this, artist -> {
            nameTextView.setText(artist.name);
        });
    }
}
