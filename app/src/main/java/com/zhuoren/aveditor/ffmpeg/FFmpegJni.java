package com.zhuoren.aveditor.ffmpeg;




public final class FFmpegJni {
    private FFmpegJni() {

    }

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("ffmpeg");
        System.loadLibrary("cmd-ffmpeg");
    }

    public static native int execute(String[] commands);

    public static native String getLog();

}
