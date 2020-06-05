package com.example.appdevelopment.ui.slideshow;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.appdevelopment.R;

import java.util.Calendar;

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;

    View root;
    private FragmentActivity myContext;

    WebView myWebView;
    String date="";
    String url="";
    EditText eText;
    DatePickerDialog picker;
    Button btnGet;
    TextView tvw;
    String newHGO;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(SlideshowViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
     //   final TextView textView = root.findViewById(R.id.text_slideshow);


        eText=( EditText ) root.findViewById(R.id.editText1);
        eText.setInputType(InputType.TYPE_NULL);
        eText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
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
                                Log.d("NEW iugyfhchugyhf", ":"+date);
                                newHGO = "https://tokyo2020.org/en/games/schedule/olympic/"+date+".html";

                                eText.setText(dayOfMonth + "-" + (monthOfYear) + "-" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });

        btnGet=(Button)root.findViewById(R.id.button1);
        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String vdate =eText.getText().toString();

                String NEW = newHGO;

                String check = date ;

                int newCheck = Integer.parseInt(check);
                Log.d("Cecke", ":"+newCheck);

                if(newCheck <= 20200809 && newCheck >= 20200722) {
                    newHGO = "https://tokyo2020.org/en/games/schedule/olympic/" + date + ".html";

                    WebView webviewMain = ( WebView ) root.findViewById(R.id.webview);

                    webviewMain.setWebViewClient(new WebViewClient());
                    webviewMain.loadUrl(NEW);
                }
                else {

                    AlertDialog.Builder popup = new AlertDialog.Builder(getContext());

                    popup.setTitle("Sorry....");
                    popup.setMessage("No events on that day");

                    popup.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {


                        }
                    });

                    popup.show();
                }

            }
        });
        url = "https://tokyo2020.org/en/games/schedule/olympic/";
        // this is for schedule
        // Create a reference to the webview in the xml file
        WebView webviewMain = (WebView) root.findViewById(R.id.webview);

        webviewMain.setWebViewClient(new WebViewClient());
        webviewMain.loadUrl(url);

        webviewMain.setWebViewClient(new WebViewClient());

        return root;
    }
}