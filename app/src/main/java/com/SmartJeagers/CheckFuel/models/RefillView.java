package com.SmartJeagers.CheckFuel.models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.checkfuel.something.R;

import java.text.DecimalFormat;

class RefillView extends RecyclerView.ViewHolder {

    private TextView textLiter;
    private TextView textKilometer;
    private TextView textDate;
    private ImageView gasStation;
    private ImageView goToDetail;

    public RefillView(ViewGroup parent, Context context) {
        super(LayoutInflater.from(context).inflate(R.layout.item_of_list_usage_statistic, parent, false));

        textLiter = itemView.findViewById(R.id.textLiter);
        textKilometer = itemView.findViewById(R.id.textKilometer);
        textDate = itemView.findViewById(R.id.textDate);
        gasStation = itemView.findViewById(R.id.imageView);
        goToDetail = itemView.findViewById(R.id.goCurrentUse);
    }

    public void bind(Refill refill) {
        textLiter.setText(new DecimalFormat().format(refill.getVolumeFillReal()));
        textKilometer.setText("0");
        textDate.setText(refill.getDate());
        gasStation.setImageResource(R.drawable.purple_okko);
        goToDetail.setImageResource(R.drawable.gray_orientation);
    }

}
