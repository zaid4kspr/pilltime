package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.listOfMedication.listOfMedicationPage.listOfMyMeds;
import com.example.myapplication.listOfPriseHomePage.listOfPriseHomePage;
import com.example.myapplication.listOfProgramms.listOfPrograms;
import com.google.android.material.bottomnavigation.BottomNavigationView;



import androidx.core.app.NotificationCompat;


public class MainActivity extends AppCompatActivity {
    //1 - FOR DESIGN
    SharedPreferences preferences;
    private static final String[] INITIAL_PERMS={
            Manifest.permission.ACCESS_FINE_LOCATION

    };
    private static final String[] CAMERA_PERMS={
            Manifest.permission.CAMERA
    };
    private static final String[] CONTACTS_PERMS={
            Manifest.permission.READ_CONTACTS
    };
    private static final String[] LOCATION_PERMS={
            Manifest.permission.ACCESS_FINE_LOCATION
    };
    private static final int INITIAL_REQUEST=1337;
    private static final int CAMERA_REQUEST=INITIAL_REQUEST+1;
    private static final int CONTACTS_REQUEST=INITIAL_REQUEST+2;
    private static final int LOCATION_REQUEST=INITIAL_REQUEST+3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      //  notificationManager = NotificationManagerCompat.from(this);



        getSupportFragmentManager().beginTransaction().replace(R.id.activity_main_frame_layout, new listOfPriseHomePage()).commit();
        BottomNavigationView bottomNavigationView = findViewById(R.id.activity_main_bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        preferences = getSharedPreferences("myprefs", MODE_PRIVATE);
        String id = preferences.getString("id", "");
        //  Toast.makeText(this, "FROM MAIN" + id, Toast.LENGTH_SHORT).show();


        if (!canAccessLocation()) {
            requestPermissions(INITIAL_PERMS, INITIAL_REQUEST);
        }

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    // selectedFragment = new HomeCalendarPage();
                    selectedFragment = new listOfPriseHomePage();
                    break;
                case R.id.navigation_add:
                    if (countMyMeds() == 0) {
                        selectedFragment = new fragment_add_med();
                    } else {
                        selectedFragment = new listOfMyMeds();
                    }
//                this.mainFragment.updateDesignWhenUserClickedBottomView(MainFragment.REQUEST_LOGO);
                    break;
                case R.id.navigation_more:
                    selectedFragment = new more();
                    break;
                case R.id.navigation_dashboard:
                    if (CountProgramms() == 0) {
                        selectedFragment = new fragment_add_prog();
                    } else {
                        selectedFragment = new listOfPrograms();
                    }

//                this.mainFragment.updateDesignWhenUserClickedBottomView(MainFragment.REQUEST_LANDSCAPE);
                    break;

            }
            getSupportFragmentManager().beginTransaction().replace(R.id.activity_main_frame_layout, selectedFragment).commit();



            return true;

        }
    };


    int countMyMeds() {



        return preferences.getInt("meds", 0);

    }


    int CountProgramms() {

        return preferences.getInt("programs", 0);

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {


        switch(requestCode) {

            case LOCATION_REQUEST:
                if (canAccessLocation()) {

                }
                else {
                    bzzzt();
                }
                break;
        }
    }

    private boolean canAccessLocation() {
        return(hasPermission(Manifest.permission.ACCESS_FINE_LOCATION));
    }

    private boolean hasPermission(String perm) {
        return(PackageManager.PERMISSION_GRANTED==checkSelfPermission(perm));
    }

    private void bzzzt() {
        Toast.makeText(this, "zzzzzzz", Toast.LENGTH_LONG).show();
    }


}





