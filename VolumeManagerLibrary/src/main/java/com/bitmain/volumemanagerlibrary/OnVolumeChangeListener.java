package com.bitmain.volumemanagerlibrary;

/**
 * Created by kongqw on 2017/3/1.
 * 音量变化的接口
 */

public interface OnVolumeChangeListener {

    /**
     * 音量变大的回调
     *
     * @param volume 调整后的音量
     */
    void onVolumeUp(int volume);

    /**
     * 音量大小没变的回调
     *
     * @param volume 调整后的音量
     */
    void onVolumeSame(int volume);

    /**
     * 音量减小的回调
     *
     * @param volume 调整后的音量
     */
    void onVolumeDown(int volume);

    /**
     * 当音量已经是最大 继续增加音量的时候回调
     */
    void onMaxVolume();

    /**
     * 当音量已经是最小 继续减小音量的时候回调
     */
    void onMinVolume();
}
