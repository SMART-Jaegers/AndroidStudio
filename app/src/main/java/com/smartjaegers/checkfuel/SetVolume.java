package com.smartjaegers.checkfuel;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

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
        double volumeFill = 10;                 //TODO (hardcode), take date with bluetooth and create exeption when can't connect
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


