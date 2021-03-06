package com.example.mediaplayer;

public class Music {

    private String mMusicName;
    private int mMusicResourceId;

    public Music(String musicName, int musicResourceId) {
        mMusicName = musicName;
        mMusicResourceId = musicResourceId;
    }

    public int getMusicResourceId() {
        return mMusicResourceId;
    }

    public String getMusicName() {
        return mMusicName;
    }
}
