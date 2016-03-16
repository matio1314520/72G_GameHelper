package matio.angel.com.myapplication_gamehelper.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.io.File;

import matio.angel.com.myapplication_gamehelper.activity.GameDetailActivity;

/**
 * Created by Angel on 2016/1/20.
 */
public class MyDownloadService extends Service {
    public static final int Flag_Init = 0; // 初始状态

    public static final int Flag_Down = 1; // 下载状态

    public static final int Flag_Pause = 2; // 暂停状态

    public static final int Flag_Done = 3; // 完成状态

    private String url;

    // 下载进度
    private float progress = 0;

    public float getProgress() {
        return progress;
    }

    // 下载状态标志
    private int flag;

    public int getFlag() {
        return flag;
    }

    DownThread mThread;

    Downloader downloader;

    private static MyDownloadService instance;

    public static MyDownloadService getInstance() {
        return instance;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Log.i("sss", "service.........onCreate");
        instance = this;
        flag = Flag_Init;
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String msg = intent.getExtras().getString("flag");
        url = intent.getExtras().getString("url");
        if (mThread == null) {
            mThread = new DownThread();
        }
        if (downloader == null) {
            downloader = new Downloader(this, url, "ss.aa");
        }
        downloader.setDownloadListener(downListener);

        if (msg.equals("start")) {
            startDownload();
        } else if (msg.equals("pause")) {
            downloader.pause();
        } else if (msg.equals("resume")) {
            downloader.resume();
        } else if (msg.equals("stop")) {
            downloader.cancel();
            stopSelf();
        }

        return super.onStartCommand(intent, flags, startId);
    }

    private void startDownload() {
        if (flag == Flag_Init || flag == Flag_Pause) {
            if (mThread != null && !mThread.isAlive()) {
                mThread = new DownThread();
            }
            mThread.start();
        }
    }

    @Override
    public void onDestroy() {
        Log.e("jlf", "service...........onDestroy");
        try {
            flag = 0;
            mThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mThread = null;
        super.onDestroy();
    }

    class DownThread extends Thread {

        @Override
        public void run() {

            if (flag == Flag_Init || flag == Flag_Done) {
                flag = Flag_Down;
            }

            downloader.start();
        }
    }

    private DownloadListener downListener = new DownloadListener() {

        int fileSize;
        Intent intent = new Intent();

        @Override
        public void onSuccess(File file) {
            intent.setAction(GameDetailActivity.ACTION_DOWNLOAD_SUCCESS);
            intent.putExtra("progress", 100);
            intent.putExtra("file", file);
            sendBroadcast(intent);
        }

        @Override
        public void onStart(int fileByteSize) {
            fileSize = fileByteSize;
            flag = Flag_Down;
        }

        @Override
        public void onResume() {
            flag = Flag_Down;
        }

        @Override
        public void onProgress(int receivedBytes) {
            if (flag == Flag_Down) {
                progress = ((receivedBytes / (float) fileSize) * 100);
                intent.setAction(GameDetailActivity.ACTION_DOWNLOAD_PROGRESS);
                Log.i("demo", "progress -------> " + progress);
                intent.putExtra("progress", progress);
                sendBroadcast(intent);

                if ((int) progress == 100) {
                    flag = Flag_Done;
                }
            }
        }

        @Override
        public void onPause() {
            flag = Flag_Pause;
        }

        @Override
        public void onFail() {
            intent.setAction(GameDetailActivity.ACTION_DOWNLOAD_FAIL);
            sendBroadcast(intent);
            flag = Flag_Init;
        }

        @Override
        public void onCancel() {
            progress = 0;
            flag = Flag_Init;
        }
    };
}
