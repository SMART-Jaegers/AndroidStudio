package com.SmartJeagers.CheckFuel.utils;

import android.util.Log;

import com.SmartJeagers.CheckFuel.models.DayOfUse;
import com.SmartJeagers.CheckFuel.models.Refill;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {

    private final static String TAG = "MYTAG------------------";

    private static DatabaseReference CHECK_FUEL_REFERENCE = FirebaseDatabase.getInstance().getReference();
    private static FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private static List<Refill> refils = new ArrayList<>();
    private static List<DayOfUse> daysOfUse = new ArrayList<>();

    public static void writeRefill(double volumeFillReal, double volumeFillExpected, double density,
                                   boolean qualityOfFuel, String nameStation,
                                   String typeFuel, String date) {
        Refill refill = new Refill(volumeFillReal, volumeFillExpected, density, qualityOfFuel,
                nameStation, typeFuel, date);
        CHECK_FUEL_REFERENCE.child(user.getUid()).push().setValue(refill);
    }

    public static void readRefill() {
        ValueEventListener RefillListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot keyNode : dataSnapshot.getChildren()) {
                    Refill refill = keyNode.getValue(Refill.class);
                    refils.add(refill);
                    Log.i(TAG, refill.getDate());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                //TODO something
            }
        };
        CHECK_FUEL_REFERENCE.child(user.getUid()).addValueEventListener(RefillListener);
        Log.i(TAG, "loadPost:Ok");
    }

    public static List<Refill> getRefils() {
        for (Refill iterator : refils) {
//            Log.i(TAG, iterator.getDate());
        }
        return refils;
    }

    public static void writeDataOfUse(double kmPerDay, double volumePerDay, String day) {
        DayOfUse dayOfUse = new DayOfUse(kmPerDay, volumePerDay, day);
        CHECK_FUEL_REFERENCE.child(user.getUid()).push().setValue(dayOfUse);
    }

    public static List<DayOfUse> readDayOfUse() {
        ValueEventListener RefillListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot keyNode : dataSnapshot.getChildren()) {
                    DayOfUse dayOfUse = keyNode.getValue(DayOfUse.class);
                    daysOfUse.add(dayOfUse);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                //TODO something
            }
        };

        CHECK_FUEL_REFERENCE.child(user.getUid()).addValueEventListener(RefillListener);
        Log.i(TAG, "loadPost:Ok");
        return daysOfUse;
    }

    public static List<DayOfUse> getDaysOfUse() {
        return daysOfUse;
    }
}
