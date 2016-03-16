package matio.angel.com.myapplication_gamehelper.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import matio.angel.com.myapplication_gamehelper.R;
import matio.angel.com.myapplication_gamehelper.adapter.FirstBaseAdapter;
import matio.angel.com.myapplication_gamehelper.adapter.GameDetailAdapter;
import matio.angel.com.myapplication_gamehelper.asynctask.FirstAsyncTask;
import matio.angel.com.myapplication_gamehelper.bean.FirstPresent;
import matio.angel.com.myapplication_gamehelper.bean.ForthGameDetail;
import matio.angel.com.myapplication_gamehelper.cache.DoubleCache;
import matio.angel.com.myapplication_gamehelper.cache.ImageLoader;
import matio.angel.com.myapplication_gamehelper.common.ForthMoneyConstant;
import matio.angel.com.myapplication_gamehelper.fragment.Callback;
import matio.angel.com.myapplication_gamehelper.fragment.Callback3;
import matio.angel.com.myapplication_gamehelper.service.MyDownloadService;
import matio.angel.com.myapplication_gamehelper.utils.ActivityDetailJsonUtils;
import matio.angel.com.myapplication_gamehelper.utils.FirstJsonUtils;
import matio.angel.com.myapplication_gamehelper.utils.GameDetailJsonUtils;
import matio.angel.com.myapplication_gamehelper.utils.ShareUtils;
import matio.angel.com.myapplication_gamehelper.utils.ViewPagerUtils;

/**
 * Created by Angel on 2016/1/15.
 */
