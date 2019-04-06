package com.rc.facecase.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.rc.facecase.R;
import com.rc.facecase.base.BaseActivity;
import com.rc.facecase.model.AppUser;
import com.rc.facecase.model.Items;
import com.rc.facecase.model.ParamsUpdateUserHistory;
import com.rc.facecase.retrofit.APIClient;
import com.rc.facecase.retrofit.APIInterface;
import com.rc.facecase.retrofit.APIResponse;
import com.rc.facecase.util.AllConstants;
import com.rc.facecase.util.AppUtil;
import com.rc.facecase.util.Logger;
import com.reversecoder.library.event.OnSingleClickListener;
import com.reversecoder.library.network.NetworkManager;
import com.reversecoder.library.storage.SessionManager;
import com.reversecoder.library.util.AllSettingsManager;
import com.rodolfonavalon.shaperipplelibrary.ShapeRipple;
import com.rodolfonavalon.shaperipplelibrary.model.Image;

import org.parceler.Parcels;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Response;

import static com.rc.facecase.util.AllConstants.ANSWER_TITLE;
import static com.rc.facecase.util.AllConstants.SUB_CATEGORY_NAME;
import static com.rc.facecase.util.AllConstants.SUB_CATEGORY_SOURCE_NAME;

public class GamePlayActivity extends BaseActivity {

    // PlayCountDownTimer playCountDownTimer;
    private final long splashTime = 8 * 1000;
    private final long interval = 1000;
    private TextView tvCount, tvTitle, tvAnswerTitle,tvAdditionalTimeTitle;
    private ImageView ivBack, ivHome, ivLoading, ivAnswer, ivPlay11Sec, ivPlaceHolder, ivShowAnswer;

    private ShapeRipple shapeRipple;
    private LinearLayout linAnswer, linShowAnswer;
    //  private String imageUrl = "http://iexpresswholesale.com/faceoff-games/uploads/pictures/pele.jpg";
    private String imageUrl = "", subCategoryName = "", answerTitle = "";
    private AppUser mAppUser;
    private Items items;

    //Background task
    private APIInterface mApiInterface;
    private UpdateUserHistoryTask updateUserHistoryTask;

    @Override
    public String[] initActivityPermissions() {
        return new String[]{};
    }

