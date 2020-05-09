package com.example.ip_player.ui.home;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Switch;

import com.example.ip_player.Channel;
import com.example.ip_player.MainActivity;
import com.example.ip_player.R;
import com.example.ip_player.RecyclerViewItem;

import java.util.ArrayList;

public class Info {

    // ARRAYS

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

    public static ArrayList<RecyclerViewItem> toRecyclerViewItemArrayList(Channel[] channels){
        ArrayList<RecyclerViewItem> newChannels = new ArrayList<>();
        for(int i = 0; i < channels.length; i++){
            newChannels.add(new RecyclerViewItem(R.drawable.ic_launcher_foreground, channels[i].name));
        }
        return newChannels;
    }

    public static ArrayList<RecyclerViewItem> toRecyclerViewItemArrayList(ArrayList<Channel> channels){
        ArrayList<RecyclerViewItem> newChannels = new ArrayList<>();
        for(int i = 0; i < channels.size(); i++){
            newChannels.add(new RecyclerViewItem(R.drawable.ic_launcher_foreground, channels.get(i).name));
        }
        return newChannels;
    }

    public static ArrayList<Channel> combineToOneChannelArrayList(ArrayList<Channel> channels1, ArrayList<Channel> channels2){
        channels1.addAll(channels2);
        return channels1;
    }
//
//    public static int getSpanCount(int numberOfItems, int itemsInRow){
//        return numberOfItems / itemsInRow + (numberOfItems % itemsInRow == 0 ? 0 : 1);
//    }

    public static ArrayList<Channel> removeFromTo(ArrayList<Channel> array, int from, int to){
        try {
            for (int i = from; i <= to; i++) {
                array.remove(i);
            }
        } catch (Exception e) {

        }
        return array;
    }

    // INFO

    public static void useChannels(ArrayList<Channel> channelsToUse, ArrayList<Channel> listOfChannels) {
        listOfChannels.clear();

        for (int i = 0; i < channelsToUse.size(); i++) {
            listOfChannels.add(i, channelsToUse.get(i));
        }
        try{
            for(int i = channelsToUse.size();;i++){

                if(listOfChannels.get(i) != null){
                    listOfChannels.set(i, null);
                }else {
                    break;
                }
            }
        } catch (IndexOutOfBoundsException e){
            return;
        }
    }

    public static ArrayList<String> getChannelsInfo(String TAG, Activity context) {
        SharedPreferences sPrefs = context.getPreferences(Context.MODE_PRIVATE);
        ArrayList<String> results = new ArrayList<String>();

        for(int i = 0; ;i++){
            Log.d("Info", "in getChannelsInfo(): value in " + (i) + " = " + sPrefs.getString(TAG + String.valueOf(i), null));
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


        for(int i = 0; i < listOfChannels.size(); i++){
            String name = listOfChannels.get(i).name;
            String url = listOfChannels.get(i).url;
            Log.d("Info", "name in " + String.valueOf(i) + " = " + url.toString());

            editor.putString(MainActivity.CHANNELS_NAMES_TAG + String.valueOf(i), name);
            editor.putString(MainActivity.CHANNELS_URLS_TAG + String.valueOf(i), url);

        }

        for(int i = listOfChannels.size();; i++) {
            Log.d("Info", "in For: i = " + i);
            Log.d("Info", "in For: name in i = " + sPrefs.getString(MainActivity.CHANNELS_NAMES_TAG + String.valueOf(i), null));
            if (sPrefs.getString(MainActivity.CHANNELS_NAMES_TAG + String.valueOf(i), null) != null) {
                editor.putString(MainActivity.CHANNELS_NAMES_TAG + String.valueOf(i), null);
            } else {
                break;
            }
        }
        for(int i = listOfChannels.size();; i++) {
            if (sPrefs.getString(MainActivity.CHANNELS_URLS_TAG + String.valueOf(i), null) != null) {
                editor.putString(MainActivity.CHANNELS_URLS_TAG + String.valueOf(i), null);
            } else {
                break;
            }
        }

        editor.apply();


        Log.d("Info", "in setChannelsInfo(): names: " + String.valueOf(getChannelsInfo(MainActivity.CHANNELS_NAMES_TAG, context)));
        Log.d("Info", "in setChannelsInfo(): urls: " + String.valueOf(getChannelsInfo(MainActivity.CHANNELS_URLS_TAG, context)));

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