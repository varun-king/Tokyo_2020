package com.example.appdevelopment;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "ratingDB")
public class Rating {

    @PrimaryKey(autoGenerate = true)
    @NonNull

    private int  rating_id;

    private String username;
    private String place_name;
    private float rating;


    public Rating(){

    }
    @Ignore
    public Rating(@NonNull int rating_id, String username, String place_name, float rating) {
        this.rating_id = rating_id;
        this.username = username;
        this.place_name = place_name;
        this.rating = rating;
    }

    @NonNull
    public int getRating_id() {
        return rating_id;
    }

    public void setRating_id(@NonNull int rating_id) {
        this.rating_id = rating_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPlace_name() {
        return place_name;
    }

    public void setPlace_name(String place_name) {
        this.place_name = place_name;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
