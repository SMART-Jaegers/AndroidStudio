package com.smartjaegers.checkfuel.managers;

import com.smartjaegers.checkfuel.R;

public class StationManager {

    public static int findStationImage(String stationName) {
        switch (stationName) {
            case "Okko":
                return R.drawable.purple_okko;
            case "Wog":
                return R.drawable.wog_purple;
            case "UkrNafta":
                return R.drawable.ukrnafta_purple;
            case "Shell":
                return R.drawable.shell_purple;
            case "Socar":
                return R.drawable.logo_socar;
            case "UPG":
                return R.drawable.logo_upg_purple;
            default:
                return R.drawable.bad_fuel;
        }
    }
}
