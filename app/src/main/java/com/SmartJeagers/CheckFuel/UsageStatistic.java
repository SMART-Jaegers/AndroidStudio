package com.SmartJeagers.CheckFuel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.SmartJeagers.CheckFuel.models.DayOfUse;
import com.SmartJeagers.CheckFuel.models.Refill;
import com.SmartJeagers.CheckFuel.utils.DatabaseManagerForDayOfUse;
import com.SmartJeagers.CheckFuel.utils.HistoryManager;
import com.SmartJeagers.CheckFuel.utils.DatabaseManagerForRefill;
import com.checkfuel.something.R;

import java.util.List;

public class UsageStatistic extends Activity {

    private RecyclerView recyclerView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usage_statistic);

        List<Refill> refills = DatabaseManagerForRefill.getRefils();
        List<DayOfUse> daysOfUse = DatabaseManagerForDayOfUse.getDaysOfUse();

        recyclerView = findViewById(R.id.statisticView);
        HistoryManager.setConfig(recyclerView, UsageStatistic.this, refills, daysOfUse);
    }

    public void backToMain(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

}
