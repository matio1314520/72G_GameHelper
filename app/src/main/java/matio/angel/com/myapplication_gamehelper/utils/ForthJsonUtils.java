package matio.angel.com.myapplication_gamehelper.utils;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import matio.angel.com.myapplication_gamehelper.bean.FirstPresent;
import matio.angel.com.myapplication_gamehelper.bean.ForthMoney;

/**
 * Created by Angel on 2016/1/11.
 */
public class ForthJsonUtils {

    public static List<ForthMoney> parse(String str) {
        List<ForthMoney> list = new ArrayList<>();
        try {
            if (str != null) {
                JSONObject jsonObj = new JSONObject(str);
                JSONArray jsonArray = jsonObj.getJSONArray("info");
                for (int i = 0, len = jsonArray.length(); i < len; i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String id = jsonObject.getString("id");
                    String icon = jsonObject.getString("icon");
                    String name = jsonObject.getString("name");
                    String score = jsonObject.getString("score");
                    String count_dl = jsonObject.getString("count_dl");
                    String size = jsonObject.getString("size");
                    String all_prize = jsonObject.getString("all_prize");
                    ForthMoney present = new ForthMoney(id,icon, name, score, count_dl, size, all_prize);
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
