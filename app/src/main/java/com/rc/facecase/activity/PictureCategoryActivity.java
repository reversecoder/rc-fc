package com.rc.facecase.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.rc.facecase.R;
import com.rc.facecase.adapter.CategoryListAdapter;
import com.rc.facecase.adapter.SubCategoryListAdapter;
import com.rc.facecase.base.BaseActivity;
import com.rc.facecase.model.Category;
import com.rc.facecase.model.Items;
import com.rc.facecase.model.SubCategory;
import com.rc.facecase.util.AllConstants;
import com.rc.facecase.util.DataUtils;
import com.rc.facecase.util.Logger;
import com.reversecoder.library.event.OnSingleClickListener;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import static com.rc.facecase.util.AllConstants.SUB_CATEGORY_ALL_OTHER_SPORTS;
import static com.rc.facecase.util.AllConstants.SUB_CATEGORY_ENTERTAINERS;
import static com.rc.facecase.util.AllConstants.SUB_CATEGORY_FAMOUS_PEOPLE;
import static com.rc.facecase.util.AllConstants.SUB_CATEGORY_FAMOUS_PLACES;
import static com.rc.facecase.util.AllConstants.SUB_CATEGORY_FAMOUS_WORLD_PLACES;
import static com.rc.facecase.util.AllConstants.SUB_CATEGORY_SOURCE_NAME;
import static com.rc.facecase.util.AllConstants.SUB_CATEGORY_PRIMARY_SPORTS;

public class PictureCategoryActivity extends BaseActivity {
    private ImageView ivHome,ivBack,ivPrimarySports,ivAllotherSports,ivFamoususPlaces,ivFamousworldPlaces,ivEntertainers,ivFamoususPeople;
    private Category musicCategory;
  //  SubCategory subCategory;
    private List<SubCategory> subCategories = new ArrayList<>();
    private NestedScrollView mainScrollView;
    private RecyclerView rvSubCategory;
    private SubCategoryListAdapter subCategoryListAdapter;
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
                musicCategory = Parcels.unwrap(mParcelablePictureCategory);
                Logger.d(TAG, TAG + " >>> " + "musicCategory: " + musicCategory.toString());
            }
        }
    }

    @Override
    public void initActivityViews() {
        mainScrollView = (NestedScrollView)findViewById(R.id.nested_scroll);
        rvSubCategory= (RecyclerView)findViewById(R.id.rv_subcategory);
        ivBack= (ImageView)findViewById(R.id.iv_back);
        ivHome = (ImageView) findViewById(R.id.iv_home);

        ivPrimarySports = (ImageView)findViewById(R.id.iv_primary_sports);
        ivAllotherSports = (ImageView)findViewById(R.id.iv_allother_sports);
        ivFamoususPlaces = (ImageView)findViewById(R.id.iv_famousus_places);
        ivFamousworldPlaces = (ImageView)findViewById(R.id.iv_famousworld_places);
        ivEntertainers = (ImageView)findViewById(R.id.iv_entertainers);
        ivFamoususPeople = (ImageView)findViewById(R.id.iv_famous_people);

    }

    @Override
    public void initActivityViewsData(Bundle savedInstanceState) {

        subCategoryListAdapter = new SubCategoryListAdapter( getApplicationContext());
        rvSubCategory.setNestedScrollingEnabled(false);
        rvSubCategory.setLayoutManager( new GridLayoutManager( getActivity(), 3) );
        rvSubCategory.setHasFixedSize( true );
        rvSubCategory.scrollToPosition(0);
        initSubCategoryData(musicCategory);
    }

    @Override
    public void initActivityActions(Bundle savedInstanceState) {
        // Wait until my scrollView is ready
        mainScrollView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                // Ready, move up
                mainScrollView.fullScroll(View.FOCUS_UP);
            }
        });
        ivBack.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View view) {
                initActivityBackPress();
            }
        });

        ivHome.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View view) {
                initActivityBackPress();
            }
        });

