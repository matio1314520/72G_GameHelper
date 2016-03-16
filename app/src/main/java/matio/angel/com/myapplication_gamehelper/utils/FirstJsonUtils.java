package matio.angel.com.myapplication_gamehelper.utils;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import matio.angel.com.myapplication_gamehelper.bean.FirstGiftDetail;
import matio.angel.com.myapplication_gamehelper.bean.FirstPresent;

/**
 * Created by Angel on 2016/1/11.
 */
public class FirstJsonUtils {

    public static List<FirstPresent> parse(String str) {
        List<FirstPresent> list = new ArrayList<>();
        try {
            if (str != null) {
                JSONObject jsonObject = new JSONObject(str);
                JSONArray jsonArray = jsonObject.getJSONArray("info");
                for (int i = 0, len = jsonArray.length(); i < len; i++) {
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    String id = jsonObject1.getString("id");
                    String icon = jsonObject1.getString("icon");
                    String name = jsonObject1.getString("name");
                    String remain = jsonObject1.getString("remain");
                    String content = jsonObject1.getString("content");
                    FirstPresent present = new FirstPresent(id,icon, name, remain, content);
                    list.add(present);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("matio", "parse:list.size " + list.size());
        return list;
    }
}
