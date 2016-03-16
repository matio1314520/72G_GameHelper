package matio.angel.com.myapplication_gamehelper.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import matio.angel.com.myapplication_gamehelper.R;
import matio.angel.com.myapplication_gamehelper.asynctask.FirstAsyncTask;
import matio.angel.com.myapplication_gamehelper.bean.FirstGiftDetail;
import matio.angel.com.myapplication_gamehelper.cache.DoubleCache;
import matio.angel.com.myapplication_gamehelper.cache.ImageLoader;
import matio.angel.com.myapplication_gamehelper.common.FirstPresentConstant;
import matio.angel.com.myapplication_gamehelper.fragment.Callback;
import matio.angel.com.myapplication_gamehelper.fragment.Callback3;
import matio.angel.com.myapplication_gamehelper.utils.FirstItemJsonUtils;
import matio.angel.com.myapplication_gamehelper.utils.FirstJsonUtils;
import matio.angel.com.myapplication_gamehelper.utils.ShareUtils;

/**
 * Created by Angel on 2016/1/15.
 */
public class GiftDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView iconImg;

    private TextView nameTxt;

    private TextView leftTxt;

    private TextView sttimeTxt;

    private TextView typeTxt;

    private TextView sizeTxt;

    private TextView contentTxt;

    private TextView howgetTxt;

    private ProgressBar progressBar;

    private TextView left2Txt;

    private FirstGiftDetail giftDetail;

    private Intent intent;

    private ImageLoader imageLoader;

    private RelativeLayout hideLin;

    private Button getBbtn;

    private LinearLayout dialogLin;

    private ImageView wechatImg;

    private ImageView qqFriImg;

    private ImageView friCircleImg;

    private ImageView sinaImg;

    private Button cancelBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gift_detail);
        imageLoader = ImageLoader.getInstance(this, new DoubleCache(this));

        //初始化控件
        initView();

        //从intent中获取数据
        getDataFromIntent();

        //设置点击事件
        setMyClickListener();
    }

    /**
     * 设置点击事件
     */
    private void setMyClickListener() {
        wechatImg.setOnClickListener(this);
        sinaImg.setOnClickListener(this);
        friCircleImg.setOnClickListener(this);
        qqFriImg.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);
    }

    /**
     * 从intent中获取数据
     */
    private void getDataFromIntent() {
        intent = getIntent();
        if (intent != null) {

            String id = intent.getStringExtra("id");
            Log.i("mawentao", "getDataFromIntent: " + id);
            if (!TextUtils.isEmpty(id)) {
                //获取网络数据
                getNetworkData(id);
            }
        }


    }

    /**
     * 获取网络数据
     */
    private void getNetworkData(String param) {
        new FirstAsyncTask(this, new Callback() {
            @Override
            public void callback(String string) {

                giftDetail = FirstItemJsonUtils.parse(string);

                //设置文本及图片
                setTextAndImg();
            }
        }, "id=" + param).execute(FirstPresentConstant.FIRST_URLPATH_ITEM);
    }

    /**
     * 设置文本及图片
     */
    private void setTextAndImg() {
        left2Txt.setText(giftDetail.getConsume() + "U币");
        nameTxt.setText(giftDetail.getName());
        leftTxt.setText(giftDetail.getRemain() + "/" + giftDetail.getTotal());

        //加载图片
        imageLoader.displayImage(iconImg, giftDetail.getIcon());

        progressBar.setMax(Integer.valueOf(giftDetail.getTotal()));
        progressBar.setProgress(Integer.valueOf(giftDetail.getRemain()));

        sttimeTxt.setText(giftDetail.getStime() + "-" + giftDetail.getEtime());

        String type = giftDetail.getGame_type();
        String size = giftDetail.getSize();

        if (!"null".equals(type)) {
            //类型为空 ，，隐藏
            hideLin.setVisibility(View.VISIBLE);
            typeTxt.setText(type);
            sizeTxt.setText(size);
        } else {
            hideLin.setVisibility(View.GONE);
            getBbtn.setText("淘号");
        }
        contentTxt.setText(giftDetail.getContent());
        howgetTxt.setText(giftDetail.getHowget());
    }

    /**
     * 初始化控件
     */
    private void initView() {
        iconImg = (ImageView) findViewById(R.id.image_firstitem);

        nameTxt = (TextView) findViewById(R.id.name_firstitem);

        leftTxt = (TextView) findViewById(R.id.left_firstitem);

        sttimeTxt = (TextView) findViewById(R.id.endstart_firstitem);

        typeTxt = (TextView) findViewById(R.id.type_firstitem);

        sizeTxt = (TextView) findViewById(R.id.size_firstitem);

        contentTxt = (TextView) findViewById(R.id.giftcontent_firstitem);

        howgetTxt = (TextView) findViewById(R.id.getway_firstitem);

        progressBar = (ProgressBar) findViewById(R.id.progress_firstitem);

        left2Txt = (TextView) findViewById(R.id.money_firstitem);

        hideLin = (RelativeLayout) findViewById(R.id.hide_firstitem);

        getBbtn = (Button) findViewById(R.id.getcount_firstitem);

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
            case R.id.back_firstitem:
                //返回上一页
                finish();
                break;
            case R.id.share_firstitem:
                //分享 设置对话框
                setMyDialog();
                break;
            case R.id.getcount_firstitem:
                //立即领取 跳转页面
                Intent intent1 = new Intent(this, LoginActivity.class);
                startActivity(intent1);
                break;
            case R.id.download_giftdetail:
                //进入游戏详情页面
                toGameDetailPage();

                break;

        }
    }

    /**
     * 进入游戏详情界面
     */
    private void toGameDetailPage() {
        Intent intent2 = new Intent(this, GameDetailActivity.class);
        intent2.putExtra("id", giftDetail.getId());
        Log.i("matiosgame", "choose: " + giftDetail.getName() + giftDetail.getId());
        intent2.putExtra("gamename", giftDetail.getName());
        startActivity(intent2);

    }

    /**
     * 设置对话框
     */
    private void setMyDialog() {
        new AlertDialog.Builder(this)
                .setView(dialogLin)
                .create()
                .show();
    }

    /**
     * 点击事件
     *
     * @param v
     */
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