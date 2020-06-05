package com.example.appdevelopment;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;


@Dao
public interface RatingDAO {

    @Insert
    public void addRating(Rating rating);

    @Query("Select * from ratingDB ")
    public List<Rating> getPlaceRating();

    @Query("Select * from ratingDB where place_name = :place and username =:user")
    public List<Rating> getPlaceAddRating(String place, String user);


    @Query("Update ratingDB Set rating = :newRaing where place_name = :place and username = :user")
    public void updateRating(float newRaing, String place, String user);


}
