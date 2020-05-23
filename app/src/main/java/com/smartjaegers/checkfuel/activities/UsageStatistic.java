package com.smartjaegers.checkfuel.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.smartjaegers.checkfuel.R;
import com.smartjaegers.checkfuel.models.DayOfUse;
import com.smartjaegers.checkfuel.models.Refill;
import com.smartjaegers.checkfuel.managers.DatabaseManagerForDayOfUse;
import com.smartjaegers.checkfuel.managers.HistoryManager;
import com.smartjaegers.checkfuel.managers.DatabaseManagerForRefill;

import java.util.List;

public class UsageStatistic extends AppCompatActivity {

    private String nowSorting;
    private String nowFilter;
    private int nowFilterNumber;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usage_statistic);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onStart() {
        super.onStart();
        nowSorting = getIntent().getStringExtra("nowSorting");
        nowFilter = getIntent().getStringExtra("Filter");
        nowFilterNumber = getIntent().getIntExtra("FilterNumber", -1);
        if (nowFilter == null) {
            nowFilter = "11";
        }
        Log.i("-------Test-----", nowFilter);
        if (nowSorting == null) {
            nowSorting = "date_new_old";
        }

        List<Refill> refills = DatabaseManagerForRefill.getRefills();
        List<DayOfUse> daysOfUse = DatabaseManagerForDayOfUse.getDaysOfUse();


        if (refills.size() == 0) {
            Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();
        }

        RecyclerView recyclerView = findViewById(R.id.statisticView);
        HistoryManager.setConfig(recyclerView, UsageStatistic.this, refills, daysOfUse, nowSorting, nowFilter, nowFilterNumber);
    }

    public void goToMore(View view) {
        DialogFragment newFragment = new ChooseFiltersAndSort(nowSorting);
        newFragment.show(getSupportFragmentManager(), "filterInfo");

    }

    public void backToMain(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

}
