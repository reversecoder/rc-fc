package com.rc.facecase.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.rc.facecase.R;
import com.rodolfonavalon.shaperipplelibrary.ShapeRipple;
import com.rodolfonavalon.shaperipplelibrary.model.Image;

public class HomeActivity extends AppCompatActivity {

     ImageView ivHome;
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
        ivHome= (ImageView)findViewById(R.id.iv_home);
    }

    private void setOnClickListener() {
        ivHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iFacePlay=new Intent(getApplicationContext(),FacecasePlayActivity.class);
                startActivity(iFacePlay);
            }
        });
    }


}
