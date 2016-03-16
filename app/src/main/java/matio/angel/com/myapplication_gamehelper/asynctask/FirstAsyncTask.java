package matio.angel.com.myapplication_gamehelper.asynctask;

import android.content.Context;
import android.os.AsyncTask;

import matio.angel.com.myapplication_gamehelper.fragment.Callback;
import matio.angel.com.myapplication_gamehelper.net.HttpUtils;

/**
 * Created by Angel on 2016/1/11.
 * 异步任务请求网络数据
 */
public class FirstAsyncTask extends AsyncTask<String, Void, String> {

    private Context context;
    private Callback callback;
    private String param;

    public FirstAsyncTask(Context context, Callback callback, String param) {
        this.context = context;
        this.callback = callback;
        this.param = param;
    }

    @Override
    protected String doInBackground(String... params) {
        if (HttpUtils.isNetworkConnected(context)) {
            //网络连接成功

            byte[] resultByte = HttpUtils.getNetworkResult(params[0], param);

            if (resultByte != null && resultByte.length > 0) {

                StringBuffer stringBuffer = new StringBuffer(new String(resultByte));

                return stringBuffer.toString();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {

        callback.callback(s);
    }
}
