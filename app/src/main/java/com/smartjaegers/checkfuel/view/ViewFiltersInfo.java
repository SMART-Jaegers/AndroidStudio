package com.smartjaegers.checkfuel.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.smartjaegers.checkfuel.R;

public class ViewFiltersInfo extends RecyclerView.ViewHolder {
    private TextView filterNameText;
    private TextView filterDescriptionText;
    private Context context;


    public ViewFiltersInfo(ViewGroup parent, Context context) {
        super(LayoutInflater.from(context).inflate(R.layout.item_of_list_usage_statistic, parent, false));
        this.context = context;
        filterNameText = itemView.findViewById(R.id.filter_name);
        filterDescriptionText = itemView.findViewById(R.id.filter_description);
    }

    public void bind(String filterName, String filterDescription) {
        filterNameText.setText("111");
        filterDescriptionText.setText("233");
    }
}
