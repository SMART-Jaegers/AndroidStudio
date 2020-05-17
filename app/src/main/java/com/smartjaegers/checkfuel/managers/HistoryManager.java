package com.smartjaegers.checkfuel.managers;

import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.smartjaegers.checkfuel.models.DayOfUse;
import com.smartjaegers.checkfuel.models.Refill;
import com.smartjaegers.checkfuel.adapters.HistoryAdapter;

import java.util.List;

public class HistoryManager {

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void setConfig(RecyclerView recyclerView, Context context, List<Refill> refills, List<DayOfUse> daysOfUse) {

        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(new HistoryAdapter(refills, daysOfUse, context));
    }

}