package matio.angel.com.myapplication_gamehelper.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import matio.angel.com.myapplication_gamehelper.R;
import matio.angel.com.myapplication_gamehelper.activity.GameDetailActivity;
import matio.angel.com.myapplication_gamehelper.asynctask.FirstAsyncTask;
import matio.angel.com.myapplication_gamehelper.bean.ForthGuessLike;
import matio.angel.com.myapplication_gamehelper.cache.DoubleCache;
import matio.angel.com.myapplication_gamehelper.cache.ImageLoader;
import matio.angel.com.myapplication_gamehelper.common.ForthMoneyConstant;
import matio.angel.com.myapplication_gamehelper.utils.ForthGuessJsonUtils;

/**
 * Created by Angel on 2016/1/21.
 */
public class GamedetailFragment extends Fragment {
    private View view;

    private ImageLoader imageLoader;

    private List<ForthGuessLike> list = new ArrayList<>();

    private LinearLayout imgLin;

    private LinearLayout contentLin;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.gamedetail_fragment, container, false);

        imageLoader = ImageLoader.getInstance(getActivity(), new DoubleCache(getActivity()));

        //初始化控件
        initView();

        //从网络获取数据
        getDataFromNetwork("id=221&platform=2");

        return view;
    }

    /**
     * 初始化控件
     */
    private void initView() {
        imgLin = (LinearLayout) view.findViewById(R.id.image_guess);
        contentLin = (LinearLayout) view.findViewById(R.id.content_guess);
    }

    /**
     * 从网络获取数据
     */
    private void getDataFromNetwork(String param) {
        new FirstAsyncTask(getActivity(), new Callback() {
            @Override
            public void callback(String string) {
                list.addAll(ForthGuessJsonUtils.parse(string));
                //设置数据
                setDataAndIimg();
            }
        }, param).execute(ForthMoneyConstant.GUESSULIKE_URL);
    }

    /**
     * 设置数据
     */
    private void setDataAndIimg() {
        for (int i = 0, size = list.size(); i < size; i++) {
            ImageView imageView = (ImageView) imgLin.getChildAt(i);
            final int finalI = i;
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //跳往游戏详情界面
                    Intent intent = new Intent(getActivity(), GameDetailActivity.class);
                    intent.putExtra("id", list.get(finalI).getId());
                    intent.putExtra("gamename", list.get(finalI).getName());
                    startActivity(intent);
                }
            });
            TextView textView = (TextView) contentLin.getChildAt(i);
            imageLoader.displayImage(imageView, list.get(i).getIcon());
            textView.setText(list.get(i).getName() + "\n" + list.get(i).getCount_dl() + "下载");
        }
    }
}
