package com.rc.facecase.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.rc.facecase.R;

import com.reversecoder.library.storage.SessionManager;


/**
 * @author Md. Rashadul Alam
 *         Email: rashed.droid@gmail.com
 */
public class SplashActivity extends AppCompatActivity {

    SplashCountDownTimer splashCountDownTimer;
    private final long splashTime = 4 * 1000;
    private final long interval = 1 * 1000;
    TextView tvAppVersion;
    ImageView ivLoading;
    TextView tvLoadingMessage;
    private String TAG = SplashActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initSplashUI();
    }

    private void initSplashUI() {

        tvAppVersion = (TextView) findViewById(R.id.tv_app_version);
        tvAppVersion.setText(getString(R.string.app_version_text) + " " + getString(R.string.app_version_name));

        //loading gif
        ivLoading = (ImageView) findViewById(R.id.iv_app_logo);
        Glide
                .with(SplashActivity.this)
                .load(R.drawable.gif_loading)
                .apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC))
                .into(ivLoading);

        splashCountDownTimer = new SplashCountDownTimer(splashTime, interval);
        splashCountDownTimer.start();
    }

    public class SplashCountDownTimer extends CountDownTimer {
        public SplashCountDownTimer(long startTime, long interval) {
            super(startTime, interval);
        }

        @Override
        public void onFinish() {
                Intent intent = new Intent(SplashActivity.this, FaceCasePlayActivity.class);
                startActivity(intent);
                finish();
        }

        @Override
        public void onTick(long millisUntilFinished) {
        }
    }



}
