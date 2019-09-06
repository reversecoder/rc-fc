package com.rc.facecase.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.daimajia.slider.library.Animations.NoDescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.rc.facecase.R;
import com.rc.facecase.model.Image;
import com.rc.facecase.util.Logger;
import com.reversecoder.library.event.OnSingleClickListener;
import com.reversecoder.library.storage.SessionManager;

import java.util.ArrayList;
import java.util.List;

import static com.rc.facecase.util.AllConstants.KEY_INTENT_EXTRA_HOME;
import static com.rc.facecase.util.AllConstants.SESSION_INTRODUCTION;


/**
 * @author Md. Rashadul Alam
 * Email: rashed.droid@gmail.com
 */
public class IntroductionActivity extends AppCompatActivity {

    private String TAG = IntroductionActivity.class.getSimpleName();
    private ImageView ivPlayIntroduction, ivStarBurst, ivAdditionalCategory, ivHome;
    private LinearLayout linIntroMian;
    private RelativeLayout linSliderLayout;
    private RelativeLayout relCross;

    //Image slider
    private SliderLayout mDemoSlider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);

        Intent intent = getIntent();
        if (intent != null) {
            boolean isFromHome = intent.getBooleanExtra(KEY_INTENT_EXTRA_HOME, false);
            if (!isFromHome &&
                    SessionManager.getIntegerSetting(IntroductionActivity.this, SESSION_INTRODUCTION, 0) == 1) {
                Intent intentHome = new Intent(IntroductionActivity.this, HomeActivity.class);
                startActivity(intentHome);
                finish();
            } else {
                initUI();
            }
        }
        SessionManager.setIntegerSetting(IntroductionActivity.this, SESSION_INTRODUCTION, 1);
    }

    private void initUI() {
        ivPlayIntroduction = (ImageView) findViewById(R.id.iv_play_introduction);
        ivStarBurst = (ImageView) findViewById(R.id.iv_starburst);
        ivAdditionalCategory = (ImageView) findViewById(R.id.iv_additional_categories);
        ivHome = (ImageView) findViewById(R.id.iv_home);
        linSliderLayout = (RelativeLayout) findViewById(R.id.lin_slider_layout);
        linIntroMian = (LinearLayout) findViewById(R.id.lin_lay_intro_mian);
        relCross = (RelativeLayout) findViewById(R.id.rel_cross);
        mDemoSlider = (SliderLayout) findViewById(R.id.slider_layout);

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

        ivStarBurst.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View view) {
                linIntroMian.setVisibility(View.GONE);
                linSliderLayout.setVisibility(View.VISIBLE);
                initImageSlider();
//                String socialLink = "https://www.google.com";
//
//                try {
//                    Uri uri = Uri.parse(socialLink);
//                    Intent youtubeIntent = new Intent(Intent.ACTION_VIEW, uri);
//                    youtubeIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    startActivity(youtubeIntent);
//
//                } catch (Exception e) {
//                    Logger.d("youtube api", "exception: " + e.getMessage());
//                    // Youtube is not installed. Open in the browser
//                    Uri uri = Uri.parse(socialLink);
//                    startActivity(new Intent(Intent.ACTION_VIEW, uri));
//                }
            }
        });

        relCross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeSlider();
            }
        });

        ivAdditionalCategory.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View view) {
                Intent intent = new Intent(IntroductionActivity.this, AdditionalCategoryActivity.class);
                startActivity(intent);
                // finish();
            }
        });

        ivHome.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View view) {
                Intent intent = new Intent(IntroductionActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void initImageSlider() {
        List<Image> foodImages = new ArrayList<>();
        foodImages.add(new Image(R.drawable.slider_1));
        foodImages.add(new Image(R.drawable.slider_2));
        foodImages.add(new Image(R.drawable.slider_3));
        foodImages.add(new Image(R.drawable.slider_4));
        foodImages.add(new Image(R.drawable.slider_5));
        foodImages.add(new Image(R.drawable.slider_6));
        foodImages.add(new Image(R.drawable.slider_7));
        foodImages.add(new Image(R.drawable.slider_8));
        foodImages.add(new Image(R.drawable.slider_9));
        foodImages.add(new Image(R.drawable.slider_10));

        for (final Image image : foodImages) {
            TextSliderView textSliderView = new TextSliderView(getApplicationContext());
            // initialize a SliderLayout
            textSliderView
                    .description("")
                    .descriptionVisibility(View.GONE)
                    .image(image.getResId())
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                        @Override
                        public void onSliderClick(BaseSliderView slider) {
//                            Toast.makeText(getActivity(),slider.getBundle().get("extra") + "",Toast.LENGTH_SHORT).show();
//                            Toast.makeText(getActivity(), image.getId(), Toast.LENGTH_SHORT).show();
                        }
                    });

            //add your extra information
//            textSliderView.bundle(new Bundle());
//            textSliderView.getBundle()
//                    .putString("extra", image.getId());

            mDemoSlider.addSlider(textSliderView);
        }

        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mDemoSlider.setIndicatorVisibility(PagerIndicator.IndicatorVisibility.Visible);
        mDemoSlider.setCustomAnimation(new NoDescriptionAnimation());
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setDuration(5000);
        mDemoSlider.addOnPageChangeListener(new ViewPagerEx.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        //Image slider
//        mDemoSlider.stopAutoCycle();
//    }

    private void closeSlider() {
        Animation animation = AnimationUtils.loadAnimation(IntroductionActivity.this, R.anim.hide_view);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                linIntroMian.setVisibility(View.VISIBLE);
                linSliderLayout.setVisibility(View.GONE);
                mDemoSlider.stopAutoCycle();
                mDemoSlider.removeAllSliders();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        linSliderLayout.startAnimation(animation);
    }

    @Override
    public void onBackPressed() {
        if (linSliderLayout.getVisibility() == View.VISIBLE) {
            closeSlider();
        } else {
            super.onBackPressed();
        }
    }
}