public class GameDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private ForthGameDetail gameDetail;

    private ImageView snapshotImg;

    private ImageView iconImg;

    private TextView nameTxt;

    private RatingBar scoreRb;

    private TextView versionSizeCountTxt;

    private TextView gamestateTxt;

    private TextView jifenTxt;

    private TextView gamedescTxt;

    private ImageLoader imageLoader;

    private String[] snapshotArr;

    private ViewPager viewPager;

    private ListView listView;

    private Intent intent;

    private List<View> viewList = new ArrayList<>();

    private View view1;

    private View view2;

    private View view3;

    private List<FirstPresent> list = new ArrayList<>();

    private FirstBaseAdapter adapter;

    private List<FirstPresent> presentList = new ArrayList<>();

    private ListView listView2;

    private GameDetailAdapter adapter2;

    private RadioGroup radioGroup;

    private boolean isAviableBottom;

    private int page = 1;

    private String id;

    private ImageView shareImg;

    private Button loadBtn;

    private LinearLayout noinfoLin;

    private LinearLayout loadLin;

    private LinearLayout publishLin;

    private LinearLayout dialogLin;

    private ImageView wechatImg;

    private ImageView qqFriImg;

    private ImageView friCircleImg;

    private ImageView sinaImg;

    public static final String ACTION_DOWNLOAD_PROGRESS = "my_download_progress";

    public static final String ACTION_DOWNLOAD_SUCCESS = "my_download_success";

    public static final String ACTION_DOWNLOAD_FAIL = "my_download_fail";

    private int flag = 0;

    private MyReceiver receiver;

    private Button cancelBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gamedetail);

        imageLoader = ImageLoader.getInstance(this, new DoubleCache(this));

        receiver = new MyReceiver();

        //初始化viewpager中视图数据源
        initViewPagerViewResource();

        //初始化控件
        initWedget();

        //从intent中取出数据
        getDataFromIntent();

        //设置viewpager适配器
        setViewPagerAdapter();

        //设置listview适配器
        setListViewAdapter();

        //设置点击监听
        setClickListener();

        //设置radiogroup的选中监听
        setRadioGroupListener();

        //设置viewpager监听器
        setViewPagerListener();

        //设置listview的滚动监听
        setListViewScrollListener();
    }

    private void setListViewScrollListener() {
        listView2.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && isAviableBottom) {
                    getNetworkData3("id=" + id + "&page=" + page);
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
     * 设置radiogroup的选中监听
     */
    private void setRadioGroupListener() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                for (int i = 0, count = group.getChildCount(); i < count; i++) {
                    RadioButton rb = (RadioButton) group.getChildAt(i);
                    if (checkedId == rb.getId()) {
                        viewPager.setCurrentItem(i);
                        //设置视图的显示或隐藏
                        setVisibleOrNor(i);
                        rb.setTextColor(Color.WHITE);
                        rb.setBackgroundColor(Color.BLUE);
                    } else {
                        rb.setTextColor(Color.BLUE);
                        rb.setBackgroundColor(Color.WHITE);
                    }
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
                for (int i = 0, count = radioGroup.getChildCount(); i < count; i++) {
                    if (i == position) {
                        ((RadioButton) radioGroup.getChildAt(position)).setChecked(true);
                    }
                    //设置视图的显示或隐藏
                    setVisibleOrNor(position);

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    /**
     * 设置视图的显示或隐藏
     *
     * @param whichOne
     */
    private void setVisibleOrNor(int whichOne) {
        if (whichOne == 2) {
            publishLin.setVisibility(View.VISIBLE);
            loadLin.setVisibility(View.GONE);
        } else {
            loadLin.setVisibility(View.VISIBLE);
            publishLin.setVisibility(View.GONE);
        }
    }

    /**
     * 设置listview适配器
     */
    private void setListViewAdapter() {
        adapter = new FirstBaseAdapter(presentList, this, new Callback3() {
            @Override
            public void click(View view) {
                Intent intent1 = new Intent(GameDetailActivity.this, LoginActivity.class);
                startActivity(intent1);
            }
        });
        listView.setAdapter(adapter);

        adapter2 = new GameDetailAdapter(list, this);
        listView2.setAdapter(adapter2);
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
        view1 = LayoutInflater.from(this).inflate(R.layout.gamedetail_viewpager1, null);
        view2 = LayoutInflater.from(this).inflate(R.layout.gamedetail_viewpager2, null);
        view3 = LayoutInflater.from(this).inflate(R.layout.gamedetail_viewpager3, null);

        //添加视图
        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);
    }

    //从intent中取出数据
    private void getDataFromIntent() {
        intent = getIntent();
        id = intent.getStringExtra("id");
        String gamename = intent.getStringExtra("gamename");
        if (!TextUtils.isEmpty(gamename) && !TextUtils.isEmpty(id)) {
            //获取网络数据
            getNetworkData("id=" + id, gamename);
        }
    }

    /**
     * 设置点击监听
     */
    private void setClickListener() {
        snapshotImg.setOnClickListener(this);
        shareImg.setOnClickListener(this);
        loadBtn.setOnClickListener(this);
        wechatImg.setOnClickListener(this);
        sinaImg.setOnClickListener(this);
        friCircleImg.setOnClickListener(this);
        qqFriImg.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);
    }

    /**
     * 初始化控件
     */
    private void initWedget() {
        snapshotImg = (ImageView) findViewById(R.id.snapshot_gamedetail);
        iconImg = (ImageView) findViewById(R.id.icon_gamedetail);
        nameTxt = (TextView) findViewById(R.id.name_gamedetail);
        scoreRb = (RatingBar) findViewById(R.id.score_gamedetail);
        versionSizeCountTxt = (TextView) findViewById(R.id.versionsizecount_gamedetail);
        gamedescTxt = (TextView) view1.findViewById(R.id.gamedesc_viewpager1);
        gamestateTxt = (TextView) view1.findViewById(R.id.game_task_state_viewpager1);
        jifenTxt = (TextView) view1.findViewById(R.id.dl_back_jifen_viewpager1);
        viewPager = (ViewPager) findViewById(R.id.vieapager_gamedetail);
        listView = (ListView) view2.findViewById(R.id.listview_viewpager2);
        listView2 = (ListView) view3.findViewById(R.id.listview_viewpager3);
        radioGroup = (RadioGroup) findViewById(R.id.radiogroup_gamedetail);
        shareImg = (ImageView) findViewById(R.id.share_gamedetail);
        loadBtn = (Button) findViewById(R.id.load_gamedetail);
        noinfoLin = (LinearLayout) view2.findViewById(R.id.noinfo);
        loadLin = (LinearLayout) findViewById(R.id.load_lin_gamedetail);
        publishLin = (LinearLayout) findViewById(R.id.publishspeak_lin);
        dialogLin = (LinearLayout) getLayoutInflater().inflate(R.layout.dialog_view, null);
        wechatImg = (ImageView) dialogLin.findViewById(R.id.wechat);
        sinaImg = (ImageView) dialogLin.findViewById(R.id.sina);
        friCircleImg = (ImageView) dialogLin.findViewById(R.id.friendcircle);
        qqFriImg = (ImageView) dialogLin.findViewById(R.id.qqfriend);
        cancelBtn = (Button) dialogLin.findViewById(R.id.cancel_dialog);
    }

    /**
     * 获取网络数据
     */
    private void getNetworkData(String param, String gamename) {
        //请求第一页的游戏信息数据
        new FirstAsyncTask(this, new Callback() {
            @Override
            public void callback(String string) {
                //json解析网络数据存入list集合
                gameDetail = GameDetailJsonUtils.parse(string);

                //设置图片及文本
                setTxtAndImg();
            }
        }, param).execute(ForthMoneyConstant.GAMEDETAIL_URL1);

        //请求第二页的游戏礼包数据
        new FirstAsyncTask(this, new Callback() {
            @Override
            public void callback(String string) {
                //json解析网络数据存入list集合
                presentList.addAll(FirstJsonUtils.parse(string));
                if (presentList != null && presentList.size() > 0) {
                    //有数据 ,隐藏
                    noinfoLin.setVisibility(View.GONE);
                } else {
                    noinfoLin.setVisibility(View.VISIBLE);
                }
                adapter.notifyDataSetChanged();
            }
        }, "gamename=" + gamename + "&platform=2&gifttype=1").execute(ForthMoneyConstant.GAMEDETAIL_URL2);

        //请求第三页的网络数据
        getNetworkData3(param);
    }

    /**
     * 请求第三页的网络数据
     *
     * @param param post请求参数
     */
    private void getNetworkData3(String param) {
        //请求第三页的评论数据
        new FirstAsyncTask(this, new Callback() {
            @Override
            public void callback(String string) {
                //json解析网络数据存入list集合
                list.addAll(ActivityDetailJsonUtils.parse(string));
                adapter.notifyDataSetChanged();
            }
        }, param).execute(ForthMoneyConstant.GAMEDETAIL_URL3);
    }

    /**
     * 设置图片集文本
     */
    private void setTxtAndImg() {
        //拆分
        snapshotArr = gameDetail.getSnapshot().split(",");

        imageLoader.displayImage(snapshotImg, snapshotArr[0]);

        imageLoader.displayImage(iconImg, gameDetail.getIcon());

        nameTxt.setText(gameDetail.getName());

        scoreRb.setRating(Float.valueOf(gameDetail.getScore()) / 2);

        versionSizeCountTxt.setText(gameDetail.getVersion() + " | " + gameDetail.getSize() +
                " | " + gameDetail.getCount_dl() + "人下载");

        gamedescTxt.setText(gameDetail.getGame_desc());

        jifenTxt.setText("+" + gameDetail.getDl_back_jifen());

        gamestateTxt.setText(gameDetail.getGame_task_state());
    }

    /**
     * 点击事件
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_gamedetail:
                //返回
                finish();
                break;
            case R.id.snapshot_gamedetail:
                //跳转页面到图片界面
                Intent snapshotIntent = new Intent(this, SnapshotActivity.class);
                snapshotIntent.putExtra("snapshot", snapshotArr);
                startActivity(snapshotIntent);
                break;
            case R.id.share_gamedetail:
                //对话框
                setMyDialog();
                break;
            case R.id.publishspeak_gamedetail:
                //发表评论，跳转到登录界面
            case R.id.submit_gamedetail:
                //提交,跳转到登录界面
                Intent loginIntent = new Intent(this, LoginActivity.class);
                startActivity(loginIntent);
                break;
            case R.id.load_gamedetail:
                //下载游戏
           //     downloadGame();
                break;

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

    /**
     * 下载游戏
     */
    private void downloadGame() {
        if (flag != 0) {
            if ((flag % 2) == 1) {
                if (MyDownloadService.getInstance() != null) {
                    loadBtn.setText((float) (Math.round(MyDownloadService.getInstance().getProgress() * 10)) / 10 + "%");
                }
                startDownloadService();
            }
            if ((flag % 2) == 0) {
                pauseDownloadService();
            }
        }
    }

    /**
     * 开启下载服务
     */
    public void startDownloadService() {
        if (MyDownloadService.getInstance() != null
                && MyDownloadService.getInstance().getFlag() != MyDownloadService.Flag_Init) {
            Toast.makeText(this, "已经在下载", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent it = new Intent(this, MyDownloadService.class);
        it.putExtra("flag", "start");
        it.putExtra("url", gameDetail.getAndroid_dl());
        startService(it);
    }

    /**
     * 暂停下载服务
     */
    public void pauseDownloadService() {
        String flag = null;
        int f = MyDownloadService.getInstance().getFlag();
        if (MyDownloadService.getInstance() != null) {
            // 如果当前已经暂停，则恢复
            if (f == MyDownloadService.Flag_Pause) {
                flag = "resume";
            } else if (f == MyDownloadService.Flag_Down) {
                flag = "pause";
            } else {
                return;
            }
        }
        Intent it = new Intent(this, MyDownloadService.class);
        it.putExtra("flag", flag);
        startService(it);
    }

    /**
     * 停止下载服务
     */
    public void stopDownloadService() {
        Intent it = new Intent(this, MyDownloadService.class);
        it.putExtra("flag", "stop");
        startService(it);
        loadBtn.setText("下载");
    }

//    public void onBackPressed() {
//        final int f = MyDownloadService.getInstance().getFlag();
//        //XXX:暂停状态下退出？？？
//        if (f == MyDownloadService.Flag_Down || f == MyDownloadService.Flag_Pause) {
//            new AlertDialog.Builder(this)
//                    .setTitle("确定退出程序？")
//                    .setMessage("你有未完成的下载任务")
//                    .setNegativeButton("取消下载", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            stopDownloadService();
//                            GameDetailActivity.super.onBackPressed();
//                        }
//                    })
//                    .setPositiveButton("后台下载", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//
//                            if (f == MyDownloadService.Flag_Pause) {
//                                Intent it = new Intent(GameDetailActivity.this, MyDownloadService.class);
//                                it.putExtra("flag", "resume");
//                                startService(it);
//                            }
//                            GameDetailActivity.super.onBackPressed();
//                        }
//                    })
//                    .create()
//                    .show();
//            return;
//        }
//        if (MyDownloadService.getInstance() != null)
//            MyDownloadService.getInstance().stopSelf();
//        super.onBackPressed();
//    }

    /**
     * 制作对话框
     */
    private void setMyDialog() {
        new AlertDialog.Builder(this)
                .setView(dialogLin)
                .create()
                .show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION_DOWNLOAD_PROGRESS);
        filter.addAction(ACTION_DOWNLOAD_SUCCESS);
        filter.addAction(ACTION_DOWNLOAD_FAIL);
        registerReceiver(receiver, filter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(receiver);
    }

    class MyReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(ACTION_DOWNLOAD_PROGRESS)) {
                float pro = intent.getExtras().getFloat("progress");
                pro = (float) (Math.round(pro * 10)) / 10;
                Log.i("demo", "mButton ------ > " + pro + "%");
                loadBtn.setText(pro + "%");
            } else if (action.equals(ACTION_DOWNLOAD_SUCCESS)) {
                Toast.makeText(GameDetailActivity.this, "下载成功", Toast.LENGTH_SHORT).show();
            } else if (action.equals(ACTION_DOWNLOAD_FAIL)) {
                Toast.makeText(GameDetailActivity.this, "下载失败", Toast.LENGTH_SHORT).show();
            }
        }
    }
}