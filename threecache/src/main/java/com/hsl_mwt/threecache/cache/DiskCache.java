package com.hsl_mwt.threecache.cache;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Angel on 2016/3/6.\
 * <p/>
 * 外存缓存
 */
public class DiskCache implements ImageCache {

    private File mFile;

    public DiskCache(Context context) {
        if (Environment.getExternalStorageDirectory().equals(Environment.MEDIA_MOUNTED)) {
            mFile = context.getExternalCacheDir();
        }
    }

    @Override
    public Bitmap get(String url) {
        Bitmap bitmap = null;
        if (url != null){
            File file = new File(mFile,url.substring(url.lastIndexOf('/') + 1, url.length()));
            if (file.exists()) {
                bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            }
        }
        return bitmap;
    }

    @Override
    public void put(String url, Bitmap bitmap) {
        String fileName =url.substring(url.lastIndexOf('/') + 1, url.length());
        FileOutputStream fileOutputStream = null;
        try {
            //获取文件输出流
            fileOutputStream = new FileOutputStream(new File(mFile, fileName));
            //参数一  图片压缩的格式
            //参数二  quality
            //参数三  输出流
            if (bitmap != null) {
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}