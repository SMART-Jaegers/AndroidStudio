package com.smartjaegers.checkfuel.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.smartjaegers.checkfuel.R;
import com.smartjaegers.checkfuel.adapters.FiltersAdapter;

import java.util.ArrayList;
import java.util.List;

public class Test2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);
        List<String> filterNames = new ArrayList<>();
        filterNames.add("werwer");
        filterNames.add("wer12");
        filterNames.add("we34er");
        List<String> filterDescriptions = new ArrayList<>();
        filterDescriptions.add("wwww");
        filterDescriptions.add("ww11");
        filterDescriptions.add("ww44");
        ArrayAdapter<String> arrayAdapter=new  ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,filterNames);
        ListView listView = findViewById(R.id.existing_filters);
        FiltersAdapter adapter = new FiltersAdapter(this, filterNames, filterDescriptions);
        listView.setAdapter(adapter);
    }
}
