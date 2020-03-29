package com.example.ip_player;

import android.app.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.app.Fragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;


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

        return  itemView;
    }

    View setOnClickListener(View itemView, final int position){

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

}
