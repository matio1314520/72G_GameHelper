package matio.angel.com.myapplication_gamehelper.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import matio.angel.com.myapplication_gamehelper.R;
import matio.angel.com.myapplication_gamehelper.bean.FirstPresent;
import matio.angel.com.myapplication_gamehelper.cache.DoubleCache;
import matio.angel.com.myapplication_gamehelper.cache.ImageCache;
import matio.angel.com.myapplication_gamehelper.cache.ImageLoader;
import matio.angel.com.myapplication_gamehelper.viewholder.FistViewHolder;

/**
 * Created by Angel on 2016/1/15.
 */
public class ActivityDetailBaseAdapter extends BaseAdapter {
    private List<FirstPresent> presentList;
    private Context context;
    private ImageCache imageCache;
    private ImageLoader imageLoader;

    public ActivityDetailBaseAdapter(List<FirstPresent> presentList, Context context) {
        this.presentList = presentList;
        this.context = context;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.second_listview_speak, parent, false);

            viewHolder.iconImg = (ImageView) convertView.findViewById(R.id.icon_secondlistview);
            viewHolder.nameTxt = (TextView) convertView.findViewById(R.id.nickname_secondlistview);
            viewHolder.remainTxt = (TextView) convertView.findViewById(R.id.pudate_secondlistview);
            viewHolder.endTxt = (TextView) convertView.findViewById(R.id.floor_secondlistview);
            viewHolder.contentTxt = (TextView) convertView.findViewById(R.id.content_secondlistview);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (FistViewHolder) convertView.getTag();
        }

        FirstPresent present = presentList.get(position);

        viewHolder.nameTxt.setText(present.getName());
        viewHolder.remainTxt.setText(present.getRemain());
        viewHolder.contentTxt.setText(present.getContent());
        viewHolder.endTxt.setText(present.getId() + "楼");

        if (present.getIcon() != null) {
            imageLoader.displayImage(viewHolder.iconImg, present.getIcon());
        } else {
            viewHolder.iconImg.setImageResource(R.mipmap.touxiang);
        }
        return convertView;
    }
}
