package com.checkfuel.frontend;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.checkfuel.something.R;
import com.checkfuel.utils.AuthenticationManager;
import com.checkfuel.utils.TextReader;

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

        authentication.entryToDatabase();

        confirmFireMissiles();
        authentication.signIn("tarasfqx@gmail.com", "werwerwer");
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

}


