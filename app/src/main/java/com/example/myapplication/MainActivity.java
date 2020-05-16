package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.myapplication.listOfMedication.listOfMedicationPage.listOfMyMeds;
import com.example.myapplication.listOfPriseHomePage.listOfPriseHomePage;
import com.example.myapplication.listOfProgramms.listOfPrograms;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    //1 - FOR DESIGN


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().replace(R.id.activity_main_frame_layout, new listOfPriseHomePage()).commit();
        BottomNavigationView bottomNavigationView = findViewById(R.id.activity_main_bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        SharedPreferences preferences = getSharedPreferences("myprefs", MODE_PRIVATE);
        String id = preferences.getString("id", "");
      //  Toast.makeText(this, "FROM MAIN" + id, Toast.LENGTH_SHORT).show();


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
                    if(countMyMeds()!=0){
                        selectedFragment = new fragment_add_med();
                    }else{
                        selectedFragment = new listOfMyMeds();
                    }
//                this.mainFragment.updateDesignWhenUserClickedBottomView(MainFragment.REQUEST_LOGO);
                    break;
                case R.id.navigation_more:
                    selectedFragment = new more();
                    break;
                case R.id.navigation_dashboard:
                    if(CountProgramms()==0){
                        selectedFragment = new fragment_add_prog();
                    }else{
                        selectedFragment = new listOfPrograms();
                    }

//                this.mainFragment.updateDesignWhenUserClickedBottomView(MainFragment.REQUEST_LANDSCAPE);
                    break;

            }
            getSupportFragmentManager().beginTransaction().replace(R.id.activity_main_frame_layout, selectedFragment).commit();
            return true;

        }
    };







    int countMyMeds(){

        return 0;

    }


    int CountProgramms(){

        return 120;

    }






}





