package com.rc.facecase.activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.text.HtmlCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rc.facecase.R;
import com.rc.facecase.base.BaseActivity;
import com.rc.facecase.model.AppUser;
import com.rc.facecase.model.Category;
import com.rc.facecase.retrofit.APIClient;
import com.rc.facecase.retrofit.APIInterface;
import com.rc.facecase.retrofit.APIResponse;
import com.rc.facecase.util.AllConstants;
import com.rc.facecase.util.AppUtil;
import com.rc.facecase.util.Logger;
import com.reversecoder.library.storage.SessionManager;
import com.reversecoder.library.util.AllSettingsManager;

import org.parceler.Parcels;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class CategoryActivity extends BaseActivity {

    TextView tvPlayingList;
    ImageView ivPicturePlay,ivMusicPlay;
    private AppUser mAppUser;
    Category pictureCategory,musicCategory;
    //Background task
    private APIInterface mApiInterface;
    private GetCategoryListTask getCategoryListTask;

    @Override
    public String[] initActivityPermissions() {
        return new String[]{};
    }

    @Override
    public int initActivityLayout() {
        return R.layout.activity_category_screen;
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
        tvPlayingList= (TextView)findViewById(R.id.tv_playing_list);
        ivPicturePlay= (ImageView)findViewById(R.id.iv_picture_play);
        ivMusicPlay= (ImageView)findViewById(R.id.iv_music_play);
    }

    @Override
    public void initActivityViewsData(Bundle savedInstanceState) {
        mApiInterface = APIClient.getClient(getActivity()).create(APIInterface.class);

        String appUserID = SessionManager.getStringSetting(getActivity(), AllConstants.SESSION_KEY_USER);
        if (!AllSettingsManager.isNullOrEmpty(appUserID)) {
            mAppUser = APIResponse.getResponseObject(appUserID, AppUser.class);
            Logger.d(TAG, TAG + " >>> " + "mAppUser: " + mAppUser.toString());
        }
       // Logger.d(TAG, TAG + " getId>>> " + "mAppUser: " + mAppUser.getId());

        getCategoryListTask = new GetCategoryListTask(getActivity());
        getCategoryListTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    public void initActivityActions(Bundle savedInstanceState) {
        ivPicturePlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pictureCategory!=null) {
                    Intent iFacePlay = new Intent(getApplicationContext(), PictureCategoryActivity.class);
                    iFacePlay.putExtra(AllConstants.SESSION_KEY_PICTURE_CATEGORY, Parcels.wrap(pictureCategory));
                    startActivity(iFacePlay);
                }
            }
        });
        ivMusicPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (musicCategory!=null) {
                    Intent iFaceMusicPlay = new Intent(getApplicationContext(), MusicCategoryActivity.class);
                    iFaceMusicPlay.putExtra(AllConstants.SESSION_KEY_MUSIC_CATEGORY, Parcels.wrap(musicCategory));
                    startActivity(iFaceMusicPlay);
                }
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
        dismissProgressDialog();
        if (getCategoryListTask != null && getCategoryListTask.getStatus() == AsyncTask.Status.RUNNING) {
            getCategoryListTask.cancel(true);
        }
    }

    @Override
    public void initActivityPermissionResult(int requestCode, String[] permissions, int[] grantResults) {

    }


    /************************
     * Server communication *
     ************************/
    private class GetCategoryListTask extends AsyncTask<String, Integer, Response> {

        Context mContext;

        public GetCategoryListTask(Context context) {
            mContext = context;
        }

        @Override
        protected void onPreExecute() {
            showProgressDialog();
        }

        @Override
        protected Response doInBackground(String... params) {
            try {
                Call<APIResponse<List<Category>>> call = mApiInterface.apiGetCategoryList(mAppUser.getId());
                Response response = call.execute();
                Logger.d(TAG, TAG + " Category>>> " + "response: " + response);
                if (response.isSuccessful()) {
                    return response;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Response result) {
            try {
                dismissProgressDialog();

                if (result != null && result.isSuccessful()) {
                    Logger.d(TAG, "APIResponse(GetCategoryListTask): onResponse-server = " + result.toString());
                    APIResponse<List<Category>> data = (APIResponse<List<Category>>) result.body();
                    Logger.e("Category", data.toString() + "");

                    if (data != null && data.getSuccess().equalsIgnoreCase("1")) {
                        Logger.d(TAG, "APIResponse(GetCategoryListTask()): onResponse-object = " + data.toString());


                        if (data.getData().size() > 0) {
                            pictureCategory = data.getData().get(0);
                            musicCategory = data.getData().get(1);
                            Log.e("categoryPicture",pictureCategory.toString()+"");
                            Log.e("categoryMusic",musicCategory.toString()+"");
                        }
//
//                        //set count review number
//                        tvReviewCount.setText(getString(R.string.view_review_count, data.getData().size()));
                    } else {
                        Toast.makeText(getActivity(), getResources().getString(R.string.toast_no_info_found), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity(), getResources().getString(R.string.toast_could_not_retrieve_info), Toast.LENGTH_SHORT).show();
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }


//    TextView tvPlayingList;
//    ImageView ivPicturePlay,ivMusicPlay;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        setContentView(R.layout.activity_category_screen);
//        initUI();
//        setOnClickListener();
//    }
//
//    private void initUI() {
//        tvPlayingList= (TextView)findViewById(R.id.tv_playing_list);
//        ivPicturePlay= (ImageView)findViewById(R.id.iv_picture_play);
//        ivMusicPlay= (ImageView)findViewById(R.id.iv_music_play);
//
//
//       // tvPlayingList.setText(Html.fromHtml(getResources().getString(R.string.txt_before_playing_list)));
//
//    }
//    private void setOnClickListener() {
//        ivPicturePlay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent iFacePlay=new Intent(getApplicationContext(),PictureCategoryActivity.class);
//                startActivity(iFacePlay);
//            }
//        });
//        ivMusicPlay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent iFacePlay=new Intent(getApplicationContext(),MusicCategoryActivity.class);
//                startActivity(iFacePlay);
//            }
//        });
//
//    }
}
