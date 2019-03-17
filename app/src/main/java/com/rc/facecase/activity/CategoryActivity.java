package com.rc.facecase.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.text.HtmlCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.rc.facecase.R;

public class CategoryActivity extends AppCompatActivity {

    TextView tvPlayingList;
    ImageView ivPicturePlay,ivMusicPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_category_screen);
        initUI();
        setOnClickListener();
    }

    private void initUI() {
        tvPlayingList= (TextView)findViewById(R.id.tv_playing_list);
        ivPicturePlay= (ImageView)findViewById(R.id.iv_picture_play);
        ivMusicPlay= (ImageView)findViewById(R.id.iv_music_play);


       // tvPlayingList.setText(Html.fromHtml(getResources().getString(R.string.txt_before_playing_list)));

    }
    private void setOnClickListener() {
        ivPicturePlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iFacePlay=new Intent(getApplicationContext(),PictureCategoryActivity.class);
                startActivity(iFacePlay);
            }
        });
        ivMusicPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iFacePlay=new Intent(getApplicationContext(),MusicCategoryActivity.class);
                startActivity(iFacePlay);
            }
        });

    }
}
