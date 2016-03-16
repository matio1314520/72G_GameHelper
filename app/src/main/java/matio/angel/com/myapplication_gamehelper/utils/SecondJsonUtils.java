package matio.angel.com.myapplication_gamehelper.utils;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import matio.angel.com.myapplication_gamehelper.bean.SecondAreaActivity1;
import matio.angel.com.myapplication_gamehelper.bean.SecondAreaActivity2;

/**
 * Created by Angel on 2016/1/11.
 */
public class SecondJsonUtils {

    public static List<SecondAreaActivity1> parse(String str) {
        List<SecondAreaActivity1> list = new ArrayList<>();
        try {
            if (str != null) {
                JSONObject jsonObj = new JSONObject(str);
                JSONArray jsonArray = jsonObj.getJSONArray("info");
                for (int i = 0, len = jsonArray.length(); i < len; i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String bimg = jsonObject.getString("bimg");
                    String bname = jsonObject.getString("bname");
                    String target_id = jsonObject.getString("target_id");
                    SecondAreaActivity1 second = new SecondAreaActivity1(target_id,bimg, bname);
                    list.add(second);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<SecondAreaActivity2> parse2(String str) {
        List<SecondAreaActivity2> list = new ArrayList<>();
        try {
            if (str != null) {
                JSONObject jsonObj = new JSONObject(str);
                JSONArray jsonArray = jsonObj.getJSONArray("info");
                for (int i = 0, len = jsonArray.length(); i < len; i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String id = jsonObject.getString("id");
                    String hotpic = jsonObject.getString("hotpic");
                    String aname = jsonObject.getString("aname");
                    String shortname = jsonObject.getString("shortname");
                    String total_join_user = jsonObject.getString("total_join_user");
                    String status = jsonObject.getString("status");

                    SecondAreaActivity2 present = new SecondAreaActivity2(id, hotpic, aname, shortname, total_join_user, status);
                    list.add(present);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}
