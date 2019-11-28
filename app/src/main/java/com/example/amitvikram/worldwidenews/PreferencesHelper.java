package com.example.amitvikram.worldwidenews;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

/**
 * This class handles all the values which are stored/persisted.
 *
 * @version 1.0.
 */
public class PreferencesHelper {

    private static PreferencesHelper preferencesHelper;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor prefsEditor;

    // PREFERENCES
    public static String PREFS_NAME = "com.example.amitvikram.worldwidenews.prefs";

    public static String PREF_APP_COLOR_SCHEME = "PREF_APP_COLOR_SCHEME";

    public static String CONST_AUTOMATIC = "Automatic";
    public static String CONST_DARK_MODE = "Dark Mode";
    public static String CONST_LIGHT_MODE = "Light Mode";

    public static PreferencesHelper getInstance(Context context) {

        if (preferencesHelper == null) {
            preferencesHelper = new PreferencesHelper(context);
        }
        return preferencesHelper;
    }

    public static void setInstance(PreferencesHelper preferencesHelper) {
        preferencesHelper = preferencesHelper;
    }

    public PreferencesHelper(Context context) {

        this.sharedPreferences = context.getSharedPreferences(PREFS_NAME, Activity.MODE_PRIVATE);
        this.prefsEditor = sharedPreferences.edit();
    }

    public boolean contains(String key) {

        return this.sharedPreferences.contains(key);
    }

    public Long getLong(String key) {
        return sharedPreferences.getLong(key, 0);
    }

    public void putLong(String key, Long value) {
        prefsEditor.putLong(key, value);
        prefsEditor.commit();
    }

    public String getString(String key) {
        return sharedPreferences.getString(key, ""); // Get our string from prefs or return an empty string
    }

    public String getString(String key, String mDefault) {
        return sharedPreferences.getString(key, mDefault); // Get our string from prefs or return an empty string
    }

    public void putString(String key, String value) {
        prefsEditor.putString(key, value);
        prefsEditor.commit();
    }

    public float getFloat(String key) {
        return sharedPreferences.getFloat(key, 0F);
    }

    public void putFloat(String key, Float value) {
        prefsEditor.putFloat(key, value);
        prefsEditor.commit();
    }

    public void putBoolean(String key, Boolean value) {
        prefsEditor.putBoolean(key, value);
        prefsEditor.commit();
    }

    public boolean getBoolean(String key) {
        return sharedPreferences.getBoolean(key, false); // Get our string from prefs or return an false
    }

    public int getInt(String key) {
        return sharedPreferences.getInt(key, 0);
    }

    public void putInt(String key, int value) {
        prefsEditor.putInt(key, value);
        prefsEditor.commit();
    }

    public void putObject(String key, Object value) {
        Gson gson = new Gson();
        String json = gson.toJson(value);
        prefsEditor.putString(key, json);
        prefsEditor.commit();
    }

    public Object getObject(String key, Class<?> clazz) {
        Gson gson = new Gson();
        String json = sharedPreferences.getString(key, "");
        Object jsonObject = gson.fromJson(json, clazz);
        return jsonObject;
    }

    public void clear() {
        prefsEditor.clear();
        prefsEditor.commit();
    }

    public void removeKey(String Key) {
        prefsEditor.remove(Key).apply();
    }
}
