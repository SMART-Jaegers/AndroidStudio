package com.SmartJeagers.CheckFuel.utils;

import android.util.Log;

import com.SmartJeagers.CheckFuel.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DatabaseManagerForUser {
    private final static String TAG = "MYTAG------------------";
    private static DatabaseReference CHECK_FUEL_REFERENCE = FirebaseDatabase.getInstance().getReference();
    private static FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private static User databaseUser;

    public static void writeUser(String email, String userName, String password) {
        User databaseUser = new User(email, userName, password);
        CHECK_FUEL_REFERENCE.child(user.getUid()).child("User").setValue(databaseUser);
    }

    public static void readUser() {
        ValueEventListener RefillListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                databaseUser = dataSnapshot.getValue(User.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                //TODO something
            }
        };

        CHECK_FUEL_REFERENCE.child(user.getUid()).child("User").addValueEventListener(RefillListener);
        Log.i(TAG, "loadPost:Ok");
    }

    public static User getUser() {
        return databaseUser;
    }

}
