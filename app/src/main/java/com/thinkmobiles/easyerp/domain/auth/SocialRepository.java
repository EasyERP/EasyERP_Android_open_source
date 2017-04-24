package com.thinkmobiles.easyerp.domain.auth;

import android.os.Bundle;

import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.google.gson.Gson;
import com.thinkmobiles.easyerp.data.model.social.FacebookResponse;
import com.thinkmobiles.easyerp.data.model.social.SocialRegisterProfile;
import com.thinkmobiles.easyerp.presentation.base.NetworkRepository;
import com.thinkmobiles.easyerp.presentation.screens.login.LoginContract;

import org.androidannotations.annotations.EBean;

import rx.Observable;

/**
 * @author Michael Soyma (Created on 4/24/2017).
 *         Company: Thinkmobiles
 *         Email:  michael.soyma@thinkmobiles.com
 */
@EBean(scope = EBean.Scope.Singleton)
public class SocialRepository extends NetworkRepository implements LoginContract.SocialModel {

    @Override
    public Observable<SocialRegisterProfile> loginWithFacebook(LoginResult loginResult) {
        return getNetworkObservable(Observable.fromCallable(() -> {
            GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), null);
            Bundle parameters = new Bundle();
            parameters.putString("fields", "id, first_name, last_name, email, link, location");
            request.setParameters(parameters);

            final GraphResponse graphResponse = request.executeAndWait();

            if (graphResponse != null) {
                if (graphResponse.getError() == null) {
                    final FacebookResponse facebookResponse = new Gson().fromJson(graphResponse.getJSONObject().toString(), FacebookResponse.class);
                    final SocialRegisterProfile socialRegisterProfile = SocialRegisterProfile.withFacebook(facebookResponse);
                    socialRegisterProfile.accessToken = loginResult.getAccessToken().getToken();
                    return socialRegisterProfile;
                } else throw new Exception(graphResponse.getError().toString());
            } else throw new Exception("Failed to get result");
        }));
    }
}
