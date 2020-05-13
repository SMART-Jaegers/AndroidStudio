package com.SmartJeagers.CheckFuel;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.SmartJeagers.CheckFuel.utils.DatabaseManagerForDayOfUse;
import com.SmartJeagers.CheckFuel.utils.DatabaseManagerForRefill;
import com.checkfuel.something.R;
import com.SmartJeagers.CheckFuel.utils.AuthenticationManager;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends AppCompatActivity {
    private AuthenticationManager authentication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        AuthenticationManager authentication = new AuthenticationManager();
        if (!authentication.entryToDatabase()) {
            Intent intent = new Intent(this, SignUp.class);
            startActivity(intent);
            return;
        }
        DatabaseManagerForRefill.readRefill();
        DatabaseManagerForDayOfUse.readDayOfUse();
        //        DatabaseManagerForDayOfUse.writeDataOfUse(10,4,"2020-05-01");
//        DatabaseManagerForRefill.writeRefill(0,0,1000,"qqq","a95","2020-05-01");
        //TODO realise transit to userProfile
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onStart() {
        super.onStart();


        SimpleDateFormat pattern = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = pattern.parse("2020-05-02");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Log.i("----Result----\n\n", pattern.format(date));


    }

    public void confirmFireMissiles() {
        DialogFragment newFragment = new NoWifi();
        newFragment.show(getSupportFragmentManager(), "noWifi");
    }

    public void refueling(@NotNull View view) {
        Intent intent = new Intent(this, SetVolume.class);
        startActivity(intent);
    }


    public void goToUsageStatistic(@NotNull View view) {
        Intent intent = new Intent(this, UsageStatistic.class);
        startActivity(intent);
    }

    public void chooseTheGasStation(@NotNull View view) {
        Intent intent = new Intent(this, ChooseGasStation.class);
        startActivity(intent);
    }

    public void goToUserProfile(View view) {
        AuthenticationManager authentication = new AuthenticationManager();
        if (!authentication.entryToDatabase()) {
            Intent intent = new Intent(this, SignUp.class);
            startActivity(intent);
            return;
        }
        DatabaseManagerForRefill.getRefils();
        //TODO realise transit to userProfile
    }

}