//        ivPrimarySports.setOnClickListener(new OnSingleClickListener() {
//            @Override
//            public void onSingleClick(View view) {
//                if (pictureCategory!=null) {
//                    List<Items> pictureItem = DataUtils.getItemsList(pictureCategory,SUB_CATEGORY_PRIMARY_SPORTS);
//                    Log.e("pictureItem",pictureItem.toString()+">>>");
//                    if (pictureItem!=null&&pictureItem.size()>0){
//                        int itemPos = pictureItem.indexOf(pictureItem.get(0).getSource());
//                        Intent iFacePlay = new Intent(getApplicationContext(), GamePlayActivity.class);
//                        iFacePlay.putExtra(SUB_CATEGORY_SOURCE_NAME,pictureItem.get(0).getSource());
//                        startActivity(iFacePlay);
//                    }
//                }
//            }
//        });
//
//        ivAllotherSports.setOnClickListener(new OnSingleClickListener() {
//            @Override
//            public void onSingleClick(View view) {
//                if (pictureCategory!=null) {
//                    List<Items> allOtherSportsItem = DataUtils.getItemsList(pictureCategory,SUB_CATEGORY_ALL_OTHER_SPORTS);
//                    Log.e("allOtherSportsItem",allOtherSportsItem.toString()+">>>");
//                    if (allOtherSportsItem!=null && allOtherSportsItem.size()>0){
//                        Intent iFaceallOtherSportsPlay = new Intent(getApplicationContext(), GamePlayActivity.class);
//                        iFaceallOtherSportsPlay.putExtra(SUB_CATEGORY_SOURCE_NAME,allOtherSportsItem.get(0).getSource());
//                        startActivity(iFaceallOtherSportsPlay);
//                    }
//
//                }
//            }
//        });
//
//        ivFamoususPlaces.setOnClickListener(new OnSingleClickListener() {
//            @Override
//            public void onSingleClick(View view) {
//                if (pictureCategory!=null) {
//                    List<Items> famoususPlacesItem = DataUtils.getItemsList(pictureCategory,SUB_CATEGORY_FAMOUS_PLACES);
//                    Log.e("allOtherSportsItem",famoususPlacesItem.toString()+">>>");
//                    if (famoususPlacesItem!=null && famoususPlacesItem.size()>0){
//                        Intent iFaceFamoususPlacesPlay = new Intent(getApplicationContext(), GamePlayActivity.class);
//                        iFaceFamoususPlacesPlay.putExtra(SUB_CATEGORY_SOURCE_NAME,famoususPlacesItem.get(0).getSource());
//                        startActivity(iFaceFamoususPlacesPlay);
//                    }
//
//                }
//            }
//        });
//
//        ivFamousworldPlaces.setOnClickListener(new OnSingleClickListener() {
//            @Override
//            public void onSingleClick(View view) {
//                if (pictureCategory!=null) {
//                    List<Items> famousworldPlacesItem = DataUtils.getItemsList(pictureCategory,SUB_CATEGORY_FAMOUS_WORLD_PLACES);
//                    Log.e("allOtherSportsItem",famousworldPlacesItem.toString()+">>>");
//                    if (famousworldPlacesItem!=null && famousworldPlacesItem.size()>0){
//                        Intent iFaceFamousworldPlacesPlay = new Intent(getApplicationContext(), GamePlayActivity.class);
//                        iFaceFamousworldPlacesPlay.putExtra(SUB_CATEGORY_SOURCE_NAME,famousworldPlacesItem.get(0).getSource());
//                        startActivity(iFaceFamousworldPlacesPlay);
//                    }
//
//                }
//            }
//        });
//
//        ivEntertainers.setOnClickListener(new OnSingleClickListener() {
//            @Override
//            public void onSingleClick(View view) {
//                if (pictureCategory!=null) {
//                    List<Items> entertainersItem = DataUtils.getItemsList(pictureCategory,SUB_CATEGORY_ENTERTAINERS);
//                    Log.e("entertainersItem",entertainersItem.toString()+">>>");
//                    if (entertainersItem!=null && entertainersItem.size()>0){
//                        Intent iFaceEntertainersPlay = new Intent(getApplicationContext(), GamePlayActivity.class);
//                        iFaceEntertainersPlay.putExtra(SUB_CATEGORY_SOURCE_NAME,entertainersItem.get(0).getSource());
//                        startActivity(iFaceEntertainersPlay);
//                    }
//
//                }
//            }
//        });
//
//        ivFamoususPeople.setOnClickListener(new OnSingleClickListener() {
//            @Override
//            public void onSingleClick(View view) {
//                if (pictureCategory!=null) {
//                    List<Items> famoususPeopleItem = DataUtils.getItemsList(pictureCategory,SUB_CATEGORY_FAMOUS_PEOPLE);
//                    Log.e("allOtherSportsItem",famoususPeopleItem.toString()+">>>");
//                    if (famoususPeopleItem!=null && famoususPeopleItem.size()>0){
//                        Intent iFaceFamoususPeoplePlay = new Intent(getApplicationContext(), GamePlayActivity.class);
//                        iFaceFamoususPeoplePlay.putExtra(SUB_CATEGORY_SOURCE_NAME,famoususPeopleItem.get(0).getSource());
//                        startActivity(iFaceFamoususPeoplePlay);
//                    }
//
//                }
//            }
//        });

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
    private void initSubCategoryData(Category musicCategories ) {
        if (musicCategories !=null) {
            if (subCategoryListAdapter != null) {
                subCategoryListAdapter.addAll(musicCategories.getSub_categories());
                rvSubCategory.setAdapter(subCategoryListAdapter);
                Log.e("getSub_categories",musicCategories.getSub_categories().size()+"");
                subCategoryListAdapter.notifyDataSetChanged();
            }
        }
    }

}
