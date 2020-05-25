package com.smartjaegers.checkfuel.activities;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;

import com.smartjaegers.checkfuel.R;
import com.smartjaegers.checkfuel.adapters.FiltersAdapter;
import com.smartjaegers.checkfuel.managers.UtilsManagerForStatistic;
import com.smartjaegers.checkfuel.models.SortType;

import java.util.ArrayList;
import java.util.List;


public class ChooseFiltersAndSort extends DialogFragment implements View.OnClickListener {

    private String nowSorting;

    ChooseFiltersAndSort(String nowSorting) {
        this.nowSorting = nowSorting;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.choose_filter_and_sort, null);
        setPosition();

        TextView textName = view.findViewById(R.id.textView13);
        TextView textSubName = view.findViewById(R.id.textView20);


        switch (nowSorting) {
            case "date_new_old":
                textName.setText("Date");
                textSubName.setText("from newest to last");
                break;
            case "date_old_new":
                textName.setText("Date");
                textSubName.setText("from last to newest");
                break;
            case "volume_descending":
                textName.setText("Volume");
                textSubName.setText("from biggest to smallest");
                break;
            case "volume_ascending":
                textName.setText("Volume");
                textSubName.setText("from smallest to biggest");
                break;
            case "efficiency_ascending":
                textName.setText("Efficiency");
                textSubName.setText("from biggest to smallest");
                break;
            case "efficiency_descending":
                textName.setText("Efficiency");
                textSubName.setText("from smallest to biggest");
                break;
        }

        List<String> filterNames = new ArrayList<>();
        filterNames.add("Gas station");
        filterNames.add("Type fuel");
        filterNames.add("Volume");
        filterNames.add("Density");
        List<String> filterDescriptions = new ArrayList<>();
        filterDescriptions.add(":Wog,Shell");
        filterDescriptions.add(":A95");
        filterDescriptions.add("full");
        filterDescriptions.add("good quality");

//        ListView listView = view.findViewById(R.id.existing_filters);
//        FiltersAdapter adapter = new FiltersAdapter(getDialog().getContext(), filterNames, filterDescriptions);
//        listView.setAdapter(adapter);

        view.findViewById(R.id.choose_filter).setOnClickListener(this);
        view.findViewById(R.id.choose_sort).setOnClickListener(this);
        view.findViewById(R.id.remove_filters).setOnClickListener(this);

        return view;
    }

    private void setPosition() {
        Window window = getDialog().getWindow();
        window.setGravity(Gravity.TOP | Gravity.RIGHT);
        WindowManager.LayoutParams params = window.getAttributes();
        params.x = 40;
        params.y = 100;
        window.setAttributes(params);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.choose_filter:
                intent.setClass(getActivity(), ChooseFilters.class);

                startActivity(intent);
                dismiss();
                break;
            case R.id.choose_sort:
                intent.setClass(getActivity(), SortBy.class);
                intent.putExtra("nowSorting", nowSorting);
                startActivity(intent);
                dismiss();
                break;
            case R.id.remove_filters:
                dismiss();
                break;
        }
    }

    @Nullable
    @Override
    public Context getContext() {
        return super.getContext();
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
    }

    @Override
    public void onCancel(@NonNull DialogInterface dialog) {
        super.onCancel(dialog);
    }

}
