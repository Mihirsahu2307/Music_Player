package com.example.mediaplayer;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class CurrentFragment extends Fragment {

    private SharedViewModel viewModel;
    private TextView currentTextView;

    public CurrentFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.current_song, container, false);

        currentTextView = rootView.findViewById(R.id.current_song);

//        MainActivity activity = (MainActivity) getActivity();
//        currentTextView.setText(activity.mSongName);
        
        // Assuming the AdapterToFragment interface is working properly, find a way to change
        // the text when play button is clicked. The above lines of code will only change it once
        // as soon as the fragment is created

        // Try searching for ViewPager current fragment checker so that every time we swipe
        // to this fragment the string activity.mSongName gets updated and set to the TextView
        return rootView;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);
        viewModel.getText().observe(getViewLifecycleOwner(), new Observer<CharSequence>() {
            @Override
            public void onChanged(CharSequence charSequence) {
                currentTextView.setText(charSequence);

                Log.d(currentTextView.getText().toString(), "Updated SongName: " + currentTextView.getText().toString());

            }
        });
    }
}
