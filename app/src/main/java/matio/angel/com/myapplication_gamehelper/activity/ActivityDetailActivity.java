package matio.angel.com.myapplication_gamehelper.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import matio.angel.com.myapplication_gamehelper.R;
import matio.angel.com.myapplication_gamehelper.adapter.ActivityDetailBaseAdapter;
import matio.angel.com.myapplication_gamehelper.asynctask.FirstAsyncTask;
import matio.angel.com.myapplication_gamehelper.bean.FirstPresent;
import matio.angel.com.myapplication_gamehelper.bean.SecondActivityDetailBean;
import matio.angel.com.myapplication_gamehelper.cache.DoubleCache;
import matio.angel.com.myapplication_gamehelper.cache.ImageLoader;
import matio.angel.com.myapplication_gamehelper.common.SecondActivityConstant;
import matio.angel.com.myapplication_gamehelper.fragment.Callback;
import matio.angel.com.myapplication_gamehelper.utils.ActivityDetailJsonUtils;
import matio.angel.com.myapplication_gamehelper.utils.SecondItemJsonUtils;
import matio.angel.com.myapplication_gamehelper.utils.ShareUtils;

/**
 * Created by Angel on 2016/1/15.
 * 活动详情
 */
public class ActivityDetailActivity extends AppCompatActivity implements View.OnClickListener {
    private Intent intent;

    private TextView anameTxt;

    private ImageView hotpicImg;

    private TextView writerTxt;

    private TextView pubdateTxt;

    private TextView contentView;

    //第几楼
    private EditText whichFloorEdt;

    //总共的楼层
    private TextView totalFloorTxt;

    private ListView listView;

    private SecondActivityDetailBean secondBean;

    private ImageLoader imageLoader;

    private View headerView;

    private ActivityDetailBaseAdapter adapter;

    private List<FirstPresent> presentList = new ArrayList<>();

    private boolean isAviableBottom;

    private int page = 1;

    private String id;

    private LinearLayout dialogLin;

    private ImageView wechatImg;

    private ImageView qqFriImg;

    private ImageView friCircleImg;

    private ImageView sinaImg;

