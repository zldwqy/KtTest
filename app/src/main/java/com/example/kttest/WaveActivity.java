package com.example.kttest;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kttest.wave.SiriWaveView;
import com.example.kttest.wave.WaveTwoView;

public class WaveActivity extends AppCompatActivity {

    private WaveTwoView wave1;
    private SiriWaveView waveView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getRequestedOrientation()!= ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER);
        }
        setContentView(R.layout.activity_wave);

        waveView = findViewById(R.id.wave);

        wave1 = findViewById(R.id.wave1);

        wave1.startAnimate();
        wave1.setVolume(100);
    }

    @Override
    protected void onResume() {
        super.onResume();

        waveView.startAnim();
        waveView.setVolume(100);

    }

    @Override
    protected void onPause() {
        super.onPause();
        wave1.stopAnimate();
        waveView.stopAnim();
    }
}
