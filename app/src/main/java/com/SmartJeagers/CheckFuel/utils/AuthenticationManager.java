package com.SmartJeagers.CheckFuel.utils;

import android.app.Activity;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

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

    public void logIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "logInWithEmail:success");
                        } else {
                            Log.w(TAG, "logInWithEmail:failure", task.getException());
                        }
                    }
                });
    }

    public void createUser(final String userName, final String email, final String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            DatabaseManagerForUser.writeUser(email, userName, password);

                            Log.d(TAG, "createUserWithEmail:success");
                        } else {
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                        }
                    }
                });
    }

    public void signOut() {
        mAuth.signOut();
    }


}
