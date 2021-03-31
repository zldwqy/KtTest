package com.example.kttest.wave;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.kttest.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 在SiriWaveView基础上进行了修改
 */
public class WaveTwoView extends View {

    public static final String TAG = "WaveTwoView";

    private int mWidth = 420;
    private int mHeight = 48;

    public static final int BLUE_WAVE_WIDTH = 150;
    public static final int PINK_WAVE_WIDTH = 200;
    public static final int GREEN_WAVE_WIDTH = 250;
    public static final int WHITE_WAVE_WIDTH = 300;

    private int[] COLORS = {R.color.wave_blue, R.color.wave_pink, R.color.wave_green,R.color.wave_white};
    private int[] waveWidths = {BLUE_WAVE_WIDTH,PINK_WAVE_WIDTH,GREEN_WAVE_WIDTH,WHITE_WAVE_WIDTH};
    private double [] maxYAmptitudes = {0.9d,0.63d,0.4d,0.1325d};


    private int midH;

    boolean run = false;
    private List<Wave> mWaves;
    private double mVolume = 1;
    private double waveHeight;


    public WaveTwoView(Context context) {
        super(context);
        init();
    }

    public WaveTwoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public WaveTwoView(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init(){

        initWave();
    }

    /**
     * 0到100
     * @param volume [0,100]
     */
    public void setVolume(float volume) {
        if (volume < 0 || volume > 100){
            return;
        }
        mVolume = volume/100;
    }

    private void initWave() {
        mWaves = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Wave wave = new Wave(waveWidths[i],COLORS[i],maxYAmptitudes[i]);
            mWaves.add(wave);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
        midH = mHeight /2;
        waveHeight = mHeight * 0.41;

        Log.d(TAG,"onMeasure--mWidth: " + mWidth + " mHeight: " + mHeight + " midH: " + midH + " waveHeight: " + waveHeight);

        int blueWidth = (int) (mWidth * 0.27f);
        int pinkWidth = (int) (mWidth * 0.36f);
        int greenWidth = (int) (mWidth * 0.45f);
        int whiteWidth = (int) (mWidth * 0.54f);

        waveWidths[0] = blueWidth;
        waveWidths[1] = pinkWidth;
        waveWidths[2] = greenWidth;
        waveWidths[3] = whiteWidth;


        for (int i = 0; i < mWaves.size(); i++) {
            mWaves.get(i).updateWaveWidth(waveWidths[i]);
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (Wave wave : mWaves) {
            wave.drawInternal(canvas);
        }
        if (run){
            postInvalidateDelayed( 100);
        }
    }

    private class Wave{
        int open_class = 2;
        int waveWidth;

        Path path = new Path();
        Path pathN = new Path();
        Paint paint = new Paint();

        int start;
        double maxYAmptitude;
        double minYAmptitude = 0.0;

        public Wave(int waveWidth,int color,double maxYAmptitude){
            paint.setAntiAlias(true);
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(getContext().getColor(color));

            this.waveWidth = waveWidth;
            start = - waveWidth;

            this.maxYAmptitude = maxYAmptitude;
        }

        public void updateWaveWidth(int waveWidth){
            this.waveWidth = waveWidth;
            start = -waveWidth;
        }
        double equation(double i, double x_mid,double minYAmptitude,double maxYAmptitude) {
            int halfWid = mWidth /2;

            double rate = 0.3;
            if (x_mid <= 0){
                rate = 0;
            }else if (x_mid > 0 && x_mid < halfWid){
                rate = x_mid / halfWid;
            }else if (x_mid ==halfWid){
                rate = 1.0d;
            }else if (x_mid > halfWid && x_mid <= mWidth){
                rate = (mWidth - x_mid) /halfWid;
            }else if (x_mid > mWidth){
                rate = 0;
            }

            double yAmplitude = minYAmptitude + rate  * (maxYAmptitude - minYAmptitude);
//            Log.d(TAG,"x_mid: " + x_mid + " yAmplitude； " + yAmplitude + " rate: " + rate );
            i = Math.abs(i);
            double pow = Math.pow(1 / (1 + Math.pow(open_class * i, 2)), 2);
            double y = -1 * yAmplitude
                    * pow;
//            Log.d(TAG,"equation； " + i + " y: " + y + " pow: " + pow);
            return y;
        }

        private void drawInternal(Canvas canvas) {

            path.reset();
            pathN.reset();

            path.moveTo(0,midH);
            pathN.moveTo(0,midH);

            double x_base = start;
            double y_base = mHeight / 2;
            start += 40;
            if (start >=  0){
                start = -waveWidth;
            }

            double i = -1;
            double x, y1,y2 = 0;
//        while (i <= 1) {
            while (true) {
                x = x_base + i * waveWidth;
                double function = equation(i,x_base,minYAmptitude,maxYAmptitude) * waveHeight * mVolume;
//                Log.d(TAG,"function: "+ function + " waveHeight: " + waveHeight);
                y1 = y_base + function;
                y2 = y_base - function;

//            Log.d(TAG,"y2: " + y2);
                if (y1 > 0.1) {
                    path.lineTo((float) x, (float) y1);
                }
                if (y2 < mHeight){
                    pathN.lineTo((float) x, (float) y2);
                }
                i += 0.01;
                if (i >= 1 && x < mWidth){
//                    Log.d(TAG,"aaa i >= 1--x_base: " + x_base);
                    x_base += waveWidth;
                    i = -1;
                }else if (i >= 1 && x >= mWidth + 2 * waveWidth){
//                    Log.d(TAG,"aaa: " + x);
                    break;
                }
            }
            canvas.drawPath(path,paint);
            canvas.drawPath(pathN,paint);

        }
    }





    public void stopAnimate(){
        run = false;
    }

    public void startAnimate(){
        run = true;
        postInvalidate();
    }

}
