package com.example.kongqingwei.volumemanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bitmain.volumemanagerlibrary.VolumeManager;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private VolumeManager volumeManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        volumeManager = new VolumeManager(getApplicationContext()) {
            @Override
            public void onVolumeUp(int volume) {
                Log.i(TAG, "onVolumeUp: " + volume);
            }

            @Override
            public void onVolumeDown(int volume) {
                Log.i(TAG, "onVolumeDown: " + volume);
            }

            @Override
            public void onVolumeMax(int volume) {
                Log.i(TAG, "onVolumeMax: " + volume);
                Toast.makeText(getApplicationContext(), "已经是最大音量:" + volume, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onVolumeMin(int volume) {
                Log.i(TAG, "onVolumeMin: " + volume);
                Toast.makeText(getApplicationContext(), "已经是最小音量:" + volume, Toast.LENGTH_SHORT).show();
            }
        };
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
}
