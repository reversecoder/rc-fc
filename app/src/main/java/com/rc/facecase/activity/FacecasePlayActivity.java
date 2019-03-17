package com.rc.facecase.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.rc.facecase.R;

public class FacecasePlayActivity extends AppCompatActivity {

    ImageView ivPlay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_facecase_screen);
        initUI();
        setOnClickListener();
    }

    private void initUI() {
        ivPlay= (ImageView)findViewById(R.id.iv_play);
    }


    private void setOnClickListener() {
        ivPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iFacePlay=new Intent(getApplicationContext(),CategoryActivity.class);
                startActivity(iFacePlay);
            }
        });
    }
}
