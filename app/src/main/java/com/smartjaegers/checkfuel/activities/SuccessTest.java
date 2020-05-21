package com.smartjaegers.checkfuel.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.smartjaegers.checkfuel.R;


public class SuccessTest extends DialogFragment implements View.OnClickListener {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_connection_to_bluetooth, null);

        TextView successView = view.findViewById(R.id.success_view);
        successView.setText(R.string.successfully_saved);
        view.setOnClickListener(this);
        return view;
    }


    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
        super.onDismiss(dialog);
    }

    @Override
    public void onCancel(@NonNull DialogInterface dialog) {
        super.onCancel(dialog);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
    }
}