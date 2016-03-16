package matio.angel.com.myapplication_gamehelper.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

import matio.angel.com.myapplication_gamehelper.R;
import matio.angel.com.myapplication_gamehelper.asynctask.BitmapAsyncTask;
import matio.angel.com.myapplication_gamehelper.bean.ForthMoney;
import matio.angel.com.myapplication_gamehelper.cache.DoubleCache;
import matio.angel.com.myapplication_gamehelper.cache.ImageCache;
import matio.angel.com.myapplication_gamehelper.cache.ImageLoader;
import matio.angel.com.myapplication_gamehelper.fragment.Callback3;
import matio.angel.com.myapplication_gamehelper.viewholder.FistViewHolder;
import matio.angel.com.myapplication_gamehelper.viewholder.ForthViewHolder;

/**
 * Created by Angel on 2016/1/11.
 */
public class ForthBaseAdapter extends BaseAdapter implements View.OnClickListener {

    private List<ForthMoney> presentList;
    private Context context;
    private ImageCache imageCache;
    private ImageLoader imageLoader;
    private boolean isHidden;

    private Callback3 callback3;

    public ForthBaseAdapter(List<ForthMoney> presentList, Context context, Callback3 callback3, boolean isHidden) {
        this.presentList = presentList;
        this.context = context;
        this.isHidden = isHidden;
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
        ForthViewHolder viewHolder = new ForthViewHolder();

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.foth_listview, parent, false);
            viewHolder.iconImg = (ImageView) convertView.findViewById(R.id.forth_image_listview2);
            viewHolder.nameTxt = (TextView) convertView.findViewById(R.id.forth_title_listview2);
            viewHolder.remainRb = (RatingBar) convertView.findViewById(R.id.forth_left_listview2);
            viewHolder.contentTxt = (TextView) convertView.findViewById(R.id.forth_join_listview2);
            viewHolder.textView1 = (TextView) convertView.findViewById(R.id.forth_end_listview2);
            viewHolder.textView2 = (TextView) convertView.findViewById(R.id.forth_ubi_listview2);

            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.load_fothlistview);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ForthViewHolder) convertView.getTag();
        }
        ForthMoney present = presentList.get(position);

        viewHolder.nameTxt.setText(present.getName());
        viewHolder.remainRb.setRating(Float.valueOf(present.getSource()) / 2);
        viewHolder.contentTxt.setText(present.getCount_dl());
        viewHolder.textView1.setText(present.getSize());
        int prize = Integer.parseInt(present.getAll_prize());
        if (!isHidden) {
            if (prize < 1000) {
                viewHolder.textView2.setText("奖" + prize + "U币");
            } else {
                viewHolder.textView2.setText("奖" + prize / 1000 + ",000U币");
            }
            viewHolder.imageView.setOnClickListener(this);
        } else {
            viewHolder.textView2.setVisibility(View.GONE);
            viewHolder.imageView.setVisibility(View.GONE);
        }
        imageLoader.displayImage(viewHolder.iconImg, present.getIcon());

        return convertView;
    }

    @Override
    public void onClick(View v) {
        callback3.click(v);
    }
}
