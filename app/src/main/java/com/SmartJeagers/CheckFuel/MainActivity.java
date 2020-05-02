package com.SmartJeagers.CheckFuel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.SmartJeagers.CheckFuel.utils.DatabaseManager;
import com.checkfuel.something.R;
import com.SmartJeagers.CheckFuel.utils.AuthenticationManager;

import org.jetbrains.annotations.NotNull;


public class MainActivity extends AppCompatActivity {
    private AuthenticationManager authentication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseManager.readRefill();
    }

    @Override
    public void onStart() {
        super.onStart();
        confirmFireMissiles();
        DatabaseManager.getRefils();
    }

    public void confirmFireMissiles() {
        DialogFragment newFragment = new NoWifi();
        newFragment.show(getSupportFragmentManager(), "missiles");
    }

    public void refueling(@NotNull View view) {
        Intent intent = new Intent(this, SetVolume.class);
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
        DatabaseManager.writeRefill(0, 0, 0, true, "fklml", "23kdd", "2.05.20");


        //TODO realise transit to userProfile

    }

}