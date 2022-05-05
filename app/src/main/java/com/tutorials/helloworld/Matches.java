package com.tutorials.helloworld;


import android.graphics.drawable.Drawable;

public class Matches {
    private String name;
    private String description;
    private boolean liked;
    private int imageUrl;

    public Matches(String name, String description, boolean liked, int imageUrl) {
        this.name = name;
        this.description = description;
        this.liked = liked;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public int getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(int imageUrl) {
        this.imageUrl = imageUrl;
    }
}
