package com.rc.facecase.retrofit;

import com.rc.facecase.model.AppUser;
import com.rc.facecase.model.ParamsAppUser;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


/**
 * @author Md. Rashadul Alam
 * Email: rashed.droid@gmail.com
 */
public interface APIInterface {
    @POST("addUser")
    Call<APIResponse<List<AppUser>>> apiAddUser(@Body ParamsAppUser paramToken);

}