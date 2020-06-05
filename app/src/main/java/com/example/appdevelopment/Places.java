package com.example.appdevelopment;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "placesDB")
public class Places {

    @PrimaryKey
    @NonNull
    private String place_name;

    private String place_address;
    private String place_description;
    private float place_rating;


    public Places(){

    }

    @Ignore
    public Places(String place_name, String place_address, String place_description) {
        this.place_name = place_name;
        this.place_address = place_address;
        this.place_description = place_description;
    }

    @Ignore
    public Places(@NonNull String place_name, String place_address, String place_description, float place_rating) {
        this.place_name = place_name;
        this.place_address = place_address;
        this.place_description = place_description;
        this.place_rating = place_rating;
    }

    public float getPlace_rating() {
        return place_rating;
    }

    public void setPlace_rating(float place_rating) {
        this.place_rating = place_rating;
    }

    public String getPlace_name() {
        return place_name;
    }

    public void setPlace_name(String place_name) {
        this.place_name = place_name;
    }

    public String getPlace_address() {
        return place_address;
    }

    public void setPlace_address(String place_address) {
        this.place_address = place_address;
    }

    public String getPlace_description() {
        return place_description;
    }

    public void setPlace_description(String place_description) {
        this.place_description = place_description;
    }
}
