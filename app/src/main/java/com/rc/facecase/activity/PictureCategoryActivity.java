package com.rc.facecase.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.rc.facecase.MainActivity;
import com.rc.facecase.R;
import com.rc.facecase.base.BaseActivity;
import com.rc.facecase.model.Category;
import com.rc.facecase.util.AllConstants;
import com.rc.facecase.util.Logger;
import com.reversecoder.library.event.OnSingleClickListener;

import org.parceler.Parcels;

public class PictureCategoryActivity extends BaseActivity {
    ImageView ivBack,ivPrimarySports,ivAllotherSports;
    Category pictureCategory;
    @Override
    public String[] initActivityPermissions() {
        return new String[]{};

    }

    @Override
    public int initActivityLayout() {
        return R.layout.activity_picture_category_screen;
    }

    @Override
    public void initStatusBarView() {

    }

    @Override
    public void initNavigationBarView() {

    }

    @Override
    public void initIntentData(Bundle savedInstanceState, Intent intent) {
        if (intent != null) {
            Parcelable mParcelablePictureCategory = intent.getParcelableExtra(AllConstants.SESSION_KEY_PICTURE_CATEGORY);
            if (mParcelablePictureCategory != null) {
                pictureCategory = Parcels.unwrap(mParcelablePictureCategory);
                Logger.d(TAG, TAG + " >>> " + "pictureCategory: " + pictureCategory.toString());
            }
        }
    }

    @Override
    public void initActivityViews() {
        ivBack= (ImageView)findViewById(R.id.iv_back);
        ivPrimarySports= (ImageView)findViewById(R.id.iv_primary_sports);
        ivAllotherSports= (ImageView)findViewById(R.id.iv_allother_sports);
    }

    @Override
    public void initActivityViewsData(Bundle savedInstanceState) {

    }

    @Override
    public void initActivityActions(Bundle savedInstanceState) {
        ivBack.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View view) {
                initActivityBackPress();
            }
        });

        ivPrimarySports.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View view) {
                if (pictureCategory!=null) {
                    if (pictureCategory.getSub_categories().get(0).getSub_category_name().equalsIgnoreCase("Primary sports")) {
                        Intent iFacePlay = new Intent(getApplicationContext(), GamePlayActivity.class);
                        startActivity(iFacePlay);
                    }
                }

            }
        });
        ivAllotherSports.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View view) {
                initActivityBackPress();
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


}
