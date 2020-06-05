package com.example.appdevelopment.ui.gallery;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.appdevelopment.AttractionListViewAdapter;
import com.example.appdevelopment.CustomWishlistAdapter;
import com.example.appdevelopment.DetailPlaceInfo;
import com.example.appdevelopment.Login;
import com.example.appdevelopment.Places;
import com.example.appdevelopment.R;
import com.example.appdevelopment.Wishlist;
import com.example.appdevelopment.ui.home.HomeFragment;

import java.util.ArrayList;
import java.util.List;

public class GalleryFragment extends Fragment {



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        final View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);

        //textView.setText("This is the Galary View by varun");

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyData", Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("name","");
        final String password =  sharedPreferences.getString("password","");

        List<Wishlist> wish = Login.connect.wishlistDAO().getWishListOfUser(name);

        CustomWishlistAdapter adapter = new CustomWishlistAdapter(getContext(), R.layout.custom_wish_list_adpater, wish);

        final ListView listview = (ListView)root.findViewById(R.id.list_of_wishList);

        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Log.d("in click", "here");
                // 2. Get the object at that row



                Wishlist wishlist = (Wishlist) listview.getItemAtPosition(position);



                final String place_name = wishlist.getPlace_name();
                final String username = wishlist.getUsername();

                int wid = Login.connect.wishlistDAO().getIdWishListOfUser(username, place_name);
                Login.connect.wishlistDAO().deleteByWishlistId(wid);

              /*  Button remove_button = (Button) root.findViewById(R.id.remove_from_wishlist);
                remove_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        int id = Login.connect.wishlistDAO().getIdWishListOfUser(username, place_name);
                        Log.d("Id ", "id of the wish list :"+id);

                        Log.d("hey you re",":"+username+": "+ place_name);

                     Login.connect.wishlistDAO().deleteByWishlistId(id);

                        Intent intent = new Intent(getContext(), HomeFragment.class);
                        intent.putExtra("username", username);

                        startActivity(intent);

                    }
                });*/


            }
        });

        return root;
    }
}