package com.smartjaegers.checkfuel.managers;

import com.smartjaegers.checkfuel.models.DayOfUse;
import com.smartjaegers.checkfuel.models.ItemStatistic;
import com.smartjaegers.checkfuel.models.Quality;
import com.smartjaegers.checkfuel.models.Refill;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class ItemStatisticManager {

    private static final SimpleDateFormat pattern = new SimpleDateFormat("yyyy-MM-dd", Locale.ROOT);

    public static List<ItemStatistic> createStatisticItem(List<Refill> refills, List<DayOfUse> daysOfUse, List<Quality> qualities) {
        List<ItemStatistic> itemStatisticList = new LinkedList<>();

        try {
            for (int position = 0; position < refills.size(); position++) {
                Date dateOfRefill = pattern.parse(refills.get(position).getDate());
                Date currentDate = Calendar.getInstance().getTime();
                String timeDriving = String.valueOf((currentDate.getTime() - dateOfRefill.getTime()) / (24 * 60 * 60 * 1000));
                Date dateNextRefill = null;
                if (position != 0) {
                    dateNextRefill = pattern.parse(refills.get(position - 1).getDate());
                    timeDriving = String.valueOf((dateNextRefill.getTime() - dateOfRefill.getTime()) / (24 * 60 * 60 * 1000));
                }

                double distance = calculateDistance(daysOfUse, dateOfRefill, dateNextRefill);
                double volume = calculateVolume(daysOfUse, dateOfRefill, dateNextRefill);
                Quality quality = getQualityInDate(qualities, dateOfRefill, dateNextRefill);

                itemStatisticList.add(new ItemStatistic(refills.get(position), distance, volume, timeDriving, quality.getDateOfTesting(), quality.getRate()));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return itemStatisticList;
    }

    private static double calculateDistance(List<DayOfUse> daysOfUse, Date refillDate, Date nextRefillDate) {
        double distance = 0;
        try {
            for (DayOfUse dayOfUse : daysOfUse) {
                Date date = pattern.parse(dayOfUse.getDate());
                assert date != null;
                if (nextRefillDate == null) {
                    if ((date.after(refillDate) || date.equals(refillDate))) {
                        distance += dayOfUse.getKmPerDay();
                    }
                } else {
                    if ((date.before(nextRefillDate)) && (date.after(refillDate) || date.equals(refillDate))) {
                        distance += dayOfUse.getKmPerDay();
                    }
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return distance;
    }

    private static double calculateVolume(List<DayOfUse> daysOfUse, Date refillDate, Date nextRefillDate) {
        double volume = 0;
        try {
            for (DayOfUse dayOfUse : daysOfUse) {
                Date date = pattern.parse(dayOfUse.getDate());
                assert date != null;
                if (nextRefillDate == null) {
                    if ((date.after(refillDate) || date.equals(refillDate))) {
                        volume += dayOfUse.getVolumePerDay();
                    }
                } else {
                    if ((date.before(nextRefillDate)) && (date.after(refillDate) || date.equals(refillDate))) {
                        volume += dayOfUse.getVolumePerDay();
                    }
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return volume;
    }

    private static Quality getQualityInDate(List<Quality> qualities, Date refillDate, Date nextRefillDate) {
        if (qualities == null) {
            return new Quality(0, 11111, "1111");
        }
        try {
            for (Quality quality : qualities) {
                Date date = pattern.parse(quality.getDateOfTesting());
                assert date != null;
                if (nextRefillDate == null) {
                    if ((date.after(refillDate) || date.equals(refillDate))) {
                        return quality;
                    }
                } else {
                    if ((date.before(nextRefillDate)) && (date.after(refillDate) || date.equals(refillDate))) {
                        return quality;
                    }
                }
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Quality(0, 11111, "1111");
    }
}
