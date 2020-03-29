package com.example.ip_player;

import android.content.Context;

import android.os.Bundle;
import android.view.View;

import com.example.ip_player.ui.home.ListFragment;
import com.example.ip_player.ui.player.PlayerFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.lang.ref.SoftReference;
import java.nio.ByteBuffer;

import veg.mediaplayer.sdk.MediaPlayer;


public class MainActivity extends AppCompatActivity implements AddChannelDialog.AddChannelDialogListener,  MediaPlayer.MediaPlayerCallback  {

    public Channel myChannel;
    public static boolean isF = true;
    public static Channel currentChannel;
    public static SoftReference<PlayerFragment> player;
    NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         navController = Navigation.findNavController(this, R.id.nav_host_fragment);

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

    @Override
    public void applyData(String name, String stringUri) {

        myChannel = new Channel(name, stringUri);
        ListFragment.listOfChannels.add(myChannel);
        ListFragment.adapter.notifyDataSetChanged();
    }

    public void openDialog() {
        AddChannelDialog dialog = new AddChannelDialog();
        dialog.show(getSupportFragmentManager(), "add channel dialog");
    }

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
