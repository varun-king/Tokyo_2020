package com.example.appdevelopment;

import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.RoomDatabase;

@Database(entities = {User.class, Places.class, Wishlist.class, Rating.class}, version = 1)
public abstract class MyDataBaseDB extends RoomDatabase {

    public abstract UserDAO userDAO();
    public abstract PlacesDAO placesDAO();
    public abstract RatingDAO ratingDAO();
    public abstract WishlistDAO wishlistDAO();

}
