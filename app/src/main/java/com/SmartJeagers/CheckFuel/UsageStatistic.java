package com.SmartJeagers.CheckFuel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.SmartJeagers.CheckFuel.models.Refill;
import com.SmartJeagers.CheckFuel.utils.StatisticRefillManager;
import com.SmartJeagers.CheckFuel.utils.DatabaseManagerForRefill;
import com.checkfuel.something.R;

import java.util.List;

public class UsageStatistic extends Activity {

    private RecyclerView recyclerView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usage_statistic);

        recyclerView = findViewById(R.id.statisticView);
        List<Refill> refills = DatabaseManagerForRefill.getRefils();
        new StatisticRefillManager().setConfig(recyclerView, UsageStatistic.this, refills);
    }

    public void backToMain(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

}
