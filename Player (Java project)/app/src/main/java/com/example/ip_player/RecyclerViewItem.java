package com.example.ip_player;

public class RecyclerViewItem {
    private int drawableId;
    private String name;

    public RecyclerViewItem(int drawableId, String name) {
        this.drawableId = drawableId;
        this.name = name;
    }

    public int getDrawableId() {
        return drawableId;
    }

    public String getName() {
        return name;
    }
}
