package com.thinkmobiles.easyerp.data.services;

import com.thinkmobiles.easyerp.data.model.user.ResponseGetCurrentUser;
import com.thinkmobiles.easyerp.data.model.user.organization.ResponseGetOrganizationSettings;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Lynx on 1/18/2017.
 */

public interface UserService {

    @GET(Constants.GET_CURRENT_USER)
    Observable<ResponseGetCurrentUser> getCurrentUser();

    @GET(Constants.GET_LOGOUT)
    Observable<Void> logOut();

    @FormUrlEncoded
    @PATCH(Constants.PATCH_CHANGE_PASSWORD)
    Observable<Void> changePassword(@Path("userId") final String userId, @Field("oldpass") final String oldPass, @Field("pass") final String pass);

    @GET(Constants.GET_ORGANIZATION_SETTINGS)
    Observable<ResponseGetOrganizationSettings> getOrganizationSettings();
}
