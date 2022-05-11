package com.mentalmachines.han.data.local;

import android.content.Context;
import android.content.SharedPreferences;


public class PreferencesHelper {
    public static final String PREF_FILE_NAME = "mvpstarter_pref_file";
    private final SharedPreferences sharedPreferences;

    public PreferencesHelper(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
    }

    public void clear() {
        sharedPreferences.edit().clear().apply();
    }
}
