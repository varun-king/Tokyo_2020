package com.example.appdevelopment.ui.send;

import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.appdevelopment.R;

public class SendFragment extends Fragment {



    final String TAG="PHONE-EXAMPLE";
    Intent i;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        final View root = inflater.inflate(R.layout.fragment_send, container, false);


        Button call_btn = (Button) root.findViewById(R.id.call_button_call);

        call_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPhoneButtonPressed(root);
            }
        });

        Button sms_btn = root.findViewById(R.id.sms_button);
        sms_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSMSButtonPressed(root);
            }
        });

        Button mail_btn = root.findViewById(R.id.email_button);
        mail_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendemailButtonPressed(root);

            }
        });


        return root;
    }

    public void callPhoneButtonPressed(View view) {

        // call the other phone directly without open the phone screen

       // EditText etPhoneNumber = (EditText) view.findViewById(R.id.etPhoneNumber);
      //  String number = etPhoneNumber.getText().toString();

        String number = "5554";

        Log.d("Varn", "Phon "+number);

        String formattedPhoneNumber = "tel:" + number;


        // create an explicit intent
        // Tell android

        Intent i = new Intent(Intent.ACTION_CALL);

        Log.d(TAG, "Formatted Phone number is: " + formattedPhoneNumber);


        // sending the phone to dialer screen
        i.setData(Uri.parse(formattedPhoneNumber));


        //run the intend

        if (i.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(i);
        }
        else {
            Log.d(TAG, "ERROR: Cannot find app that matches ACTION_DIALER intent");
        }

        // Change the menifest file
    }

    public void sendSMSButtonPressed(View view) {

        // // sending an sms directly
        //
        //        // 1. get the phone number

       // EditText etPhoneNumber = (EditText) view.findViewById(R.id.etPhoneNumber);
       // String number = etPhoneNumber.getText().toString();
        String  number = "5553";

        // no need to fromat it
        //  String formattedPhoneNumber = "smsto:" + number;

        //        // 2. get the message


        // get the messgage

        EditText etMessage = (EditText) view.findViewById(R.id.etMessage);

        String  message = etMessage.getText().toString();

        //        // 3. do some SMS configuration with your cell phone carrier
        //        //        - Configure the SMSC (Short Message Service Center)

        String scAddress = null;
        //        // 4. Create the intent

        PendingIntent sendIntent = null;
        PendingIntent deliveryIntent = null;
        //        // 5. Send the SMS using SMSManager (built in Android class)

        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(number, scAddress, message, sendIntent, deliveryIntent);

        etMessage.setText("");
        Toast.makeText(getContext(), "Message Has been sent successfully.", Toast.LENGTH_SHORT).show();

        // Change the menifest file

    }

    public void sendemailButtonPressed(View view) {
        EditText et_subject = (EditText) view.findViewById(R.id.etMailSubject);
        String subject = et_subject.getText().toString();

        EditText et_message = (EditText) view.findViewById(R.id.etMailMessage);
        String message = et_message.getText().toString();

        String to = "vickypatel.canada@gmail.com";

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, to);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);

        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Choose an email client"));

        et_message.setText("");
        et_subject.setText("");
       // Toast.makeText(getContext(), "Email has been sent successfully.", Toast.LENGTH_SHORT).show();

    }
}