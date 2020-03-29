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

public class ListFragment extends Fragment {

    public static ChannelAdapter adapter;
    public static ArrayList<Channel> listOfChannels;
    public static ListView listViewChannels;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        if(MainActivity.isF) {
            listOfChannels = new ArrayList<>();
            MainActivity.isF = false;

            listOfChannels
                    .add(new Channel("Random", "http://31.134.126.6/stream?id=3456"));
        }

        listViewChannels = view.findViewById(R.id.listOfChannelsV);
        adapter = new ChannelAdapter(getActivity(), listOfChannels);
        listViewChannels.setAdapter(adapter);

        return view;
    }

}