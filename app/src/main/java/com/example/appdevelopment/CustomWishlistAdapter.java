package com.example.appdevelopment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomWishlistAdapter extends ArrayAdapter<Wishlist> {

    private Context context;
    private int resource;
    List<Wishlist> wishlists;
    /**
     * Constructor
     *
     * @param context  The current context.
     * @param resource The resource ID for a layout file containing a TextView to use when
     *                 instantiating views.
     * @param objects  The objects to represent in the ListView.
     */
    public CustomWishlistAdapter(@NonNull Context context, int resource, @NonNull List<Wishlist> objects) {
        super(context, resource, objects);

        this.context = context;
        this.resource = resource;
        this.wishlists = objects;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        String place_name = this.wishlists.get(position).getPlace_name();


        // Connect the row layout file to this adapter
        LayoutInflater inflater = LayoutInflater.from(this.context);
        convertView = inflater.inflate(this.resource,parent,false);


        // Get the textviews and images from the row layout file

        TextView placeName = (TextView) convertView.findViewById(R.id.wishlist_of_places);


        //set text for each text views
        placeName.setText(place_name);




        return convertView;
    }
}
