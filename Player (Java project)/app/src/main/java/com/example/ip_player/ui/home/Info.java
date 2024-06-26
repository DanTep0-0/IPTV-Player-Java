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

        newChannels.add(0, new RecyclerViewItem(R.drawable.ic_ukraine, channels.get(0).name));
        newChannels.add(1, new RecyclerViewItem(R.drawable.ic_1plus1, channels.get(1).name));
        newChannels.add(2, new RecyclerViewItem(R.drawable.ic_novyi, channels.get(2).name));
        newChannels.add(3, new RecyclerViewItem(R.drawable.ic_inter, channels.get(3).name));
        newChannels.add(4, new RecyclerViewItem(R.drawable.ic_ictv, channels.get(4).name));
        newChannels.add(5, new RecyclerViewItem(R.drawable.ic_112, channels.get(5).name));

        try{
            for(int i = 6; i < channels.size(); i++){
                newChannels.add(i, new RecyclerViewItem(R.drawable.ic_live_tv, channels.get(i).name));
            }
        } catch (Exception e){
            Log.d("Info", e.getMessage());
        };

        return newChannels;
    }

    public static ArrayList<Channel> combineToOneChannelArrayList(ArrayList<Channel> channels1, ArrayList<Channel> channels2){
        channels1.addAll(channels2);
        return channels1;
    }

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

    }

    //Switch

    public static boolean getSwitch(Activity activity, String Tag, boolean defValue){
        SharedPreferences sharedPreferences = activity.getPreferences(Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(Tag, defValue);
    }
}