    private Button cancelBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activitydetail);

        imageLoader = ImageLoader.getInstance(this, new DoubleCache(this));

        //初始化控件
        initView();

        //从Intent中获取数据
        getDataFromIntent();

        //设置listview适配器
        setListViewAdapter();

        //设置listview的滚动监听
        setListViewScrollListener();

        //设置点击监听事件
        setMyClickListener();
    }

    /**
     * 设置点击监听
     */
    private void setMyClickListener() {
        wechatImg.setOnClickListener(this);
        sinaImg.setOnClickListener(this);
        friCircleImg.setOnClickListener(this);
        qqFriImg.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);
    }

    /**
     * 从Intent中获取数据
     */
    private void getDataFromIntent() {
        intent = getIntent();
        if (intent != null) {
            id = intent.getStringExtra("id");
            //获取网络数据
            getNetworkData(id);
        }
    }

    /**
     * 设置listview的滚动监听
     */
    private void setListViewScrollListener() {
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && isAviableBottom) {
                    getListviewNetwortData(id, page);
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
        adapter = new ActivityDetailBaseAdapter(presentList, this);
        listView.setAdapter(adapter);
    }

    /**
     * 获取网络数据00
     */
    private void getNetworkData(String param) {

        getListviewNetwortData(param, 1);

        new FirstAsyncTask(this, new Callback() {
            @Override
            public void callback(String string) {
                //json解析网络数据存入list集合
                secondBean = SecondItemJsonUtils.parse(string);

                //设置文本图片
                setTextAndImg();

            }
        }, "id=" + param).execute(SecondActivityConstant.ACTIVITYDETAIL_HEADER_URL);
    }

    /**
     * 设置文本图片
     */
    private void setTextAndImg() {
        anameTxt.setText(secondBean.getAname());
        writerTxt.setText(secondBean.getWriter());
        pubdateTxt.setText(secondBean.getPubdate());
        contentView.setText(secondBean.getContent());

        //加载图片
        imageLoader.displayImage(hotpicImg, secondBean.getHotpic());
    }

    /**
     * 从网络中获取数据
     *
     * @param id
     * @param page
     */
    private void getListviewNetwortData(String id, int page) {
        new FirstAsyncTask(this, new Callback() {
            @Override
            public void callback(String string) {
                //json解析网络数据存入list集合

                presentList.addAll(ActivityDetailJsonUtils.parse(string));
                //唤醒
                adapter.notifyDataSetChanged();
                totalFloorTxt.setText("共" + presentList.get(0).getId() + "楼");
            }
        }, "id=" + id + "&page=" + page).execute(SecondActivityConstant.ACTIVITYDETAIL_URL);
    }

    /**
     * 初始化控件
     */
    private void initView() {
        headerView = getLayoutInflater().inflate(R.layout.second_header__getprize, null);
        anameTxt = (TextView) headerView.findViewById(R.id.aname_listviewheader);
        hotpicImg = (ImageView) headerView.findViewById(R.id.touxiang);
        writerTxt = (TextView) headerView.findViewById(R.id.writer_listviewheader);
        pubdateTxt = (TextView) headerView.findViewById(R.id.pudate_listviewheader);
        contentView = (TextView) headerView.findViewById(R.id.content_listviewheader);
        listView = (ListView) findViewById(R.id.speak_activitydetail);

        whichFloorEdt = (EditText) findViewById(R.id.whichfloor_activitydetail);
        totalFloorTxt = (TextView) findViewById(R.id.totalfloor_activitydetail);

        //listview添加头部
        listView.addHeaderView(headerView);

        dialogLin = (LinearLayout) getLayoutInflater().inflate(R.layout.dialog_view, null);
        wechatImg = (ImageView) dialogLin.findViewById(R.id.wechat);
        sinaImg = (ImageView) dialogLin.findViewById(R.id.sina);
        friCircleImg = (ImageView) dialogLin.findViewById(R.id.friendcircle);
        qqFriImg = (ImageView) dialogLin.findViewById(R.id.qqfriend);
        cancelBtn = (Button) dialogLin.findViewById(R.id.cancel_dialog);
    }

    /**
     * 点击事件
     *
     * @param view
     */
    public void choose(View view) {
        switch (view.getId()) {
            case R.id.back_second_activitydetail:
                //返回上一页，页面跳转
                finish();
                //返回上一页
                break;
            case R.id.share_second_activitydetail:
                //分享
                setMyDialog();
                break;
        }
    }

    /**
     * 制作对话框
     */
    private void setMyDialog() {
        new AlertDialog.Builder(this)
                .setView(dialogLin)
                .create()
                .show();

    }

    /**
     * 选定楼层后跳转页面
     *
     * @param view
     */
    public void nextPage(View view) {

        //输入的楼层
        String whichFloor = whichFloorEdt.getText().toString();

        //最高的楼层
        String maxFloor = presentList.get(0).getId();

        int whichFloorInt = Integer.parseInt(whichFloor);

        int maxFloorInt = Integer.parseInt(maxFloor);

        if (whichFloorInt <= maxFloorInt) {
            //跳转到"部分详情"页面
            Intent intent = new Intent(this, PartSpeakActivity.class);
            intent.putExtra("id", id);
            intent.putExtra("floor", whichFloor);
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sina:
                //新浪微博
                ShareUtils.toSina(this);
                break;

            case R.id.friendcircle:
                //朋友圈
            case R.id.wechat:
                //微信
                ShareUtils.toWechat(this);
                break;

            case R.id.qqfriend:
                //QQ
                ShareUtils.toQq(this);
                break;
            case R.id.cancel_dialog:
                finish();
                break;
        }
    }
}