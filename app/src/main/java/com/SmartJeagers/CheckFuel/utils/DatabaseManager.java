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

public class DatabaseManager {
    private final static String TAG = "MYTAG------------------";
    private static DatabaseReference CHECK_FUEL_REFERENCE = FirebaseDatabase.getInstance().getReference("data");
    private static FirebaseAuth mAuth;
    private static Refill refill = null;
    private static FirebaseUser user = mAuth.getInstance().getCurrentUser();

    public static void writePost(double temperature, double weight, double volumeFill, double volumeRemaining, double distance) {
        Refill refill = new Refill();//TODO add data and fix code
        CHECK_FUEL_REFERENCE.child(user.getUid()).setValue(refill);
    }

    public static void readPost() {


        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                refill = dataSnapshot.getValue(Refill.class);

                Log.i(TAG, "loadPost:Ok");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                //TODO something
            }
        };

        CHECK_FUEL_REFERENCE.child(user.getUid()).addValueEventListener(postListener);
    }

    public static Refill getRefill() {
        return refill;
    }
}
