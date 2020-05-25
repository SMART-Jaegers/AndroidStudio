package com.smartjaegers.checkfuel.models;

import java.text.ParseException;

public interface OnGetResult {
    void onSuccess();

    void onStart();

    void onFailure();
}
