package com.example.mediaplayer;

import android.app.Activity;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MusicAdapter extends ArrayAdapter<Music> {

    private MediaPlayer mp;
    private ArrayList<Integer> Lengths = new ArrayList<>();
    private int mCurrentPosition = 0;
    private LinearLayout mpreviousView = null;
    private LinearLayout mitemView = null;
    private AdapterToFragment listener;

    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
            Lengths.set(mCurrentPosition, 0);
            mitemView.setBackgroundColor(Color.parseColor("#FDBCA8"));
        }
    };

    public interface AdapterToFragment {

        void onSuccess(String songName);
    }

    public MusicAdapter(Activity context, ArrayList<Music> songs, AdapterToFragment listener) {

        super(context, 0, songs);
        for(int i = 0; i < songs.size(); i ++) {
            Lengths.add(0);
        }

        this.listener = listener;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        mpreviousView = mitemView;

        View musicItemView = convertView;

        if(musicItemView == null) {
            musicItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.music_item, parent, false);
        }

        Music currentSong = getItem(position);

        LinearLayout itemView = musicItemView.findViewById(R.id.item);
        mitemView = itemView;

        TextView songnameView = musicItemView.findViewById(R.id.song_name);
        songnameView.setText(currentSong.getMusicName());

        Button stop = (Button) musicItemView.findViewById(R.id.stop_button);
        Button pause = (Button) musicItemView.findViewById(R.id.pause_button);
        Button play = (Button) musicItemView.findViewById(R.id.play_button);

        /**
         * This is the best place to create on click listener for the buttons
         */
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                releaseMediaPlayer();

                if(listener != null)
                    listener.onSuccess(currentSong.getMusicName());

                mCurrentPosition = position;

                itemView.setBackgroundColor(Color.parseColor("#FAA185"));

                /**
                 * NOTE : Use Color.parseColor(string) to set to custom color
                 */

                mp = MediaPlayer.create(getContext(), currentSong.getMusicResourceId());

                mp.start();
                mp.seekTo(Lengths.get(position));

                mp.setOnCompletionListener(mCompletionListener);
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                releaseMediaPlayer();
                itemView.setBackgroundColor(Color.parseColor("#FDBCA8"));
                Lengths.set(position, 0);
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentPosition = 0;
                if(mp == null) {
                    currentPosition = 0;
                }
                else {
                    currentPosition = mp.getCurrentPosition();
                    mp.stop();
                    Lengths.set(position, currentPosition);
                    releaseMediaPlayer();
//                    mp.setOnCompletionListener(mCompletionListener);
                }
            }
        });

        return musicItemView;
    }

    private void releaseMediaPlayer() {
        if (mp != null) {
            mp.release();
            mp = null;

            if(mpreviousView != null)
                mpreviousView.setBackgroundColor(Color.parseColor("#FDBCA8"));

//            mp.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }
}
