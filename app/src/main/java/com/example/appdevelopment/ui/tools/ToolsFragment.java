package com.example.appdevelopment.ui.tools;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.appdevelopment.Login;
import com.example.appdevelopment.MainActivity;
import com.example.appdevelopment.R;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.Objects;

public class ToolsFragment extends Fragment {

    private ToolsViewModel toolsViewModel;
    String date="";
    String notificationDate;
    String url="";
    EditText eText, timeText;
    DatePickerDialog picker;
    TimePickerDialog timePicker;
    Button btnGet, reminderbtn;
    TextView tvw;
    String newHGO;
    final String CHANNEL_ID = "CP24";
    int newCheck;
    String game_name;


    final Calendar cldr = Calendar.getInstance();
    int day = cldr.get(Calendar.DAY_OF_MONTH);
    int month = cldr.get(Calendar.MONTH);
    int year = cldr.get(Calendar.YEAR);


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        toolsViewModel =
                ViewModelProviders.of(this).get(ToolsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_tools, container, false);

       this.createNotificationChannel();

       EditText game_nm = (EditText) root.findViewById(R.id.reminder_txt_game_name);
       final String game_name = game_nm.getText().toString();


        eText=( EditText ) root.findViewById(R.id.editText1);
        eText.setInputType(InputType.TYPE_NULL);
        eText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // date picker dialog
                picker = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                                String newMonth = null, newDay="";

                                monthOfYear = monthOfYear+1;
                                if(monthOfYear <9){

                                    String month1 = String.valueOf(monthOfYear);
                                    newMonth = "0"+month1;
                                }
                                else {
                                    newMonth = String.valueOf(monthOfYear);
                                }

                                if(dayOfMonth <9){
                                    String day = String.valueOf(dayOfMonth);
                                    newDay = "0"+day;
                                }
                                else{
                                    newDay = String.valueOf(dayOfMonth);
                                }
                                String year1 = String.valueOf(year);

                                date = year1+newMonth+newDay;
                                notificationDate = dayOfMonth + "-" + (monthOfYear) + "-" + year;
                                Log.d("NEW iugyfhchugyhf", ":"+date);
                                newHGO = "https://tokyo2020.org/en/games/schedule/olympic/"+date+".html";

                                eText.setText(dayOfMonth + "-" + (monthOfYear) + "-" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });




        String newMonth = null, newDay="" ;

        month = month + 1;
        if(month <9){

            String month1 = String.valueOf(month);
            newMonth = "0"+month1;
        }
        else {
            newMonth = String.valueOf(month);
        }

        if(day < 9){
            String vday = String.valueOf(day);
            newDay = "0"+vday;
        }
        else{
            newDay = String.valueOf(day);
        }
        final String year1 = String.valueOf(year);


        String okok = year1+newMonth+newDay;

        Log.d("Current Date",":::"+okok);
        Log.d("Selected Date",":::"+date);


        Log.d("Cecke", ":"+newCheck);

         reminderbtn =  (Button) root.findViewById(R.id.button1);

        final String finalNewMonth = newMonth;
        final String finalNewDay = newDay;
        reminderbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int  newCheck = Integer.parseInt(date);

                if(newCheck <= 20200809 && newCheck >= 20200722){
                    // Set reminder here
                    Intent intent = new Intent(getContext(), ToolsFragment.this.getClass());


                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    PendingIntent pendingIntent = PendingIntent.getActivity(getContext(), 0, intent, 0);

                    NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext(), CHANNEL_ID)
                            .setSmallIcon(R.drawable.reminder)
                            .setContentTitle("Remider for event")
                            .setContentText("Your game "+game_name+" will start on "+notificationDate+ ". So be ready for game. and Enjoy the game.")
                            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                            // Biji file mathi copy kareli che "Set the notification's tap action"
                            //https://developer.android.com/training/notify-user/build-notification#click
                            .setContentIntent(pendingIntent)
                            .setAutoCancel(true);

                    // must define

                    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getContext());

                    int notificationId = 1234;
                    // notificationId is a unique int for each notification that you must define


                   notificationManager.notify(notificationId, builder.build());
                    //



                }else{
                    AlertDialog.Builder popup = new AlertDialog.Builder(getContext());

                    popup.setTitle("Sorry....");
                    popup.setMessage("No events on that day. So you can not put reminder");

                    popup.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });

                    popup.show();
                }
            }
        });

        return root;
    }

    private void createNotificationChannel(){
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {


            CharSequence name = "My Varun";
            String description = "VARUN CODE FOR NOTIFICATIOJ IS HERE";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getActivity().getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }


}