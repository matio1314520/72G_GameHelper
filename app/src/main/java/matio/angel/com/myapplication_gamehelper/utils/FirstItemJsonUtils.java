package matio.angel.com.myapplication_gamehelper.utils;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import matio.angel.com.myapplication_gamehelper.bean.FirstGiftDetail;

/**
 * Created by Angel on 2016/1/15.
 */
public class FirstItemJsonUtils {

    public static FirstGiftDetail parse(String str) {
        Log.i("matio", "获取的网络数据" + str);
        try {
            JSONObject jsonObject1 = new JSONObject(str);
            JSONObject jsonObject = jsonObject1.getJSONObject("info");

            String id = jsonObject.getString("game_id");
            String icon = jsonObject.getString("icon");
            String name = jsonObject.getString("name");
            String remain = jsonObject.getString("remain");
            String total = jsonObject.getString("total");
            String stime = jsonObject.getString("stime");
            String etime = jsonObject.getString("etime");
            String game_type = jsonObject.getString("game_type");
            String size = jsonObject.getString("size");
            String content = jsonObject.getString("content");
            String howget = jsonObject.getString("howget");
            String consume = jsonObject.getString("consume");
            FirstGiftDetail giftDetail = new FirstGiftDetail(id, icon, name
                    , total, remain, stime, etime, game_type, size, content, howget, consume);
            return giftDetail;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
