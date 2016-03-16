package matio.angel.com.myapplication_gamehelper.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import matio.angel.com.myapplication_gamehelper.R;
import matio.angel.com.myapplication_gamehelper.asynctask.FirstAsyncTask;
import matio.angel.com.myapplication_gamehelper.bean.FirstPresent;
import matio.angel.com.myapplication_gamehelper.cache.DoubleCache;
import matio.angel.com.myapplication_gamehelper.cache.ImageCache;
import matio.angel.com.myapplication_gamehelper.cache.ImageLoader;
import matio.angel.com.myapplication_gamehelper.common.SecondActivityConstant;
import matio.angel.com.myapplication_gamehelper.fragment.Callback;
import matio.angel.com.myapplication_gamehelper.utils.ActivityDetailJsonUtils;

/**
 * Created by Angel on 2016/1/16.
 */
public class PartSpeakActivity extends AppCompatActivity {

    private FirstPresent present;

    private ImageView iconImg;

    private TextView nicknameTxt;

    private TextView telTxt;

    private TextView floorTxt;

    private TextView contentTxt;

    private TextView thefloorTxt;

    private String whichFloor;

    private ImageLoader imageLoader;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_partdetail);

        imageLoader = ImageLoader.getInstance(this, new DoubleCache(this));

        //初始化控件
        initView();

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        whichFloor = intent.getStringExtra("floor");

        //获取网络数据
        getNetworkData(id, whichFloor);
    }

    /**
     * 初始化控件
     */
    private void initView() {
        thefloorTxt = (TextView) findViewById(R.id.thefloor_partdetail);
        iconImg = (ImageView) findViewById(R.id.icon_partdetail);
        nicknameTxt = (TextView) findViewById(R.id.name_partdetail);
        telTxt = (TextView) findViewById(R.id.tel_partdetail);
        floorTxt = (TextView) findViewById(R.id.floor_partdetail);
        contentTxt = (TextView) findViewById(R.id.content_partdetail);
    }

    /**
     * 获取网络数据
     */
    private void getNetworkData(String id, String floor) {
        new FirstAsyncTask(this, new Callback() {
            @Override
            public void callback(String string) {
                //json解析网络数据存入list集合
                present = ActivityDetailJsonUtils.parse2(string);

                thefloorTxt.setText("指定楼层(第" + present.getId() + "楼)");

                nicknameTxt.setText(present.getName());

                telTxt.setText(present.getRemain());
                floorTxt.setText(present.getId() + "楼");
                contentTxt.setText(present.getContent());
                //加载图片
                if (present.getIcon() != null) {
                    imageLoader.displayImage(iconImg, present.getIcon());
                }
            }
        }, "id=" + id + "&floor=" + floor).execute(SecondActivityConstant.PARTSPEAK_URL);
    }
}