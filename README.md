# 音量控制

[![](https://jitpack.io/v/kongqw/AndroidVolumeManager.svg)](https://jitpack.io/#kongqw/AndroidVolumeManager)

Step 1. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:

``` gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

Step 2. Add the dependency

``` gradle
dependencies {
        compile 'com.github.kongqw:AndroidVolumeManager:1.1.1'
}
```

## 初始化

``` java
volumeManager = new VolumeManager(getApplicationContext());
```

## 音量监听

``` java
volumeManager.setOnVolumeChangeListener(new OnVolumeChangeListener() {
    @Override
    public void onVolumeUp(int volume) {
        Log.i(TAG, "onVolumeUp: " + volume);
    }

    @Override
    public void onVolumeSame(int volume) {
        Log.i(TAG, "onVolumeSame: " + volume);
    }

    @Override
    public void onVolumeDown(int volume) {
        Log.i(TAG, "onVolumeDown: " + volume);
    }

    @Override
    public void onMaxVolume() {
        Log.i(TAG, "onMaxVolume: ");
    }

    @Override
    public void onMinVolume() {
        Log.i(TAG, "onMinVolume: ");
    }
});
```

## 增大音量
``` java
volumeManager.volumeUp();
```

## 减小音量
``` java
volumeManager.volumeDown();
```

## 设置音量
``` java
volumeManager.setVolume(10);
```

## 设置控制音量模式

默认即控制媒体音量

- AudioModeEnum.STREAM_ALARM(AudioManager.STREAM_ALARM)
- AudioModeEnum.STREAM_DTMF(AudioManager.STREAM_DTMF)
- AudioModeEnum.STREAM_MUSIC(AudioManager.STREAM_MUSIC)
- AudioModeEnum.STREAM_NOTIFICATION(AudioManager.STREAM_NOTIFICATION)
- AudioModeEnum.STREAM_RING(AudioManager.STREAM_RING)
- AudioModeEnum.STREAM_SYSTEM(AudioManager.STREAM_SYSTEM)
- AudioModeEnum.STREAM_VOICE_CALL(AudioManager.STREAM_VOICE_CALL)

``` java
volumeManager.setMode(AudioModeEnum.STREAM_MUSIC);
```

## 设置响应模式

默认即显示UI

- AudioFlagEnum.FLAG_ALLOW_RINGER_MODES(AudioManager.FLAG_ALLOW_RINGER_MODES)
- AudioFlagEnum.FLAG_PLAY_SOUND(AudioManager.FLAG_PLAY_SOUND)
- AudioFlagEnum.FLAG_REMOVE_SOUND_AND_VIBRATE(AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE)
- AudioFlagEnum.FLAG_SHOW_UI(AudioManager.FLAG_SHOW_UI)
- AudioFlagEnum.FLAG_VIBRATE(AudioManager.FLAG_VIBRATE)

``` java
volumeManager.setFlag(AudioFlagEnum.FLAG_SHOW_UI);
```


## 注意

> `setMode()` 方法会打断 `MediaPlayer` 播放
