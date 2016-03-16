package matio.angel.com.myapplication_gamehelper.utils;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import matio.angel.com.myapplication_gamehelper.bean.FirstGiftDetail;
import matio.angel.com.myapplication_gamehelper.bean.SecondActivityDetailBean;

/**
 * Created by Angel on 2016/1/15.
 */
public class SecondItemJsonUtils {


    public static SecondActivityDetailBean parse(String str) {
        Log.i("matio", "获取的网络数据" + str);
        try {
            JSONObject jsonObject1 = new JSONObject(str);
            JSONObject jsonObject = jsonObject1.getJSONObject("info");
            String aname = jsonObject.getString("aname");
            String hotpic = jsonObject.getString("hotpic");
            String writer = jsonObject.getString("writer");
            String pubdate = jsonObject.getString("pubdate");

            String content = jsonObject.getString("content");

            SecondActivityDetailBean giftDetail = new SecondActivityDetailBean(aname, hotpic,
                    writer, pubdate, content);

            return giftDetail;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
