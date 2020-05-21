package com.smartjaegers.checkfuel.activities;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.smartjaegers.checkfuel.R;
import com.smartjaegers.checkfuel.managers.WarningManager;
import com.smartjaegers.checkfuel.models.Problem;

import java.util.LinkedList;
import java.util.List;


public class Warning extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warning);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onStart() {
        super.onStart();

        List<Problem> problems = new LinkedList<>();
//        problems.add(new Problem(12));

        if (problems.size() == 0) {
            Toast.makeText(this, "No problems was found", Toast.LENGTH_SHORT).show();
        }

        RecyclerView recyclerView = findViewById(R.id.warningView);
        WarningManager.setConfig(recyclerView, Warning.this, problems);
    }

    public void backToMain(View view) {
        finish();
    }
}
