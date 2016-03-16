package matio.angel.com.myapplication_gamehelper.cache;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

/**
 * Created by Angel on 2016/1/14.
 */
public class MemoryCache extends ImageCache {


    private LruCache<String, Bitmap> mMearyCache;

    public MemoryCache() {
        //该应用程序可以使用的最大的内存大小（以KB为单位）
        int memorySize = (int) (Runtime.getRuntime().maxMemory() / 1024);

        //缓存大小一般设置为最大使用内存的1/8
        int cacheSize = memorySize / 8;
        mMearyCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                //该图片的大小（以KB为单位）
                return value.getRowBytes() * value.getHeight() / 1024;
            }
        };
    }

    @Override
    public Bitmap getBitmapFromCache(String key) {
        return mMearyCache.get(key);
    }

    @Override
    public void putBitmapToCache(String key, Bitmap bitmap) {
        if (getBitmapFromCache(key) == null) {
            if (bitmap != null) {
                mMearyCache.put(key, bitmap);
            }
        }
    }
}
