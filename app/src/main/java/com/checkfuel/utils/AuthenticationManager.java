package com.checkfuel.utils;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.Executor;

public class AuthenticationManager extends Activity {
    private final static String TAG = "MYTAG------------------";

    private FirebaseAuth mAuth;

    public AuthenticationManager() {
        mAuth = FirebaseAuth.getInstance();
    }

    public void entryToDatabase() {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            //TODO create intent to sign in/up
            return;
        }
        //ToDo realise access to this methods

    }


    public void signIn(String email, String password) {
        if (notValidateForm(email, password)) {
            Log.d(TAG, "signInWithEmail:failure incorrect password or email");
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithEmail:success");
                        } else {
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                        }
                    }
                });
    }

    public void createUser(String email, String password) {
        if (notValidateForm(email, password)) {
            Log.d(TAG, "createUserWithEmail:failure incorrect password or email");
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "createUserWithEmail:success");
                        } else {
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                        }
                    }
                });
    }

    private boolean notValidateForm(String email, String password) {
        boolean valid = true;

        if (TextUtils.isEmpty(email)) {
            valid = false;
        }
        if (TextUtils.isEmpty(password)) {
            valid = false;
        }
        return !valid;
    }

}
