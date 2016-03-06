package com.sam_chordas.android.stockhawk;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Russell Elfenbein on 3/6/2016.
 */
public class DataObject {
    private String stockcode;
    private ArrayList<Float> data;

    public DataObject(String code, float dataPoint){
        this.stockcode = code;
        data = new ArrayList<Float> ();
        data.add(dataPoint);
    }
    public DataObject(String jsonString){
        try {
            JSONObject object= new JSONObject(jsonString);
            JSONArray ja = object.getJSONArray("data");

            this.stockcode = object.getString("stockcode");
            data = new ArrayList<Float>();
            for(int i = 0; i < ja.length(); i++){
                data.add((float)ja.getDouble(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public String getCode(){
        return  stockcode;
    }
    public ArrayList<Float> getArrayList(){
        return data;
    }
    public float getDataAt(int index){
        return data.get(index);
    }
    public String toString(){
        JSONArray ja = new JSONArray();
        for(int i = 0; i < data.size(); i++) {
            ja.put(data.get(i));
        }
        JSONObject object = new JSONObject();
        try {
            object.put("stockcode",stockcode);
            object.put("data",ja);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object.toString();
    }
}
