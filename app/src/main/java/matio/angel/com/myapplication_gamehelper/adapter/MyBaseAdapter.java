package matio.angel.com.myapplication_gamehelper.adapter;

import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import matio.angel.com.myapplication_gamehelper.bean.FirstPresent;
import matio.angel.com.myapplication_gamehelper.bean.ForthMoney;
import matio.angel.com.myapplication_gamehelper.bean.SecondAreaActivity1;
import matio.angel.com.myapplication_gamehelper.bean.SecondAreaActivity2;
import matio.angel.com.myapplication_gamehelper.bean.ThirdChange;

/**
 * Created by Angel on 2016/1/13.
 */
public class MyBaseAdapter extends BaseAdapter {
    //上下文对象
    private Context context;
    //存放object对象的list集合
    private List<Object> list;
    private List<FirstPresent> firstList;
    private List<SecondAreaActivity1> secondList1;
    private List<SecondAreaActivity2> secondList2;
    private List<ThirdChange> thirdList;
    private List<ForthMoney> forthList;

    private Map<String, List> map = new HashMap<>();

    //存放资源id的集合
    private List<Integer> resourceIdList;

    public MyBaseAdapter(Context context, List<Object> objList, List<Integer> resourceId) {
        this.context = context;
        this.list = objList;
        this.resourceIdList = resourceId;
    }

    @Override
    public int getCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        List<ForthMoney> li = new ArrayList<>();
        Map<String, List> map = new HashMap<>();
        map.put("shijian", li);
        MyViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new MyViewHolder();
            viewHolder.textView0 = (TextView) convertView.findViewById(resourceIdList.get(0));
            viewHolder.textView1 = (TextView) convertView.findViewById(resourceIdList.get(1));
            viewHolder.textView2 = (TextView) convertView.findViewById(resourceIdList.get(2));
            viewHolder.textView3 = (TextView) convertView.findViewById(resourceIdList.get(3));
            viewHolder.textView4 = (TextView) convertView.findViewById(resourceIdList.get(4));
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (MyViewHolder) convertView.getTag();
        }

        return convertView;
    }

    class MyViewHolder {
        public TextView textView0;
        public TextView textView1;
        public TextView textView2;
        public TextView textView3;
        public TextView textView4;
        public TextView textView5;
    }
}
