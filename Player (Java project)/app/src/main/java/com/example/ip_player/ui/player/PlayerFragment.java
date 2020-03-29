package com.example.ip_player.ui.player;

import android.app.ProgressDialog;
import com.example.ip_player.Channel;
import veg.mediaplayer.sdk.MediaPlayer;
import veg.mediaplayer.sdk.MediaPlayerConfig;

import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.ip_player.MainActivity;
import com.example.ip_player.R;

import java.lang.ref.SoftReference;

public class PlayerFragment extends Fragment{

    private PlayerViewModel playerViewModel;
    private Channel channelToShow;
    private veg.mediaplayer.sdk.MediaPlayer mediaPlayer;
    private View thisFragmentView;
    public static ProgressDialog mDialog;


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        playerViewModel =
                ViewModelProviders.of(this).get(PlayerViewModel.class);
        View root = inflater.inflate(R.layout.fragment_player, container, false);

        channelToShow = MainActivity.currentChannel;
        thisFragmentView = root;

        MainActivity.player = new SoftReference<PlayerFragment>(this);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final String stream_url = channelToShow.url;
        mediaPlayer = (veg.mediaplayer.sdk.MediaPlayer)thisFragmentView.findViewById(R.id.player);
        final Message message = new Message();
        mDialog = new ProgressDialog(getActivity());
        mDialog.setCancelMessage(message);
        mDialog.show();

        if (mediaPlayer != null) {
            mediaPlayer.getConfig().setConnectionUrl(stream_url);
            if (mediaPlayer.getConfig().getConnectionUrl().isEmpty())
                return;

            MediaPlayerConfig config = getConfig(mediaPlayer);


            mediaPlayer.Open(config, (MediaPlayer.MediaPlayerCallback) getActivity());

        }

    }

    MediaPlayerConfig getConfig(veg.mediaplayer.sdk.MediaPlayer mediaPlayer){
        MediaPlayerConfig config = new MediaPlayerConfig();
        config.setConnectionUrl(mediaPlayer.getConfig().getConnectionUrl());
        config.setConnectionNetworkProtocol(-1);
        config.setConnectionDetectionTime(2000);
        config.setConnectionBufferingTime(500);
        config.setDecodingType(1);
        config.setRendererType(1);
        config.setSynchroEnable(1);
        config.setSynchroNeedDropVideoFrames(1);
        config.setEnableColorVideo(1);
        config.setDataReceiveTimeout(30000);
        config.setNumberOfCPUCores(0);

        return config;
    }

    public void stopPlayer(){
        mediaPlayer.Close();
    }

}