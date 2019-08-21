package com.rc.facecase.activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rc.facecase.R;
import com.rc.facecase.adapter.AdditionalCategoryListAdapter;
import com.rc.facecase.adapter.CategoryListAdapter;
import com.rc.facecase.base.BaseActivity;
import com.rc.facecase.decoration.ItemOffsetDecoration;
import com.rc.facecase.model.AppUser;
import com.rc.facecase.model.Category;
import com.rc.facecase.retrofit.APIClient;
import com.rc.facecase.retrofit.APIInterface;
import com.rc.facecase.retrofit.APIResponse;
import com.rc.facecase.util.AllConstants;
import com.rc.facecase.util.DataUtils;
import com.rc.facecase.util.Logger;
import com.reversecoder.library.event.OnSingleClickListener;
import com.reversecoder.library.network.NetworkManager;
import com.reversecoder.library.storage.SessionManager;
import com.reversecoder.library.util.AllSettingsManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class AdditionalCategoryActivity extends BaseActivity {

    private ImageView ivHome;
    private RecyclerView rvCategory;
    private AdditionalCategoryListAdapter additionalCategoryListAdapter;

    private AppUser mAppUser;
    //Background task
    private APIInterface mApiInterface;
    private GetCategoryListTask getCategoryListTask;

    @Override
    public String[] initActivityPermissions() {
        return new String[]{};
    }

    @Override
    public int initActivityLayout() {
        return R.layout.activity_additonal_category_screen;
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
        rvCategory = (RecyclerView) findViewById(R.id.rv_category_list);
        ivHome = (ImageView) findViewById(R.id.iv_home);
    }

    @Override
    public void initActivityViewsData(Bundle savedInstanceState) {
        mApiInterface = APIClient.getClient(getActivity()).create(APIInterface.class);
        additionalCategoryListAdapter = new AdditionalCategoryListAdapter(getApplicationContext());
        rvCategory.setNestedScrollingEnabled(false);
        rvCategory.setLayoutManager( new GridLayoutManager( getActivity(), 4) );
        rvCategory.setHasFixedSize( true );
        // For spacing among items
        // rvSubCategory.addItemDecoration(new EqualSpacingItemDecoration(16, EqualSpacingItemDecoration.HORIZONTAL));
        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(getActivity(), R.dimen.dp_2_5);
        rvCategory.addItemDecoration(itemDecoration);
        rvCategory.scrollToPosition(0);
        String appUserID = SessionManager.getStringSetting(getActivity(), AllConstants.SESSION_KEY_USER);

        if (!AllSettingsManager.isNullOrEmpty(appUserID)) {
            mAppUser = APIResponse.getResponseObject(appUserID, AppUser.class);
            Logger.d(TAG, TAG + " >>> " + "mAppUser: " + mAppUser.toString());
        }

        if (mAppUser != null) {
            //Check internet connection
            if (!NetworkManager.isConnected(getActivity())) {
                Toast.makeText(getActivity(), getResources().getString(R.string.toast_network_error), Toast.LENGTH_SHORT).show();
                return;
            }

            getCategoryListTask = new GetCategoryListTask(getActivity());
            getCategoryListTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
    }

    @Override
    public void initActivityActions(Bundle savedInstanceState) {
        ivHome.setOnClickListener(new OnSingleClickListener() {
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
        finish();
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

    private void initCategoryData(List<Category> categories) {
        if (additionalCategoryListAdapter != null) {
            additionalCategoryListAdapter.addAll(categories);
            rvCategory.setAdapter(additionalCategoryListAdapter);
            additionalCategoryListAdapter.notifyDataSetChanged();
        }
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
                            List<Category> additionalCategory = DataUtils.getCategoryList(data.getData(),"2");
                            if (additionalCategory.size()>0 && additionalCategory!=null){
                                initCategoryData(additionalCategory);
                            }
                            Logger.d(TAG, "additionalCategory onResponse= " + additionalCategory.toString());
                        }
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
