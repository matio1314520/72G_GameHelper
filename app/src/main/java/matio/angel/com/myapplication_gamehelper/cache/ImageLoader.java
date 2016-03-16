package matio.angel.com.myapplication_gamehelper.cache;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.widget.ImageView;

import matio.angel.com.myapplication_gamehelper.asynctask.DownloadAsyncTask;

/**
 * Created by Angel on 2016/1/14.
 */
public class ImageLoader {

    private Context context;

    private ImageCache imageCache;

    private static ImageLoader imageLoader;

    public static ImageLoader getInstance(Context context, ImageCache imageCache) {
        if (imageLoader == null) {
            imageLoader = new ImageLoader(context, imageCache);
        }
        return imageLoader;
    }

    private ImageLoader(Context context, ImageCache imageCache) {
        this.context = context;
        this.imageCache = imageCache;
    }

    public void displayImage(ImageView imageView, String urlString) {
        if (!TextUtils.isEmpty(urlString)) {
            Bitmap bitmap = imageCache.getBitmapFromCache(urlString);
            if (bitmap == null) {
                imageView.setTag(urlString);
                new DownloadAsyncTask(context, imageView, imageCache).execute(urlString);
            } else {
                imageView.setImageBitmap(bitmap);
            }
        } else {
            imageView.setImageBitmap(null);
        }
    }
}
