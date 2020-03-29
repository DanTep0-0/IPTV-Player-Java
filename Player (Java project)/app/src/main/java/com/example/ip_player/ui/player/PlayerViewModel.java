package com.example.ip_player.ui.player;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PlayerViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PlayerViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is playlist fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}