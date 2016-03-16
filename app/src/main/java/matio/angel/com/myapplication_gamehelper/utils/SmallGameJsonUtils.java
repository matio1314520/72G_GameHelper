package matio.angel.com.myapplication_gamehelper.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import matio.angel.com.myapplication_gamehelper.bean.FirstPresent;
import matio.angel.com.myapplication_gamehelper.bean.SmallGame;

/**
 * Created by Angel on 2016/1/22.
 */
public class SmallGameJsonUtils  {

    public static List<SmallGame> parse(String str) {
        List<SmallGame> list = new ArrayList<>();
        try {
            JSONObject jsonObject1 = new JSONObject(str);
            JSONArray jsonArray = jsonObject1.getJSONArray("info");
            for (int i = 0, len = jsonArray.length(); i < len; i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String id = jsonObject.getString("id");
                String icon = jsonObject.getString("icon");
                String name = jsonObject.getString("name");
                String click = jsonObject.getString("click");
                String game_desc = jsonObject.getString("game_desc");
                SmallGame present = new SmallGame(id, icon, name, click, game_desc);
                list.add(present);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
