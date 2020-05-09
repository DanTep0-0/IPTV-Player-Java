package com.example.ip_player.ui.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.ip_player.Channel;
import com.example.ip_player.ChannelAdapter;
import com.example.ip_player.MainActivity;
import com.example.ip_player.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ListFragment extends Fragment {

    public static ChannelAdapter adapter;
    public static ArrayList<Channel> listOfChannels = new ArrayList<Channel>();
    public static ListView listViewChannels;

    private final Channel[] standartChannels = {
            new Channel("112", "http://31.134.126.6/stream?id=3456")
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        Log.d("navigation", "here");

        ArrayList<String> channels_names = Info.getChannelsInfo(MainActivity.CHANNELS_NAMES_TAG, getActivity());
        ArrayList<String> channels_urls = Info.getChannelsInfo(MainActivity.CHANNELS_URLS_TAG, getActivity());


        try{
//            new ArrayList<>().get(0);

            if(channels_urls.get(0).equals(null)
               || channels_names.get(0).equals(null)
               || channels_urls.size() != channels_names.size()) {

                throw new Exception();

            } else {
                Info.useChannels(Info.combine(channels_names, channels_urls), listOfChannels);
            }

        } catch (Exception e){
            Info.useChannels(Info.toArrayList(standartChannels), listOfChannels);
        }


        listViewChannels = view.findViewById(R.id.listOfChannelsV);
        adapter = new ChannelAdapter(getActivity(), listOfChannels);
        listViewChannels.setAdapter(adapter);

        return view;
    }



}
