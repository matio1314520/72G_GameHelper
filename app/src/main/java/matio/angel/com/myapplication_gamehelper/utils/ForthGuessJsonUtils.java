package matio.angel.com.myapplication_gamehelper.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import matio.angel.com.myapplication_gamehelper.bean.FirstPresent;
import matio.angel.com.myapplication_gamehelper.bean.ForthGuessLike;

/**
 * Created by Angel on 2016/1/21.
 */
public class ForthGuessJsonUtils {

    public static List<ForthGuessLike> parse(String str) {
        List<ForthGuessLike> list = new ArrayList<>();
        try {
            JSONObject jsonObject1 = new JSONObject(str);

            JSONArray jsonArray = jsonObject1.getJSONArray("info");
            for (int i = 0, len = jsonArray.length(); i < len; i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String id = jsonObject.getString("id");
                String name = jsonObject.getString("name");
                String icon = jsonObject.getString("icon");
                String count_dl = jsonObject.getString("count_dl");

                ForthGuessLike present = new ForthGuessLike(id, name, icon, count_dl);
                list.add(present);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
