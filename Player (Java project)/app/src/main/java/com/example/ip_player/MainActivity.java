package com.example.ip_player;

import android.app.AlertDialog;
import android.content.DialogInterface;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.ip_player.ui.home.Info;
import com.example.ip_player.ui.home.ListFragment;
import com.example.ip_player.ui.player.PlayerFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.lang.ref.SoftReference;
import java.nio.ByteBuffer;
import java.util.Objects;

import veg.mediaplayer.sdk.MediaPlayer;


public class MainActivity extends AppCompatActivity implements AddChannelDialog.AddChannelDialogListener,  MediaPlayer.MediaPlayerCallback {

    public Channel myChannel;
    public static boolean isF = true;
    public static boolean isPlayerPaused;
    public static int returnTo;

    public static Channel currentChannel;
    public static SoftReference<PlayerFragment> player;
    public NavController navController;
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
            Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        }

        setCurrentTheme(Info.getSwitch(MainActivity.this, "themeDark", false));
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
                goTo(returnTo);
                break;

            case R.id.clear:
                clearListOfChannels();
                break;

        }
    }


    //adding new channel
    @Override
    public void applyData(String name, String stringUri) {
        myChannel = new Channel(name, stringUri);
        ListFragment.listOfChannels.add(ChannelAdapter.validateChannel(myChannel));
        Info.setChannelsInfo(ListFragment.listOfChannels, this);
        Log.d("Info", "APPLYDATA: validated channel url = " + ChannelAdapter.validateChannel(myChannel).url);
        Log.d("Info", "APPLYDATA: channel url = " + myChannel.url);
        ListFragment.adapter.notifyDataSetChanged();
    }

    //dialog when adding channels
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

    public void goTo(int goTo){
        navController.navigate(goTo);
    }


    public void clearListOfChannels(){
        if(ChannelAdapter.isListEmpty()){
            Toast.makeText(getApplicationContext(), "Nothing to delete!", Toast.LENGTH_SHORT).show();
        } else {
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_delete)
                    .setTitle("Are you sure?")
                    .setMessage("Do you want delete all recent data?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if(ChannelAdapter.clearData()){
                                Info.setChannelsInfo(ListFragment.listOfChannels, MainActivity.this);
                                Toast.makeText(getApplicationContext(), "Cleared successfully!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    })
                    .setNegativeButton("No", null)
                    .show();
        }
    }

    private void setCurrentTheme(boolean isDark) {
        if (isDark) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else if (!isDark) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

}
