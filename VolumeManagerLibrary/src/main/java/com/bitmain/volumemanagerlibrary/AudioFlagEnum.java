package com.bitmain.volumemanagerlibrary;

import android.media.AudioManager;

/**
 * Created by kongqingwei on 2017/3/1.
 */

public enum AudioFlagEnum {

    FLAG_ALLOW_RINGER_MODES(AudioManager.FLAG_ALLOW_RINGER_MODES),
    FLAG_PLAY_SOUND(AudioManager.FLAG_PLAY_SOUND),
    FLAG_REMOVE_SOUND_AND_VIBRATE(AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE),
    FLAG_SHOW_UI(AudioManager.FLAG_SHOW_UI),
    FLAG_VIBRATE(AudioManager.FLAG_VIBRATE);

    private int flag;

    AudioFlagEnum(int flag) {
        this.flag = flag;
    }

    public int getFlag() {
        return flag;
    }
}
