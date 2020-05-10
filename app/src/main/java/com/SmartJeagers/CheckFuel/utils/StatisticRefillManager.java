package com.SmartJeagers.CheckFuel.utils;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.SmartJeagers.CheckFuel.models.Refill;
import com.SmartJeagers.CheckFuel.models.StatisticAdapter;

import java.util.List;

public class StatisticRefillManager {

    public void setConfig(RecyclerView recyclerView, Context context, List<Refill> refills) {
        StatisticAdapter statisticAdapter = new StatisticAdapter(refills, context);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(statisticAdapter);
    }

}

