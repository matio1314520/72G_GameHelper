package matio.angel.com.myapplication_gamehelper.activity;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import matio.angel.com.myapplication_gamehelper.R;
import matio.angel.com.myapplication_gamehelper.utils.ViewPagerUtils;

/**
 * 欢迎界面
 */
public class MainActivity extends AppCompatActivity {

    //加载器
    private LayoutInflater inflater;
    //存放View视图的List
    private List<View> viewList = new ArrayList<View>();
    //viewPager控件对象
    private ViewPager welcomeViewPpager;
    //第四个页面上的“开始体验”按钮
    private Button experienceBbtn;
    //viewList长度
    private int viewListSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //加载布局
        setContentView(R.layout.activity_main);

        inflater = LayoutInflater.from(this);

        //初始化视图 控件
        initViewWeidgt();

        //初始化ViewPager视图数据源
        initViewPagerViewResource();

        //viewpager适配器
        setMyViewPagerAdapter();

        //viewpager监听器
        setMyViewPagerListener();
        //页面跳转
    }

    /**
     * 初始化视图 控件
     */
    private void initViewWeidgt() {
        welcomeViewPpager = (ViewPager) findViewById(R.id.welcome_viewpager);
    }

    /**
     * 设置viewpager适配器
     */
    private void setMyViewPagerAdapter() {
        ViewPagerUtils.setViewPagerAdapter(welcomeViewPpager, viewList);
    }

    /**
     * viewPager监听器
     */
    private void setMyViewPagerListener() {
        welcomeViewPpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //设置第四个页面上button显示或隐藏
                setCurrentViewButton(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    /**
     * 设置第四个页面上button显示或隐藏
     *
     * @param position viewpager页面索引
     */
    private void setCurrentViewButton(int position) {

        if (position == (viewListSize - 1)) {
            //设置为可见的
            experienceBbtn.setVisibility(View.VISIBLE);
        } else {
            //设置为不可见的
            experienceBbtn.setVisibility(View.INVISIBLE);
        }

    }

    /**
     * 初始化ViewPager视图数据源
     */
    private void initViewPagerViewResource() {
        //加载欢迎界面视图1
        View welcomeFirstView = inflater.inflate(R.layout.welcome_first, null);
        //加载欢迎界面视图2
        View welcomeSecondView = inflater.inflate(R.layout.welcome_second, null);
        //加载欢迎界面视图3
        View welcomeThirdView = inflater.inflate(R.layout.welcome_third, null);
        //加载欢迎界面视图4
        View welcomeForthView = inflater.inflate(R.layout.welcome_forth, null);

        experienceBbtn = (Button) welcomeForthView.findViewById(R.id.welcome_experience);

        //按顺序向viewList中添加视图
        viewList.add(welcomeFirstView);
        viewList.add(welcomeSecondView);
        viewList.add(welcomeThirdView);
        viewList.add(welcomeForthView);
        //list长度
        viewListSize = viewList.size();
    }

    /**
     * 监听事件
     *
     * @param view
     */
    public void experience(View view) {
        //页面跳转
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}
