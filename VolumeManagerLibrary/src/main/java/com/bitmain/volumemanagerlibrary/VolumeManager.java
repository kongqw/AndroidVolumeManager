package com.bitmain.volumemanagerlibrary;

import android.content.Context;
import android.media.AudioManager;

/**
 * Created by kongqingwei on 2017/1/10.
 * 音量管理者
 */

public abstract class VolumeManager {

    private final AudioManager mAudioManager;
    private final int mMaxVolume;
    private final int STREAM_TYPE = AudioManager.STREAM_MUSIC;
    private final int FLAGS = AudioManager.FLAG_SHOW_UI;


    // 声音变大的回调
    public abstract void onVolumeUp();

    // 声音减小的回调
    public abstract void onVolumeDown();

    // 声音已经最大的回调
    public abstract void onVolumeMax();

    // 声音已经最小的回调
    public abstract void onVolumeMin();

    public VolumeManager(Context context) {
        // 多媒体管理器
        mAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        // 最大音量
        mMaxVolume = mAudioManager.getStreamMaxVolume(STREAM_TYPE);
    }

    /**
     * 控制增加音量
     */
    public void volumeUp() {
        // 当前音量
        int streamVolume = mAudioManager.getStreamVolume(STREAM_TYPE);
        if(streamVolume < mMaxVolume){
            // 当前还不是最大音量 增加音量
            mAudioManager.setMode(STREAM_TYPE);
            mAudioManager.setStreamVolume(STREAM_TYPE, ++streamVolume, FLAGS);
            // 音量变大
            onVolumeUp();
        } else {
            // 已经是最大音量
            onVolumeMax();
        }
    }

    /**
     * 控制减小音量
     */
    public void volumeDown() {
        // 当前音量
        int streamVolume = mAudioManager.getStreamVolume(STREAM_TYPE);
        if(streamVolume > 0){
            // 当前还不是最小音量 减小音量
            mAudioManager.setMode(STREAM_TYPE);
            mAudioManager.setStreamVolume(STREAM_TYPE, --streamVolume, FLAGS);
            // 音量变小
            onVolumeDown();
        } else {
            // 已经是最小音量
            onVolumeMin();
        }
    }
}
