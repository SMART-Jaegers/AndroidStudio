package com.checkfuel.frontend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.checkfuel.something.R;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;

public class ChooseTheGasStation extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MYTAG------------------";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_the_gas_station);

        ImageView okko = findViewById(R.id.okko);
        ImageView wog = findViewById(R.id.wog);
        FrameLayout ukrNafta = findViewById(R.id.ukrNafta);
        LinearLayout shell = findViewById(R.id.shell);
        FrameLayout socar = findViewById(R.id.socar);
        FrameLayout upg = findViewById(R.id.upg);

        okko.setOnClickListener(this);
        wog.setOnClickListener(this);
        ukrNafta.setOnClickListener(this);
        shell.setOnClickListener(this);
        socar.setOnClickListener(this);
        upg.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        ArrayList<String> fuelTypes = new ArrayList<>();
        ArrayList<String> fuelDensities = new ArrayList<>();
        switch (v.getId()) {
            case R.id.okko:
                fuelTypes = getFuelTypes("Okko");
                fuelDensities = getFuelDensities("Okko");
                break;
            case R.id.wog:
                fuelTypes = getFuelTypes("Wog");
                fuelDensities = getFuelDensities("Wog");
                break;
            case R.id.ukrNafta:
                fuelTypes = getFuelTypes("UkrNafta");
                fuelDensities = getFuelDensities("UkrNafta");
                break;
            case R.id.shell:
                fuelTypes = getFuelTypes("Shell");
                fuelDensities = getFuelDensities("Shell");
                break;
            case R.id.socar:
                fuelTypes = getFuelTypes("Socar");
                fuelDensities = getFuelDensities("Socar");
                break;
            case R.id.upg:
                fuelTypes = getFuelTypes("UPG");
                fuelDensities = getFuelDensities("UPG");
                break;
            default:
                Toast.makeText(this, "this gas Station don't exist", Toast.LENGTH_SHORT).show();
                //ToDo remove yhis and do some another exeption

        }

        Intent intent = new Intent(this, GasStation.class);
        intent.putExtra("fuelTypes", fuelTypes);
        intent.putExtra("fuelDensities", fuelDensities);
        startActivity(intent);

    }

    public ArrayList<String> getFuelTypes(String nameStation) {
        ArrayList<String> list = new ArrayList<>();
        list.add(nameStation);
        try {
            XmlPullParser parser = getResources().getXml(R.xml.check_fuel);

            while (parser.getEventType() != XmlPullParser.END_DOCUMENT) {
                if (parser.getEventType() == XmlPullParser.START_TAG && parser.getName().equals(nameStation)) {
                    for (int i = 0; i < parser.getAttributeCount(); i++) {
                        list.add(parser.getAttributeValue(i));
                    }
                }
                parser.next();
            }
        } catch (Throwable t) {
            Log.i(TAG, "eror");
        }
        for (int i = 0; i < list.size(); i++) {
            Log.i(TAG, list.get(i));
        }
        return list;
    }

    public ArrayList<String> getFuelDensities(String nameStation) {
        ArrayList<String> list = new ArrayList<>();
        try {
            XmlPullParser parser = getResources().getXml(R.xml.check_fuel);

            while (parser.getEventType() != XmlPullParser.END_DOCUMENT) {
                if (parser.getEventType() == XmlPullParser.START_TAG && parser.getName().equals(nameStation)) {
                    parser.nextTag();
                    if (parser.getEventType() == XmlPullParser.START_TAG && parser.getName().equals("density"))
                        for (int i = 0; i < parser.getAttributeCount(); i++) {
                            list.add(parser.getAttributeValue(i));
                        }
                }
                parser.next();
            }
        } catch (Throwable t) {
            Log.i(TAG, "eror");
        }
        for (int i = 0; i < list.size(); i++) {
            Log.i(TAG, list.get(i));
        }
        return list;
    }


    public void backToMain(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}
