package com.example.ip_player.ui.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.ip_player.R;
import com.example.ip_player.ui.home.Info;

import java.util.Objects;


public class SettingsFragment extends Fragment {

    public static Switch switch_theme;
    public static Switch switch_notif;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SettingsViewModel settingsViewModel = ViewModelProviders.of(this).get(SettingsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_settings, container, false);

        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final SharedPreferences.Editor editor = getActivity().getPreferences(Context.MODE_PRIVATE).edit();

        switch_notif = view.findViewById(R.id.switch_notifications);
        switch_notif.setChecked(Info.getSwitch(getActivity(), "notifications", true));
        switch_notif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (switch_notif.isChecked()) {
                    editor.putBoolean("notifications", true);
                    editor.apply();
                    switch_notif.setChecked(true);
                } else {
                    editor.putBoolean("notifications", false);
                    editor.apply();
                    switch_notif.setChecked(false);
                }
            }
        });

        switch_theme = view.findViewById(R.id.switch_darkTheme);
        switch_theme.setChecked(Info.getSwitch(getActivity(), "themeDark", false));


        switch_theme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (switch_theme.isChecked()) {
                    editor.putBoolean("themeDark", true);
                    editor.apply();
                    switch_theme.setChecked(true);
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                } else {
                    editor.putBoolean("themeDark", false);
                    editor.apply();
                    switch_theme.setChecked(false);
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            }
        });

    }

}