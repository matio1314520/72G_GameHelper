package matio.angel.com.myapplication_gamehelper.utils;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import matio.angel.com.myapplication_gamehelper.bean.FirstPresent;

/**
 * Created by Angel on 2016/1/15.
 */
public class ActivityDetailJsonUtils {

    public static List<FirstPresent> parse(String str) {
        List<FirstPresent> list = new ArrayList<>();
        try {
            JSONObject jsonObject1 = new JSONObject(str);

            JSONArray jsonArray = jsonObject1.getJSONArray("info");
            for (int i = 0, len = jsonArray.length(); i < len; i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String id = jsonObject.getString("id");
                String hpic = jsonObject.getString("hpic");
                String nickname = jsonObject.getString("nickname");
                String pubdate = jsonObject.getString("pubdate");
                String floor = jsonObject.getString("floor");
                String content = jsonObject.getString("content");

                FirstPresent present = new FirstPresent(floor, hpic, nickname, pubdate, content);
                list.add(present);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static FirstPresent parse2(String str) {
        try {
            JSONObject jsonObject1 = new JSONObject(str);
            JSONObject jsonObject = jsonObject1.getJSONObject("info");
            String id = jsonObject.getString("id");
            String hpic = jsonObject.getString("hpic");
            String nickname = jsonObject.getString("nickname");
            String pubdate = jsonObject.getString("pubdate");
            String floor = jsonObject.getString("floor");
            String content = jsonObject.getString("content");
            FirstPresent present = new FirstPresent(floor, hpic, nickname, pubdate, content);
            return present;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
