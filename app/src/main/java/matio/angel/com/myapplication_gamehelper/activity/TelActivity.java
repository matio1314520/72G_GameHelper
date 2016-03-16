package matio.angel.com.myapplication_gamehelper.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import matio.angel.com.myapplication_gamehelper.R;

/**
 * Created by Angel on 2016/1/19.
 */
public class TelActivity extends AppCompatActivity {

    private TextView telTxt;

    private Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tel_correct);

        //初始化控件
        initView();

        //从intent中获取数据
        getDataFromIntent();
    }

    /**
     * 从intent中获取数据
     */
    private void getDataFromIntent() {
        intent = getIntent();
        String tel = intent.getStringExtra("tel");
        telTxt.setText(tel);
    }

    /**
     * 初始化控件
     */
    private void initView() {
        telTxt = (TextView) findViewById(R.id.youtel_correct);
    }
}
