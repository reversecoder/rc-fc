package com.rc.facecase.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.rc.facecase.MainActivity;
import com.rc.facecase.R;

public class MusicCategoryActivity extends AppCompatActivity {

    TextView tvPlayingList;
    ImageView icBack,ivFamousCommercials;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home_screen);
        initUI();
        setOnClickListener();
    }

    private void initUI() {
       // tvPlayingList= (TextView)findViewById(R.id.tv_playing_list);
        icBack= (ImageView)findViewById(R.id.ic_back);
        ivFamousCommercials= (ImageView)findViewById(R.id.iv_famous_commercials);

    }
    private void setOnClickListener() {
        icBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });

        ivFamousCommercials.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iFacePlay=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(iFacePlay);
            }
        });
    }
}
