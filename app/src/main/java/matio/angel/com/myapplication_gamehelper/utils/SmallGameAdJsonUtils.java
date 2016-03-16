package matio.angel.com.myapplication_gamehelper.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import matio.angel.com.myapplication_gamehelper.bean.FirstPresent;
import matio.angel.com.myapplication_gamehelper.bean.SmallGameAd;

/**
 * Created by Angel on 2016/1/20.
 */
public class SmallGameAdJsonUtils {

    public static List<SmallGameAd> parse(String str) {
        List<SmallGameAd> list = new ArrayList<>();
        try {
            JSONObject jsonObject1 = new JSONObject(str);
            JSONArray jsonArray = jsonObject1.getJSONArray("info");
            for (int i = 0, len = jsonArray.length(); i < len; i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String name = jsonObject.getString("name");
                String image = jsonObject.getString("image");
                SmallGameAd present = new SmallGameAd(name, image);
                list.add(present);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}