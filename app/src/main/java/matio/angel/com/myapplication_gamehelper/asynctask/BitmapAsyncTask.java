package matio.angel.com.myapplication_gamehelper.asynctask;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import matio.angel.com.myapplication_gamehelper.net.HttpUtils;

/**
 * Created by Angel on 2016/1/11.
 */
public class BitmapAsyncTask extends AsyncTask<String, Void, Bitmap> {

    private Context context;
    private String param;
    private ImageView imageView;

    public BitmapAsyncTask(Context context, String param, ImageView imageView) {
        this.context = context;
        this.param = param;
        this.imageView = imageView;
    }

    @Override
    protected Bitmap doInBackground(String... params) {

        if (HttpUtils.isNetworkConnected(context)) {
            byte[] result = HttpUtils.getNetworkResult(params[0], param);
            if (result != null && result.length > 0) {
                return BitmapFactory.decodeByteArray(result, 0, result.length);
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
    }
}
