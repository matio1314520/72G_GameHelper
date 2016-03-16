package com.hsl_mwt.threecache.cache;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.widget.ImageView;

/**
 * Created by Angel on 2016/3/6.
 */
public class ImageLoader {

    private Context mContext;

    private ImageCache mImageCache;

    private static ImageLoader mImageLoader;

    public static ImageLoader getInstance(Context context, ImageCache imageCache) {
        if (mImageLoader == null) {
            mImageLoader = new ImageLoader(context, imageCache);
        }
        return mImageLoader;
    }

    private ImageLoader(Context context, ImageCache imageCache) {
        this.mContext = context;
        this.mImageCache = imageCache;
    }

    public void displayImage(ImageView imageView, String url) {
        if (!TextUtils.isEmpty(url)) {
            Bitmap bitmap = mImageCache.get(url);
            if (bitmap == null) {
                imageView.setTag(url);
                new DownloadAsyncTask(mContext, imageView, mImageCache).execute(url);
            } else {
                imageView.setImageBitmap(bitmap);
            }
        } else {
            imageView.setImageBitmap(null);
        }
    }
}
