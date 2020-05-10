package com.example.ip_player.ui.playlist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ip_player.Channel;
import com.example.ip_player.ChannelAdapterP;
import com.example.ip_player.R;

import com.example.ip_player.ui.home.Info;
import com.example.ip_player.ui.home.ListFragment;

import java.util.ArrayList;

public class ListFragmentP extends Fragment {

    public static ArrayList<Channel> listOfChannelsP;

    //  Playlist page - ListView
    private RecyclerView gridView;
    private ChannelAdapterP channelAdapterP;
    private Channel [] standartPlaylistChannels = {
            new Channel("Україна", "http://31.134.126.6/stream?id=3453"),
            new Channel("1+1", "http://31.134.126.6/stream?id=3454"),
            new Channel("Новий канал", "http://31.134.126.6/stream?id=3451"),
            new Channel("Інтер", "http://31.134.126.6/stream?id=3452"),
            new Channel("ICTV", "http://31.134.126.6/stream?id=3450"),
            new Channel("112 Україна", "http://31.134.126.6/stream?id=3456"),
    };
    private ArrayList<Channel> listOfUserChannels = Info.removeFromTo(ListFragment.listOfChannels, 0, ListFragment.standartChannels.length - 1);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_p, null);

        //list view
        gridView = view.findViewById(R.id.grid);
        gridView.setHasFixedSize(true);

        ArrayList<Channel> listOfAllChannels = Info.combineToOneChannelArrayList(Info.toArrayList(standartPlaylistChannels), ListFragment.listOfChannels);

        //set layout manager and adapter for "GridView"
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        gridView.setLayoutManager(layoutManager);
        channelAdapterP = new ChannelAdapterP(getActivity(), listOfAllChannels);
        gridView.setAdapter(channelAdapterP);

        return view;
    }

}
