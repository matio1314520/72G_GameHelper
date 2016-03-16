package matio.angel.com.myapplication_gamehelper.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import matio.angel.com.myapplication_gamehelper.R;
import matio.angel.com.myapplication_gamehelper.asynctask.BitmapAsyncTask;
import matio.angel.com.myapplication_gamehelper.bean.FirstPresent;
import matio.angel.com.myapplication_gamehelper.bean.ThirdChange;
import matio.angel.com.myapplication_gamehelper.cache.DoubleCache;
import matio.angel.com.myapplication_gamehelper.cache.ImageCache;
import matio.angel.com.myapplication_gamehelper.cache.ImageLoader;
import matio.angel.com.myapplication_gamehelper.fragment.Callback3;
import matio.angel.com.myapplication_gamehelper.viewholder.FistViewHolder;

/**
 * Created by Angel on 2016/1/11.
 */
public class ThirdNewBaseAdapter extends BaseAdapter implements View.OnClickListener {

    private List<ThirdChange> presentList;

    private Context context;

    private Callback3 callback3;

    private ImageCache imageCache;

    private ImageLoader imageLoader;

    public ThirdNewBaseAdapter(List<ThirdChange> presentList, Context context, Callback3 callback3) {
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
        FistViewHolder viewHolder = new FistViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.third_listview1, parent, false);
            viewHolder.iconImg = (ImageView) convertView.findViewById(R.id.third_icon_listview1);
            viewHolder.nameTxt = (TextView) convertView.findViewById(R.id.third_name_listview1);
            viewHolder.remainTxt = (TextView) convertView.findViewById(R.id.third_remain_listview1);
            viewHolder.contentTxt = (TextView) convertView.findViewById(R.id.third_content_listview1);
            viewHolder.endTxt = (TextView) convertView.findViewById(R.id.thirdget_listview1);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (FistViewHolder) convertView.getTag();
        }
        ThirdChange present = presentList.get(position);

        viewHolder.nameTxt.setText(present.getName());
        viewHolder.remainTxt.setText(present.getConsume());
        viewHolder.contentTxt.setText(present.getRemain());
        imageLoader.displayImage(viewHolder.iconImg, present.getIcon());
        viewHolder.endTxt.setOnClickListener(this);

        return convertView;
    }

    @Override
    public void onClick(View v) {
        callback3.click(v);
    }
}
