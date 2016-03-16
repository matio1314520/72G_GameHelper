package matio.angel.com.myapplication_gamehelper.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import matio.angel.com.myapplication_gamehelper.bean.FirstPresent;
import matio.angel.com.myapplication_gamehelper.bean.SecondAreaActivity1;

/**
 * Created by Angel on 2016/1/22.
 */
public class SmallHotJsonUtils {

    public static List<SecondAreaActivity1> parse(String str) {
        List<SecondAreaActivity1> list = new ArrayList<>();
        try {
            JSONObject jsonObject1 = new JSONObject(str);

            JSONArray jsonArray = jsonObject1.getJSONArray("info");
            for (int i = 0, len = jsonArray.length(); i < len; i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String id = jsonObject.getString("id");
                String icon = jsonObject.getString("icon");
                String name = jsonObject.getString("name");
                SecondAreaActivity1 present = new SecondAreaActivity1(id, icon, name);
                list.add(present);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
