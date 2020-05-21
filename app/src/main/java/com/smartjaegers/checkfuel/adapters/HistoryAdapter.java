package com.smartjaegers.checkfuel.adapters;

import android.content.Context;
import android.os.Build;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.smartjaegers.checkfuel.managers.ItemStatisticManager;
import com.smartjaegers.checkfuel.managers.UtilsManagerForStatistic;
import com.smartjaegers.checkfuel.models.DayOfUse;
import com.smartjaegers.checkfuel.models.ItemStatistic;
import com.smartjaegers.checkfuel.view.ViewOfItemStatistic;
import com.smartjaegers.checkfuel.models.Refill;
import com.smartjaegers.checkfuel.models.SortType;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<ViewOfItemStatistic> {
    private List<ItemStatistic> itemStatisticList;
    private Context context;


    @RequiresApi(api = Build.VERSION_CODES.N)
    public HistoryAdapter(List<Refill> refills, List<DayOfUse> daysOfUse, Context context) {
        this.itemStatisticList = ItemStatisticManager.createStatisticItem(refills, daysOfUse);
        this.context = context;
    }

    @NonNull
    @Override
    public ViewOfItemStatistic onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewOfItemStatistic(parent, context);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewOfItemStatistic holder, int position) {

        holder.bind(itemStatisticList.get(position));

    }

    @Override
    public int getItemCount() {
        return itemStatisticList.size();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void setSortingBy(String sortBy) {
        switch (sortBy) {
            case "date_new_old":
                UtilsManagerForStatistic.sortByDate(itemStatisticList, SortType.DESCENDING);
                break;
            case "date_old_new":
                UtilsManagerForStatistic.sortByDate(itemStatisticList, SortType.ASCENDING);
                break;
            case "volume_descending":
                UtilsManagerForStatistic.sortByVolume(itemStatisticList, SortType.DESCENDING);
                break;
            case "volume_ascending":
                UtilsManagerForStatistic.sortByVolume(itemStatisticList, SortType.ASCENDING);
                break;
            case "efficiency_ascending":
                UtilsManagerForStatistic.sortByDistance(itemStatisticList, SortType.ASCENDING);
                break;
            case "efficiency_descending":
                UtilsManagerForStatistic.sortByDistance(itemStatisticList, SortType.DESCENDING);
                break;
        }

    }

}
