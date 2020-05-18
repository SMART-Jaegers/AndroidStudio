package com.smartjaegers.checkfuel.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.smartjaegers.checkfuel.R;

import java.util.List;


public class FiltersAdapter extends ArrayAdapter<String> {
    private List<String> filterNames;
    private List<String> filterDescriptions;
    private Context context;

    public FiltersAdapter(@NonNull Context context, List<String> filterNames, List<String> filterDescriptions) {
        super(context, android.R.layout.simple_list_item_1,filterNames);
        this.context = context;
        this.filterNames = filterNames;
        this.filterDescriptions = filterDescriptions;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.item_of_choose_filter, parent, false);
        TextView filterNameView = rowView.findViewById(R.id.filter_name);
        TextView filterDescriptionView = rowView.findViewById(R.id.filter_description);

        filterNameView.setText(filterNames.get(position));
        filterDescriptionView.setText(filterDescriptions.get(position));

        return rowView;
    }
}

