package com.example.appdevelopment;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDAO {

    @Insert
    public void addUser(User user);

    @Query("Select * From userDB")
    public List<User> getAllUser();


}
