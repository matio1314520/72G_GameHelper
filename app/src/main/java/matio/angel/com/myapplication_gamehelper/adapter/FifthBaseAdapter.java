package matio.angel.com.myapplication_gamehelper.adapter;

import android.content.Context;
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
import matio.angel.com.myapplication_gamehelper.bean.SecondAreaActivity1;
import matio.angel.com.myapplication_gamehelper.cache.DoubleCache;
import matio.angel.com.myapplication_gamehelper.cache.ImageCache;
import matio.angel.com.myapplication_gamehelper.cache.ImageLoader;
import matio.angel.com.myapplication_gamehelper.fragment.Callback3;
import matio.angel.com.myapplication_gamehelper.viewholder.FistViewHolder;

/**
 * Created by Angel on 2016/1/13.
 */
public class FifthBaseAdapter extends BaseAdapter implements View.OnClickListener {

    private List<SecondAreaActivity1> presentList;

    private Context context;

    private ImageCache imageCache;

    private ImageLoader imageLoader;

    private Callback3 callback3;

    public FifthBaseAdapter(List<SecondAreaActivity1> presentList, Context context, Callback3 callback3) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.fifth_listview, parent, false);

            viewHolder.iconImg = (ImageView) convertView.findViewById(R.id.fifth_image_listview2);
            viewHolder.nameTxt = (TextView) convertView.findViewById(R.id.fifth_title_listview2);
            viewHolder.clickBtn = (Button) convertView.findViewById(R.id.fifth_ubi_listview2);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (FistViewHolder) convertView.getTag();

        }
        SecondAreaActivity1 present = presentList.get(position);

        imageLoader.displayImage(viewHolder.iconImg, present.getBimg());

        viewHolder.nameTxt.setText(present.getBname());

        viewHolder.clickBtn.setOnClickListener(this);
        return convertView;
    }

    @Override
    public void onClick(View v) {
        callback3.click(v);
    }
}
