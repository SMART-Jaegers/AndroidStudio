package com.smartjaegers.checkfuel.models;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.smartjaegers.checkfuel.GasStationInfo;
import com.smartjaegers.checkfuel.R;

import java.text.DecimalFormat;

public class ItemViewOfUsageStatistic extends RecyclerView.ViewHolder {
    private Context context;
    private TextView textLiter;
    private TextView textKilometer;
    private TextView textDate;
    private ImageView gasStation;
    private ImageView goToDetail;

    public ItemViewOfUsageStatistic(ViewGroup parent, Context context) {
        super(LayoutInflater.from(context).inflate(R.layout.item_of_list_usage_statistic, parent, false));
        this.context = context;
        textLiter = itemView.findViewById(R.id.textLiter);
        textKilometer = itemView.findViewById(R.id.textKilometer);
        textDate = itemView.findViewById(R.id.textDate);
        gasStation = itemView.findViewById(R.id.imageView);
        goToDetail = itemView.findViewById(R.id.goCurrentUse);
    }

    public void bind(ItemStatistic itemStatistic) {
        textLiter.setText(String.format("Volume of refill:%s", new DecimalFormat().format(itemStatistic.getVolumeFillReal())));
        textKilometer.setText(String.format("Distance after refuelling:%s km", new DecimalFormat().format(itemStatistic.getDistance())));
        textDate.setText(itemStatistic.getDate());
        goToDetail.setImageResource(R.drawable.gray_orientation);
        gasStation.setImageResource(findStationImage(itemStatistic.getNameStation()));

        itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, GasStationInfo.class);
            intent.putExtra("nameStation", itemStatistic.getNameStation());
            intent.putExtra("ItemStatistic", (Parcelable) itemStatistic);
            intent.putExtra("nameStation", itemStatistic.getNameStation());
            context.startActivity(intent);
        });

    }

    private int findStationImage(String stationName) {
        switch (stationName) {
            case "Okko":
                return R.drawable.purple_okko;
            case "Wog":
                return R.drawable.wog_purple;
            case "UkrNafta":
                return R.drawable.ukrnafta_purple;
            case "Shell":
                return R.drawable.shell_purple;
            case "Socar":
                return R.drawable.logo_socar;
            case "UPG":
                return R.drawable.logo_upg_purple;
            default:
                return R.drawable.bad_fuel;
        }
    }

}
