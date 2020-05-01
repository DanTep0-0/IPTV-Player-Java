package com.example.ip_player.ui.playlist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ip_player.Channel;
import com.example.ip_player.ChannelAdapterP;
import com.example.ip_player.MainActivity;
import com.example.ip_player.R;
import com.example.ip_player.RecyclerViewItem;

import java.util.ArrayList;

public class ListFragmentP extends Fragment {

//    public static ChannelAdapterP adapter;
//    public static ArrayList<Channel> listOfChannelsP;
//    public static ListView listViewChannelsP;

    //  Playlist page - ListView
    private RecyclerView gridView;
    private ChannelAdapterP channelAdapterP;
    private ArrayList<RecyclerViewItem> listOfStandardChannels;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_p, null);

        //list view
        gridView = view.findViewById(R.id.grid);
        gridView.setHasFixedSize(true);

        setStandardData();
        //set layout manager and adapter for "GridView"
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        gridView.setLayoutManager(layoutManager);
        channelAdapterP = new ChannelAdapterP(getActivity(), listOfStandardChannels);
        gridView.setAdapter(channelAdapterP);


//        if(MainActivity.isF) {
//            listOfChannelsP = new ArrayList<>();
//            MainActivity.isF = false;
//
//            listOfChannelsP
//                    .add(new Channel("112", "http://31.134.126.6/stream?id=3456"));
//        }

        return view;
    }

    //create list of standard channels
    private void setStandardData(){
        listOfStandardChannels = new ArrayList<>();
        listOfStandardChannels.add(new RecyclerViewItem(R.drawable.ic_launcher_foreground, "1+1 Channel"));
        listOfStandardChannels.add(new RecyclerViewItem(R.drawable.ic_launcher_foreground, "1+1 Channel"));
    }
}
