package com.zhuoren.aveditor.edit.video;


import android.view.Menu;
import com.zhuoren.aveditor.edit.EditMediaListActivity;
import com.zhuoren.aveditor.ffmpeg.Callback;
import com.zhuoren.aveditor.ffmpeg.FFmpegVideo;
import com.zhuoren.aveditor.util.DateUtil;
import com.zhuoren.aveditor.util.FileUtil;
import com.zhuoren.aveditor.util.SnackBarUtil;
import java.io.File;
import java.util.Date;


public class VideoDeMuxActivity extends EditMediaListActivity {
    private static final String TAG = "VideoDeMuxActivity";
    public static final String TITLE = "提取视频";

    @Override
    protected String getEditTitle() {
        return TITLE;
    }

    @Override
    protected void createOptionsMenu(Menu menu) {
        menu.add("选择视频");
        menu.add("删除");
        menu.add("开始");
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
        } else {
            if (mMediaFileList.size() == 0) {
                SnackBarUtil.showError(mRoot, "请选择视频");
                return;
            }
            run();
        }
    }

    private void run() {
        showLoadingDialog();
        final String output = FileUtil.OUTPUT_VIDEO_DIR + File.separator +
                "demux_" + DateUtil.format(new Date()) + ".mp4";
        FFmpegVideo.demuxVideo(mMediaFileList.get(0).getPath(), output, new Callback() {
            @Override
            public void onSuccess() {
                dismissLoadingDialog();
                showSaveDoneAndPlayDialog(output, true);
            }

            @Override
            public void onLog(String log) {

            }

            @Override
            public void onFail() {
                dismissLoadingDialog();

            }
        });
    }

}
