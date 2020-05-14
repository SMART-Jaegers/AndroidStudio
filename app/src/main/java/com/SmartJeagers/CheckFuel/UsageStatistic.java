package com.SmartJeagers.CheckFuel;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.SmartJeagers.CheckFuel.models.DayOfUse;
import com.SmartJeagers.CheckFuel.models.Refill;
import com.SmartJeagers.CheckFuel.utils.DatabaseManagerForDayOfUse;
import com.SmartJeagers.CheckFuel.utils.HistoryManager;
import com.SmartJeagers.CheckFuel.utils.DatabaseManagerForRefill;
import com.checkfuel.something.R;

import java.util.Collections;
import java.util.List;

public class UsageStatistic extends AppCompatActivity {

    private RecyclerView recyclerView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usage_statistic);
        boolean goForward = false;
        while (!goForward) {
            goForward = DatabaseManagerForRefill.readRefill() & DatabaseManagerForDayOfUse.readDayOfUse();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        List<Refill> refills = DatabaseManagerForRefill.getRefils();
        List<DayOfUse> daysOfUse = DatabaseManagerForDayOfUse.getDaysOfUse();
        Collections.reverse(refills);

        if (refills.size() == 0) {
            Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();
        }

        recyclerView = findViewById(R.id.statisticView);
        HistoryManager.setConfig(recyclerView, UsageStatistic.this, refills, daysOfUse);
    }

    public void goToMore(View view) {
        DialogFragment newFragment = new FilterInfo();
        newFragment.show(getSupportFragmentManager(), "filterInfo");
    }

    public void backToMain(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

}
