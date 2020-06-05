package com.example.appdevelopment;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PlacesDAO {

    @Insert
    public void addPlace(Places places);

    @Query("Select * from placesDB ")
    public List<Places> getAllplaces();

    @Query("Select * from placesDB where place_name = :place")
    public List<Places> getPlacesInfo(String place);

}
