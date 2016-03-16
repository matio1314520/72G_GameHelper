package matio.angel.com.myapplication_gamehelper.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import matio.angel.com.myapplication_gamehelper.R;
import matio.angel.com.myapplication_gamehelper.adapter.FirstBaseAdapter;
import matio.angel.com.myapplication_gamehelper.adapter.ForthBaseAdapter;
import matio.angel.com.myapplication_gamehelper.asynctask.FirstAsyncTask;
import matio.angel.com.myapplication_gamehelper.bean.FirstPresent;
import matio.angel.com.myapplication_gamehelper.bean.ForthMoney;
import matio.angel.com.myapplication_gamehelper.common.FirstPresentConstant;
import matio.angel.com.myapplication_gamehelper.fragment.Callback;
import matio.angel.com.myapplication_gamehelper.fragment.Callback3;
import matio.angel.com.myapplication_gamehelper.utils.FirstJsonUtils;
import matio.angel.com.myapplication_gamehelper.utils.ForthJsonUtils;

/**
 * Created by Angel on 2016/1/18.
 */
public class SearchActivity extends AppCompatActivity {

    private EditText searchEdt;

    private ListView searchListView;

    private List<ForthMoney> list = new ArrayList<>();

    private List<FirstPresent> giftList = new ArrayList<>();

    private ForthBaseAdapter adapter;

    private ImageView searchImg;

    private RelativeLayout noinfoRel;

    private String type;

    private FirstBaseAdapter giftAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_search);

        //初始化控件
        initView();

        //从intent中获取数据
        getDataFromIntent();

        //点击搜索
        startSearch();

        //设置listview适配器
        setListViewAdapter();

        //设置listview的选择列表项监听
        setListViewItemListener();
    }

    /**
     * 从intent中获取数据
     */
    private void getDataFromIntent() {
        Intent intent = getIntent();
        type = intent.getStringExtra("type");
        Log.i("type", "getDataFromIntent: " + type);
        if ("gift".equals(type)) {
            searchEdt.setHint("请输入礼包名称");
        } else if ("game".equals(type)) {
            searchEdt.setHint("请输入游戏名称");
        }
    }

    /**
     * 设置listview的选择列表项监听
     */
    private void setListViewItemListener() {
        searchListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = null;
                if ("game".equals(type)) {
                    //搜索游戏
                    intent = new Intent(SearchActivity.this, GameDetailActivity.class);
                    if (list != null && list.size() > 0) {
                        intent.putExtra("id", list.get(position).getId());
                        intent.putExtra("gamename", list.get(position).getName());
                    }
                } else if ("gift".equals(type)) {
                    //搜索礼包
                    intent = new Intent(SearchActivity.this, GiftDetailActivity.class);
                    if (giftList != null && giftList.size() > 0) {
                        intent.putExtra("id", giftList.get(position).getId());
                    }
                }
                startActivity(intent);
            }
        });
    }

    /**
     * 点击搜索
     */
    private void startSearch() {
        searchImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gameName = searchEdt.getText().toString();

                if (!TextUtils.isEmpty(gameName)) {
                    try {
                        //获取网络数据
                        if (list != null) {
                            list.clear();
                        }
                        if ("game".equals(type)) {
                            //搜索游戏
                            getNetworkData(FirstPresentConstant.SEARCH_URL, FirstPresentConstant.SEARCH_PARAM + "&name=" + URLEncoder.encode(gameName, "UTF-8"));
                        } else if ("gift".equals(type)) {
                            //搜索礼包
                            getNetworkData(FirstPresentConstant.FIRST_URLPATH, FirstPresentConstant.SEARCH_PARAM + "&name=" + URLEncoder.encode(gameName, "UTF-8"));
                        }
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
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
    private void getNetworkData(String urlPath, String param) {
        new FirstAsyncTask(this, new Callback() {
            @Override
            public void callback(String string) {
                if ("game".equals(type)) {
                    //游戏详情
                    list.addAll(ForthJsonUtils.parse(string));
                    adapter.notifyDataSetChanged();
                } else if ("gift".equals(type)) {
                    //礼包详情
                    giftList.addAll(FirstJsonUtils.parse(string));
                    giftAdapter.notifyDataSetChanged();

                }
            }
        }, param).execute(urlPath);
    }

    /**
     * 设置listview适配器
     */
    private void setListViewAdapter() {
        if (list != null) {
            if ("game".equals(type)) {
                adapter = new ForthBaseAdapter(list, this, new Callback3() {
                    @Override
                    public void click(View view) {
                    }
                }, true);
                searchListView.setAdapter(adapter);
            } else if ("gift".equals(type)) {
                giftAdapter = new FirstBaseAdapter(giftList, this, new Callback3() {
                    @Override
                    public void click(View view) {
                    }
                });
                searchListView.setAdapter(giftAdapter);
            }
        } else {
            noinfoRel.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 初始化控件
     */
    private void initView() {
        searchEdt = (EditText) findViewById(R.id.search_first);
        searchListView = (ListView) findViewById(R.id.search_listview);
        searchImg = (ImageView) findViewById(R.id.startsearch_first);

        noinfoRel = (RelativeLayout) findViewById(R.id.noinfo_search);
    }
}