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
 * Created by Angel on 2016/1/20.
 */
public class GameDetailAdapter extends BaseAdapter {
    private List<FirstPresent> presentList;
    private Context context;
    private ImageCache imageCache;
    private ImageLoader imageLoader;

    public GameDetailAdapter(List<FirstPresent> presentList, Context context) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.gamedetail_listview3, parent, false);
            viewHolder.nameTxt = (TextView) convertView.findViewById(R.id.nickname_gamedetail);
            viewHolder.remainTxt = (TextView) convertView.findViewById(R.id.pudate_gamedetail);
            viewHolder.contentTxt = (TextView) convertView.findViewById(R.id.content_gamedetail);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (FistViewHolder) convertView.getTag();
        }

        FirstPresent present = presentList.get(position);

        viewHolder.nameTxt.setText(present.getName());
        viewHolder.remainTxt.setText(present.getRemain());
        viewHolder.contentTxt.setText(present.getContent());

        return convertView;
    }
}
