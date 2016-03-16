package matio.angel.com.myapplication_gamehelper.utils;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Angel on 2016/1/13.
 */
public class ViewPagerUtils {

    /**
     * viewpager设置适配器
     *
     * @param viewPager
     * @param viewList  存数视图的list集合
     */
    public static void setViewPagerAdapter(ViewPager viewPager, final List<View> viewList) {

        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                if (viewList != null) {
                    return viewList.size();
                }
                return 0;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == (View) object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                View view = viewList.get(position);
                container.addView(view);
                return view;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(viewList.get(position));
            }
        });
    }
}

