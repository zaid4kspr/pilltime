package com.example.myapplication;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;

public class SMS extends AppCompatActivity {
    @BindView(R.id.send)
    Button sendButton;
    @BindView(R.id.msg)
    EditText msg;
    @BindView(R.id.number)
    EditText number;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        sendButton.setEnabled(false);
        if(checkPermission(Manifest.permission.SEND_SMS)){
            sendButton.setEnabled(true);
        }else {
            //ActivityCompat.requestPermissions(this,
                 //   new String[]{Manifest.permission.SEND_SMS},SEND_SMS_PERMISSION_REQUEST_CODE);
        }

    }
    public void onSend(View v) {
    String phoneNumber = number.getText().toString();
    String smsMessage = msg.getText().toString();
    }
    public boolean checkPermission(String permission) {
        int check = ContextCompat.checkSelfPermission(this,permission);
        return (check==PackageManager.PERMISSION_GRANTED);
    }
}
