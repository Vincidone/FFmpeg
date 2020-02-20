package com.zhuoren.aveditor.edit.video;

import android.view.Menu;

import com.zhuoren.aveditor.edit.EditMediaListActivity;
import com.zhuoren.aveditor.edit.MediaFile;
import com.zhuoren.aveditor.ffmpeg.Callback;
import com.zhuoren.aveditor.ffmpeg.FFmpegVideo;
import com.zhuoren.aveditor.util.FileUtil;
import com.zhuoren.aveditor.util.SnackBarUtil;

import java.io.File;
import java.util.List;


public class VideoFlipActivity extends EditMediaListActivity {

    private static final String TAG = "VideoFlipActivity";

    public static final String TITLE = "视频镜像";


    @Override
    protected String getEditTitle() {
        return TITLE;
    }

    @Override
    protected void createOptionsMenu(Menu menu) {
        menu.add("选择视频");
        menu.add("删除视频");
        menu.add("上下翻转");
        menu.add("左右翻转");
    }

    @Override
    protected void onMenuClick(int order) {
        if (order == 0) {
            if (mMediaFileList.size() == 1) {
                SnackBarUtil.showError(mRoot, "已选择视频");
                return;
            }
            pickVideo();
        } else if (order == 1) {
            deleteLastMediaFile();
        } else if (order == 2) {
            run(mMediaFileList, true);
        } else if (order == 3) {
            run(mMediaFileList, false);
        }
    }

    private void run(List<MediaFile> mMediaFileList, boolean vertical) {
        final String output = FileUtil.OUTPUT_VIDEO_DIR + File.separator +
                "flip_" + TAG + ".mp4";
        showLoadingDialog();
        FFmpegVideo.flipVideo(mMediaFileList.get(0).getPath(),
                output, vertical, new Callback() {
                    @Override
                    public void onLog(String log) {

                    }

                    @Override
                    public void onSuccess() {
                        dismissLoadingDialog();
                        showSaveDoneAndPlayDialog(output, true);
                    }

                    @Override
                    public void onFail() {
                        dismissLoadingDialog();
                        SnackBarUtil.showError(mRoot, "合成失败");
                    }
                });

    }


}
