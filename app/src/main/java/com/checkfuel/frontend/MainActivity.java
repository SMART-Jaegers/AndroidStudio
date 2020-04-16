package com.checkfuel.frontend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import com.checkfuel.something.R;
import com.checkfuel.utils.AuthenticationManager;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {
    private AuthenticationManager authentication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        authentication = new AuthenticationManager();

    }

    @Override
    public void onStart() {
        super.onStart();
        authentication.checkSignIn();
    }

    public void refueling(@NotNull View view) {
        if (view.getId() == R.id.refuling) {
            Intent intent = new Intent(this, SetVolume.class);
            startActivity(intent);
        }
    }

    public void chooseTheGasStation(@NotNull View view) {
        if (view.getId() == R.id.quality) {
            Intent intent = new Intent(this, ChooseTheGasStation.class);
            startActivity(intent);
        }
    }


}