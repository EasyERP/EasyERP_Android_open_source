package com.thinkmobiles.easyerp.data.services;

import com.thinkmobiles.easyerp.data.model.social.SocialRegisterProfile;
import com.thinkmobiles.easyerp.data.model.user.UpdateUser;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Lynx on 1/13/2017.
 */

public interface LoginService {

    @FormUrlEncoded
    @POST(Constants.POST_LOGIN)
    Observable<Void> login(@Field("login") String login, @Field("pass") String pass, @Field("dbId") String dbId, @Field("rememberMe") boolean isRememberMe);

    @POST(Constants.POST_LOGIN_SOCIAL)
    Observable<Void> loginSocial(@Body final SocialRegisterProfile socialRegisterProfile);

    @FormUrlEncoded
    @POST(Constants.POST_SIGN_UP)
    Observable<Void> signUp(
            @Field("login") String login,
            @Field("email") String email,
            @Field("first") String fName,
            @Field("last") String lName,
            @Field("pass") String password,
            @Field("dbId") String dbId);

    @PATCH(Constants.POST_UPDATE_USER)
    Observable<Void> update(@Path("userId") String userId, @Body UpdateUser data);

    @FormUrlEncoded
    @POST(Constants.POST_FORGOT_PASSWORD)
    Observable<Void> forgotPassword(@Field("login") String login, @Field("dbId") String dbId);
}
