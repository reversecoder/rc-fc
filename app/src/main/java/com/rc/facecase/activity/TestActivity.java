//package com.rc.facecase.activity;
//
//import android.graphics.Bitmap;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.view.View;
//import android.view.Window;
//import android.view.WindowManager;
//import android.widget.ImageView;
//
//import com.bumptech.glide.Glide;
//import com.bumptech.glide.request.target.SimpleTarget;
//import com.bumptech.glide.request.transition.Transition;
//import com.rc.facecase.R;
//import com.rodolfonavalon.shaperipplelibrary.ShapeRipple;
//import com.rodolfonavalon.shaperipplelibrary.model.Image;
//
//public class TestActivity extends AppCompatActivity {
//
//    private ImageView ivLoading;
//    private ShapeRipple shapeRipple;
//    private String imageUrl = "http://iexpresswholesale.com/faceoff-games/uploads/pictures/pele.jpg";
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        setContentView(R.layout.activity_test);
//
//        ivLoading = (ImageView) findViewById(R.id.iv_loading);
//        shapeRipple = (ShapeRipple) findViewById(R.id.shape_ripple);
//        try {
//            // Load
//            Glide.with(TestActivity.this)
//                    .asGif()
//                    .load(R.drawable.gif_loading)
//                    .into(ivLoading);
//
//            Glide
//                    .with(TestActivity.this)
//                    .asBitmap()
//                    .load(imageUrl)
//                    .into(new SimpleTarget<Bitmap>() {
//                        @Override
//                        public void onResourceReady(Bitmap bitmap, Transition<? super Bitmap> transition) {
//                            //you can use loaded bitmap here
//                            shapeRipple.setVisibility(View.VISIBLE);
//                            ivLoading.setVisibility(View.GONE);
//                            shapeRipple.setRippleShape(new Image(bitmap));
//                            shapeRipple.setEnableSingleRipple(true);
//                            shapeRipple.setEnableRandomPosition(true);
//                            shapeRipple.setRippleMaximumRadius(200);
//                            shapeRipple.setRippleCount(2);
//                            shapeRipple.setRippleDuration(2000);
//                        }
//                    });
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//}
