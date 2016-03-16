package matio.angel.com.myapplication_gamehelper.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import matio.angel.com.myapplication_gamehelper.R;
import matio.angel.com.myapplication_gamehelper.activity.GameDetailActivity;
import matio.angel.com.myapplication_gamehelper.activity.LoginActivity;
import matio.angel.com.myapplication_gamehelper.activity.SearchActivity;
import matio.angel.com.myapplication_gamehelper.adapter.ForthBaseAdapter;
import matio.angel.com.myapplication_gamehelper.asynctask.FirstAsyncTask;
import matio.angel.com.myapplication_gamehelper.bean.ForthMoney;
import matio.angel.com.myapplication_gamehelper.common.ForthMoneyConstant;
import matio.angel.com.myapplication_gamehelper.utils.ForthJsonUtils;

/**
 * Created by Angel on 2016/1/8.
 */
public class ForthMoneyFragment extends Fragment {

    private ListView listView;

    private View view;

    private ForthBaseAdapter adapter;

    private List<ForthMoney> list = new ArrayList<>();

    private LinearLayout searchGameLin;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //加载布局
        view = inflater.inflate(R.layout.forth_money, container, false);

        //初始化控件
        initWedget();

        //异步任务获取网络数据
        getNetworkData(ForthMoneyConstant.PRAM);

        //设置listview适配器
        setListViewAdapter();

        //设置listview的选择列表项的监听
        setListViewListener();

        return view;
    }

    /**
     * 设置listview的选择列表项的监听
     */
    private void setListViewListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //页面跳转游戏详情界面
                Intent intent = new Intent(getActivity(), GameDetailActivity.class);
                if (list != null && list.size() > 0) {
                    String itemId = list.get(position).getId();
                    String gamename = list.get(position).getName();
                    if (!TextUtils.isEmpty(itemId)) {
                        intent.putExtra("id", itemId);
                        intent.putExtra("gamename", gamename);
                        startActivity(intent);
                    }
                }
            }
        });
    }

    /**
     * 获取网络数据
     *
     * @param param post请求参数
     */
    private void getNetworkData(String param) {
        new FirstAsyncTask(getActivity(), new Callback() {
            @Override
            public void callback(String string) {
                list.addAll(ForthJsonUtils.parse(string));
                adapter.notifyDataSetChanged();
            }
        }, param).execute(ForthMoneyConstant.PATH);
    }

    /**
     * 设置listview适配器
     */
    private void setListViewAdapter() {
        adapter = new ForthBaseAdapter(list, getActivity(), new Callback3() {
            @Override
            public void click(View view) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        }, false);
        listView.setAdapter(adapter);
    }

    /**
     * 初始化控件
     */
    private void initWedget() {
        listView = (ListView) view.findViewById(R.id.forthmoney_listview);

        searchGameLin = (LinearLayout) view.findViewById(R.id.searchgame_lin);
    }

    /**
     * 点击事件
     *
     * @param view
     */
    public void doMyChoose(View view) {
        switch (view.getId()) {
            case R.id.jifen:
            case R.id.todaytask:
            case R.id.qiandao:
            case R.id.first_myinfo:
                //跳往登录界面
                toLoginPage();
                break;
            case R.id.search:
            case R.id.searchgame:
                //跳往搜索界面
                toSearchPage();
                break;
        }
    }

    /**
     * 跳往登录界面
     */
    private void toLoginPage() {
        Intent loginIntent = new Intent(getActivity(), LoginActivity.class);
        startActivity(loginIntent);
    }

    /**
     * 跳往搜索界面
     */
    private void toSearchPage() {
        Intent searchIntent = new Intent(getActivity(), SearchActivity.class);
        searchIntent.putExtra("type", "game");
        startActivity(searchIntent);
    }
}