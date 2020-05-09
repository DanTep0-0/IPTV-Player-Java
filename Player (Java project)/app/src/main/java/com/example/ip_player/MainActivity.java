package com.example.ip_player;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;

import com.example.ip_player.ui.home.Info;
import com.example.ip_player.ui.home.ListFragment;
import com.example.ip_player.ui.player.PlayerFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.ref.SoftReference;
import java.nio.ByteBuffer;
import java.util.ArrayList;

import veg.mediaplayer.sdk.MediaPlayer;


public class MainActivity extends AppCompatActivity implements AddChannelDialog.AddChannelDialogListener,  MediaPlayer.MediaPlayerCallback  {

    public Channel myChannel;
    public static boolean isF = true;
    public static Channel currentChannel;
    public static SoftReference<PlayerFragment> player;
    NavController navController;

    public static final String CHANNELS_URLS_TAG = "channels_urls";
    public final static String CHANNELS_NAMES_TAG = "channels_names";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        Toolbar toolbar = findViewById(R.id.toolbar);

        if(toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        setNav();

    }


    void setNav() {
        BottomNavigationView navView = findViewById(R.id.nav_view);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_playlist, R.id.navigation_settings)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

    }

    public void click(View v) {

        switch (v.getId()) {
            case R.id.addChannelBtn:
                openDialog();
                break;

            case R.id.backButton:
                player.get().stopPlayer();
                goToHome();
                break;

        }
    }

    //adding new channel
    @Override
    public void applyData(String name, String stringUri) {
        myChannel = new Channel(name, stringUri);
        ListFragment.listOfChannels.add(myChannel);
        Info.setChannelsInfo(ListFragment.listOfChannels, this);
        ListFragment.adapter.notifyDataSetChanged();
        Log.d("Info", "in applyData: listOfChannels" + ListFragment.listOfChannels.toString());
    }

    public void openDialog() {
        AddChannelDialog dialog = new AddChannelDialog();
        dialog.show(getSupportFragmentManager(), "add channel dialog");
    }

    //player status
    @Override
    public int Status(int i) {
        if(i == MediaPlayer.PlayerNotifyCodes.PLP_PLAY_SUCCESSFUL.ordinal())
            PlayerFragment.mDialog.dismiss();
        return 0;
    }

    @Override
    public int OnReceiveData(ByteBuffer byteBuffer, int i, long l) {
        return 0;
    }

    public void goToHome(){
        navController.navigate(R.id.navigation_home);
    }

    public void goToPlaylist(){
        navController.navigate(R.id.navigation_playlist);
    }

    public void goToPlayer(){
        navController.navigate(R.id.navigation_player);
    }

}
