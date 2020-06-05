package com.example.appdevelopment;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "wishlistDB")

public class Wishlist {

    @PrimaryKey(autoGenerate = true)
    @NonNull

    private int wishlist_id;
    private String username;
    private String place_name;

    @Ignore
    public Wishlist(int wishlist_id, String username, String place_name) {
        this.wishlist_id = wishlist_id;
        this.username = username;
        this.place_name = place_name;
    }

    public Wishlist(){

    }

    @Ignore
    public Wishlist(@NonNull String username, String place_name) {
        this.username = username;
        this.place_name = place_name;
    }

    public int getWishlist_id() {
        return wishlist_id;
    }

    public void setWishlist_id(int wishlist_id) {
        this.wishlist_id = wishlist_id;
    }

    @NonNull
    public String getUsername() {
        return username;
    }

    public void setUsername(@NonNull String username) {
        this.username = username;
    }

    public String getPlace_name() {
        return place_name;
    }

    public void setPlace_name(String place_name) {
        this.place_name = place_name;
    }
}
