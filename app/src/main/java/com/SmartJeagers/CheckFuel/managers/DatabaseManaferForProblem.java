package com.SmartJeagers.CheckFuel.managers;

import android.util.Log;

import com.SmartJeagers.CheckFuel.models.Problem;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.LinkedList;
import java.util.List;

public class DatabaseManaferForProblem {

    private final static String TAG = "MYTAG------------------";

    private static DatabaseReference CHECK_FUEL_REFERENCE = FirebaseDatabase.getInstance().getReference();
    private static FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private static List<Problem> problems = new LinkedList<>();


    public static void writeProblem(int problemId) {
        Problem problem = new Problem(problemId);
        CHECK_FUEL_REFERENCE.child(user.getUid()).child("Problems").push().setValue(problem);
    }

    public static void readProblems() {
        ValueEventListener RefillListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                problems.clear();
                for (DataSnapshot keyNode : dataSnapshot.getChildren()) {
                    Problem problem = keyNode.getValue(Problem.class);
                    problems.add(problem);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                //TODO something
            }
        };
        CHECK_FUEL_REFERENCE.child(user.getUid()).child("Problems").addValueEventListener(RefillListener);
        Log.i(TAG, "loadPost:Ok");
    }

    public static List<Problem> getProblem() {
        return problems;
    }
}
