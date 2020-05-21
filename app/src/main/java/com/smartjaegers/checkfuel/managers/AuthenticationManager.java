package com.smartjaegers.checkfuel.managers;

import android.app.Activity;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.smartjaegers.checkfuel.models.OnGetResult;

public class AuthenticationManager extends Activity {
    private final static String TAG = "MYTAG------------------";

    private FirebaseAuth mAuth;

    public AuthenticationManager() {
        mAuth = FirebaseAuth.getInstance();
    }

    public boolean entryToDatabase() {
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser == null) {
            return false;
        }
        //ToDo realise access to this methods
        Log.d(TAG, currentUser.getEmail());
        return true;
    }

    public void logIn(String email, String password, OnGetResult listener) {
        listener.onStart();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Log.d(TAG, "logInWithEmail:success");
                        listener.onSuccess();
                    } else {
                        Log.w(TAG, "logInWithEmail:failure", task.getException());
                        listener.onFailure();
                    }
                });
    }

    public void createUser(final String userName, final String email, final String password, OnGetResult listener) {
        listener.onStart();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {

                    if (task.isSuccessful()) {
                        DatabaseManagerForUser.writeUser(email, userName, password);
                        Log.d(TAG, "createUserWithEmail:success");
                        listener.onSuccess();
                    } else {
                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                        listener.onFailure();
                    }
                });
    }

    public void signOut() {
        mAuth.signOut();
    }


}
