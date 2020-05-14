package com.SmartJeagers.CheckFuel.utils;

import android.util.Log;

import com.SmartJeagers.CheckFuel.models.DayOfUse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DatabaseManagerForDayOfUse {

    private final static String TAG = "MYTAG------------------";
    private static DatabaseReference CHECK_FUEL_REFERENCE = FirebaseDatabase.getInstance().getReference();
    private static FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private static List<DayOfUse> daysOfUse = new ArrayList<>();

    public static void writeDataOfUse(double kmPerDay, double volumePerDay, String day) {
        DayOfUse dayOfUse = new DayOfUse(kmPerDay, volumePerDay, day);
        CHECK_FUEL_REFERENCE.child(user.getUid()).child("DataOfUse").push().setValue(dayOfUse);
    }

    public static boolean readDayOfUse() {
        ValueEventListener RefillListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                daysOfUse.clear();
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

        CHECK_FUEL_REFERENCE.child(user.getUid()).child("DataOfUse").addValueEventListener(RefillListener);
        Log.i(TAG, "loadPost:Ok");
        return true;
    }

    public static List<DayOfUse> getDaysOfUse() {
        return daysOfUse;
    }
}