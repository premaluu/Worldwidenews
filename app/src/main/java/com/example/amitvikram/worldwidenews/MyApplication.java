package com.example.amitvikram.worldwidenews;

import android.app.Application;
import android.support.v7.app.AppCompatDelegate;
import android.text.TextUtils;

public class MyApplication extends Application {

    public static PreferencesHelper mPreferencesHelper;

    @Override
    public void onCreate() {
        super.onCreate();

        mPreferencesHelper = PreferencesHelper.getInstance(this);

        if (TextUtils.isEmpty(mPreferencesHelper.getString(PreferencesHelper.PREF_APP_COLOR_SCHEME))) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO);
        } else {
            if (mPreferencesHelper.getString(PreferencesHelper.PREF_APP_COLOR_SCHEME) == PreferencesHelper.CONST_AUTOMATIC) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO);
            } else if (mPreferencesHelper.getString(PreferencesHelper.PREF_APP_COLOR_SCHEME) == PreferencesHelper.CONST_LIGHT_MODE) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }
        }
    }
}
