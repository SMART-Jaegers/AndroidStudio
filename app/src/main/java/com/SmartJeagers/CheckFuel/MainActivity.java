package com.SmartJeagers.CheckFuel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.SmartJeagers.CheckFuel.utils.DatabaseManagerForDayOfUse;
import com.SmartJeagers.CheckFuel.utils.DatabaseManagerForRefill;
import com.checkfuel.something.R;
import com.SmartJeagers.CheckFuel.utils.AuthenticationManager;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    private DrawerLayout drawerLayout;
    private AuthenticationManager authentication = new AuthenticationManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_wiew);
        LinearLayout mainPage = findViewById(R.id.main_page);


        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
//q q q q q q q q q q q q q q q q q q  qq  q q q  q q q q qq  q qq q q q q  q q q q q q qq q

        //DatabaseManagerForDayOfUse.writeDataOfUse(10,4,"2020-05-01");
        //DatabaseManagerForRefill.writeRefill(0,0,1000,"qqq","a95","2020-05-01");
//qqqqqqqqqqqqqqqqqqq q q q qq q q q qq  q qq q q q q q q q q q q q q q qq q q q qq q q q  qq q q  qq q  qq q q q  qqqqqq
        findViewById(R.id.refuling).setOnClickListener(this);
        findViewById(R.id.currentUse).setOnClickListener(this);
        findViewById(R.id.usageStatistic).setOnClickListener(this);
        findViewById(R.id.quality).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.refuling:
                intent.setClass(this, SetVolume.class);
                break;
            case R.id.currentUse:
                //TODO Create intent to current use
                break;
            case R.id.usageStatistic:
                if (authentication.entryToDatabase()) {
                    intent.setClass(this, UsageStatistic.class);
                } else{
                    Toast.makeText(this, "You aren't log in", Toast.LENGTH_SHORT).show();
                    return;
                }break;
            case R.id.quality:
                intent.setClass(this, ChooseGasStation.class);
                break;
        }
        startActivity(intent);
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
        Intent intent = new Intent();
        switch (item.getItemId()) {
            case R.id.sign_in:
                intent.setClass(this, SignIn.class);
                startActivity(intent);
                break;
            case R.id.sign_up:
                intent.setClass(this, SignUp.class);
                startActivity(intent);
                break;
            case R.id.logout:
                authentication.signOut();
                break;
        }
        return true;
    }

}