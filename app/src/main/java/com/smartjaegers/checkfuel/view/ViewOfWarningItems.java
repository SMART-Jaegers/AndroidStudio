package com.smartjaegers.checkfuel.view;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.smartjaegers.checkfuel.R;
import com.smartjaegers.checkfuel.activities.GasStationInfo;
import com.smartjaegers.checkfuel.managers.StationManager;
import com.smartjaegers.checkfuel.models.ItemStatistic;
import com.smartjaegers.checkfuel.models.Problem;

import java.text.DecimalFormat;

public class ViewOfWarningItems extends RecyclerView.ViewHolder {
    private Context context;
    private TextView textName;
    private TextView textDate;
    private ImageView w;


    public ViewOfWarningItems(ViewGroup parent, Context context) {
        super(LayoutInflater.from(context).inflate(R.layout.item_of_list_warning, parent, false));
        this.context = context;
        textName = itemView.findViewById(R.id.textName);
        textDate = itemView.findViewById(R.id.date);
    }

    public void bind(Problem problem) {
        textName.setText((problem.getProblemId().toString()));
//        itemView.setOnClickListener(v -> {
//            Intent intent = new Intent(context, GasStationInfo.class);
//            intent.putExtra("itemStatistic", itemStatistic);
//            context.startActivity(intent);
//        });

    }

}
