package com.rc.facecase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.rodolfonavalon.shaperipplelibrary.ShapeRipple;
import com.rodolfonavalon.shaperipplelibrary.model.Image;

public class MainActivity extends AppCompatActivity {

    private ShapeRipple shapeRipple;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        shapeRipple = (ShapeRipple) findViewById(R.id.shape_ripple);
        shapeRipple.setRippleShape(new Image(R.drawable.ic_man));
        shapeRipple.setEnableSingleRipple(true);
        shapeRipple.setEnableRandomPosition(true);
        shapeRipple.setRippleMaximumRadius(200);
        shapeRipple.setRippleCount(2);
        shapeRipple.setRippleDuration(2000);
    }
}
