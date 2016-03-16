package matio.angel.com.myapplication_gamehelper.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import matio.angel.com.myapplication_gamehelper.R;
import matio.angel.com.myapplication_gamehelper.activity.LoginActivity;
import matio.angel.com.myapplication_gamehelper.bean.FirstPresent;
import matio.angel.com.myapplication_gamehelper.cache.DoubleCache;
import matio.angel.com.myapplication_gamehelper.cache.ImageCache;
import matio.angel.com.myapplication_gamehelper.cache.ImageLoader;
import matio.angel.com.myapplication_gamehelper.fragment.Callback;
import matio.angel.com.myapplication_gamehelper.fragment.Callback3;
import matio.angel.com.myapplication_gamehelper.viewholder.FistViewHolder;

/**
 * Created by Angel on 2016/1/11.
 */
public class FirstBaseAdapter extends BaseAdapter implements View.OnClickListener {

    private Callback3 callback3;

    private ImageCache imageCache;

    private ImageLoader imageLoader;

    private Context context;

    private List<FirstPresent> presentList;

    public FirstBaseAdapter(List<FirstPresent> presentList, Context context, Callback3 callback3) {
        this.presentList = presentList;
        this.context = context;
        this.callback3 = callback3;
        imageCache = new DoubleCache(context);

        imageLoader = ImageLoader.getInstance(context, imageCache);
    }

    @Override
    public int getCount() {
        if (presentList != null) {
            return presentList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return presentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FistViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new FistViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.first_listview1, parent, false);
            viewHolder.iconImg = (ImageView) convertView.findViewById(R.id.icon_listview1);
            viewHolder.nameTxt = (TextView) convertView.findViewById(R.id.name_listview1);
            viewHolder.remainTxt = (TextView) convertView.findViewById(R.id.remain_listview1);
            viewHolder.contentTxt = (TextView) convertView.findViewById(R.id.content_listview1);
            viewHolder.endTxt = (TextView) convertView.findViewById(R.id.get_listview1);
            viewHolder.clickBtn = (Button) convertView.findViewById(R.id.get_listview1);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (FistViewHolder) convertView.getTag();
        }
        FirstPresent present = presentList.get(position);

        viewHolder.nameTxt.setText(present.getName());
        viewHolder.remainTxt.setText(present.getRemain());
        viewHolder.contentTxt.setText(present.getContent());
        //加载图片
        imageLoader.displayImage(viewHolder.iconImg, present.getIcon());
        if (!"0".equals(present.getRemain())) {
            viewHolder.endTxt.setText("免费领取");
            viewHolder.endTxt.setTextColor(Color.BLUE);
        } else {
            viewHolder.endTxt.setText("淘号");
            viewHolder.endTxt.setTextColor(Color.YELLOW);
        }

        viewHolder.clickBtn.setOnClickListener(this);

        return convertView;
    }

    @Override
    public void onClick(View v) {
        callback3.click(v);

    }
}
