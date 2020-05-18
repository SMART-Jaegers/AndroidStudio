package com.smartjaegers.checkfuel.models;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.smartjaegers.checkfuel.GasStationInfo;
import com.smartjaegers.checkfuel.R;
import com.smartjaegers.checkfuel.managers.StationManager;

import java.text.DecimalFormat;

public class ViewOfItemStatistic extends RecyclerView.ViewHolder {
    private Context context;
    private TextView textLiter;
    private TextView textKilometer;
    private TextView textDate;
    private ImageView gasStation;
    private ImageView goToDetail;

    public ViewOfItemStatistic(ViewGroup parent, Context context) {
        super(LayoutInflater.from(context).inflate(R.layout.item_of_list_usage_statistic, parent, false));
        this.context = context;
        textLiter = itemView.findViewById(R.id.textLiter);
        textKilometer = itemView.findViewById(R.id.textKilometer);
        textDate = itemView.findViewById(R.id.textDate);
        gasStation = itemView.findViewById(R.id.imageView);
        goToDetail = itemView.findViewById(R.id.goCurrentUse);
    }

    public void bind(ItemStatistic itemStatistic) {
        textLiter.setText(String.format("%s L", new DecimalFormat().format(itemStatistic.getVolumeFillReal())));
        textKilometer.setText(String.format("%s km", new DecimalFormat().format(itemStatistic.getDistance())));
        textDate.setText(itemStatistic.getDate());
        goToDetail.setImageResource(R.drawable.gray_orientation);
        gasStation.setImageResource(StationManager.findStationImage(itemStatistic.getNameStation()));

        itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, GasStationInfo.class);
            intent.putExtra("itemStatistic", itemStatistic);
            context.startActivity(intent);
        });

    }

}
