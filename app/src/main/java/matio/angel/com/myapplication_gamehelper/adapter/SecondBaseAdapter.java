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
import matio.angel.com.myapplication_gamehelper.asynctask.BitmapAsyncTask;
import matio.angel.com.myapplication_gamehelper.bean.SecondAreaActivity2;
import matio.angel.com.myapplication_gamehelper.cache.DoubleCache;
import matio.angel.com.myapplication_gamehelper.cache.ImageCache;
import matio.angel.com.myapplication_gamehelper.cache.ImageLoader;
import matio.angel.com.myapplication_gamehelper.viewholder.FistViewHolder;

/**
 * Created by Angel on 2016/1/11.
 */
public class SecondBaseAdapter extends BaseAdapter {

    private List<SecondAreaActivity2> presentList;
    private Context context;
    private ImageCache imageCache;
    private ImageLoader imageLoader;

    public SecondBaseAdapter(List<SecondAreaActivity2> presentList, Context context) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.second_listview, parent, false);
            viewHolder.iconImg = (ImageView) convertView.findViewById(R.id.second_image_listview2);
            viewHolder.nameTxt = (TextView) convertView.findViewById(R.id.second_title_listview2);
            viewHolder.remainTxt = (TextView) convertView.findViewById(R.id.second_left_listview2);
            viewHolder.contentTxt = (TextView) convertView.findViewById(R.id.second_join_listview2);
            viewHolder.endTxt = (TextView) convertView.findViewById(R.id.second_end_listview2);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (FistViewHolder) convertView.getTag();
        }
        SecondAreaActivity2 present = presentList.get(position);

        viewHolder.nameTxt.setText(present.getAnme());
        viewHolder.remainTxt.setText(present.getShortname());
        viewHolder.contentTxt.setText(present.getTotal_join_user());
        viewHolder.endTxt.setText(present.getStatus());

        imageLoader.displayImage(viewHolder.iconImg, present.getHotpic());
        return convertView;
    }
}
