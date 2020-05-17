package com.smartjaegers.checkfuel.managers;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.smartjaegers.checkfuel.models.ItemStatistic;

import java.util.List;
import java.util.stream.Collectors;

public class FilterManagerForStatistic {

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static List<ItemStatistic> findByVolume(List<ItemStatistic> itemStatisticList) {
        return itemStatisticList.stream().filter(item -> item.getVolumeFillReal() == item.getVolumeFillExpected()).collect(Collectors.toList());
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static List<ItemStatistic> findByFuelCompany(List<ItemStatistic> itemStatisticList, String fuelCompany) {
        return itemStatisticList.stream().filter(item -> item.getNameStation().equals(fuelCompany)).collect(Collectors.toList());
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static List<ItemStatistic> findByFuelType(List<ItemStatistic> itemStatisticList, String fuelType) {
        return itemStatisticList.stream().filter(item -> item.getTypeFuel().equals(fuelType)).collect(Collectors.toList());
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static List<ItemStatistic> findByQuality(List<ItemStatistic> itemStatisticList) {
        return itemStatisticList.stream().filter(item -> item.getDensity() < 1).collect(Collectors.toList());
        //TODO realise this method (now its hardcode)
    }


}
