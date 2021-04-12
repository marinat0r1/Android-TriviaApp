package com.example.trivia.util;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Collection;

public class ArrayUtil {

    public static ArrayList<String> convert(JSONArray jArr)
    {
        ArrayList<String> list = new ArrayList<String>();
        try {
            for (int i=0, l=jArr.length(); i<l; i++){
                list.add((String) jArr.get(i));
            }
        } catch (JSONException ignored) {}

        return list;
    }

    public static JSONArray convert(Collection<Object> list)
    {
        return new JSONArray(list);
    }
}
