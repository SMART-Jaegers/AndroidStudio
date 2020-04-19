package com.checkfuel.frontend;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.checkfuel.models.Post;
import com.checkfuel.something.R;
import com.checkfuel.utils.DatabaseManager;

import org.jetbrains.annotations.NotNull;

public class SetVolume extends AppCompatActivity {
    private EditText numberEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_volume);

        numberEditText = findViewById(R.id.editText);
        numberEditText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
    }

    public void goToVolumeCompare(@NotNull View view) {
        Post post = DatabaseManager.getPost();

        if (post == null) {
            Toast toast = Toast.makeText(this, "Failed to connect to database, \n please check the internet connection or sign in in your user account", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            return;
        }
        double volumeFill = post.getVolumeFill();
        double volumeExpected = Double.parseDouble(numberEditText.getText().toString());
        if (view.getId() == R.id.btncheckfuel) {
            Intent intent = new Intent();

            if (volumeFill >= volumeExpected) {
                intent.setClass(this, VolumeCompareGood.class);
            } else {
                intent.setClass(this, VolumeCompareBad.class);
            }

            intent.putExtra("expectedVolume", volumeExpected);
            intent.putExtra("realVolume", volumeFill);
            startActivity(intent);
        }
    }

    public void backToMain(@NotNull View view) {
        finish();
    }

}


