package com.SmartJeagers.CheckFuel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.SmartJeagers.CheckFuel.utils.DatabaseManagerForRefill;
import com.checkfuel.something.R;
import com.SmartJeagers.CheckFuel.utils.AuthenticationManager;

import org.jetbrains.annotations.NotNull;


public class MainActivity extends AppCompatActivity {
    private AuthenticationManager authentication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseManagerForRefill.readRefill();
    }

    @Override
    public void onStart() {
        super.onStart();
        confirmFireMissiles();

    }

    public void confirmFireMissiles() {
        DialogFragment newFragment = new NoWifi();
        newFragment.show(getSupportFragmentManager(), "noWifi");
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
        DatabaseManagerForRefill.getRefils();
        //TODO realise transit to userProfile

    }

}