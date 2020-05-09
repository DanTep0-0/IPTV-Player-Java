package com.example.ip_player.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.ip_player.Channel;
import com.example.ip_player.ChannelAdapter;
import com.example.ip_player.MainActivity;
import com.example.ip_player.R;

import java.util.ArrayList;
import java.util.Objects;


public class ListFragment extends Fragment {

    public static ChannelAdapter adapter;
    public static ArrayList<Channel> listOfChannels = new ArrayList<Channel>();
    public static ListView listViewChannels;

    public static final Channel[] standartChannels = {
            new Channel("112", "http://31.134.126.6/stream?id=3456")
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        ArrayList<String> channels_names = Info.getChannelsInfo(MainActivity.CHANNELS_NAMES_TAG, getActivity());
        ArrayList<String> channels_urls = Info.getChannelsInfo(MainActivity.CHANNELS_URLS_TAG, getActivity());

        Log.d("Info", "channels_names = " + channels_names.toString());
        Log.d("Info", "channels_urls = " + channels_urls.toString());



        try{

            if(channels_urls.get(0) == null
               || channels_names.get(0) == null
               || channels_urls.size() != channels_names.size()) {
                Log.d("Info", "here1");
                Log.d("Info", "are channels null? = " + String.valueOf(channels_urls.get(0).equals(null)
                        || channels_names.get(0).equals(null)));
                Log.d("Info", "are they different? = " +  String.valueOf(channels_urls.size() != channels_names.size()));
                Info.useChannels(Info.toArrayList(standartChannels), listOfChannels);

            } else {
                Log.d("Info", "here2");
                Info.useChannels(Info.combine(channels_names, channels_urls), listOfChannels);
            }

        } catch (Exception e){
            Log.d("Info", "here3");
            Info.useChannels(Info.toArrayList(standartChannels), listOfChannels);

        }


        listViewChannels = view.findViewById(R.id.listOfChannelsV);
        adapter = new ChannelAdapter(Objects.requireNonNull(getActivity()), listOfChannels);
        listViewChannels.setAdapter(adapter);

        return view;
    }



}
