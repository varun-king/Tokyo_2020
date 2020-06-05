package com.example.appdevelopment;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface WishlistDAO {

    @Insert
    public void addToWishlist(Wishlist wishlist);

    @Query("Select * From wishlistDB where username = :name")
    public List<Wishlist> getWishListOfUser(String name);

    @Query("Select wishlist_id From wishlistDB where username = :name and place_name =:place")
    public int getIdWishListOfUser(String name, String place);

    @Query("Delete From wishlistDB where wishlist_id = :id")

    void deleteByWishlistId(int id);

}
