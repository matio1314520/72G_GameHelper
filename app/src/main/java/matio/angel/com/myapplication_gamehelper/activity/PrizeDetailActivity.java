package matio.angel.com.myapplication_gamehelper.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import matio.angel.com.myapplication_gamehelper.R;
import matio.angel.com.myapplication_gamehelper.asynctask.FirstAsyncTask;
import matio.angel.com.myapplication_gamehelper.bean.ThirdChange;
import matio.angel.com.myapplication_gamehelper.cache.DoubleCache;
import matio.angel.com.myapplication_gamehelper.cache.ImageLoader;
import matio.angel.com.myapplication_gamehelper.common.ThirdChangeConstant;
import matio.angel.com.myapplication_gamehelper.fragment.Callback;
import matio.angel.com.myapplication_gamehelper.utils.ThirdJsonUtils;

/**
 * Created by Angel on 2016/1/15.
 */

public class PrizeDetailActivity extends AppCompatActivity {

    private ImageView iconImg;

    private TextView nameTxt;

    private TextView remainTxt;

    private Button valueBtn;

    private ImageLoader imageLoader;

    private ThirdChange change;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prizedetail);

        imageLoader = ImageLoader.getInstance(this, new DoubleCache(this));
        //初始化控件
        initView();
        Intent intent = getIntent();
        if (intent != null) {
            String id = intent.getStringExtra("id");
            getNetworkData(id);
        }
    }

    /**
     * 获取网络数据
     */
    private void getNetworkData(String param) {

        new FirstAsyncTask(this, new Callback() {
            @Override
            public void callback(String string) {
                change = ThirdJsonUtils.parse2(string);

                imageLoader.displayImage(iconImg, change.getIcon());
                nameTxt.setText(change.getName());
                remainTxt.setText(change.getRemain());
                String value = change.getConsume();
                int valueInt = Integer.parseInt(value);
                if (valueInt > 1000) {
                    valueBtn.setText("立即兑换" + valueInt / 1000 + ",000U币");
                } else {
                    valueBtn.setText("立即兑换" + value + "U币");
                }
            }
        }, "id=" + param).execute(ThirdChangeConstant.PRIZEDETAIL_URL);
    }

    /**
     * 初始化控件
     */
    private void initView() {

        iconImg = (ImageView) findViewById(R.id.icon_prizedetail);
        nameTxt = (TextView) findViewById(R.id.name_prizedetail);
        remainTxt = (TextView) findViewById(R.id.remain_prizedatail);
        valueBtn = (Button) findViewById(R.id.value_prizedetail);
    }

    public void choose(View view) {
        switch (view.getId()) {
            case R.id.back_prizedetail:
                //返回上一页
                finish();
                break;
            case R.id.value_prizedetail:
                //选择立即兑换
                Intent intent = new Intent(this,LoginActivity.class);
                startActivity(intent);
                break;
        }
    }
}
