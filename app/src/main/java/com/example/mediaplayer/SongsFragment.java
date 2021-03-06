package com.example.mediaplayer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class SongsFragment extends Fragment implements MusicAdapter.AdapterToFragment {

    private SharedViewModel viewModel;

    public SongsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.songs_list, container, false);

        final ArrayList<Music> songs = new ArrayList<>();
        songs.add(new Music("First song", R.raw.phrase_where_are_you_going));
        songs.add(new Music("Second song", R.raw.phrase_yes_im_coming));

        MusicAdapter.AdapterToFragment listener = new MusicAdapter.AdapterToFragment() {
            @Override
            public void onSuccess(String songName) {
//                MainActivity activity = (MainActivity) getActivity();
//                activity.mSongName = songName;
                viewModel = ViewModelProviders.of(requireActivity()).get(SharedViewModel.class);
                viewModel.setText(songName);

                Log.d(songName, "SongName: " + songName);
            }
        };


        final MusicAdapter adapter =new MusicAdapter(getActivity(), songs, listener);

        ListView listView = rootView.findViewById(R.id.list);

        listView.setAdapter(adapter);

        return rootView;
    }


    @Override
    public void onStop() {
        super.onStop();
//        releaseMediaPlayer();
    }


    @Override
    public void onSuccess(String songName) {
        viewModel.setText(songName);

//        CurrentFragment currentFragment = new CurrentFragment();
//        Bundle bundle = new Bundle();
//        bundle.putString("STRING", songName);
//        currentFragment.setArguments(bundle);
//        getFragmentManager().beginTransaction();
    }
}