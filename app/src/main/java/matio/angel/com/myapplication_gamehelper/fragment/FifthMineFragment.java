package matio.angel.com.myapplication_gamehelper.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import matio.angel.com.myapplication_gamehelper.R;
import matio.angel.com.myapplication_gamehelper.activity.GameDetailActivity;
import matio.angel.com.myapplication_gamehelper.activity.LoginActivity;
import matio.angel.com.myapplication_gamehelper.activity.MyPlayActivity;
import matio.angel.com.myapplication_gamehelper.activity.SettingActivity;
import matio.angel.com.myapplication_gamehelper.adapter.FifthBaseAdapter;
import matio.angel.com.myapplication_gamehelper.asynctask.FirstAsyncTask;
import matio.angel.com.myapplication_gamehelper.bean.SecondAreaActivity1;
import matio.angel.com.myapplication_gamehelper.common.FifthMineConstant;
import matio.angel.com.myapplication_gamehelper.utils.SecondJsonUtils;

/**
 * Created by Angel on 2016/1/8.
 */
public class FifthMineFragment extends Fragment implements View.OnClickListener {

    private View view;

    private RelativeLayout taskRel;

    private RelativeLayout wechatRel;

    private RelativeLayout inviteRel;

    private RelativeLayout setingRel;

    private ListView listView;

    private List<SecondAreaActivity1> list = new ArrayList<SecondAreaActivity1>();

    private FifthBaseAdapter adapter;

    private View headerView;

    private RelativeLayout loginRel;

    private RelativeLayout smallGameRel;

    private LinearLayout giftLin;

    private LinearLayout activityLin;

    private LinearLayout exchangeLin;

    private LinearLayout playLin;

    private ImageView moreImg;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fifth_mime, container, false);

        headerView = LayoutInflater.from(getActivity()).inflate(R.layout.fifth_header, null);

        //初始化控件
        initWedget();

        //设置点击监听
        setCilckListener();

        //获取网络数据
        getNetworkData();

        //设置listview适配器
        setListViewAdapter();

        //设置listview的选择列表项监听
        setListViewItemListener();

        return view;
    }

    /**
     * 设置listview的选择列表项监听
     */
    private void setListViewItemListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //跳往游戏详情页面
                Intent intent = new Intent(getActivity(), GameDetailActivity.class);
                if (list != null && list.size() > 0) {
                    intent.putExtra("id", list.get(position - 1).getTarget_id());
                    intent.putExtra("gamename", list.get(position - 1).getBname());
                }
                startActivity(intent);
            }
        });
    }

    /**
     * 设置点击监听
     */
    private void setCilckListener() {
        loginRel.setOnClickListener(this);
        taskRel.setOnClickListener(this);
        wechatRel.setOnClickListener(this);
        setingRel.setOnClickListener(this);
        smallGameRel.setOnClickListener(this);
        inviteRel.setOnClickListener(this);
        giftLin.setOnClickListener(this);
        activityLin.setOnClickListener(this);
        exchangeLin.setOnClickListener(this);
        playLin.setOnClickListener(this);
        moreImg.setOnClickListener(this);
    }

    /**
     * 设置listview适配器
     */
    private void setListViewAdapter() {
        adapter = new FifthBaseAdapter(list, getActivity(), new Callback3() {
            @Override
            public void click(View view) {
                //跳往游戏详情界面
                Intent intent = new Intent(getActivity(), GameDetailActivity.class);
                if (list != null && list.size() > 0) {
                    intent.putExtra("id", list.get(0).getTarget_id());
                    intent.putExtra("gamename", list.get(0).getBname());
                }
                startActivity(intent);
            }
        });
        listView.setAdapter(adapter);
    }

    /**
     * 获取网络数据
     */
    private void getNetworkData() {
        new FirstAsyncTask(getActivity(), new Callback() {
            @Override
            public void callback(String string) {

                list.addAll(SecondJsonUtils.parse(string));
                adapter.notifyDataSetChanged();
            }
        }, FifthMineConstant.FIFTH_PARAM).execute(FifthMineConstant.FIFTH_URPPATH);
    }

    /**
     * 初始化控件
     */
    private void initWedget() {
        listView = (ListView) (view.findViewById(R.id.listview_fifthmine));
        moreImg = (ImageView) view.findViewById(R.id.more);

        loginRel = (RelativeLayout) headerView.findViewById(R.id.login_header);

        taskRel = (RelativeLayout) headerView.findViewById(R.id.task_rel);
        wechatRel = (RelativeLayout) headerView.findViewById(R.id.wechat_rel);
        inviteRel = (RelativeLayout) headerView.findViewById(R.id.invite_rel);
        smallGameRel = (RelativeLayout) headerView.findViewById(R.id.smallgame);
        setingRel = (RelativeLayout) headerView.findViewById(R.id.setting_rel);

        giftLin = (LinearLayout) headerView.findViewById(R.id.gift_header);
        activityLin = (LinearLayout) headerView.findViewById(R.id.activity_header);
        exchangeLin = (LinearLayout) headerView.findViewById(R.id.exchange_header);
        playLin = (LinearLayout) headerView.findViewById(R.id.play_header);

        //添加头部
        listView.addHeaderView(headerView);
    }

    /**
     * 点击事件
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.more:
            case R.id.gift_header:
            case R.id.activity_header:
            case R.id.exchange_header:
            case R.id.task_rel:
            case R.id.wechat_rel:
            case R.id.invite_rel:
            case R.id.login_header:
                //跳往登录页面
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.smallgame:
                //跳往小游戏界面
                Intent intent1 = new Intent(getActivity(), SmallGameActivity.class);
                startActivity(intent1);
                break;
            case R.id.setting_rel:
                //跳往设置页面
                Intent intent2 = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent2);
                break;
            case R.id.play_header:
                //跳往试玩界面
                Intent intent3 = new Intent(getActivity(), MyPlayActivity.class);
                startActivity(intent3);
                break;
        }
    }
}