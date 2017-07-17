package com.thinkmobiles.easyerp.domain.auth;

import com.thinkmobiles.easyerp.data.api.Rest;
import com.thinkmobiles.easyerp.data.model.social.SocialRegisterProfile;
import com.thinkmobiles.easyerp.data.model.user.UpdateUser;
import com.thinkmobiles.easyerp.data.services.LoginService;
import com.thinkmobiles.easyerp.presentation.base.NetworkRepository;
import com.thinkmobiles.easyerp.presentation.screens.login.LoginContract;
import com.thinkmobiles.easyerp.presentation.screens.tutorial.TutorialContract;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import org.androidannotations.annotations.EBean;

import rx.Observable;

import static com.thinkmobiles.easyerp.BuildConfig.PRODUCTION;

/**
 * Created by Lynx on 1/13/2017.
 */

@EBean(scope = EBean.Scope.Singleton)
public class LoginRepository extends NetworkRepository implements LoginContract.LoginModel, TutorialContract.TutorialModel {

    private LoginService loginService;

    public LoginRepository() {
        loginService = Rest.getInstance().getLoginService();
    }

    @Override
    public Observable<?> login(String login, String pass) {
        return getNetworkObservable(loginService.login(login, pass, PRODUCTION ? null : Constants.DB_TEST_ID, true));
    }

    @Override
    public Observable<?> login(SocialRegisterProfile socialRegisterProfile) {
        if (!PRODUCTION)
            socialRegisterProfile.dbId = Constants.DB_TEST_ID;
        return getNetworkObservable(loginService.loginSocial(socialRegisterProfile));
    }

    @Override
    public Observable<?> signUp(String fName, String lName, String email, String password) {
        return getNetworkObservable(
                loginService.signUp(
                        email, email,
                        fName,
                        lName,
                        password,
                        PRODUCTION ? null : Constants.DB_TEST_ID)
        );
    }

    @Override
    public Observable<?> update(String userId, String fName, String lName, String phone, String compName, String compSite) {
        return getNetworkObservable(
                loginService.update(userId, new UpdateUser(fName, lName, phone, compName, compSite))
        );
    }

    @Override
    public Observable<?> forgotPassword(String login) {
        return getNetworkObservable(loginService.forgotPassword(login, PRODUCTION ? null : Constants.DB_TEST_ID));
    }
}
