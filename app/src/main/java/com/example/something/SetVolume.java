package com.example.something;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
import android.view.View;
import android.widget.EditText;

import org.jetbrains.annotations.NotNull;

public class SetVolume extends AppCompatActivity {
    EditText numberEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_volume2);
        numberEditText = (EditText) findViewById(R.id.editText);
        numberEditText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
    }
    public void backtomain(@NotNull View v) {
        switch (v.getId()) {
            case R.id.btnback:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
    public void gotovolumecompare(@NotNull View v) {
        switch (v.getId()) {
            case R.id.btncheckfuel:
                Intent intent = new Intent(this, VolumeCompare.class);
                intent.putExtra("volume",numberEditText.getText().toString());
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}




