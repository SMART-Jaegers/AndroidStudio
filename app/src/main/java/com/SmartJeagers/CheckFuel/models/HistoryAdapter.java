package com.SmartJeagers.CheckFuel.models;

import android.content.Context;
import android.util.Log;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryItemView> {
    private List<Refill> refills;
    private List<DayOfUse> daysOfUse;
    private Context context;

    public HistoryAdapter(List<Refill> refills, List<DayOfUse> daysOfUse, Context context) {
        this.refills = refills;
        this.context = context;
        this.daysOfUse = daysOfUse;
    }

    @NonNull
    @Override
    public HistoryItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HistoryItemView(parent, context);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryItemView holder, int position) {
        int index = refills.size() - (position + 1);//нумерація з першого

        SimpleDateFormat pattern = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dateOld = pattern.parse(refills.get(index).getDate());
            double distance = 0;
            if (index == (refills.size() - 1)) {    //for first element
                for (DayOfUse dayOfUse : daysOfUse) {
                    Date date = pattern.parse(dayOfUse.getDate());

                    if (date.after(dateOld) || date.equals(dateOld)) {
                        distance += dayOfUse.getKmPerDay();
                    }
                }
                holder.bind(refills.get(index), distance);
                return;
            }
            Date dateNow = pattern.parse(refills.get(index + 1).getDate());


            for (DayOfUse dayOfUse : daysOfUse) {
                Date date = pattern.parse(dayOfUse.getDate());

                if ((date.before(dateNow)) && (date.after(dateOld) || date.equals(dateOld))) {
                    distance += dayOfUse.getKmPerDay();
                }
            }

            holder.bind(refills.get(index), distance);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return refills.size();
    }

}
