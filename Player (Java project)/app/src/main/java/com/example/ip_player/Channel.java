package com.example.ip_player;

public class Channel {
    public String name;
    public String url;
    public String img;

    public Channel(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public Channel(String name, String url, String img) {
        this.name = name;
        this.url = url;
        this.img = img;
    }
}
