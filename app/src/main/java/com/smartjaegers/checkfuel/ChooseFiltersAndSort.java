package com.smartjaegers.checkfuel;

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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;

import com.smartjaegers.checkfuel.adapters.FiltersAdapter;

import java.util.ArrayList;
import java.util.List;


public class ChooseFiltersAndSort extends DialogFragment implements View.OnClickListener {
    public ChooseFiltersAndSort(String nowSorting) {
        this.nowSorting = nowSorting;
    }

    private String nowSorting;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.choose_filter_and_sort, null);
        setPosition();


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

        ListView listView = view.findViewById(R.id.existing_filters);
        FiltersAdapter adapter = new FiltersAdapter(getDialog().getContext(), filterNames, filterDescriptions);
        listView.setAdapter(adapter);

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
                intent.setClass(getActivity(), MainActivity.class);

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
