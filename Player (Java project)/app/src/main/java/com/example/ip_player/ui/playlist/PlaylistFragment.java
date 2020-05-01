package com.example.ip_player.ui.playlist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.ip_player.R;

public class PlaylistFragment extends Fragment {

    private PlaylistViewModel playlistViewModel;

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        playlistViewModel =
                ViewModelProviders.of(this).get(PlaylistViewModel.class);
        View root = inflater.inflate(R.layout.fragment_playlist, null);

        return root;
    }
}