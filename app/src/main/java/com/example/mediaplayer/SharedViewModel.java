package com.example.mediaplayer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {
    private MutableLiveData<CharSequence> text = new MutableLiveData<>();

    public void setText(CharSequence inputText) {
        text.setValue(inputText);
    }

    public LiveData<CharSequence> getText() {
        return text;
    }
}
