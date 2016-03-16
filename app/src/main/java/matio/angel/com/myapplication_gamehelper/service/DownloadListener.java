package matio.angel.com.myapplication_gamehelper.service;

import java.io.File;

/**
 * Created by Angel on 2016/1/22.
 */
public interface DownloadListener {

    void onStart(int fileByteSize);

    void onPause();

    void onResume();

    void onProgress(int receivedBytes);

    void onFail();

    void onSuccess(File file);

    void onCancel();
}
