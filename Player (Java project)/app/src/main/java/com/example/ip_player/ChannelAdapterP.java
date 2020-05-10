package com.example.ip_player;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ip_player.ui.home.Info;

import java.util.ArrayList;
import java.util.List;

public class ChannelAdapterP extends RecyclerView.Adapter<ChannelAdapterP.ViewHolder> {
    private ArrayList<Channel> items;
    private List<RecyclerViewItem> RVitems;
    private Activity activity;
    public static ImageView imageView;


    public ChannelAdapterP(Activity activity, ArrayList<Channel> items) {
        this.activity = activity;
        this.items = items;

        RVitems = Info.toRecyclerViewItemArrayList(items);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.list_item_p_grid, parent, false);

        imageView = view.findViewById(R.id.imageViewP);

        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.imageView.setImageResource(RVitems.get(position).getDrawableId());
        holder.textView.setText(RVitems.get(position).getName());

        holder.setPosition(position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView imageView;
        private TextView textView;

        private int position;
        final NavController navController = Navigation.findNavController(activity, R.id.nav_host_fragment);

        public ViewHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.textViewNameP);
            imageView = view.findViewById(R.id.imageViewP);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view){
            MainActivity.currentChannel = items.get(position);
            navController.navigate(R.id.navigation_player);
        }


        public void setPosition(int position){
            this.position = position;
        }

    }
}
