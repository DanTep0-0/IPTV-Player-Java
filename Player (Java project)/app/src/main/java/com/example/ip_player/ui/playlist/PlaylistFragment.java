package com.example.ip_player.ui.playlist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.ip_player.R;

public class PlaylistFragment extends Fragment {

    private PlaylistViewModel playlistViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        playlistViewModel =
                ViewModelProviders.of(this).get(PlaylistViewModel.class);
        View root = inflater.inflate(R.layout.fragment_playlist, container, false);
        final TextView textView = root.findViewById(R.id.text_playlist);
        playlistViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}