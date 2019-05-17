package com.rc.facecase.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.rc.facecase.R;
import com.rc.facecase.util.Logger;
import com.reversecoder.library.event.OnSingleClickListener;
import com.reversecoder.library.storage.SessionManager;

import static com.rc.facecase.util.AllConstants.KEY_INTENT_EXTRA_HOME;
import static com.rc.facecase.util.AllConstants.SESSION_INTRODUCTION;


/**
 * @author Md. Rashadul Alam
 * Email: rashed.droid@gmail.com
 */
public class FaceCaseIntroductionActivity extends AppCompatActivity {

    private String TAG = FaceCaseIntroductionActivity.class.getSimpleName();
    private ImageView ivPlayIntroduction, ivSkip, ivHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facecase_introduction);

        Intent intent = getIntent();
        if (intent != null) {
            boolean isFromHome = intent.getBooleanExtra(KEY_INTENT_EXTRA_HOME, false);
            if (!isFromHome &&
                    SessionManager.getIntegerSetting(FaceCaseIntroductionActivity.this, SESSION_INTRODUCTION, 0) == 1) {
                Intent intentHome = new Intent(FaceCaseIntroductionActivity.this, HomeActivity.class);
                startActivity(intentHome);
                finish();
            } else {
                initUI();
            }
        }
        SessionManager.setIntegerSetting(FaceCaseIntroductionActivity.this, SESSION_INTRODUCTION, 1);
    }

    private void initUI() {
        ivPlayIntroduction = (ImageView) findViewById(R.id.iv_play_introduction);
        ivSkip = (ImageView) findViewById(R.id.iv_skip);
        ivHome = (ImageView) findViewById(R.id.iv_home);

        ivPlayIntroduction.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View view) {
                String socialLink = "https://www.youtube.com/watch?v=TjPaHzYyBks";

                try {
                    Uri uri = Uri.parse(socialLink);
                    Intent youtubeIntent = new Intent(Intent.ACTION_VIEW, uri);
                    youtubeIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(youtubeIntent);

                } catch (Exception e) {
                    Logger.d("youtube api", "exception: " + e.getMessage());
                    // Youtube is not installed. Open in the browser
                    Uri uri = Uri.parse(socialLink);
                    startActivity(new Intent(Intent.ACTION_VIEW, uri));
                }
            }
        });

        ivSkip.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View view) {
                Intent intent = new Intent(FaceCaseIntroductionActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        ivHome.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View view) {
                Intent intent = new Intent(FaceCaseIntroductionActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}