package com.SmartJeagers.CheckFuel.utils;

import android.util.Log;

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

public class DatabaseManagerForRefill {

    private final static String TAG = "MYTAG------------------";

    private static DatabaseReference CHECK_FUEL_REFERENCE = FirebaseDatabase.getInstance().getReference();
    private static FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private static List<Refill> refils = new ArrayList<>();


    public static void writeRefill(double volumeFillReal, double volumeFillExpected, double density,
                                   boolean qualityOfFuel, String nameStation,
                                   String typeFuel, String date) {
        Refill refill = new Refill(volumeFillReal, volumeFillExpected, density, qualityOfFuel,
                nameStation, typeFuel, date);
        CHECK_FUEL_REFERENCE.child(user.getUid()).child("Refill").push().setValue(refill);
    }

    public static void readRefill() {
        ValueEventListener RefillListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot keyNode : dataSnapshot.getChildren()) {
                    Refill refill = keyNode.getValue(Refill.class);
                    refils.add(refill);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                //TODO something
            }
        };
        CHECK_FUEL_REFERENCE.child(user.getUid()).child("Refill").addValueEventListener(RefillListener);
        Log.i(TAG, "loadPost:Ok");
    }

    public static List<Refill> getRefils() {
        return refils;
    }
}

