package com.example.appdevelopment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.room.Room;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appdevelopment.ui.home.HomeFragment;

import java.util.ArrayList;
import java.util.List;

public class Login extends AppCompatActivity implements View.OnClickListener {

    public static MyDataBaseDB connect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        connect = Room.databaseBuilder(getApplicationContext(),
                MyDataBaseDB.class, "users").allowMainThreadQueries().build();

        List<User> users = Login.connect.userDAO().getAllUser();


        if(users.size() == 0) {
            addDefaultData();
        }

        Button login_chk = findViewById(R.id.check_user);

        TextView user_register = findViewById(R.id.new_user_register);

        login_chk.setOnClickListener(this);
        user_register.setOnClickListener(this);

        
    }


    @Override
    public void onClick(View v) {

        switch(v.getId()){

            case R.id.check_user:


                EditText txt_user = (EditText) findViewById(R.id.txt_username);
                String username = txt_user.getText().toString();

                EditText txt_pass = (EditText) findViewById(R.id.txt_password);
                String password = txt_pass.getText().toString();

                Log.d("As", "username :"+username+" ; "+password);


                List<User> users = Login.connect.userDAO().getAllUser();

                ArrayList<String> userData = new ArrayList<>();

                for (int i = 0 ; i < users.size(); i++){

                    Log.d("isAdmin kese before if" ," : "+users.get(i).isAdmin());



                    if(username.equals(users.get(i).getUsername()) &&
                            password.equals(users.get(i).getPassword()) &&
                            users.get(i).isAdmin()==true ){

                        Log.d("isAdmin kese ho" ," : "+users.get(i).isAdmin());
                        Intent intent = new Intent(this, AddNewPlaces.class);
                        startActivity(intent);
                    }

                    else if(username.equals(users.get(i).getUsername()) &&
                            password.equals(users.get(i).getPassword()) &&
                            users.get(i).isAdmin()==false){
                        Log.d("ME", "This else");
                        Log.d("ME", "This else user "+username);

                        SharedPreferences sharedPref = getSharedPreferences("MyData",MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putString("name",username);
                        editor.putString("password", password);
                        editor.commit();


                        Intent intent = new Intent(this, MainActivity.class);
                        intent.putExtra("user", username);
                        startActivity(intent);
                    }

                }

                break;

            case R.id.new_user_register:

                Intent intent = new Intent(this, AddUser.class);
                startActivity(intent);
                break;


        }

    }

    public void addDefaultData(){

        List<User> users = Login.connect.userDAO().getAllUser();


        if(users.size() == 0){
            User u = new User();
            u.setAdmin(true);
            u.setUsername("admin");
            u.setPassword("admin");

            // Insert admin into USERS

            Login.connect.userDAO().addUser(u);

        }else {

            for (int i = 0; i < users.size(); i++) {


                if ("admin".equals(users.get(i).getUsername()) &&
                        "admin".equals(users.get(i).getPassword()) &&
                        users.get(i).isAdmin() == true) {

                    Toast.makeText(getApplicationContext(), "Admin Added", Toast.LENGTH_SHORT).show();
                } else {
                    User u = new User();
                    u.setAdmin(true);
                    u.setUsername("admin");
                    u.setPassword("admin");

                    // Insert admin into USERS

                    Login.connect.userDAO().addUser(u);
                }
            }

        }}
}
