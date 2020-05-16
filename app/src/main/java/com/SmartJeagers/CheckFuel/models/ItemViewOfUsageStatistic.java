package com.SmartJeagers.CheckFuel.models;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.SmartJeagers.CheckFuel.GasStationInfo;
import com.checkfuel.something.R;

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
        OnItemClickListener listener = itemStatistic1 -> {
            Intent intent = new Intent(context, GasStationInfo.class);
            context.startActivity(intent);
        };
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(itemStatistic);
            }
        });
        switch (itemStatistic.getNameStation()) {
            case "Okko":
                gasStation.setImageResource(R.drawable.purple_okko);
                break;
            case "Wog":
                gasStation.setImageResource(R.drawable.purple_okko);
                break;
            default:
                gasStation.setImageResource(R.drawable.purple_filter);

        }

        goToDetail.setImageResource(R.drawable.gray_orientation);
    }

    public void onClick(View view) {
        goToDetail.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, GasStationInfo.class);

            }
        });

    }

}