    @Override
    public int initActivityLayout() {
        return R.layout.activity_game_play_screen;

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
            subCategoryName = getIntent().getExtras().getString(SUB_CATEGORY_NAME);
            imageUrl = getIntent().getExtras().getString(SUB_CATEGORY_SOURCE_NAME);
            answerTitle = getIntent().getExtras().getString(ANSWER_TITLE);
            Logger.d(TAG, TAG + " >>> " + "answerTitle: " + answerTitle);
            Parcelable mParcelableItem = intent.getParcelableExtra(AllConstants.INTENT_KEY_ITEM);
            if (mParcelableItem != null) {
                items = Parcels.unwrap(mParcelableItem);
                Logger.d(TAG, TAG + " >>> " + "mParcelableItem: " + mParcelableItem.toString());
            }
            if (imageUrl != null) {
                Logger.d(TAG, TAG + " >>> " + "subCategoryName: " + subCategoryName);
                Logger.d(TAG, TAG + " >>> " + "imageUrl: " + imageUrl);
            }
        }
    }

    @Override
    public void initActivityViews() {
        shapeRipple = (ShapeRipple) findViewById(R.id.shape_ripple);
        linAnswer = (LinearLayout) findViewById(R.id.lin_answer);
        linShowAnswer = (LinearLayout) findViewById(R.id.lin_show_answer);
        ivBack = (ImageView) findViewById(R.id.iv_back);
        ivHome = (ImageView) findViewById(R.id.iv_home);
        ivLoading = (ImageView) findViewById(R.id.iv_loading);
        ivShowAnswer = (ImageView) findViewById(R.id.iv_show_answer);
        ivAnswer = (ImageView) findViewById(R.id.iv_answer);
        ivPlay11Sec = (ImageView) findViewById(R.id.iv_play_11sec);
        ivPlaceHolder = (ImageView) findViewById(R.id.iv_placeholder);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvAnswerTitle = (TextView) findViewById(R.id.tv_answer_title);
        tvAdditionalTimeTitle = (TextView) findViewById(R.id.tv_additional_time_title);
        tvCount = (TextView) findViewById(R.id.tv_count);
        AllConstants.isShown = true;
    }

    @Override
    public void initActivityViewsData(Bundle savedInstanceState) {
        mApiInterface = APIClient.getClient(getActivity()).create(APIInterface.class);
        tvTitle.setText(subCategoryName);

        if (items != null) {
            tvAnswerTitle.setText(items.getTitle());
        }
        String appUserID = SessionManager.getStringSetting(getActivity(), AllConstants.SESSION_KEY_USER);
        if (!AllSettingsManager.isNullOrEmpty(appUserID)) {
            mAppUser = APIResponse.getResponseObject(appUserID, AppUser.class);
            Logger.d(TAG, TAG + " >>> " + "mAppUser: " + mAppUser.toString());
        }
        try {
            // Load
            Glide.with(GamePlayActivity.this)
                    .asGif()
                    .load(R.drawable.gif_loading)
                    .into(ivLoading);

            Glide
                    .with(GamePlayActivity.this)
                    .asBitmap()
                    .load(items.getSource())
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(Bitmap bitmap, Transition<? super Bitmap> transition) {
                            //you can use loaded bitmap here
                            shapeRipple.setVisibility(View.VISIBLE);
                            ivLoading.setVisibility(View.GONE);
                            shapeRipple.setRippleShape(new Image(bitmap));
                            shapeRipple.setEnableSingleRipple(true);
                            shapeRipple.setEnableRandomPosition(true);
                            shapeRipple.setRippleMaximumRadius(200);
                            shapeRipple.setRippleCount(2);
                            shapeRipple.setRippleDuration(1500);
                            new CountDownTimer(splashTime, interval) {
                                public void onTick(long millisUntilFinished) {
                                    Log.e("leftSeconds>>>", millisUntilFinished / 1000 + "");
                                    tvCount.setText("" + millisUntilFinished / 1000);

                                }

                                public void onFinish() {
                                    tvCount.setVisibility(View.GONE);
                                    linAnswer.setVisibility(View.VISIBLE);
                                    ivPlaceHolder.setVisibility(View.VISIBLE);
                                    tvAdditionalTimeTitle.setVisibility(View.VISIBLE);
                                    shapeRipple.stopRipple();
                                }
                            }.start();
                        }
                    });
            AppUtil.loadImage(getApplicationContext(), ivShowAnswer, items.getSource(), false, false, false);


        } catch (Exception ex) {
            ex.printStackTrace();
        }

        //Check internet connection
        if (!NetworkManager.isConnected(getActivity())) {
            Toast.makeText(getActivity(), getResources().getString(R.string.toast_network_error), Toast.LENGTH_SHORT).show();

        } else {
            if (items != null) {
                //Update User History
                ParamsUpdateUserHistory paramUpdateUserHistory = new ParamsUpdateUserHistory(mAppUser.getId(), items.getId());
                updateUserHistoryTask = new UpdateUserHistoryTask(getActivity(), paramUpdateUserHistory);
                updateUserHistoryTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }
        }
    }

    @Override
    public void initActivityActions(Bundle savedInstanceState) {

        ivHome.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View view) {
                Intent iFacePlay = new Intent(getApplicationContext(), FaceCasePlayActivity.class);
                startActivity(iFacePlay);
                initActivityBackPress();
            }
        });

        ivBack.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View view) {
                AllConstants.isShown = true;
                initActivityBackPress();
            }
        });
        ivAnswer.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View view) {
                ivShowAnswer.setVisibility(View.VISIBLE);
                linShowAnswer.setVisibility(View.VISIBLE);
                // tvAnswerTitle.setVisibility(View.VISIBLE);
                linAnswer.setVisibility(View.GONE);
                ivPlaceHolder.setVisibility(View.GONE);
                tvAdditionalTimeTitle.setVisibility(View.GONE);

            }
        });
        ivPlay11Sec.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View view) {
                shapeRipple.startRipple();
                tvCount.setVisibility(View.VISIBLE);
                linAnswer.setVisibility(View.GONE);
                ivPlaceHolder.setVisibility(View.GONE);
                tvAdditionalTimeTitle.setVisibility(View.GONE);
                new CountDownTimer(12000, 1000) {

                    public void onTick(long millisUntilFinished) {
                        Log.e("leftSeconds>>>", millisUntilFinished / 1000 + "");
                        tvCount.setText("" + millisUntilFinished / 1000);

                    }

                    public void onFinish() {
                        tvAdditionalTimeTitle.setVisibility(View.VISIBLE);
                        tvAdditionalTimeTitle.setText(getResources().getString(R.string.txt_time_answer));
                        tvCount.setVisibility(View.GONE);
                        ivPlay11Sec.setVisibility(View.GONE);
                        ivAnswer.setVisibility(View.VISIBLE);
                        linAnswer.setVisibility(View.VISIBLE);
                        ivPlaceHolder.setVisibility(View.VISIBLE);
                        shapeRipple.stopRipple();

                    }
                }.start();
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
        dismissProgressDialog();
        if (updateUserHistoryTask != null && updateUserHistoryTask.getStatus() == AsyncTask.Status.RUNNING) {
            updateUserHistoryTask.cancel(true);
        }
        AllConstants.isShown = true;
    }

    @Override
    public void initActivityPermissionResult(int requestCode, String[] permissions, int[] grantResults) {

    }

    @Override
    public void onResume() {
        super.onResume();
        AllConstants.isShown = true;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        AllConstants.isShown = true;
    }

    /*
            public class PlayCountDownTimer extends CountDownTimer {

                public PlayCountDownTimer(long startTime, long interval) {
                    super(startTime, interval);
                    // Calculate left seconds.
                    long leftSeconds = startTime / 1000;
                    tvCount.setVisibility(View.VISIBLE);
                    Log.e("leftSeconds",leftSeconds+"");
        //            tvCount.setText(String.valueOf(getDateFromMillis(startTime)));

                }

                @Override
                public void onFinish() {
                  //  tvCount.setVisibility(View.GONE);
                }

                @Override
                public void onTick(long millisUntilFinished) {
                }
            }
        */
    public static String getDateFromMillis(long d) {
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
        df.setTimeZone(TimeZone.getTimeZone("GMT"));
        return df.format(d);
    }

    /************************
     * Server communication *
     ************************/
    private class UpdateUserHistoryTask extends AsyncTask<String, Integer, Response> {

        Context mContext;
        ParamsUpdateUserHistory mParamUpdateUserHistory;

        private UpdateUserHistoryTask(Context context, ParamsUpdateUserHistory paramUpdateUserHistory) {
            mContext = context;
            mParamUpdateUserHistory = paramUpdateUserHistory;
        }

        @Override
        protected void onPreExecute() {
            //  showProgressDialog();
        }

        @Override
        protected Response doInBackground(String... params) {
            try {
                Logger.d(TAG, TAG + " >>> " + "AppUser(home-param): " + mParamUpdateUserHistory.toString());
                Call<APIResponse> call = mApiInterface.apiUpdateUserBrowsingHistory(mParamUpdateUserHistory);

                Response response = call.execute();
                Logger.d(TAG, TAG + " >>> " + "response: " + response);
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
                // dismissProgressDialog();

                if (result != null && result.isSuccessful()) {
                    Logger.d(TAG, TAG + " >>> " + "APIResponse(UpdateUserHistory): onResponse-server = " + result.toString());
                    APIResponse data = (APIResponse) result.body();
                    Logger.d(TAG, TAG + " >>> " + "data: " + data.toString() + "");

                    if (data != null && data.getStatus().equalsIgnoreCase("1")) {
                        Logger.d(TAG, TAG + " >>> " + "APIResponse(UpdateUserHistory()): onResponse-object = " + data.toString());


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


}
