package com.bitmain.volumemanagerlibrary;

import android.media.AudioManager;

/**
 * Created by kongqingwei on 2017/3/1.
 */

public enum AudioModeEnum {
    STREAM_ALARM(AudioManager.STREAM_ALARM),
    STREAM_DTMF(AudioManager.STREAM_DTMF),
    STREAM_MUSIC(AudioManager.STREAM_MUSIC),
    STREAM_NOTIFICATION(AudioManager.STREAM_NOTIFICATION),
    STREAM_RING(AudioManager.STREAM_RING),
    STREAM_SYSTEM(AudioManager.STREAM_SYSTEM),
    STREAM_VOICE_CALL(AudioManager.STREAM_VOICE_CALL);

    private int mode;

    AudioModeEnum(int mode) {
        this.mode = mode;
    }

    public int getMode() {
        return mode;
    }
}
