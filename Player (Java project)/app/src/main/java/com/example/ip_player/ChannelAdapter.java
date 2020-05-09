package com.example.ip_player;

import com.example.ip_player.ui.home.Info;
import com.example.ip_player.ui.home.ListFragment;

import android.app.Activity;

import android.app.AlertDialog;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;


import com.example.ip_player.ui.player.PlayerFragment;

import java.util.ArrayList;

public class ChannelAdapter extends BaseAdapter {
    Activity context;
    ArrayList<Channel> channels;
    private static LayoutInflater inflater = null;
    PlayerFragment currentPlayer;
    int position;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    public ChannelAdapter(Activity context, ArrayList<Channel> channels) {
        this.context = context;
        this.channels = channels;
        inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return channels.size();
    }

    @Override
    public Channel getItem(int position) {
        return channels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = convertView;
        itemView = (itemView == null) ? inflater.inflate(R.layout.list_item, null) : itemView;
        TextView textViewName = itemView.findViewById(R.id.textViewName);
        TextView textViewUrl = itemView.findViewById(R.id.textViewUrl);
        Channel selectedChannel = channels.get(position);

        textViewName.setText(selectedChannel.name);
        textViewUrl.setText(selectedChannel.url.toString());

        setOnClickListener(itemView, position);
        setOnLongClickListener(itemView, position);

        return  itemView;
    }

    private View setOnClickListener(View itemView, final int position){
        final NavController navController = Navigation.findNavController(context, R.id.nav_host_fragment);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.currentChannel = channels.get(position);
                navController.navigate(R.id.navigation_player);
            }
        });

        return itemView;
    }

    private View setOnLongClickListener(View itemView, final int position){
        itemView.setOnLongClickListener(new View.OnLongClickListener(){

            @Override
            public boolean onLongClick(View v) {
                final Channel item = ListFragment.listOfChannels.get(position);

                new AlertDialog.Builder(context)
                        .setIcon(android.R.drawable.ic_delete)
                        .setTitle("Are you sure?")
                        .setMessage("Do you want to delete this item?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ListFragment.listOfChannels.remove(item);
                                Info.setChannelsInfo(ListFragment.listOfChannels, context);
                                ListFragment.adapter.notifyDataSetChanged();

                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
                return true;
            }
        });
        return itemView;
    }

    public static boolean isListEmpty() {
        if (ListFragment.listOfChannels.isEmpty() || ListFragment.listOfChannels.equals(Info.toArrayList(ListFragment.standartChannels))) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean clearData() {
        ListFragment.listOfChannels.clear();
        ListFragment.adapter.notifyDataSetChanged();
        return true;
    }

    public static Channel validateChannel(Channel channel){
        channel.name = !channel.name.isEmpty() && !channel.name.equals(" ") ? channel.name : "none";
        channel.url = !channel.url.isEmpty() && !channel.url.equals(" ") ? channel.url : "none";
        return channel;
    }

}
