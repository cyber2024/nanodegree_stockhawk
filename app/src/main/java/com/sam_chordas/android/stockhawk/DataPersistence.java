package com.sam_chordas.android.stockhawk;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by Russell Elfenbein on 3/6/2016.
 */
public class DataPersistence {
    public static final String PREFSTAG = "com.ggg.prefstag";
    public static final int PREFSMODE = Context.MODE_PRIVATE;
    public static void SaveData(Context context, DataObject object){
        SharedPreferences prefs = context.getSharedPreferences(PREFSTAG, PREFSMODE);
        prefs.edit().putString(object.getCode(), object.toString());
    }
    public static JSONArray getData(Context context, String code){
        SharedPreferences prefs = context.getSharedPreferences(PREFSTAG, PREFSMODE);
        try {
            JSONArray ja = new JSONArray(prefs.getString(code, ""));
            return ja;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new JSONArray();
    }
    public static void saveDataPoint(Context context, String code, Float point){
        JSONArray ja = getData(context, code);
        ja.put(point);
        saveJSONArray(context, code, ja.toString());
    }
    public static void saveJSONArray(Context context, String code, String data){
        SharedPreferences prefs = context.getSharedPreferences(PREFSTAG, PREFSMODE);
        prefs.edit().putString(code, data).commit();
    }
}
