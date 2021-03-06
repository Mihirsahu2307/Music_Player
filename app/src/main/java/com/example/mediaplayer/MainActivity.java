package com.example.mediaplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

//    public String mSongName = "Play a song!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setElevation(0);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        TabAdapter adapter = new TabAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    // Design Plan : 2 Fragments : 1 displaying the song list and 2 displaying the name of the
    // song currently playing (*also try playing from device). Use ViewPager

    // TASK1 : Fix the music_item layout so that text is on the left side and buttons on
    // the right side of the screen
    // (CLEARED)

    // TASK2 : Add play, pause and restart icons (Download icons) in second fragment below
    // TextView that can change state of any song being played in first fragment

    // TASK3 : Change width of Songs tab and Change second tab's name to "Currently Playing"
}