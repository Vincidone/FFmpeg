package com.zhuoren.aveditor.common;

import android.media.MediaMetadataRetriever;
import android.media.MediaScannerConnection;

import com.zhuoren.aveditor.util.FileUtil;

public class VideoUtil {

    public static long getVideoDuration(String path) {
        MediaMetadataRetriever mmr = new MediaMetadataRetriever();
        mmr.setDataSource(path);
        String result = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
        try {
            return Long.parseLong(result);
        } catch (Exception ignored) {

        }
        return 0;
    }

    public static int getVideoWidth(String path) {
        MediaMetadataRetriever mmr = new MediaMetadataRetriever();
        mmr.setDataSource(path);
        String result = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_WIDTH);
        try {
            return Integer.parseInt(result);
        } catch (Exception ignored) {

        }
        return 0;
    }

    public static int getVideoHeight(String path) {
        MediaMetadataRetriever mmr = new MediaMetadataRetriever();
        mmr.setDataSource(path);
        String result = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_HEIGHT);
        try {
            return Integer.parseInt(result);
        } catch (Exception ignored) {

        }
        return 0;
    }

    /**
     * 扫描媒体文件
     *
     * @param filePath
     */
    public static void scanFile(String filePath) {
        if (FileUtil.isFileExist(filePath)) {
            MediaScannerConnection.scanFile(App.get(),
                    new String[]{filePath}, null, null);
        }
    }

}
