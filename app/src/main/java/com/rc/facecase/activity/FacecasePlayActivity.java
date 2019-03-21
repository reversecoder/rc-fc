package com.rc.facecase.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.rc.facecase.R;
import com.rc.facecase.base.BaseActivity;

public class FacecasePlayActivity extends BaseActivity {
    ImageView ivPlay;

    @Override
    public String[] initActivityPermissions() {
        return new String[]{};

    }

    @Override
    public int initActivityLayout() {
        return R.layout.activity_facecase_screen;
    }

    @Override
    public void initStatusBarView() {

    }

    @Override
    public void initNavigationBarView() {

    }

    @Override
    public void initIntentData(Bundle savedInstanceState, Intent intent) {

    }

    @Override
    public void initActivityViews() {
        ivPlay= (ImageView)findViewById(R.id.iv_play);
    }

    @Override
    public void initActivityViewsData(Bundle savedInstanceState) {

    }

    @Override
    public void initActivityActions(Bundle savedInstanceState) {
        ivPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iFacePlay=new Intent(getApplicationContext(),CategoryActivity.class);
                startActivity(iFacePlay);
            }
        });
    }

    @Override
    public void initActivityOnResult(int requestCode, int resultCode, Intent data) {

    }

    @Override
    public void initActivityBackPress() {

    }

    @Override
    public void initActivityDestroyTasks() {

    }

    @Override
    public void initActivityPermissionResult(int requestCode, String[] permissions, int[] grantResults) {

    }

//    ImageView ivPlay;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        setContentView(R.layout.activity_facecase_screen);
//        initUI();
//        setOnClickListener();
//    }
//
//    private void initUI() {
//        ivPlay= (ImageView)findViewById(R.id.iv_play);
//    }
//
//
//    private void setOnClickListener() {
//        ivPlay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent iFacePlay=new Intent(getApplicationContext(),CategoryActivity.class);
//                startActivity(iFacePlay);
//            }
//        });
//    }
}
