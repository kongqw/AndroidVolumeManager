package com.kongqw.volumemanagerlibrary;

import android.content.Context;
import android.media.AudioManager;

/**
 * Created by kongqingwei on 2017/1/10.
 * <p>
 * 音量管理者
 */
public final class VolumeManager {

    private static VolumeManager mVolumeManager;
    private OnVolumeChangeListener mOnVolumeChangeListener;
    private AudioManager mAudioManager;
    private int STREAM_TYPE = AudioManager.STREAM_MUSIC;
    private int FLAGS = AudioManager.FLAG_SHOW_UI;

    /**
     * 私有
     *
     * @param context context
     */
    private VolumeManager(Context context) {
        // 多媒体管理器
        mAudioManager = (AudioManager) context.getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
    }

    /**
     * 获取单例
     *
     * @param context context
     * @return VolumeManager
     */
    public static VolumeManager getInstance(Context context) {
        if (null == mVolumeManager) {
            synchronized (VolumeManager.class) {
                if (null == mVolumeManager) {
                    mVolumeManager = new VolumeManager(context);
                }
            }
        }
        return mVolumeManager;
    }

    /**
     * Sets the audio mode.
     *
     * @param mode mode
     * @return VolumeManager
     */
    public VolumeManager setMode(AudioModeEnum mode) {
        STREAM_TYPE = mode.getMode();
        // 设置音频模式
        if (STREAM_TYPE != mAudioManager.getMode()) {
            mAudioManager.setMode(STREAM_TYPE);
        }
        return mVolumeManager;
    }

    /**
     * 设置Flag
     *
     * @param flag flag
     * @return VolumeManager
     */
    public VolumeManager setFlag(AudioFlagEnum flag) {
        FLAGS = flag.getFlag();
        return mVolumeManager;
    }

    /**
     * 设置音量
     *
     * @param volume 音量
     */
    public void setVolume(int volume) {
        // 获取最大音量
        int maxVolume = mAudioManager.getStreamMaxVolume(STREAM_TYPE);
        // 当前音量
        int streamVolume = mAudioManager.getStreamVolume(STREAM_TYPE);
        // 可设置音量的范围[0, maxVolume]
        int targetVolume = volume <= 0 ? (volume <= maxVolume ? volume : maxVolume) : 0;

        if (streamVolume == targetVolume) {
            // 音量不变
            if (null != mOnVolumeChangeListener) {
                mOnVolumeChangeListener.onVolumeSame(targetVolume);
            }
        } else if (streamVolume < targetVolume) {
            // 设置音量
            mAudioManager.setStreamVolume(STREAM_TYPE, targetVolume, FLAGS);
            // 音量增大
            if (null != mOnVolumeChangeListener) {
                mOnVolumeChangeListener.onVolumeUp(streamVolume);
            }
        } else {
            // 设置音量
            mAudioManager.setStreamVolume(STREAM_TYPE, targetVolume, FLAGS);
            // 音量减小
            if (null != mOnVolumeChangeListener) {
                mOnVolumeChangeListener.onVolumeDown(streamVolume);
            }
        }
    }

    /**
     * 增加音量
     */
    public void volumeUp() {
        // 获取最大音量
        int maxVolume = mAudioManager.getStreamMaxVolume(STREAM_TYPE);
        // 当前音量
        int streamVolume = mAudioManager.getStreamVolume(STREAM_TYPE);

        if (streamVolume == maxVolume) {
            // 已经是最大音量
            if (null != mOnVolumeChangeListener) {
                mOnVolumeChangeListener.onMaxVolume();
            }
        } else {
            // 增加音量
            mAudioManager.adjustStreamVolume(STREAM_TYPE, AudioManager.ADJUST_RAISE, FLAGS);
            if (null != mOnVolumeChangeListener) {
                streamVolume = mAudioManager.getStreamVolume(STREAM_TYPE); // ++streamVolume;
                mOnVolumeChangeListener.onVolumeUp(streamVolume);
            }
        }

    }

    /**
     * 减小音量
     */
    public void volumeDown() {
        // 当前音量
        int streamVolume = mAudioManager.getStreamVolume(STREAM_TYPE);

        if (0 == streamVolume) {
            // 已经是最小音量
            if (null != mOnVolumeChangeListener) {
                mOnVolumeChangeListener.onMinVolume();
            }
        } else {
            // 减小音量
            mAudioManager.adjustStreamVolume(STREAM_TYPE, AudioManager.ADJUST_LOWER, FLAGS);
            if (null != mOnVolumeChangeListener) {
                streamVolume = mAudioManager.getStreamVolume(STREAM_TYPE);
                mOnVolumeChangeListener.onVolumeDown(streamVolume);
            }
        }
    }

    /**
     * 添加音量变化的监听
     *
     * @param listener listener
     * @return VolumeManager
     */
    public VolumeManager setOnVolumeChangeListener(OnVolumeChangeListener listener) {
        mOnVolumeChangeListener = listener;
        return mVolumeManager;
    }
}
