package com.rc.facecase.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.rc.facecase.MainActivity;
import com.rc.facecase.R;
import com.rc.facecase.adapter.CategoryListAdapter;
import com.rc.facecase.adapter.SubCategoryListAdapter;
import com.rc.facecase.base.BaseActivity;
import com.rc.facecase.model.Category;
import com.rc.facecase.util.AllConstants;
import com.rc.facecase.util.Logger;

import org.parceler.Parcels;

public class MusicCategoryActivity extends BaseActivity {
    ImageView icBack,ivFamousCommercials;
    Category musicCategory;
    private RecyclerView rvCategory;
    private SubCategoryListAdapter subCategoryListAdapter;
    @Override
    public String[] initActivityPermissions() {
        return new String[]{};

    }

    @Override
    public int initActivityLayout() {
        return R.layout.activity_home_screen;
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
            Parcelable mParcelableMusicCategory = intent.getParcelableExtra(AllConstants.SESSION_KEY_MUSIC_CATEGORY);
            if (mParcelableMusicCategory != null) {
                musicCategory = Parcels.unwrap(mParcelableMusicCategory);
                Logger.d(TAG, TAG + " >>> " + "musicCategory: " + musicCategory.toString());
            }
        }
    }

    @Override
    public void initActivityViews() {
        icBack= (ImageView)findViewById(R.id.ic_back);
        ivFamousCommercials= (ImageView)findViewById(R.id.iv_famous_commercials);
    }

    @Override
    public void initActivityViewsData(Bundle savedInstanceState) {

    }

    @Override
    public void initActivityActions(Bundle savedInstanceState) {
        icBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initActivityBackPress();
            }
        });

        ivFamousCommercials.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iFacePlay=new Intent(getApplicationContext(), GamePlayActivity.class);
                startActivity(iFacePlay);
            }
        });
    }

    @Override
    public void initActivityOnResult(int requestCode, int resultCode, Intent data) {

    }

    @Override
    public void initActivityBackPress() {
        finish();
    }

    @Override
    public void initActivityDestroyTasks() {

    }

    @Override
    public void initActivityPermissionResult(int requestCode, String[] permissions, int[] grantResults) {

    }


}
