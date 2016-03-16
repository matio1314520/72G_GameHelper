package matio.angel.com.myapplication_gamehelper.cache;

import android.content.Context;
import android.graphics.Bitmap;

/**
 * Created by Angel on 2016/1/14.
 */
public class DoubleCache extends ImageCache {
    private ImageCache diskCache;
    private ImageCache memoryCache;

    public DoubleCache(Context context) {
        diskCache = new DiskCache(context);
        memoryCache = new MemoryCache();
    }

    @Override
    public Bitmap getBitmapFromCache(String key) {
        //从内存中获取图片
        Bitmap bitmap = memoryCache.getBitmapFromCache(key);
        if (bitmap == null) {
            //从磁盘获取图片00
            bitmap = diskCache.getBitmapFromCache(key);
        }
        return bitmap;
    }

    @Override
    public void putBitmapToCache(String key, Bitmap bitmap) {
        if (getBitmapFromCache(key) == null) {
            memoryCache.putBitmapToCache(key, bitmap);
            diskCache.putBitmapToCache(key, bitmap);
        }
    }
}
