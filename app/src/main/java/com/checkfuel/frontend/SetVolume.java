package com.checkfuel.frontend;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.checkfuel.models.Post;
import com.checkfuel.something.R;
import com.checkfuel.utils.ConnectToServer;
import com.checkfuel.utils.DatabaseManager;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

;

public class SetVolume extends AppCompatActivity {
    private EditText numberEditText;
    private String volume;
    private Post post;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_volume);

        numberEditText = (EditText) findViewById(R.id.editText);
        numberEditText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);

        DatabaseManager.writePost(0, 0, 0, 0, 0);
    }

    public void backToMain(@NotNull View view) {
        if (view.getId() == R.id.btnback) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    public void goToVolumeCompare(@NotNull View view) {
        volume = ConnectToServer.getVolume();
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


