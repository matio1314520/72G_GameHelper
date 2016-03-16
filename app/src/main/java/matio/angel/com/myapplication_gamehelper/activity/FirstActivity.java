package matio.angel.com.myapplication_gamehelper.activity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

import matio.angel.com.myapplication_gamehelper.R;

/**
 * Created by Angel on 2016/1/18.
 */
public class FirstActivity extends AppCompatActivity {

    private ImageView firstImg;

    private ImageView secondImg;

    //补间动画
    private Animation firstAnim;

    private Animation secondAnim;

    private float curXDelta = 0;

    private float nextXDelta = 0;

    private float curYDelta = 0;

    private float nextYDelta = 420;

    private int count;

    private int num;

    private Timer timer;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animationpage);

        //从sharedpreference中获取“count”
        getCountFromSharedPref();

        //初始化控件
        initView();

        //设置补间动画
        setMyTranslateAnima();
    }

    /**
     * 从sharedpreference中获取“count”
     */
    private void getCountFromSharedPref() {

        sharedPreferences = getSharedPreferences("count", MODE_PRIVATE);

        //查找count 默认为0
        count = sharedPreferences.getInt("count", 0);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        //写入
        editor.putInt("count", ++count);

        //提交
        editor.commit();
    }

    /**
     * 设置补间动画
     */
    private void setMyTranslateAnima() {

        //加载补间动画
        firstAnim = AnimationUtils.loadAnimation(this, R.anim.firstanim);

        //启动第一个补间动画
        firstImg.startAnimation(firstAnim);

        //设置定时器
        setMyTimer();
    }

    /**
     * 设置定时器
     */
    private void setMyTimer() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(0x110);
            }
        }, 600);
    }

    /**
     * handler
     */
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0x110) {

                secondImg.setVisibility(View.VISIBLE);

                //启动属性动画
                startPropertyAnimation();
            }
        }
    };

    /**
     * 设置属性动画
     */
    private void startPropertyAnimation() {
        //通过静态工厂方法获取ObjectAnimator对象
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(secondImg, "y", secondImg.getY(), 400.0f);
        //设置执行的时间
        objectAnimator.setDuration(600);
        //设置插值器(用于控制速度）
        objectAnimator.setInterpolator(new BounceInterpolator());
        //设置动画的执行次数,0表示默认值
        objectAnimator.setRepeatCount(0);
        //设置动画的重复模式
        objectAnimator.setRepeatMode(ValueAnimator.REVERSE);
        //开启动画
        objectAnimator.start();

        //动画完成后跳转页面
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(600);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                goToAnotherActivity();
                finish();
            }
        }).start();
    }

    /**
     * 跳转页面
     */
    private void goToAnotherActivity() {
        Intent intent = null;
        if (count == 1) {
            intent = new Intent(this, MainActivity.class);
        } else {
            intent = new Intent(this, HomeActivity.class);
        }
        startActivity(intent);
    }

    /**
     * 初始化控件
     */
    private void initView() {
        firstImg = (ImageView) findViewById(R.id.first_anim);
        secondImg = (ImageView) findViewById(R.id.second_anim);

        //隐藏图片
        firstImg.setVisibility(View.INVISIBLE);
        secondImg.setVisibility(View.INVISIBLE);
    }
}