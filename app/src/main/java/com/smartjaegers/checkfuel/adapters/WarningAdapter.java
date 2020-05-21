package com.smartjaegers.checkfuel.adapters;

import android.content.Context;
import android.os.Build;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.smartjaegers.checkfuel.managers.ItemStatisticManager;
import com.smartjaegers.checkfuel.models.DayOfUse;
import com.smartjaegers.checkfuel.models.ItemStatistic;
import com.smartjaegers.checkfuel.models.Problem;
import com.smartjaegers.checkfuel.models.Refill;
import com.smartjaegers.checkfuel.view.ViewOfWarningItems;

import java.text.SimpleDateFormat;
import java.util.List;

public class WarningAdapter extends RecyclerView.Adapter<ViewOfWarningItems> {
    private static final SimpleDateFormat pattern = new SimpleDateFormat("yyyy-MM-dd");
    private List<Problem> warningList;
    private Context context;


    @RequiresApi(api = Build.VERSION_CODES.N)
    public WarningAdapter(List<Problem> problems, Context context) {
        this.warningList = problems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewOfWarningItems onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewOfWarningItems(parent, context);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewOfWarningItems holder, int position) {

        holder.bind(warningList.get(position));
    }

    @Override
    public int getItemCount() {
        return warningList.size();
    }


}

