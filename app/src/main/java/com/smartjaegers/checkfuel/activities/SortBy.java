package com.smartjaegers.checkfuel.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.smartjaegers.checkfuel.R;

public class SortBy extends AppCompatActivity implements View.OnClickListener {

    private RelativeLayout dateNewOldLayout;
    private RelativeLayout dateOldNewLayout;
    private RelativeLayout volumeDescendingLayout;
    private RelativeLayout volumeAscendingLayout;
    private RelativeLayout efficiencyAscendingLayout;
    private RelativeLayout efficiencyDescendingLayout;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort_by);

        dateNewOldLayout = findViewById(R.id.date_new_old);
        dateOldNewLayout = findViewById(R.id.date_old_new);
        volumeDescendingLayout = findViewById(R.id.volume_descending);
        volumeAscendingLayout = findViewById(R.id.volume_ascending);
        efficiencyAscendingLayout = findViewById(R.id.efficiency_ascending);
        efficiencyDescendingLayout = findViewById(R.id.efficiency_descending);

        dateNewOldLayout.setOnClickListener(this);
        dateOldNewLayout.setOnClickListener(this);
        volumeDescendingLayout.setOnClickListener(this);
        volumeAscendingLayout.setOnClickListener(this);
        efficiencyAscendingLayout.setOnClickListener(this);
        efficiencyDescendingLayout.setOnClickListener(this);

        String nowSorting = getIntent().getStringExtra("nowSorting");
        assert nowSorting != null;
        changeLayoutColor(nowSorting);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.date_new_old:
                intent.setClass(this, UsageStatistic.class);
                intent.putExtra("nowSorting", "date_new_old");
                break;
            case R.id.date_old_new:
                intent.setClass(this, UsageStatistic.class);
                intent.putExtra("nowSorting", "date_old_new");
                break;
            case R.id.volume_descending:
                intent.setClass(this, UsageStatistic.class);
                intent.putExtra("nowSorting", "volume_descending");
                break;
            case R.id.volume_ascending:
                intent.setClass(this, UsageStatistic.class);
                intent.putExtra("nowSorting", "volume_ascending");
                break;
            case R.id.efficiency_ascending:
                intent.setClass(this, UsageStatistic.class);
                intent.putExtra("nowSorting", "efficiency_ascending");
                break;
            case R.id.efficiency_descending:
                intent.setClass(this, UsageStatistic.class);
                intent.putExtra("nowSorting", "efficiency_descending");
                break;
        }
        startActivity(intent);
    }

    private void changeLayoutColor(String layoutName) {
        switch (layoutName) {
            case "date_new_old":
                dateNewOldLayout.setBackgroundColor(getResources().getColor(R.color.colorPurpleWhite));
                break;
            case "date_old_new":
                dateOldNewLayout.setBackgroundColor(getResources().getColor(R.color.colorPurpleWhite));
                break;
            case "volume_descending":
                volumeDescendingLayout.setBackgroundColor(getResources().getColor(R.color.colorPurpleWhite));
                break;
            case "volume_ascending":
                volumeAscendingLayout.setBackgroundColor(getResources().getColor(R.color.colorPurpleWhite));
                break;
            case "efficiency_ascending":
                efficiencyAscendingLayout.setBackgroundColor(getResources().getColor(R.color.colorPurpleWhite));
                break;
            case "efficiency_descending":
                efficiencyDescendingLayout.setBackgroundColor(getResources().getColor(R.color.colorPurpleWhite));
                break;
            default:
                break;
        }

    }

    public void backToStatistic(View view) {
        super.onBackPressed();
    }
}
