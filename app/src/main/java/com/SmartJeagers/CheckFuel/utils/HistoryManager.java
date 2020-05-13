package com.SmartJeagers.CheckFuel.utils;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.SmartJeagers.CheckFuel.models.DayOfUse;
import com.SmartJeagers.CheckFuel.models.Refill;
import com.SmartJeagers.CheckFuel.models.HistoryAdapter;

import java.util.List;

public class HistoryManager {

    public static void setConfig(RecyclerView recyclerView, Context context, List<Refill> refills, List<DayOfUse> daysOfUse) {

        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(new HistoryAdapter(refills, daysOfUse, context));
    }

}

