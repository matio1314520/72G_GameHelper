package matio.angel.com.myapplication_gamehelper.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import matio.angel.com.myapplication_gamehelper.R;
import matio.angel.com.myapplication_gamehelper.adapter.SmallGameAdapter;
import matio.angel.com.myapplication_gamehelper.asynctask.FirstAsyncTask;
import matio.angel.com.myapplication_gamehelper.bean.SecondAreaActivity1;
import matio.angel.com.myapplication_gamehelper.bean.SmallGame;
import matio.angel.com.myapplication_gamehelper.bean.SmallGameAd;
import matio.angel.com.myapplication_gamehelper.cache.DoubleCache;
import matio.angel.com.myapplication_gamehelper.cache.ImageLoader;
import matio.angel.com.myapplication_gamehelper.common.FifthMineConstant;
import matio.angel.com.myapplication_gamehelper.common.FirstPresentConstant;
import matio.angel.com.myapplication_gamehelper.utils.SmallGameAdJsonUtils;
import matio.angel.com.myapplication_gamehelper.utils.SmallGameJsonUtils;
import matio.angel.com.myapplication_gamehelper.utils.SmallHotJsonUtils;
import matio.angel.com.myapplication_gamehelper.utils.ViewPagerUtils;

/**
 * Created by Angel on 2016/1/20.
 */
public class SmallGameActivity extends AppCompatActivity {

    private ImageView adImg1;

    private ImageView adImg2;

    private ImageView adImg3;

    private TextView adTxt;

    private List<SmallGameAd> gameAdList = new ArrayList<>();

    private ImageLoader imageLoader;

    private ViewPager viewPager;

    private List<View> viewList = new ArrayList<>();

    private static final int SIGNL = 1;

    private int mPosition;

    private ListView listView;

    private View headerView;

    private List<SmallGame> smallGameList = new ArrayList<>();

    private SmallGameAdapter smallGameAdapter;

    private List<SecondAreaActivity1> hotList = new ArrayList<>();

    private LinearLayout imgLin;

    private LinearLayout nameLin;

    private boolean isAviableBottom;

