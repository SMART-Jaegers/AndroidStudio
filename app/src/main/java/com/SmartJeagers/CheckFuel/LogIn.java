package com.SmartJeagers.CheckFuel;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.SmartJeagers.CheckFuel.managers.AuthenticationManager;
import com.checkfuel.something.R;

public class LogIn extends AppCompatActivity {
    EditText editEmail, editPassword;
    Button buttonSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        editEmail = findViewById(R.id.editTextEmailSignIn);
        editPassword = findViewById(R.id.editTextPassword);
        buttonSignIn = findViewById(R.id.button_log_in);
        editPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
    }

    public void SignIn(View view) {
        String email = editEmail.getText().toString();
        String password = editPassword.getText().toString();
        if (notValidForm(email, password)) {
            return;
        }
        AuthenticationManager authentication = new AuthenticationManager();
        authentication.logIn(email, password);

        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public boolean notValidForm(String email, String password) {
        boolean valid = true;

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "your email is empty", Toast.LENGTH_SHORT).show();
            valid = false;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "your password is empty", Toast.LENGTH_SHORT).show();
            valid = false;
        }
        return !valid;
    }

    public void goToSignUp(View view) {
        Intent intent = new Intent(this, SignUp.class);
        finish();
        startActivity(intent);
    }
}
