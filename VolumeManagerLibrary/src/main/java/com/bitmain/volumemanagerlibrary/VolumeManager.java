package com.bitmain.volumemanagerlibrary;

import android.content.Context;
import android.media.AudioManager;

/**
 * Created by kongqingwei on 2017/1/10.
 * 音量管理者
 */

public abstract class VolumeManager {

    private AudioManager mAudioManager;
    private int mMaxVolume;
    private int STREAM_TYPE = AudioManager.STREAM_MUSIC;
    private int FLAGS = AudioManager.FLAG_SHOW_UI;


    // 声音变大的回调
    public abstract void onVolumeUp(int volume);

    // 声音减小的回调
    public abstract void onVolumeDown(int volume);

    // 声音已经最大的回调
    public abstract void onVolumeMax(int volume);

    // 声音已经最小的回调
    public abstract void onVolumeMin(int volume);

    public VolumeManager(Context context) {
        init(context, FLAGS);
    }

    public VolumeManager(Context context, int flags) {
        init(context, flags);
    }

    private void init(Context context, int flags) {
        FLAGS = flags;
        // 多媒体管理器
        mAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);

        if (STREAM_TYPE != mAudioManager.getMode()) {
            mAudioManager.setMode(STREAM_TYPE);
        }
        // 最大音量
        mMaxVolume = mAudioManager.getStreamMaxVolume(STREAM_TYPE);
    }

    /**
     * 控制增加音量
     */
    public void volumeUp() {
        if (STREAM_TYPE != mAudioManager.getMode()) {
            mAudioManager.setMode(STREAM_TYPE);
        }
        // 当前音量
        int streamVolume = mAudioManager.getStreamVolume(STREAM_TYPE);
        if (streamVolume < mMaxVolume) {
            // 当前还不是最大音量 增加音量
            mAudioManager.setStreamVolume(STREAM_TYPE, ++streamVolume, FLAGS);
            // 音量变大
            onVolumeUp(streamVolume);
        } else {
            // 已经是最大音量
            onVolumeMax(streamVolume);
        }
    }

    /**
     * 控制减小音量
     */
    public void volumeDown() {
        if (STREAM_TYPE != mAudioManager.getMode()) {
            mAudioManager.setMode(STREAM_TYPE);
        }
        // 当前音量
        int streamVolume = mAudioManager.getStreamVolume(STREAM_TYPE);
        if (streamVolume > 0) {
            // 当前还不是最小音量 减小音量
            mAudioManager.setStreamVolume(STREAM_TYPE, --streamVolume, FLAGS);
            // 音量变小
            onVolumeDown(streamVolume);
        } else {
            // 已经是最小音量
            onVolumeMin(streamVolume);
        }
    }
}
