package com.example.mediaplayer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class TabAdapter extends FragmentPagerAdapter {

    private final String[] TabTitles = {
            "Songs",
            "Current"
    };

    public TabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TabTitles[position];
    }

    @Override
    public Fragment getItem(int position) {

        if(position == 0) {
            return new SongsFragment();
        }

        else {
            return new CurrentFragment();
        }
    }
}
