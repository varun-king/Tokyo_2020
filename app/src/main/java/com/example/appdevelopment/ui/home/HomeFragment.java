package com.example.appdevelopment.ui.home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.appdevelopment.AttractionListViewAdapter;
import com.example.appdevelopment.DetailPlaceInfo;
import com.example.appdevelopment.Login;
import com.example.appdevelopment.Places;
import com.example.appdevelopment.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    String TAG = "Hey";

    String username;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
      //  final TextView textView = root.findViewById(R.id.text_home);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyData", Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("name","");
        String password =  sharedPreferences.getString("password","");

        Log.d("DEKS", "ooo "+name);
        Log.d("DESK", "PAS "+password);

        username = name;

        ArrayList<Places> placesArrayList = new ArrayList<>();

        List<Places> pl = Login.connect.placesDAO().getAllplaces();


        // ArrayList<String> placeData = new ArrayList<>();

        for (int i = 0 ; i < pl.size(); i++) {

            Log.d("Set one Check","name : "+pl.get(i).getPlace_name());
            Log.d("Set one Check","Add : "+pl.get(i).getPlace_address());
            Log.d("Set one Check","des : "+pl.get(i).getPlace_description());

        }


       AttractionListViewAdapter adapter = new AttractionListViewAdapter(getContext(),
                R.layout.places_adpater_list, pl);


        final ListView listview = (ListView)root.findViewById(R.id.listView_of_places);

        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // 2. Get the object at that row
                Places p = (Places ) listview.getItemAtPosition(position);


                // 3. Output the person object data to the console
                Log.d(TAG, "-----------------");
                Log.d(TAG, "Place info: ");
                Log.d(TAG, "Name: " + p.getPlace_name());

                Intent intent = new Intent(getContext(), DetailPlaceInfo.class);
                intent.putExtra("username", username);
                intent.putExtra("placename", p.getPlace_name());
                startActivity(intent);


                // 4. OPTIONAL: Show person data in a toast


            }
        });





   //     textView.setText("This is home page by Varun");
        return root;
    }


}