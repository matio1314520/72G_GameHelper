package com.hsl_mwt.threecache.cache;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by Angel on 2016/3/6.
 */
public class MemoryCache implements ImageCache {

    private LruCache<String, Bitmap> mLruCache;

    public static final int MAX_SIZE = (int) (Runtime.getRuntime().maxMemory() / 1024);

    private MemoryCache() {

    }

    public LruCache getInstance() {
        if (mLruCache == null) {

            mLruCache = new LruCache<String, Bitmap>(MAX_SIZE / 8) {
                @Override
                protected int sizeOf(String key, Bitmap value) {
                    return value.getRowBytes() * value.getHeight();
                }
            };
            return mLruCache;
        }
        return mLruCache;
    }


    @Override
    public Bitmap get(String url) {
        if (url != null) {
            return mLruCache.get(url);
        }
        return null;
    }

    @Override
    public void put(String url, Bitmap bitmap) {
        if (url != null && bitmap != null) {
            mLruCache.put(url, bitmap);
        }
    }
}
