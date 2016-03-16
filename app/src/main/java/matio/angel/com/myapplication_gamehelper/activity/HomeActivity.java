package matio.angel.com.myapplication_gamehelper.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import matio.angel.com.myapplication_gamehelper.R;
import matio.angel.com.myapplication_gamehelper.fragment.FifthMineFragment;
import matio.angel.com.myapplication_gamehelper.fragment.FirstPresentFragment;
import matio.angel.com.myapplication_gamehelper.fragment.ForthMoneyFragment;
import matio.angel.com.myapplication_gamehelper.fragment.SecondActivityFragment;
import matio.angel.com.myapplication_gamehelper.fragment.ThirdGameFragment;

/**
 * Created by Angel on 2016/1/11.
 * 主页面
 */
public class HomeActivity extends AppCompatActivity {

    private RadioGroup radioGroup;
    //图片资源ID；
    private int[] ic_menu_hlArr = {R.mipmap.ic_menu_01_hl, R.mipmap.ic_menu_02_hl,
            R.mipmap.ic_menu_05_hl, R.mipmap.ic_menu_03_hl, R.mipmap.ic_menu_04_hl};

    private int[] ic_menu_norArr = {R.mipmap.ic_menu_01_nor, R.mipmap.ic_menu_02_nor,
            R.mipmap.ic_menu_05_nor, R.mipmap.ic_menu_03_nor, R.mipmap.ic_menu_04_nor};

    private List<Fragment> fragmentList;

    private FragmentTransaction transaction;

    private FragmentManager manager;

    private LinearLayout imageLin;

    private Animation animation;

    private RadioButton firstRb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_main);

        //初始化控件
        initView();

        //隐藏图片
        setImageHidden();

        //初始化fragmentList
        initFragmentList();

        //radioGroup监听事件
        setRadioGroupListener();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        radioGroup = (RadioGroup) findViewById(R.id.radiogroup);

        imageLin = (LinearLayout) findViewById(R.id.lin);

        firstRb = (RadioButton) findViewById(R.id.firstPresentRbt);

        firstRb.setVisibility(View.INVISIBLE);

        animation = AnimationUtils.loadAnimation(this, R.anim.firstpage);
    }

    /**
     * 隐藏4张图片
     */
    private void setImageHidden() {
        for (int i = 1, len = imageLin.getChildCount(); i < len; i++) {
            imageLin.getChildAt(i).setVisibility(View.INVISIBLE);
        }
    }

    /**
     * 初始化fragmentList
     */
    private void initFragmentList() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new FirstPresentFragment());
        fragmentList.add(new SecondActivityFragment());
        fragmentList.add(new ThirdGameFragment());
        fragmentList.add(new ForthMoneyFragment());
        fragmentList.add(new FifthMineFragment());

        manager = getSupportFragmentManager();
        //设置默认的fragment
        initFragment(0);

    }

    /**
     * 设置默认的fragment
     *
     * @param whichOne 被选中的下标
     */
    private void initFragment(int whichOne) {
        //开启事务
        transaction = manager.beginTransaction();
        //
        transaction.add(R.id.container_fragment, fragmentList.get(whichOne));
        //提交事务
        transaction.commit();
    }

    /**
     * radioGroup设置监听事件
     */
    private void setRadioGroupListener() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Fragment fragment = null;
                transaction = manager.beginTransaction();
                for (int i = 0; i < group.getChildCount(); i++) {
                    fragment = fragmentList.get(i);
                    //获取图片控件
                    ImageView whichImg = (ImageView) imageLin.getChildAt(i);
                    //获取radiobutton控件
                    RadioButton whichRb = (RadioButton) group.getChildAt(i);
                    if (whichRb.getId() == checkedId) {
                        //隐藏radiobutton
                        whichRb.setVisibility(View.INVISIBLE);
                        //设置图片为可见
                        whichImg.setVisibility(View.VISIBLE);

                        whichImg.startAnimation(animation);

                        if (fragment.isAdded()) {

                            transaction.show(fragment);
                        } else {
                            transaction.add(R.id.container_fragment, fragment);
                        }
                    } else if (fragment.isAdded()) {
                        //显示randiobutton
                        whichRb.setVisibility(View.VISIBLE);
                        //隐藏图片
                        whichImg.setVisibility(View.INVISIBLE);

                        transaction.hide(fragmentList.get(i));
                    }
                }
                transaction.commitAllowingStateLoss();
            }
        });
    }
}