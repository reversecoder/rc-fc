package com.rc.facecase.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.rc.facecase.R;

import java.util.Random;

/**
 * @author Md. Rashadul Alam
 * Email: rashed.droid@gmail.com
 */
public class RandomFlashManager {

    public interface RandomFlashListener {
        public void onTick(long millisUntilFinished);

        public void onFinish();
    }

    private Activity mActivity;
    private View mFlashView;
    private RandomFlashListener mRandomFlashListener;
    private long mMilliSecInFuture = 0, mCountDownInterval = 0;
    private CountDownTimer mFlashCountDownTimer;
    private String TAG = RandomFlashManager.class.getSimpleName();

    public RandomFlashManager(Activity activity, View flashView, long millisInFuture, long countDownInterval, RandomFlashListener randomFlashListener) {
        mActivity = activity;
        mFlashView = flashView;
        mMilliSecInFuture = millisInFuture;
        mCountDownInterval = countDownInterval;
        mRandomFlashListener = randomFlashListener;
    }

    public void startFlashing() {
        destroyFlashing();
        mFlashCountDownTimer = new CountDownTimer(mMilliSecInFuture, mCountDownInterval) {
            @Override
            public void onTick(long millisUntilFinished) {
                setRandomPosition(mFlashView);
                if (mRandomFlashListener != null) {
                    mRandomFlashListener.onTick(millisUntilFinished);
                }
            }

            @Override
            public void onFinish() {
                if (mRandomFlashListener != null) {
                    mRandomFlashListener.onFinish();
                }
            }
        };
        mFlashCountDownTimer.start();
    }

    public void destroyFlashing() {
        if (mFlashCountDownTimer != null) {
            mFlashCountDownTimer.cancel();
            mFlashCountDownTimer = null;
        }
        ImageBitmapRecycler.recycleBackgroundBitmap((ImageView) mFlashView);
    }

    public Point getDisplaySize(@NonNull Context context) {
        Point point = new Point();
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        manager.getDefaultDisplay().getSize(point);
        return point;
    }

    private void setRandomPosition(final View view) {
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                int lWidth = view.getWidth();
                int lHeight = view.getHeight();

                int boundX = getDisplaySize(mActivity).x - (3 * lWidth) / 2;
                int boundY = getDisplaySize(mActivity).y - (3 * lHeight) / 2;
                Logger.d(TAG, TAG + ">> boundX: " + boundX + "\nboundY: " + boundY);

                int randomX = new Random().nextInt(boundX);
                int randomY = new Random().nextInt(boundY);

                // Fade out view
                Animation animFadeOut = AnimationUtils.loadAnimation(mActivity, R.anim.fade_out);
                view.startAnimation(animFadeOut);

                // Set new location
                view.setX(randomX);
                view.setY(randomY);

                // Fade in view
                Animation animFadeIn = AnimationUtils.loadAnimation(mActivity, R.anim.fade_in);
                view.startAnimation(animFadeIn);
            }
        });
    }
}