package com.smartjaegers.checkfuel.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.smartjaegers.checkfuel.R;
import com.smartjaegers.checkfuel.managers.StationManager;
import com.smartjaegers.checkfuel.models.ItemStatistic;


public class GasStationInfo extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gas_station_info);

        Bundle arguments = getIntent().getExtras();
        assert arguments != null;
        ItemStatistic itemStatistic = arguments.getParcelable("itemStatistic");
        assert itemStatistic != null;

        ImageView stationImage = findViewById(R.id.StationImage);
        TextView stationNameText = findViewById(R.id.StationName);
        TextView fuelTypeText = findViewById(R.id.type_fuel);
        TextView stationDistance = findViewById(R.id.distance);
        TextView stationVolume = findViewById(R.id.volume_driving);
        TextView stationTimeDriving = findViewById(R.id.time_driving);
        TextView dateText = findViewById(R.id.Date);
        TextView volumeComparisonText = findViewById(R.id.volume_expected_vs_real);
        TextView densityText = findViewById(R.id.density);

        stationImage.setImageResource(StationManager.findStationImage(itemStatistic.getNameStation()));
        stationNameText.setText(itemStatistic.getNameStation());
        fuelTypeText.setText(itemStatistic.getTypeFuel());
        stationDistance.setText(String.format("%s km", itemStatistic.getDistance()));
        stationVolume.setText(String.format("%s L", itemStatistic.getVolume()));
        stationTimeDriving.setText(String.format("%s days", itemStatistic.getTimeDriving()));
        dateText.setText(itemStatistic.getDate());
        volumeComparisonText.setText(String.format("%sL/%sL", itemStatistic.getVolumeFillReal(), itemStatistic.getVolumeFillExpected()));
        densityText.setText(String.format("%s kg/m3", itemStatistic.getDistance()));
    }

    public void backToUsageStatistic(View view) {
        Intent intent = new Intent(this, UsageStatistic.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void backToMain(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

}
