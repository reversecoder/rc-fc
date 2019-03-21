package com.rc.facecase.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.rc.facecase.R;
import com.rc.facecase.base.BaseActivity;
import com.rc.facecase.model.AppUser;
import com.rc.facecase.model.ParamsAppUser;
import com.rc.facecase.retrofit.APIClient;
import com.rc.facecase.retrofit.APIInterface;
import com.rc.facecase.retrofit.APIResponse;
import com.rc.facecase.util.AllConstants;
import com.rc.facecase.util.Logger;
import com.reversecoder.library.storage.SessionManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class HomeActivity extends BaseActivity {

    ImageView ivHome;

    //Background task
    private APIInterface mApiInterface;
    private RegisterAppUserTask registerAppUserTask;

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
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    public void initNavigationBarView() {

    }

    @Override
    public void initIntentData(Bundle savedInstanceState, Intent intent) {

    }

    @Override
    public void initActivityViews() {
        ivHome = (ImageView) findViewById(R.id.iv_home);
    }

    @Override
    public void initActivityViewsData(Bundle savedInstanceState) {
        mApiInterface = APIClient.getClient(getActivity()).create(APIInterface.class);

        String deviceUniqueIdentifier = null;
        TelephonyManager tm = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        if (null != tm) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            deviceUniqueIdentifier = tm.getDeviceId();
        }
        if (null == deviceUniqueIdentifier || 0 == deviceUniqueIdentifier.length()) {
            deviceUniqueIdentifier = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);
        }
        Logger.d(TAG, TAG + " >>> " + "AppUser(deviceUniqueIdentifier): " +deviceUniqueIdentifier);

        //Register app user
        ParamsAppUser paramAppUser = new ParamsAppUser(deviceUniqueIdentifier);
        registerAppUserTask = new RegisterAppUserTask(getActivity(), paramAppUser);
        registerAppUserTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    public void initActivityActions(Bundle savedInstanceState) {
        ivHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iFacePlay=new Intent(getApplicationContext(), FacecasePlayActivity.class);
                startActivity(iFacePlay);
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
        if (registerAppUserTask != null && registerAppUserTask.getStatus() == AsyncTask.Status.RUNNING) {
            registerAppUserTask.cancel(true);
        }
    }

    @Override
    public void initActivityPermissionResult(int requestCode, String[] permissions, int[] grantResults) {

    }
    /************************
     * Server communication *
     ************************/
    private class RegisterAppUserTask extends AsyncTask<String, Integer, Response> {

        Context mContext;
        ParamsAppUser mParamAppUser;

        private RegisterAppUserTask(Context context, ParamsAppUser paramAppUser) {
            mContext = context;
            mParamAppUser = paramAppUser;
        }

        @Override
        protected void onPreExecute() {
            showProgressDialog();
        }

        @Override
        protected Response doInBackground(String... params) {
            try {
                Logger.d(TAG, TAG + " >>> " + "AppUser(home-param): " + mParamAppUser.toString());
                Call<APIResponse<List<AppUser>>> call = mApiInterface.apiAddUser(mParamAppUser);

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
                dismissProgressDialog();

                if (result != null && result.isSuccessful()) {
                    Logger.d(TAG, TAG + " >>> " + "APIResponse(DoCreateUser): onResponse-server = " + result.toString());
                    APIResponse<List<AppUser>> data = (APIResponse<List<AppUser>>) result.body();
                    Logger.d(TAG, TAG + " >>> " + "data: " + data.toString() + "");

                    if (data != null && data.getStatus().equalsIgnoreCase("1")) {
                        Logger.d(TAG, TAG + " >>> " + "APIResponse(DoCreateUser()): onResponse-object = " + data.toString());

                            //Save data into session
                            String jsonAppUser = APIResponse.getResponseString(data.getData().get(0));
                            Logger.d(TAG, TAG + " >>> " + "APIResponse(DoCreateUser()): app-user = " + jsonAppUser);
                            Logger.d(TAG, TAG + " >>> " + "AppUser(home-response): " + jsonAppUser);
                            Log.e("AppUser>>>",data.getData().toString()+">>>");
                            SessionManager.setStringSetting(getActivity(), AllConstants.SESSION_KEY_USER,  jsonAppUser);
//                            //Navigate to the home
//                            Intent intentHome = new Intent(getActivity(), HomeActivity.class);
//                            startActivity(intentHome);
//                            finish();

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
