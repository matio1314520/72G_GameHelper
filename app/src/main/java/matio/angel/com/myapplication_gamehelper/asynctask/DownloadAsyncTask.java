package matio.angel.com.myapplication_gamehelper.asynctask;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import matio.angel.com.myapplication_gamehelper.cache.ImageCache;
import matio.angel.com.myapplication_gamehelper.net.HttpUtils;

/**
 * Created by Angel on 2016/1/14.
 */
public class DownloadAsyncTask extends AsyncTask<String, Void, Bitmap> {
    private Context context;
    private ImageView imageView;
    private ImageCache imageCache;
    private String url;

    public DownloadAsyncTask(Context context, ImageView imageView, ImageCache imageCache) {
        this.context = context;
        this.imageView = imageView;
        this.imageCache = imageCache;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        url = params[0];
        if (HttpUtils.isNetworkConnected(context)) {

            byte[] buffer = HttpUtils.getNetworkResult(params[0], null);

            if (buffer != null && buffer.length > 0) {

                return BitmapFactory.decodeByteArray(buffer, 0, buffer.length);
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if (url.equals(imageView.getTag())) {
            imageView.setImageBitmap(bitmap);
        }
        imageCache.putBitmapToCache(url, bitmap);
    }
}
