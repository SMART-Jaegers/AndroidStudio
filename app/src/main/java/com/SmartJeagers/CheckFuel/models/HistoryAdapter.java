package com.SmartJeagers.CheckFuel.models;

import android.content.Context;
import android.os.Build;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.SmartJeagers.CheckFuel.utils.UtilsManagerForStatistic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<ItemViewOfUsageStatistic> {
    private static final SimpleDateFormat pattern = new SimpleDateFormat("yyyy-MM-dd");
    private List<ItemStatistic> itemStatisticList;
    private Context context;


    @RequiresApi(api = Build.VERSION_CODES.N)
    public HistoryAdapter(List<Refill> refills, List<DayOfUse> daysOfUse, Context context) {
        this.itemStatisticList = createStatisticItems(refills, daysOfUse);
        this.context = context;
        UtilsManagerForStatistic.sortByVolume(itemStatisticList, SortType.DESCENDING);
    }

    private List<ItemStatistic> createStatisticItems(List<Refill> refills, List<DayOfUse> daysOfUse) {
        List<ItemStatistic> itemStatisticList = new LinkedList<>();
        Collections.reverse(refills);


        try {
            for (int position = 0; position < refills.size(); position++) {
                Date dateOfRefill = pattern.parse(refills.get(position).getDate());

                Date dateNextRefill = null;
                if (position != 0) {
                    dateNextRefill = pattern.parse(refills.get(position - 1).getDate());
                }

                double distance = calculateDistance(daysOfUse, dateOfRefill, dateNextRefill);
                itemStatisticList.add(new ItemStatistic(refills.get(position), distance));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return itemStatisticList;
    }

    private double calculateDistance(List<DayOfUse> daysOfUse, Date refillDate, Date nextRefillDate) {
        double distance = 0;
        try {
            for (DayOfUse dayOfUse : daysOfUse) {
                Date date = null;

                date = pattern.parse(dayOfUse.getDate());
                assert date != null;
                if (nextRefillDate == null) {
                    if ((date.after(refillDate) || date.equals(refillDate))) {
                        distance += dayOfUse.getKmPerDay();
                    }
                } else {
                    if ((date.before(nextRefillDate)) && (date.after(refillDate) || date.equals(refillDate))) {
                        distance += dayOfUse.getKmPerDay();
                    }
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return distance;
    }

    @NonNull
    @Override
    public ItemViewOfUsageStatistic onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemViewOfUsageStatistic(parent, context);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewOfUsageStatistic holder, int position) {
        holder.bind(itemStatisticList.get(position));
    }

    @Override
    public int getItemCount() {
        return itemStatisticList.size();
    }

}
