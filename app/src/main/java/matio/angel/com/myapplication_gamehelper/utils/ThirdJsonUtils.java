package matio.angel.com.myapplication_gamehelper.utils;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import matio.angel.com.myapplication_gamehelper.bean.FirstPresent;
import matio.angel.com.myapplication_gamehelper.bean.ThirdChange;

/**
 * Created by Angel on 2016/1/11.
 */
public class ThirdJsonUtils {

    public static List<ThirdChange> parse(String str) {
        List<ThirdChange> list = new ArrayList<>();
        try {
            if (str != null) {
                JSONObject jsonObj = new JSONObject(str);
                JSONArray jsonArray = jsonObj.getJSONArray("info");
                for (int i = 0, len = jsonArray.length(); i < len; i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String id = jsonObject.getString("id");
                    String icon = jsonObject.getString("icon");
                    String name = jsonObject.getString("name");
                    String consume = jsonObject.getString("consume");
                    String remain = jsonObject.getString("remain");
                    ThirdChange present = new ThirdChange(id, icon, name, consume, remain);
                    list.add(present);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static ThirdChange parse2(String str) {
        try {
            if (str != null) {
                JSONObject jsonObj = new JSONObject(str);
                JSONObject jsonObject = jsonObj.getJSONObject("info");
                String id = jsonObject.getString("id");
                String icon = jsonObject.getString("icon");
                String name = jsonObject.getString("name");
                String consume = jsonObject.getString("consume");
                String remain = jsonObject.getString("remain");
                ThirdChange present = new ThirdChange(id, icon, name, consume, remain);
                return present;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
