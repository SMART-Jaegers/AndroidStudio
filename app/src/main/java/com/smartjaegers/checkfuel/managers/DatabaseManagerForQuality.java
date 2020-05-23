package com.smartjaegers.checkfuel.managers;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.smartjaegers.checkfuel.models.OnGetResult;
import com.smartjaegers.checkfuel.models.Quality;

import java.util.LinkedList;
import java.util.List;

public class DatabaseManagerForQuality {

    private final static String TAG = "MYTAG------------------";

    private static DatabaseReference CHECK_FUEL_REFERENCE = FirebaseDatabase.getInstance().getReference();
    private static FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private static List<Quality> qualities = new LinkedList<>();
    private static String key = null;


    public static void writeQuality(double rate, int numberOfUse) {
        Quality quality = new Quality(rate, numberOfUse);
        CHECK_FUEL_REFERENCE.child(user.getUid()).child("Quality").push().setValue(quality);
    }

    public static void readQuality(final OnGetResult listener) {
        listener.onStart();
        ValueEventListener RefillListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                qualities.clear();
                key = dataSnapshot.getKey();
                for (DataSnapshot keyNode : dataSnapshot.getChildren()) {
                    Quality quality = keyNode.getValue(Quality.class);
                    qualities.add(quality);
                }
                listener.onSuccess();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                listener.onFailure();
            }
        };
        CHECK_FUEL_REFERENCE.child(user.getUid()).child("Quality").addValueEventListener(RefillListener);
        Log.i(TAG, "loadPost:Ok");
    }

    public static List<Quality> getQualities() {
        for (int i = 0; i < qualities.size(); i++) {
            Log.i("-----Refills-----", String.valueOf(qualities.get(i).getRate()));
        }
        Log.i("-----Refills-----", "read refill is ok");
        Log.i("-----Refills-----", String.valueOf(qualities.size()));

        return qualities;
    }

    public static String getKey() {
        return key;
    }

}

