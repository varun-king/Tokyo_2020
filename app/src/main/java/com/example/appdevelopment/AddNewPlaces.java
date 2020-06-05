package com.example.appdevelopment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AddNewPlaces extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_places);

        Button add_place = (Button) findViewById(R.id.add_placeTO);
        add_place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                EditText txt_name =  findViewById(R.id.txt_place_name);
                String place_name = txt_name.getText().toString();

                EditText txt_address =  findViewById(R.id.txt_place_address);
                String place_address = txt_address.getText().toString();

                EditText txt_descri =  findViewById(R.id.txt_place_description);
                String place_description = txt_descri.getText().toString();


                if(!place_name.isEmpty() && !place_address.isEmpty() && !place_description.isEmpty()) {


                    Places places = new Places();

                    places.setPlace_name(place_name);
                    places.setPlace_address(place_address);
                    places.setPlace_description(place_description);

                    Login.connect.placesDAO().addPlace(places);

                    txt_name.setText("");
                    txt_address.setText("");
                    txt_descri.setText("");


                    List<Places> pl = Login.connect.placesDAO().getAllplaces();


                    // ArrayList<String> placeData = new ArrayList<>();

                    for (int i = 0; i < pl.size(); i++) {

                        Log.d("isAdmin kese before if", " name: " + pl.get(i).getPlace_name());
                        Log.d("isAdmin kese before if", " add: " + pl.get(i).getPlace_address());
                        Log.d("isAdmin kese before if", " dec: " + pl.get(i).getPlace_description());

                    }


                    Intent intent = new Intent(getApplicationContext(), AddNewPlaces.class);
                    startActivity(intent);

                }else{
                    Toast.makeText(getApplicationContext(), "Fileds should not be empty", Toast.LENGTH_SHORT).show();
                }

            }
        });

        Button button1 = (Button) findViewById(R.id.goToLogin);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(), Login.class);
                startActivity(in);
            }
        });



    }


}
