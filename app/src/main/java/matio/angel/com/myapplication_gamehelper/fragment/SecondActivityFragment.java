package matio.angel.com.myapplication_gamehelper.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import matio.angel.com.myapplication_gamehelper.R;
import matio.angel.com.myapplication_gamehelper.activity.ActivityDetailActivity;
import matio.angel.com.myapplication_gamehelper.activity.GameDetailActivity;
import matio.angel.com.myapplication_gamehelper.adapter.SecondBaseAdapter;
import matio.angel.com.myapplication_gamehelper.asynctask.FirstAsyncTask;
import matio.angel.com.myapplication_gamehelper.bean.SecondAreaActivity1;
import matio.angel.com.myapplication_gamehelper.bean.SecondAreaActivity2;
import matio.angel.com.myapplication_gamehelper.cache.DoubleCache;
import matio.angel.com.myapplication_gamehelper.cache.ImageLoader;
import matio.angel.com.myapplication_gamehelper.common.FirstPresentConstant;
import matio.angel.com.myapplication_gamehelper.common.SecondActivityConstant;
import matio.angel.com.myapplication_gamehelper.utils.SecondJsonUtils;
import matio.angel.com.myapplication_gamehelper.utils.ViewPagerUtils;

/**
 * Created by Angel on 2016/1/8.
 */
public class SecondActivityFragment extends Fragment {

    //广告viewpager
    private ViewPager viewPager;

    private TextView nameTxt;

    //广告标题
    private TextView titleTxt;

    private View view;

    //广告list集合
    private List<View> adViewList = new ArrayList<>();

    private List<SecondAreaActivity1> list1 = new ArrayList<SecondAreaActivity1>();

    private List<SecondAreaActivity2> list2 = new ArrayList<SecondAreaActivity2>();

    //广告指示点1
    private ImageView imageView1;

    //广告指示点2
    private ImageView imageView2;

    //BaseAdapter
    private SecondBaseAdapter adapter;

    private ListView listView;

    private ImageLoader imageLoader;

    //广告图片1
    private ImageView adImag1;

    //广告图片2
    private ImageView adImag2;

    private int mPosition;

    private static final int SIGNL = 1;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //处理消息
            if (msg.what == SIGNL) {
                mPosition++;
                if (mPosition >= adViewList.size()) {
                    mPosition = 0;
                }
                viewPager.setCurrentItem(mPosition);
                setCurrentPoint(mPosition);
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.second_activity, container, false);

        //初始化控件
        initWedget();

        //初始化viewlist
        initViewList();

        //异步任务获取网络数据1
        getNetworkData();

        //设置viewpager适配器
        setViewPagerAdapter();

        //设置viewpager监听器
        setViewPagerListener();

        //设置定时器
        setViewPagerTimer();

        //设置listview适配器
        setListviewAdapter();

        //设置listview的选择列表项的监听
        setListViewListener();

        //设置viewpager中图片的点击监听
        setImageClickListener();

        return view;
    }

    /**
     * 设置图片的点击监听
     */
    private void setImageClickListener() {
        adImag1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到游戏详情界面
                Intent intent = new Intent(getActivity(), GameDetailActivity.class);
                intent.putExtra("id", list1.get(0).getTarget_id());
                intent.putExtra("gamename", list1.get(0).getBname());
                startActivity(intent);
            }
        });
        adImag2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到活动详情界面
                Intent intent = new Intent(getActivity(), ActivityDetailActivity.class);
                intent.putExtra("id", list1.get(1).getTarget_id());
                startActivity(intent);
            }
        });
    }

    /**
     * 设置定时器
     */
    private void setViewPagerTimer() {
        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Message message = Message.obtain();
                message.what = SIGNL;
                handler.sendMessage(message);
            }
        }, 5000, 2000);
    }

    /**
     * 设置listview的选择列表项的监听
     */
    private void setListViewListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //页面跳转
                Intent intent = new Intent(getActivity(), ActivityDetailActivity.class);
                if (list2 != null && list2.size() > 0) {
                    String itemId = list2.get(position).getId();
                    if (!TextUtils.isEmpty(itemId)) {
                        intent.putExtra("id", itemId);
                    }
                }
                startActivity(intent);
            }
        });
    }

    /**
     * 设置listview适配器
     */
    private void setListviewAdapter() {
        adapter = new SecondBaseAdapter(list2, getActivity());
        listView.setAdapter(adapter);
    }

    /**
     * 异步任务获取网络数据
     */
    private void getNetworkData() {

        new FirstAsyncTask(getActivity(), new Callback() {
            @Override
            public void callback(String string) {
                list1.addAll(SecondJsonUtils.parse(string));
                adapter.notifyDataSetChanged();
                imageLoader = ImageLoader.getInstance(getActivity(), new DoubleCache(getActivity()));
                titleTxt.setText(list1.get(0).getBname());

                imageLoader.displayImage(adImag1, list1.get(0).getBimg());
                imageLoader.displayImage(adImag2, list1.get(1).getBimg());
            }
        }, SecondActivityConstant.PARAM).execute(SecondActivityConstant.VIEWPAGER_URL);

        viewPager.setCurrentItem(0);

        new FirstAsyncTask(getActivity(), new Callback() {
            @Override
            public void callback(String string) {
                list2.addAll(SecondJsonUtils.parse2(string));
                //更新
                adapter.notifyDataSetChanged();
            }
        }, SecondActivityConstant.PARAM1).execute(SecondActivityConstant.SECOND_CONTENT_URL);
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
     * 设置相应的点图片
     *
     * @param position
     */
    private void setCurrentPoint(int position) {
        if (position == 0) {
            imageView1.setImageResource(R.mipmap.ic_banner_dot);
            imageView2.setImageResource(R.mipmap.ic_banner_dot_focus);
            if (list1 != null && list1.size() > 0) {
                titleTxt.setText(list1.get(0).getBname());
            }
        } else {
            imageView2.setImageResource(R.mipmap.ic_banner_dot);
            imageView1.setImageResource(R.mipmap.ic_banner_dot_focus);
            if (list1 != null && list1.size() > 0) {
                titleTxt.setText(list1.get(1).getBname());
            }
        }
    }

    /**
     * 初始化viewlist
     */
    private void initViewList() {
        adImag1 = new ImageView(getActivity());
        adImag2 = new ImageView(getActivity());
        adViewList.add(adImag1);
        adViewList.add(adImag2);
    }

    /**
     * 设置viewpager适配器
     */
    private void setViewPagerAdapter() {
        ViewPagerUtils.setViewPagerAdapter(viewPager, adViewList);
    }

    /**
     * 初始化控件
     */
    private void initWedget() {
        viewPager = (ViewPager) view.findViewById(R.id.secondactivity_viewpager);
        nameTxt = (TextView) view.findViewById(R.id.second_text);
        imageView1 = (ImageView) view.findViewById(R.id.second_imag1);
        imageView2 = (ImageView) view.findViewById(R.id.second_imag2);
        listView = (ListView) view.findViewById(R.id.secondactivity_listview);
        titleTxt = (TextView) view.findViewById(R.id.second_text);
    }
}