    private int page = 1;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //处理消息
            if (msg.what == SIGNL) {
                mPosition++;
                if (mPosition >= viewList.size()) {
                    mPosition = 0;
                }
                viewPager.setCurrentItem(mPosition);
                setCurrentPoint(mPosition);
            }
        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_game);

        imageLoader = ImageLoader.getInstance(this, new DoubleCache(this));

        //listview头部视图
        headerView = LayoutInflater.from(this).inflate(R.layout.smallgame_header, null);

        //初始化控件
        initView();

        //获取网络数据
        getNetworkData();

        //设置listview适配器
        setListViewAdapter();

        //设置viewpager监听器
        setViewPagerListener();

        //设置定时器
        setMyTimer();

        //设置listview的滚动监听
        setListVeiwScrollListener();

        viewPager.setCurrentItem(0);
    }

    private void setListVeiwScrollListener() {
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && isAviableBottom) {
                    getListViewData(FifthMineConstant.SMALLGAME_PARAM + "&page=" + page);
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem + visibleItemCount == totalItemCount) {
                    isAviableBottom = true;
                } else {
                    isAviableBottom = false;
                }
            }
        });
    }

    /**
     * 设置viewpager监听器
     */
    private void setViewPagerListener() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                setCurrentPoint(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    /**
     * 设置listview适配器
     */
    private void setListViewAdapter() {
        smallGameAdapter = new SmallGameAdapter(smallGameList, this);
        listView.setAdapter(smallGameAdapter);
    }

    /**
     * 设置定时器
     */
    private void setMyTimer() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Message message = Message.obtain();
                message.what = SIGNL;
                handler.sendMessage(message);
            }
        }, 8000, 1500);
    }

    /**
     * 设置viewpager适配器
     */
    private void setViewPagerAdapter() {
        ViewPagerUtils.setViewPagerAdapter(viewPager, viewList);
    }

    /**
     * 初始化控件
     */
    private void initView() {
        adImg1 = (ImageView) headerView.findViewById(R.id.imag1);
        adImg2 = (ImageView) headerView.findViewById(R.id.imag2);
        adImg3 = (ImageView) headerView.findViewById(R.id.imag3);
        adTxt = (TextView) headerView.findViewById(R.id.name_smallgame);

        viewPager = (ViewPager) headerView.findViewById(R.id.activity_viewpager);

        imgLin = (LinearLayout) headerView.findViewById(R.id.icon_gamedetail);

        nameLin = (LinearLayout) headerView.findViewById(R.id.content_gameheader);

        listView = (ListView) findViewById(R.id.new_listview);
        //为listview添加头部
        listView.addHeaderView(headerView);
    }

    /**
     * 设置相应的点图片
     *
     * @param position
     */
    private void setCurrentPoint(int position) {
        if (position == 0) {
            adImg1.setImageResource(R.mipmap.ic_banner_dot_focus);
            adImg2.setImageResource(R.mipmap.ic_banner_dot);
            adImg3.setImageResource(R.mipmap.ic_banner_dot);
            if (gameAdList != null && gameAdList.size() > 0) {
                adTxt.setText(gameAdList.get(0).getName());
            }
        } else if (position == 1) {
            adImg2.setImageResource(R.mipmap.ic_banner_dot_focus);
            adImg1.setImageResource(R.mipmap.ic_banner_dot);
            adImg3.setImageResource(R.mipmap.ic_banner_dot);
            if (gameAdList != null && gameAdList.size() > 0) {
                adTxt.setText(gameAdList.get(1).getName());
            }
        } else {
            adImg2.setImageResource(R.mipmap.ic_banner_dot);
            adImg1.setImageResource(R.mipmap.ic_banner_dot);
            adImg3.setImageResource(R.mipmap.ic_banner_dot_focus);
            if (gameAdList != null && gameAdList.size() > 0) {
                adTxt.setText(gameAdList.get(2).getName());
            }
        }
    }

    /**
     * 获取网络数据
     */
    private void getNetworkData() {

        //请求广告数据
        new FirstAsyncTask(this, new Callback() {
            @Override
            public void callback(String string) {
                //json解析网络数据存入list集合

                gameAdList.addAll(SmallGameAdJsonUtils.parse(string));

                //设置viewpager中的视图
                setViewPagerView();

                //设置viewpager适配器
                setViewPagerAdapter();
            }
        }, FifthMineConstant.FIFTH_AD_PARAM).execute(FifthMineConstant.FIFTH_AD_URPPATH);

        new FirstAsyncTask(this, new Callback() {
            @Override
            public void callback(String string) {
                //json解析网络数据存入list集合
                hotList.addAll(SmallHotJsonUtils.parse(string));
                //设置图片及文本
                setTextAndImg();

            }
        }, FifthMineConstant.SMALLGAME_PARAM).execute(FifthMineConstant.SMALLGAME_HOT_URL);

        //请求listview数据
        getListViewData(FifthMineConstant.SMALLGAME_PARAM);
    }

    /**
     * 设置图片及文本
     */
    private void setTextAndImg() {
        for (int i = 0, size = hotList.size(); i < size; i++) {
            ImageView iconImg = (ImageView) imgLin.getChildAt(i);
            TextView nameTxt = (TextView) nameLin.getChildAt(i);
            imageLoader.displayImage(iconImg, hotList.get(i).getBimg());
            nameTxt.setText(hotList.get(i).getBname());
        }
    }

    /**
     * 请求listview数据
     */
    private void getListViewData(String param) {

        new FirstAsyncTask(this, new Callback() {
            @Override
            public void callback(String string) {
                //json解析网络数据存入list集合
                smallGameList.addAll(SmallGameJsonUtils.parse(string));
                smallGameAdapter.notifyDataSetChanged();

            }
        }, param).execute(FifthMineConstant.SMALLGAME_URL);
    }

    /**
     * 设置viewpager中的视图
     */
    private void setViewPagerView() {
        for (int i = 0, size = gameAdList.size(); i < size; i++) {

            ImageView imageView = new ImageView(this);
            imageLoader.displayImage(imageView, gameAdList.get(i).getImag());
            viewList.add(imageView);
        }
    }
}