package com.checkfuel.frontend;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.checkfuel.something.R;
import com.checkfuel.utils.ConnectToServer;
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

        DatabaseManager.writePost(0, 0, 0, 0, 0);
    }


    public void goToVolumeCompare(@NotNull View view) {
        String volume = ConnectToServer.getVolume();
        if (volume.equals("")) {
            Toast.makeText(this, "invalid connection, please connect later", Toast.LENGTH_LONG).show();

        } else {
            if (view.getId() == R.id.btncheckfuel) {
                Intent intent;

                if (Double.parseDouble(volume) >= Double.parseDouble(numberEditText.getText().toString())) {
                    intent = new Intent(this, VolumeCompareGood.class);
                } else {
                    intent = new Intent(this, VolumeCompareBad.class);
                }

                intent.putExtra("expectedVolume", numberEditText.getText().toString());
                intent.putExtra("realVolume", volume);

                startActivity(intent);
            }
        }
    }

    public void backToMain(@NotNull View view) {
        if (view.getId() == R.id.btnback) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

}


