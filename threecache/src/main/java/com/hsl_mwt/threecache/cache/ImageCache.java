package com.hsl_mwt.threecache.cache;

import android.graphics.Bitmap;

/**
 * Created by Angel on 2016/3/6.
 */
public interface ImageCache {

    /**
     * @param url url地址
     * @return
     */
    public Bitmap get(String url);


    /**
     * @param url url地址
     */
    public void put(String url,Bitmap bitmap);
}
