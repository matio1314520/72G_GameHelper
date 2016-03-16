package matio.angel.com.myapplication_gamehelper.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Angel on 2016/1/11.
 */
public class HttpUtils {

    /**
     * 判断网络是否连接成功
     *
     * @param context 上下文对象
     * @return boolean
     */
    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if (networkInfo == null) {
            return false;
        }
        boolean bool = networkInfo.isConnected();

        return bool;
    }

    /**
     * 请求网络数据
     *
     * @param urlPath   url地址字符串
     * @param parameter 参数
     * @return 字节数组
     */
    public static byte[] getNetworkResult(String urlPath, String parameter) {
        HttpURLConnection connection = null;
        InputStream is = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            //获取url地址
            URL url = new URL(urlPath);
            //打开网络连接
            connection = (HttpURLConnection) url.openConnection();


            if (parameter != null) {
                connection.setDoInput(true);
                connection.setDoOutput(true);
                //设置请求方式为post
                connection.setRequestMethod("POST");
                connection.getOutputStream().write(parameter.getBytes());
                connection.getOutputStream().flush();
                connection.getOutputStream().close();
            }

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                //连接成功
                is = connection.getInputStream();

                //定义长度
                int len = 0;
                //定义缓存数组
                byte[] buf = new byte[1024];
                //循环读写
                while ((len = is.read(buf)) != -1) {
                    baos.write(buf, 0, len);
                }
                //刷新
                baos.flush();
                if (baos != null) {

                    return baos.toByteArray();
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.close();
                }
                if (is != null) {
                    is.close();
                }
                if (connection != null) {
                    connection.disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();

            }
        }
        return null;
    }


    public static byte[] doPostRequest(String urlString, String params) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        InputStream is = null;
        byte[] result = null;
        URL url = null;
        HttpURLConnection connection = null;
        OutputStream out = null;
        StringBuilder sb = new StringBuilder();

//        if (params != null && params.size() != 0) {
//            for(Map.Entry<String,String> entry:params.entrySet()) {
//                try {
//                    sb.append(entry.getKey());
//                    sb.append("=");
//                    sb.append(URLEncoder.encode(entry.getValue(),"utf-8"));
//                    sb.append("&");
//                } catch (UnsupportedEncodingException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            sb.deleteCharAt(sb.length()-1);
//        }

        byte[] entity = params.getBytes();

        try {
            url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoOutput(true);

            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Content-Length", entity.length + "");

            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(entity);
            outputStream.flush();
            outputStream.close();

            if (connection.getResponseCode() == 200) {
                is = connection.getInputStream();
                byte[] buffer = new byte[1024];
                int length = 0;

                while ((length = is.read(buffer)) != -1) {
                    baos.write(buffer, 0, length);
                    baos.flush();
                }

                result = baos.toByteArray();
            }

        } catch (Exception e) {

        } finally {
            try {
                if (connection != null) {
                    connection.disconnect();
                }

                if (baos != null) {
                    baos.close();
                }

                if (is != null) {
                    is.close();
                }

            } catch (Exception e) {

            }
        }

        return result;
    }
}
