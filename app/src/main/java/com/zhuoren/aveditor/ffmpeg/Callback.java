package com.zhuoren.aveditor.ffmpeg;


public interface Callback {

    void onLog(String log);

    void onSuccess();

    void onFail();
}
