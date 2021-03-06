package com.smartjaegers.checkfuel.managers;

import android.content.Context;
import android.util.Log;

import com.smartjaegers.checkfuel.R;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;

public class TextReader {
    private static final String TAG = "MYTAG------------------";
    private Context context;

    public TextReader(Context context) {
        this.context = context;
    }

    public ArrayList<String> getFuelTypes(String nameStation) {
        ArrayList<String> list = new ArrayList<>();
        try {
            XmlPullParser parser = context.getResources().getXml(R.xml.check_fuel);
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
            XmlPullParser parser = context.getResources().getXml(R.xml.check_fuel);
            while (parser.getEventType() != XmlPullParser.END_DOCUMENT) {
                if (parser.getEventType() == XmlPullParser.START_TAG && parser.getName().equals(nameStation)) {
                    parser.nextTag();
                    if (parser.getEventType() == XmlPullParser.START_TAG && parser.getName().equals("density")) {
                        for (int i = 0; i < parser.getAttributeCount(); i++) {
                            list.add(parser.getAttributeValue(i));
                        }
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

    public ArrayList<String> getStationName() {
        ArrayList<String> list = new ArrayList<>();
        try {
            XmlPullParser parser = context.getResources().getXml(R.xml.check_fuel);

            while (parser.getEventType() != XmlPullParser.END_DOCUMENT) {
                if (parser.getEventType() == XmlPullParser.START_TAG) {
                    if (parser.getName().equals("density")) {
                        parser.next();
                    } else {
                        if (!parser.getName().equals("gasStations")) {
                            list.add(parser.getName());
                        }
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


}
