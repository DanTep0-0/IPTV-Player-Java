package com.example.ip_player.ui.home;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Switch;

import com.example.ip_player.Channel;
import com.example.ip_player.MainActivity;

import java.util.ArrayList;

public class Info {

    // ARRAYS
     public static void useChannels(ArrayList<Channel> channelsToUse, ArrayList<Channel> listOfChannels) {
        listOfChannels.clear();
        for (int i = 0; i < channelsToUse.size(); i++) {
            listOfChannels.add(i, channelsToUse.get(i));
        }
    }

    public static ArrayList<Channel> combine(ArrayList<String> names, ArrayList<String> urls) {
        ArrayList<Channel> channelsArrayList = new ArrayList<Channel>();
        for (int i = 0; i < names.size(); i++) {
            channelsArrayList.add(i, new Channel(names.get(i), urls.get(i)));
        }
        return channelsArrayList;
    }

    public static ArrayList<Channel> toArrayList(Channel[] channels) {
        ArrayList<Channel> newChannels = new ArrayList<Channel>();
        for (int i = 0; i < channels.length; i++) {
            newChannels.add(i, channels[i]);
        }
        return newChannels;
    }

    // INFO
    public static ArrayList<String> getChannelsInfo(String TAG, Activity context) {
        SharedPreferences sPrefs = context.getPreferences(Context.MODE_PRIVATE);
        ArrayList<String> results = new ArrayList<String>();

        for(int i = 0; ;i++){
            String value = sPrefs.getString(TAG + String.valueOf(i), null);
            if(value != null){
                results.add(value);
            } else {
                break;
            }
        }

        return results;
    }

    public static void setChannelsInfo(ArrayList<Channel> listOfChannels, Activity context) {
        SharedPreferences sPrefs = context.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sPrefs.edit();

        int i = 0;
        for(; i < listOfChannels.size(); i++){

            editor.putString(MainActivity.CHANNELS_NAMES_TAG + String.valueOf(i), listOfChannels.get(i).name);
            editor.putString(MainActivity.CHANNELS_URLS_TAG + String.valueOf(i), listOfChannels.get(i).url);

        }

        for(;; i++){
            if(sPrefs.getString(MainActivity.CHANNELS_NAMES_TAG + String.valueOf(i), null) != null){
                editor.putString(MainActivity.CHANNELS_NAMES_TAG + String.valueOf(i), null);
            } else {
                break;
            }
        }

        editor.apply();
    }

    //save switches states
    public static void saveSwitchState(final Activity activity, final Switch switch_theme) {
        SharedPreferences sharedPreferences = activity.getSharedPreferences("save", Context.MODE_PRIVATE);
        switch_theme.setChecked(sharedPreferences.getBoolean("themeDark", false));

        switch_theme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = activity.getSharedPreferences("save",
                        Context.MODE_PRIVATE).edit();

                if (switch_theme.isChecked()) {
                    editor.putBoolean("themeDark", true);
                    editor.apply();
                    switch_theme.setChecked(true);
                } else {
                    editor.putBoolean("themeDark", false);
                    editor.apply();
                    switch_theme.setChecked(false);
                }
            }
        });

    }
}