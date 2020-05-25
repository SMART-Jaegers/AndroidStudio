package com.smartjaegers.checkfuel.models;

import java.text.ParseException;

public interface OnGetResult {
    void onSuccess() throws ParseException;

    void onStart();

    void onFailure();
}
