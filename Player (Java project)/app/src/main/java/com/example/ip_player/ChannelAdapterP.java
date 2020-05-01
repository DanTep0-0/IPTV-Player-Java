package com.example.ip_player;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ChannelAdapterP extends RecyclerView.Adapter<ChannelAdapterP.ViewHolder> {
    private List<RecyclerViewItem> items;
    private Activity activity;

    public ChannelAdapterP(Activity activity, List<RecyclerViewItem> items) {
        this.activity = activity;
        this.items = items;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.list_item_p_grid, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.imageView.setImageResource(items.get(position).getDrawableId());
        holder.textView.setText(items.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;

        public ViewHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.textViewNameP);
            imageView = view.findViewById(R.id.imageViewP);
        }
    }
}


//public class ChannelAdapterP extends BaseAdapter {
//
//    Activity context;
//    ArrayList<Channel> channels;
//    private static LayoutInflater inflater = null;
//    PlayerFragment currentPlayer;
//    int position;
//    private FragmentManager fragmentManager;
//    private FragmentTransaction fragmentTransaction;
//
//    public ChannelAdapterP(Activity context, ArrayList<Channel> channels) {
//        this.context = context;
//        this.channels = channels;
//        inflater = (LayoutInflater) context.
//                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//    }
//
//    @Override
//    public int getCount() {
//        return channels.size();
//    }
//
//    @Override
//    public Channel getItem(int position) {
//        return channels.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        return null;
//    }
//}