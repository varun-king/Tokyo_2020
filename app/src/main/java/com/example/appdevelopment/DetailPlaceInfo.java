package com.example.appdevelopment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.List;

public class DetailPlaceInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_place_info);

        Toolbar toolbar = (Toolbar) findViewById(R.id.deatil_toolbar);

        setSupportActionBar(toolbar);

        Intent intent = getIntent();

        Bundle bundle = intent.getExtras();
        final String username =  bundle.getString("username");
        final String placename =  bundle.getString("placename");
        float p_rating;
        final List<Rating> ratingInfo = Login.connect.ratingDAO().getPlaceAddRating(placename, username);



        List<Places> placeInfo = Login.connect.placesDAO().getPlacesInfo(placename);

        String p_dec = placeInfo.get(0).getPlace_description();
        String p_name = placeInfo.get(0).getPlace_name();
        String p_address = placeInfo.get(0).getPlace_address();

        if(ratingInfo.size()==0) {
            p_rating = ( float ) 0.0;
        }else {
            p_rating = ratingInfo.get(0).getRating();
        }


        TextView p_name_g = (TextView) findViewById(R.id.detail_place_name);
        TextView p_address_g = (TextView) findViewById(R.id.detail_place_address);
        TextView p_descrip_g = (TextView) findViewById(R.id.detail_place_decription);
        RatingBar p_rating_q = (RatingBar) findViewById(R.id.detail_place_rating);


        p_name_g.setText(p_name);
        p_address_g.setText(p_address);
        p_descrip_g.setText(p_dec);
        p_rating_q.setRating(p_rating);



        Log.d("UUUU", "Username "+username+"; pass "+placename);

        ActionBar ab =  getSupportActionBar();
        ab.setDefaultDisplayHomeAsUpEnabled(true);
        ab.setDisplayHomeAsUpEnabled(true);

        Button addWish = (Button) findViewById(R.id.addToWishList);
        addWish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String check = "";

                List<Wishlist> listOfwish = Login.connect.wishlistDAO().getWishListOfUser(username);

                for(int i =0 ; i< listOfwish.size(); i++){
                    if(placename.equals(listOfwish.get(i).getPlace_name())){
                        Log.d("PLSE", ":::"+placename+"::::"+listOfwish.get(i).getPlace_name());
                        check = placename;
                    }
                }

                if(!check.equals(placename)){


                    Wishlist wishlist = new Wishlist();
                    wishlist.setUsername(username);
                    wishlist.setPlace_name(placename);
                    Login.connect.wishlistDAO().addToWishlist(wishlist);
                    Toast.makeText(getApplicationContext(), "" + placename + " added to wishlist", Toast.LENGTH_SHORT).show();


                }
                else {
                    Toast.makeText(getApplicationContext(), "" + placename + " Alredy added to wishlist", Toast.LENGTH_SHORT).show();
                }
            }
        });


        Button sumBtnRating = (Button) findViewById(R.id.submitRatingBtn);
        sumBtnRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RatingBar rBar = (RatingBar) findViewById(R.id.detail_place_rating);
                int noofstars = rBar.getNumStars();
                float getrating = rBar.getRating();

                Log.d("Rating of place", ":::"+getrating);

                List<Rating> placeRating = Login.connect.ratingDAO().getPlaceAddRating(placename, username);




                if(!placename.isEmpty() && !username.isEmpty() && placeRating.size() == 0) {



                    Rating rating = new Rating();
                    rating.setPlace_name(placename);
                    rating.setUsername(username);
                    rating.setRating(getrating);

                    Login.connect.ratingDAO().addRating(rating);
                    Log.d("RAT", ":::Rating Added");




                }else if(!placename.isEmpty() && !username.isEmpty() && getrating !=0.0 && placeRating.size()!=0){

                    Login.connect.ratingDAO().updateRating(getrating, placename, username);
                    Log.d("PlaceRating", "Place Rating is updated");
                }

                // Intent in = new Intent(getApplicationContext(), DetailPlaceInfo.class);
                //  startActivity(in);


            }
        });

    }




    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.deatil_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            Intent intent = new Intent(this, Login.class);
            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
