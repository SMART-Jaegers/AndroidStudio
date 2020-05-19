package com.smartjaegers.checkfuel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.smartjaegers.checkfuel.models.OnGetResult;
import com.smartjaegers.checkfuel.models.User;
import com.smartjaegers.checkfuel.managers.DatabaseManagerForDayOfUse;
import com.smartjaegers.checkfuel.managers.DatabaseManagerForRefill;
import com.smartjaegers.checkfuel.managers.DatabaseManagerForUser;

import com.smartjaegers.checkfuel.managers.AuthenticationManager;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private AuthenticationManager authentication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("-----MainActivity-----", "Start");
        authentication = new AuthenticationManager();


        Log.i("-----MainActivity-----", "Create");
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_wiew);

        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        if (authentication.entryToDatabase()) {

            DatabaseManagerForUser.readUser(new OnGetResult() {
                @Override
                public void onSuccess() {
                    Log.i("-----Database--------", "SuccessReadingRefill");
                    drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                    User user = DatabaseManagerForUser.getUser();
                    View headerView = navigationView.getHeaderView(0);
                    TextView title = headerView.findViewById(R.id.userName);
                    TextView subTitle = headerView.findViewById(R.id.userEmail);
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
                }
            });


        }
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);

        findViewById(R.id.refuling).setOnClickListener(this);
        findViewById(R.id.currentUse).setOnClickListener(this);
        findViewById(R.id.usageStatistic).setOnClickListener(this);
        findViewById(R.id.quality).setOnClickListener(this);

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
                    Toast.makeText(this, "You aren't log in", Toast.LENGTH_SHORT).show();
                    return;
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                authentication.signOut();
                break;
        }
        return true;
    }


}