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
import matio.angel.com.myapplication_gamehelper.bean.SecondAreaActivity2;
import matio.angel.com.myapplication_gamehelper.bean.SmallGame;
import matio.angel.com.myapplication_gamehelper.cache.DoubleCache;
import matio.angel.com.myapplication_gamehelper.cache.ImageCache;
import matio.angel.com.myapplication_gamehelper.cache.ImageLoader;
import matio.angel.com.myapplication_gamehelper.viewholder.FistViewHolder;

/**
 * Created by Angel on 2016/1/22.
 */
public class SmallGameAdapter extends BaseAdapter {
    private List<SmallGame> presentList;
    private Context context;
    private ImageCache imageCache;
    private ImageLoader imageLoader;

    public SmallGameAdapter(List<SmallGame> presentList, Context context) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.smallgame_listview, parent, false);

            viewHolder.iconImg = (ImageView) convertView.findViewById(R.id.third_icon_listview1);
            viewHolder.nameTxt = (TextView) convertView.findViewById(R.id.third_name_listview1);
            viewHolder.remainTxt = (TextView) convertView.findViewById(R.id.third_left);
            viewHolder.contentTxt = (TextView) convertView.findViewById(R.id.third_one);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (FistViewHolder) convertView.getTag();
        }
        SmallGame smallGame = presentList.get(position);

        viewHolder.nameTxt.setText(smallGame.getName());
        viewHolder.remainTxt.setText(smallGame.getClick() + "人下载");
        viewHolder.contentTxt.setText(smallGame.getGame_desc());

        imageLoader.displayImage(viewHolder.iconImg, smallGame.getIcon());

        return convertView;
    }
}
