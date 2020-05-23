package com.smartjaegers.checkfuel.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.text.SimpleDateFormat;

import android.widget.TextView;
import android.widget.Toast;

import com.smartjaegers.checkfuel.R;
import com.smartjaegers.checkfuel.managers.DatabaseManagerForQuality;
import com.smartjaegers.checkfuel.models.OnGetResult;
import com.smartjaegers.checkfuel.models.User;
import com.smartjaegers.checkfuel.managers.DatabaseManagerForDayOfUse;
import com.smartjaegers.checkfuel.managers.DatabaseManagerForRefill;
import com.smartjaegers.checkfuel.managers.DatabaseManagerForUser;

import com.smartjaegers.checkfuel.managers.AuthenticationManager;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private DrawerLayout drawerLayout;
    private AuthenticationManager authentication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.refuling).setOnClickListener(this);
        findViewById(R.id.currentUse).setOnClickListener(this);
        findViewById(R.id.usageStatistic).setOnClickListener(this);
        findViewById(R.id.quality).setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        authentication = new AuthenticationManager();
        setDrawerLayout();
    }


    @Override
    public void onClick(View v) {
        final Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.refuling:
                intent.setClass(this, SetVolume.class);
                break;
            case R.id.currentUse:
                intent.setClass(this, MainActivity.class);
                //TODO Create intent to current use
                break;
            case R.id.usageStatistic:
                if (authentication.entryToDatabase()) {
                    intent.setClass(this, Loading.class);
                    readFromDB();
                } else {
                    intent.setClass(this, SignUp.class);
                }
                break;
            case R.id.quality:
                intent.setClass(this, ChooseGasStation.class);
                break;
        }
        startActivity(intent);
    }

    private void readFromDB() {
        DatabaseManagerForDayOfUse.readDayOfUse();
        DatabaseManagerForRefill.readRefill(new OnGetResult() {

            @Override
            public void onSuccess() {
                Log.i("-----Database--------", "SuccessReadingRefill");
                Intent intent = new Intent(MainActivity.this, UsageStatistic.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }

            @Override
            public void onStart() {
                Log.i("-----Database--------", "StartReadingRefill");
            }

            @Override
            public void onFailure() {
                Log.i("-----Database--------", "FailureReadingRefill");
                Toast.makeText(MainActivity.this, "Failed read from database", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void goToProfile(View view) {
        if (!authentication.entryToDatabase()) {
            Intent intent = new Intent(this, SignUp.class);
            startActivity(intent);
            return;
        }
        drawerLayout.openDrawer(GravityCompat.START);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen((GravityCompat.START))) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void setDrawerLayout() {
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_wiew);

        View headerView = navigationView.getHeaderView(0);
        TextView title = headerView.findViewById(R.id.userName);
        TextView subTitle = headerView.findViewById(R.id.userEmail);

        if (authentication.entryToDatabase()) {
           
            findViewById(R.id.usageStatistic).setBackgroundResource(R.color.colorPurple);

            DatabaseManagerForUser.readUser(new OnGetResult() {
                @Override
                public void onSuccess() {
                    Log.i("-----Database--------", "SuccessReadingRefill");
                    User user = DatabaseManagerForUser.getUser();

                    title.setText(user.getUserName());
                    subTitle.setText(user.getEmail());
                }

                @Override
                public void onStart() {
                    Log.i("-----Database--------", "StartReadingRefill");
                }

                @Override
                public void onFailure() {
                    Log.i("-----Database--------", "FailureReadingRefill");
                    Toast.makeText(MainActivity.this, "Failed read from database", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Button signUp = headerView.findViewById(R.id.signUp);
            signUp.setVisibility(View.VISIBLE);
            signUp.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, SignUp.class);
                startActivity(intent);
            });

        }
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent();

        switch (item.getItemId()) {
            case R.id.logout:
                authentication.signOut();
                return true;
            case R.id.warning:
                intent.setClass(this, Warning.class);
                break;
            case R.id.test:
                intent.setClass(this, TestCarWithYourFuel.class);
                break;
            case R.id.addDevice:
                intent.setClass(this, AddDeviceActivity.class);
                break;
            default:
                return true;
        }
        startActivity(intent);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

}