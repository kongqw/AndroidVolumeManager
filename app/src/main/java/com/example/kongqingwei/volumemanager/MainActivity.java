package com.example.kongqingwei.volumemanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;

import com.kongqw.volumemanagerlibrary.AudioFlagEnum;
import com.kongqw.volumemanagerlibrary.AudioModeEnum;
import com.kongqw.volumemanagerlibrary.OnVolumeChangeListener;
import com.kongqw.volumemanagerlibrary.VolumeManager;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    private VolumeManager volumeManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.rb_stream_alarm).setOnClickListener(this);
        findViewById(R.id.rb_stream_dtmf).setOnClickListener(this);
        findViewById(R.id.rb_stream_music).setOnClickListener(this);
        ((RadioButton) findViewById(R.id.rb_stream_music)).setChecked(true);
        findViewById(R.id.rb_stream_notification).setOnClickListener(this);
        findViewById(R.id.rb_stream_ring).setOnClickListener(this);
        findViewById(R.id.rb_stream_system).setOnClickListener(this);
        findViewById(R.id.rb_stream_voice_call).setOnClickListener(this);

        volumeManager = new VolumeManager(getApplicationContext());
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

        volumeManager.setMode(AudioModeEnum.STREAM_MUSIC);
        volumeManager.setFlag(AudioFlagEnum.FLAG_SHOW_UI);
    }

    /**
     * 增加音量
     *
     * @param view 增加音量按钮
     */
    public void volumeUp(View view) {
        volumeManager.volumeUp();
    }

    /**
     * 减小音量
     *
     * @param view 减小音量按钮
     */
    public void volumeDown(View view) {
        volumeManager.volumeDown();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rb_stream_alarm:
                volumeManager.setMode(AudioModeEnum.STREAM_ALARM);
                break;
            case R.id.rb_stream_dtmf:
                volumeManager.setMode(AudioModeEnum.STREAM_DTMF);
                break;
            case R.id.rb_stream_music:
                volumeManager.setMode(AudioModeEnum.STREAM_MUSIC);
                break;
            case R.id.rb_stream_notification:
                volumeManager.setMode(AudioModeEnum.STREAM_NOTIFICATION);
                break;
            case R.id.rb_stream_ring:
                volumeManager.setMode(AudioModeEnum.STREAM_RING);
                break;
            case R.id.rb_stream_system:
                volumeManager.setMode(AudioModeEnum.STREAM_SYSTEM);
                break;
            case R.id.rb_stream_voice_call:
                volumeManager.setMode(AudioModeEnum.STREAM_VOICE_CALL);
                break;
            default:
                break;
        }
    }
}
