package com.smartjaegers.checkfuel.managers;

import android.util.Log;

import com.smartjaegers.checkfuel.models.OnGetResult;
import com.smartjaegers.checkfuel.models.Refill;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class DatabaseManagerForRefill {

    private final static String TAG = "MYTAG------------------";

    private static DatabaseReference CHECK_FUEL_REFERENCE = FirebaseDatabase.getInstance().getReference();
    private static FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private static List<Refill> refills = new LinkedList<>();


    public static void writeRefill(double volumeFillReal, double volumeFillExpected, double density,
                                   String nameStation, String typeFuel, String date) {
        Refill refill = new Refill(volumeFillReal, volumeFillExpected, density,
                nameStation, typeFuel, date);
        CHECK_FUEL_REFERENCE.child(user.getUid()).child("Refill").push().setValue(refill);
    }

    public static void readRefill(final OnGetResult listener) {
        listener.onStart();
        ValueEventListener RefillListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                refills.clear();
                for (DataSnapshot keyNode : dataSnapshot.getChildren()) {
                    Refill refill = keyNode.getValue(Refill.class);
                    refills.add(refill);
                }
                Collections.reverse(refills);
                listener.onSuccess();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                listener.onFailure();
            }
        };
        CHECK_FUEL_REFERENCE.child(user.getUid()).child("Refill").addValueEventListener(RefillListener);
        Log.i(TAG, "loadPost:Ok");
    }

    public static List<Refill> getRefills() {
        for (int i = 0; i < refills.size(); i++) {
            Log.i("-----Refills-----", String.valueOf(refills.get(i).getDate()));
        }
        Log.i("-----Refills-----", "read refill is ok");
        Log.i("-----Refills-----", String.valueOf(refills.size()));

        return refills;
    }

}

