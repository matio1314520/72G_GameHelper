package matio.angel.com.myapplication_gamehelper.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import matio.angel.com.myapplication_gamehelper.R;
import matio.angel.com.myapplication_gamehelper.activity.GiftDetailActivity;
import matio.angel.com.myapplication_gamehelper.activity.LoginActivity;
import matio.angel.com.myapplication_gamehelper.activity.SearchActivity;
import matio.angel.com.myapplication_gamehelper.adapter.FirstBaseAdapter;
import matio.angel.com.myapplication_gamehelper.asynctask.FirstAsyncTask;
import matio.angel.com.myapplication_gamehelper.bean.FirstPresent;
import matio.angel.com.myapplication_gamehelper.common.FirstPresentConstant;
import matio.angel.com.myapplication_gamehelper.utils.FirstJsonUtils;
import matio.angel.com.myapplication_gamehelper.utils.ViewPagerUtils;

/**
 * Created by Angel on 2016/1/11.
 * 第一个fragment
 */
public class FirstPresentFragment extends Fragment implements View.OnClickListener {

    //存放视图的list集合
    private List<View> viewList = new ArrayList<View>();
    //
    private ViewPager viewPager;

    private RadioGroup radioGroup;

    private View view;

    private ListView listView;

    //存放FirstPresent对象的list集合
    private List<FirstPresent> presentList = new ArrayList<>();

    private FirstBaseAdapter adapter;

    //是否到达一页的底部
    private boolean isAviableBottom;

    //页码
    private int page = 1;

    private TextView searchTxt;

    private ImageView searchImg;

    private ImageView infoImg;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //加载视图
        view = inflater.inflate(R.layout.first_present, container, false);

        //初始化控件
        initWedget();

        //设置点击监听
        setClickListener();

        //初始化viewpager中视图数据源
        initViewPagerViewResource();

        //请求网络数据并获取
        getNetworkData(FirstPresentConstant.FIRST_PARAM);

        //设置viewpager适配器
        setViewPagerAdapter();

        //设置listview适配器
        setListViewAdapter();

        //设置viewpager监听事件
        setViewPagerListener();

        //设置radiogroup监听事件
        setRadioGroupListener();

        //设置listview的滚动监听
        setListViewScrollListener();

        //设置listview的选择列表项监听
        setListViewItemListener();

        return view;
    }

    /**
     * 设置点击监听
     */
    private void setClickListener() {
        searchTxt.setOnClickListener(this);
        searchImg.setOnClickListener(this);
        infoImg.setOnClickListener(this);
    }

    /**
     * 点击监听
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.first_myinfo:
                //转到个人信息界面
//                FragmentManager manager = getFragmentManager();
//                FragmentTransaction transaction = manager.beginTransaction();
//                Fragment fragment = new FifthMineFragment();
//                transaction.replace(R.id.container_fragment, fragment);
//
//                transaction.show(fragment);
//                transaction.commit();
                break;
            case R.id.search:
            case R.id.searchgift:
                //转到搜索界面
                toSearchGiftPage();
                break;
        }
    }

    /**
     * 去搜索礼包界面
     */
    private void toSearchGiftPage() {
        Intent intent = new Intent(getActivity(), SearchActivity.class);
        intent.putExtra("type","gift");
        startActivity(intent);
    }

    /**
     * 设置listview的选择列表项监听
     */
    private void setListViewItemListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), GiftDetailActivity.class);
                if (presentList != null && presentList.size() > 0) {
                    intent.putExtra("id", presentList.get(position).getId());
                }
                startActivity(intent);
            }
        });
    }

    /**
     * 设置listview的滚动监听
     */
    private void setListViewScrollListener() {
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && isAviableBottom) {
                    getNetworkData(FirstPresentConstant.FIRST_PARAM + "&page=" + page);
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem + visibleItemCount == totalItemCount) {
                    //到达底部
                    isAviableBottom = true;
                    page++;
                } else {
                    isAviableBottom = false;

                }
            }
        });
    }

    /**
     * 设置listview适配器
     */
    private void setListViewAdapter() {
        adapter = new FirstBaseAdapter(presentList, getActivity(), new Callback3() {
            @Override
            public void click(View view) {
                //跳转到登录界面
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
        listView.setAdapter(adapter);
    }

    /**
     * 获取网络数据
     */
    private void getNetworkData(String param) {
        new FirstAsyncTask(getActivity(), new Callback() {
            @Override
            public void callback(String string) {
                //json解析网络数据存入list集合
                presentList.addAll(FirstJsonUtils.parse(string));
                adapter.notifyDataSetChanged();
            }
        }, param).execute(FirstPresentConstant.FIRST_URLPATH);
    }

    /**
     * 设置radiogroup监听事件
     */
    private void setRadioGroupListener() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //
                switch (checkedId) {
                    case R.id.first_radiobutton1:
                        setCurrentRadioButton(0);
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.first_radiobutton2:
                        setCurrentRadioButton(1);
                        viewPager.setCurrentItem(1);
                        break;
                }
            }
        });
    }

    /**
     * 初始化控件
     */
    private void initWedget() {
        viewPager = (ViewPager) view.findViewById(R.id.first_viewpager);

        radioGroup = (RadioGroup) view.findViewById(R.id.first_radiogroup);

        searchTxt = (TextView) view.findViewById(R.id.searchgift);

        searchImg = (ImageView) view.findViewById(R.id.search);

        infoImg = (ImageView) view.findViewById(R.id.first_myinfo);

    }

    /**
     * 设置viewpager监听事件
     */
    private void setViewPagerListener() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                //设置对应的背景颜色及文本颜色
                setCurrentRadioButton(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    /**
     * 设置对应的背景颜色及文本颜色
     */
    private void setCurrentRadioButton(int position) {
        for (int i = 0, len = radioGroup.getChildCount(); i < len; i++) {
            RadioButton radioButton = (RadioButton) radioGroup.getChildAt(i);
            if (i == position) {
                radioButton.setTextColor(Color.WHITE);
                radioButton.setBackgroundColor(Color.BLUE);
            } else {
                radioButton.setTextColor(Color.BLUE);
                radioButton.setBackgroundColor(Color.WHITE);
            }
        }
    }

    /**
     * 设置viewpager适配器
     */
    private void setViewPagerAdapter() {
        ViewPagerUtils.setViewPagerAdapter(viewPager, viewList);
    }

    /**
     * 初始化viewpager中视图数据源
     */
    private void initViewPagerViewResource() {
        //加载视图
        View view1 = LayoutInflater.from(getActivity()).inflate(R.layout.first_viewpager_listview1, null);
        View view2 = LayoutInflater.from(getActivity()).inflate(R.layout.first_viewpager_listview2, null);
        listView = (ListView) view1.findViewById(R.id.first_viewpager_listview1);
        //添加视图
        viewList.add(view1);
        viewList.add(view2);
    }
}