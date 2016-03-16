package matio.angel.com.myapplication_gamehelper.utils;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import matio.angel.com.myapplication_gamehelper.bean.ForthGameDetail;
import matio.angel.com.myapplication_gamehelper.bean.SecondActivityDetailBean;

/**
 * Created by Angel on 2016/1/19.
 */
public class GameDetailJsonUtils {

    public static ForthGameDetail parse(String str) {
        Log.i("matio", "获取的网络数据" + str);
        try {
            JSONObject jsonObject1 = new JSONObject(str);
            JSONObject jsonObject = jsonObject1.getJSONObject("info");
            String id = jsonObject.getString("id");
            String snapshot = jsonObject.getString("snapshot");
            String icon = jsonObject.getString("icon");
            String name = jsonObject.getString("name");
            String score = jsonObject.getString("score");
            String version = jsonObject.getString("version");
            String size = jsonObject.getString("size");
            String count_dl = jsonObject.getString("count_dl");
            String game_task_state = jsonObject.getString("game_task_state");
            String dl_back_jifen = jsonObject.getString("dl_back_jifen");
            String android_dl = jsonObject.getString("android_dl");
            String game_desc = jsonObject.getString("game_desc");

            ForthGameDetail gameDetail = new ForthGameDetail(id, snapshot, icon, name, score, version,
                    size, count_dl, dl_back_jifen, android_dl, game_desc, game_task_state);

            return gameDetail;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
