package com.smartjaegers.checkfuel.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.smartjaegers.checkfuel.R;
import com.smartjaegers.checkfuel.adapters.ExpandableListAdapterChooseFuel;
import com.smartjaegers.checkfuel.managers.TextReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ChooseFilters extends AppCompatActivity {

    private ExpandableListView listView;
    private ExpandableListAdapter listAdapter;
    private List<String> listHeader;
    private HashMap<String, List<String>> listItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_filters);

        initDate();

        listView = findViewById(R.id.filters_list);
        listAdapter = new ExpandableListAdapterChooseFuel(this, listHeader, listItem);
        listView.setAdapter(listAdapter);
//        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
//            @Override
//            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
//                final String selected = (String) listAdapter.getChild(groupPosition, childPosition);
//
//                switch (selected) {
//                    case "Okko":
//                        TextView textView = v.findViewById(R.id.checkedTextView);
//                        textView.setBackgroundColor(getResources().getColor(R.color.colorPurpleWhite));
//                        break;
//                    case "Wog":
//                        v.setBackgroundColor(getResources().getColor(R.color.colorPurpleWhite));
//                        break;
//                }
////                parent.getChildAt(childPosition).setBackgroundColor(getResources().getColor(R.color.colorPurpleWhite));
////                paent.getSelectedView().setBackgroundColor(getResources().getColor(R.color.colorPurpleWhite));
//                //display it or do something with it
//                return false;
//            }
//        });

    }

    private void initDate() {

        listItem = new HashMap<>();
        TextReader textReader = new TextReader(this);

        listHeader = new ArrayList<>();
        listHeader.add("Gas stations");
        listHeader.add("Type of Fuel");
        listHeader.add("Volume");
        listHeader.add("Density");

        List<String> stations = textReader.getStationName();

        List<String> fuelTypes = new ArrayList<>();
        fuelTypes.addAll(textReader.getFuelTypes(stations.get(0)));
        fuelTypes.addAll(textReader.getFuelTypes(stations.get(1)));
        fuelTypes.addAll(textReader.getFuelTypes(stations.get(2)));
        fuelTypes.addAll(textReader.getFuelTypes(stations.get(3)));
        fuelTypes.addAll(textReader.getFuelTypes(stations.get(4)));
        fuelTypes.addAll(textReader.getFuelTypes(stations.get(5)));

        List<String> volume = new ArrayList<>();
        volume.add("Good");
        volume.add("Bad");

        List<String> density = new ArrayList<>();
        density.add("Good");
        density.add("Bad");


        listItem.put(listHeader.get(0), stations);
        listItem.put(listHeader.get(1), fuelTypes);
        listItem.put(listHeader.get(2), volume);
        listItem.put(listHeader.get(3), density);


    }

}
