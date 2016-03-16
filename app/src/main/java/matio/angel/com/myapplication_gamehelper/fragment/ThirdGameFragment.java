package matio.angel.com.myapplication_gamehelper.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import matio.angel.com.myapplication_gamehelper.R;
import matio.angel.com.myapplication_gamehelper.activity.LoginActivity;
import matio.angel.com.myapplication_gamehelper.activity.PrizeDetailActivity;
import matio.angel.com.myapplication_gamehelper.adapter.ThirdNewBaseAdapter;
import matio.angel.com.myapplication_gamehelper.adapter.ThirdHotBaseAdapter;
import matio.angel.com.myapplication_gamehelper.asynctask.FirstAsyncTask;
import matio.angel.com.myapplication_gamehelper.bean.ThirdChange;
import matio.angel.com.myapplication_gamehelper.common.ThirdChangeConstant;
import matio.angel.com.myapplication_gamehelper.utils.ThirdJsonUtils;
import matio.angel.com.myapplication_gamehelper.utils.ViewPagerUtils;

/**
 * Created by Angel on 2016/1/8.
 */
public class ThirdGameFragment extends Fragment {

    private RadioGroup radioGroup;

    private View view;

    private ViewPager viewPager;

    private ThirdNewBaseAdapter adapter1;

    private ThirdHotBaseAdapter adapter2;

    private List<ThirdChange> list1 = new ArrayList<>();

    private List<ThirdChange> list2 = new ArrayList<>();

    private List<View> viewList = new ArrayList<>();

    private ListView listView2;

    private ListView listView1;

    private boolean isAviableBottom;

    private int page = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.third, container, false);

        //初始化控件
        initWedget();

        //获取网络数据
        getNetworkData1(ThirdChangeConstant.PARAM1);

        getNetworkData2(ThirdChangeConstant.PARAM2);

        //viewlist数据源
        initViewList();

        //设置listview适配器
        setListviewAdapter();

        //viewpager适配器
        setViewPagerAdapter();

        //radiogroup监听事件
        setRadioGroupListener();

        //viewpager监听事件
        setViewPagerListener();

        viewPager.setCurrentItem(0);

        //设置listview的选择列表项的监听
        setListViewListener(listView1, list1);

        setListViewListener(listView2, list2);

        //设置listview的滚动监听
        setListViewScrollListener(listView1);
        setListViewScrollListener(listView2);
        return view;
    }

    /**
     * 获取网络数据
     */
    private void getNetworkData1(String param) {
        new FirstAsyncTask(getActivity(), new Callback() {
            @Override
            public void callback(String string) {
                list1.addAll(ThirdJsonUtils.parse(string));
                adapter1.notifyDataSetChanged();
            }
        }, param).execute(ThirdChangeConstant.URL);
    }

    private void getNetworkData2(String param) {
        new FirstAsyncTask(getActivity(), new Callback() {
            @Override
            public void callback(String string) {
                list2.addAll(ThirdJsonUtils.parse(string));
                adapter2.notifyDataSetChanged();
            }
        }, param).execute(ThirdChangeConstant.URL);
    }

    /**
     * 设置listview的滚动监听
     */
    private void setListViewScrollListener(ListView listView) {
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && isAviableBottom) {
                    //  getNetworkData(FirstPresentConstant.FIRST_PARAM + "&page" + page);
                    if (((RadioButton) radioGroup.getChildAt(0)).isChecked()) {
                        getNetworkData1(ThirdChangeConstant.PARAM1 + "&page=" + page);
                    } else {
                        getNetworkData2(ThirdChangeConstant.PARAM2 + "&page=" + page);
                    }
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
     * 设置listview的选择列表项的监听
     */
    private void setListViewListener(ListView listView, final List<ThirdChange> list) {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                List<ThirdChange> targetList = new ArrayList<ThirdChange>();
                targetList.addAll(list);
                //页面跳转
                Intent intent = new Intent(getActivity(), PrizeDetailActivity.class);
                if (targetList != null && targetList.size() > 0) {
                    String itemId = targetList.get(position).getId();
                    if (!TextUtils.isEmpty(itemId)) {
                        intent.putExtra("id", itemId);
                        intent.putExtra("icon", targetList.get(position).getIcon());
                        intent.putExtra("remain", targetList.get(position).getRemain());
                        intent.putExtra("value", targetList.get(position).getConsume());
                    }
                }
                startActivity(intent);
            }
        });
    }

    /**
     * viewlist数据源
     */
    private void initViewList() {
        //加载视图
        View view1 = LayoutInflater.from(getActivity()).inflate(R.layout.third_viewpager_listview1, null);
        View view2 = LayoutInflater.from(getActivity()).inflate(R.layout.third_viewpager_listview2, null);
        listView1 = (ListView) view1.findViewById(R.id.third_listview1);
        listView2 = (ListView) view2.findViewById(R.id.third_listview2);
        //添加视图
        viewList.add(view1);
        viewList.add(view2);
    }

    /**
     * 设置listview适配器
     */
    private void setListviewAdapter() {
        adapter1 = new ThirdNewBaseAdapter(list1, getActivity(), new Callback3() {
            @Override
            public void click(View view) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
        adapter2 = new ThirdHotBaseAdapter(list2, getActivity(), new Callback3() {
            @Override
            public void click(View view) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
        listView1.setAdapter(adapter1);
        listView2.setAdapter(adapter2);
    }


    /**
     * 设置viewpager适配器
     */
    private void setViewPagerAdapter() {
        ViewPagerUtils.setViewPagerAdapter(viewPager, viewList);
    }

    /**
     * viewpager监听器
     */
    private void setViewPagerListener() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setCurrentRadioButton(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * radiogroup监听器
     */
    private void setRadioGroupListener() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {
                    case R.id.third_radiobutton1:
                        setCurrentRadioButton(0);
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.third_radiobutton2:
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
        radioGroup = (RadioGroup) view.findViewById(R.id.third_radiogroup);
        viewPager = (ViewPager) view.findViewById(R.id.third_viewpager);
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
}