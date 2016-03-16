package matio.angel.com.myapplication_gamehelper.cache;

import android.graphics.Bitmap;

/**
 * Created by Angel on 2016/1/14.
 */
public abstract class ImageCache {
    /**
     * 从缓存中获取图片
     *
     * @param key
     * @return
     */
    public abstract Bitmap getBitmapFromCache(String key);

    /**
     * 把图片存入缓存
     *
     * @param key
     * @param bitmap
     */
    public abstract void putBitmapToCache(String key, Bitmap bitmap);
}
