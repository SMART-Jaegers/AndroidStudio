package com.smartjaegers.checkfuel.managers;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.smartjaegers.checkfuel.models.ItemStatistic;
import com.smartjaegers.checkfuel.models.SortType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class UtilsManagerForStatistic {
    private static final SimpleDateFormat pattern = new SimpleDateFormat("yyyy-MM-dd", Locale.ROOT);

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void sortByVolume(List<ItemStatistic> itemStatisticList, SortType sortType) {
        Comparator<ItemStatistic> volumeComparator = (o1, o2) -> (int) (o1.getVolumeFillReal() - o2.getVolumeFillReal()) * 100;
        Collections.sort(itemStatisticList, sortType == SortType.ASCENDING ? volumeComparator : volumeComparator.reversed());
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void sortByDate(List<ItemStatistic> itemStatisticList, SortType sortType) {
        Comparator<ItemStatistic> dateComparator = (o1, o2) -> {
            try {
                Date date1 = pattern.parse(o1.getDate());
                Date date2 = pattern.parse(o2.getDate());

                assert date1 != null;

                return date1.compareTo(date2);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return 0;
        };
        Collections.sort(itemStatisticList, sortType == SortType.ASCENDING ? dateComparator : dateComparator.reversed());
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void sortByDistance(List<ItemStatistic> itemStatisticList, SortType sortType) {
        Comparator<ItemStatistic> distanceComparator = (o1, o2) -> (int) (o1.getVolume() / o1.getDistance() - o2.getVolume() / o2.getDistance()) * 10000;
        Collections.sort(itemStatisticList, sortType == SortType.ASCENDING ? distanceComparator : distanceComparator.reversed());
    }

}
