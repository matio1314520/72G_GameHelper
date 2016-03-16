package matio.angel.com.myapplication_gamehelper.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import matio.angel.com.myapplication_gamehelper.cache.DoubleCache;
import matio.angel.com.myapplication_gamehelper.cache.ImageLoader;
import matio.angel.com.myapplication_gamehelper.utils.ViewPagerUtils;

/**
 * Created by Angel on 2016/1/19.
 */
public class SnapshotActivity extends AppCompatActivity {

    //存储图片地址的数组
    private String[] snapshotArr;

    private ImageLoader imageLoader;

    private ViewPager viewPager;

    //存放imageview的list集合
    private List<View> imageViewList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //直接将viewpager设置为布局
        viewPager = new ViewPager(this);

        setContentView(viewPager);

        //设置无通知栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        imageLoader = ImageLoader.getInstance(this, new DoubleCache(this));

        //从intent中取出数据
        getSnapshotFromIntent();

        //初始化list
        initImageViewList();

        //设置图片
        setViewPagerImg();

        //设置viewpager适配器
        setViewPagerAdapter();

        viewPager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //
    private void initImageViewList() {
        imageViewList = new ArrayList<>();

    }

    /**
     * 设置viewpager适配器
     */
    private void setViewPagerAdapter() {
        ViewPagerUtils.setViewPagerAdapter(viewPager, imageViewList);
    }

    /**
     * 设置图片并添加入list集合
     */
    private void setViewPagerImg() {
        for (int i = 0, len = snapshotArr.length; i < len; i++) {
            ImageView imageView = new ImageView(this);

            //图片的点击事件，
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //结束
                    finish();
                }
            });

            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

            imageLoader.displayImage(imageView, snapshotArr[i]);

            imageViewList.add(imageView);
        }
    }

    /**
     * 从intent中取出数据
     */
    private void getSnapshotFromIntent() {
        Intent intent = getIntent();
        snapshotArr = intent.getStringArrayExtra("snapshot");
    }
}