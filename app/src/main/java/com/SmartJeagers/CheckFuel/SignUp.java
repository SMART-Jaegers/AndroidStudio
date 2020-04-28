package com.SmartJeagers.CheckFuel;

import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.SmartJeagers.CheckFuel.utils.AuthenticationManager;
import com.checkfuel.something.R;

public class SignUp extends AppCompatActivity {
    EditText editUserName, editEmail, editPassword, editPasswordConfirm;
    Button buttonSignUP;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        editUserName = findViewById(R.id.editTextUsername);
        editEmail = findViewById(R.id.editTextEmailSignUp);
        editPassword = findViewById(R.id.editTextPassword);
        editPasswordConfirm = findViewById(R.id.editTextPasswordConfirm);
        buttonSignUP = findViewById(R.id.buttonSignUp);
        editPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
        editPasswordConfirm.setTransformationMethod(PasswordTransformationMethod.getInstance());
    }

    public void SignUp(View view) {
        String userName = editUserName.getText().toString();
        String email = editEmail.getText().toString();
        String password = editPassword.getText().toString();
        String passwordConfirm = editPasswordConfirm.getText().toString();
        if (notValidForm(userName, email, password, passwordConfirm)) {
            return;
        }

        AuthenticationManager authentication = new AuthenticationManager();
        authentication.createUser(email, password);
    }

    public boolean notValidForm(String userName, String email, String password, String passwordConfirm) {
        boolean valid = true;
        if (TextUtils.isEmpty(userName)) {
            Toast.makeText(this, "your username is empty", Toast.LENGTH_SHORT).show();
            valid = false;
        }
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "your email is empty", Toast.LENGTH_SHORT).show();
            valid = false;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "your password is empty", Toast.LENGTH_SHORT).show();
            valid = false;
        }
        if (!password.equals(passwordConfirm)) {
            Toast.makeText(this, "your password isn't correct", Toast.LENGTH_SHORT).show();
            valid = false;
        }
        return !valid;
    }

}
