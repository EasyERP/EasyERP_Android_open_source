package com.thinkmobiles.easyerp.presentation.screens.login;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;
import com.thinkmobiles.easyerp.BuildConfig;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.data.model.user.UserInfo;
import com.thinkmobiles.easyerp.domain.LoginRepository;
import com.thinkmobiles.easyerp.domain.UserRepository;
import com.thinkmobiles.easyerp.presentation.managers.CookieManager;
import com.thinkmobiles.easyerp.presentation.screens.home.HomeActivity_;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringRes;

import java.util.concurrent.TimeUnit;

@EActivity(R.layout.activity_login)
public class LoginActivity extends AppCompatActivity implements LoginContract.LoginView {

    private LoginContract.LoginPresenter presenter;

    private boolean isCookieExpired = true;
    private boolean isAnimationFinished = false;

    private AnimatorSet animatorSet1, animatorSet2;
    private UserInfo userInfo;

    @ViewById
    protected RelativeLayout rvContainer_AL;
    @ViewById
    protected ImageView ivAppIcon_AL;
    @ViewById
    protected LinearLayout llInput_AL;

    @ViewById
    protected TextInputLayout tilLogin_AL;
    @ViewById
    protected TextInputLayout tilPassword_AL;
    @ViewById
    protected TextInputLayout tilDbId_AL;
    @ViewById
    protected EditText etLogin_AL;
    @ViewById
    protected EditText etPassword_AL;
    @ViewById
    protected EditText etDbId_AL;
    @ViewById
    protected Button btnLogin_AL;
    @ViewById
    protected Button btnDemoMode_AL;

    @StringRes(R.string.err_login_required)
    protected String errEmptyLogin;
    @StringRes(R.string.err_password_required)
    protected String errEmptyPassword;
    @StringRes(R.string.err_db_id_required)
    protected String errEmptyDbID;

    @Bean
    protected LoginRepository loginRepository;
    @Bean
    protected UserRepository userRepository;
    @Bean
    protected CookieManager cookieManager;

    @AfterInject
    @Override
    public void initPresenter() {
        new LoginPresenter(this, loginRepository, userRepository, cookieManager);

        isCookieExpired = cookieManager.isCookieExpired();
        if(isCookieExpired) presenter.clearCookies();
    }

    @AfterViews
    protected void initUI() {
        if(cookieManager.isCookieExists()) presenter.getCurrentUser();

        ivAppIcon_AL.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                ivAppIcon_AL.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                runSplashAnimation();
            }
        });

        RxView.clicks(btnLogin_AL)
                .throttleFirst(Constants.DELAY_CLICK, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> presenter.login());
        RxView.clicks(btnDemoMode_AL)
                .throttleFirst(Constants.DELAY_CLICK, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> presenter.launchDemoMode());
    }

    @Override
    public void displayError(String error) {
        if (llInput_AL.getVisibility() != View.VISIBLE)
            animatorSet2.start();
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public String getLogin() {
        return etLogin_AL.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return etPassword_AL.getText().toString().trim();
    }

    @Override
    public String getDbID() {
        return etDbId_AL.getText().toString().trim();
    }

    @Override
    public void displayLoginError(Constants.ErrorCodes code) {
        switch (code) {
            case FIELD_EMPTY:
                tilLogin_AL.setError(errEmptyLogin);
                tilLogin_AL.setErrorEnabled(true);
                break;
            case OK:
                tilLogin_AL.setError(null);
                tilLogin_AL.setErrorEnabled(false);
                break;
        }
    }

    @Override
    public void displayPasswordError(Constants.ErrorCodes code) {
        switch (code) {
            case FIELD_EMPTY:
                tilPassword_AL.setError(errEmptyPassword);
                tilPassword_AL.setErrorEnabled(true);
                break;
            case OK:
                tilPassword_AL.setError(null);
                tilPassword_AL.setErrorEnabled(false);
                break;
        }
    }

    @Override
    public void displayDbIdError(Constants.ErrorCodes code) {
        switch (code) {
            case FIELD_EMPTY:
                tilDbId_AL.setError(errEmptyDbID);
                tilDbId_AL.setErrorEnabled(true);
                break;
            case OK:
                tilDbId_AL.setError(null);
                tilDbId_AL.setErrorEnabled(false);
                break;
        }
    }

    @Override
    public void startHomeScreen(UserInfo userInfo) {
        this.userInfo = userInfo;
        if(isAnimationFinished) {
            HomeActivity_.intent(this)
                    .userInfo(userInfo)
                    .flags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK)
                    .start();
        }
    }

    @Override
    public void setPresenter(LoginContract.LoginPresenter presenter) {
        this.presenter = presenter;
    }

    private void runSplashAnimation() {
        ObjectAnimator iconFade = ObjectAnimator.ofFloat(ivAppIcon_AL, View.ALPHA, 0.4f, 1f);
        ObjectAnimator iconScaleX = ObjectAnimator.ofFloat(ivAppIcon_AL, View.SCALE_X, 0.5f, 1f);
        ObjectAnimator iconScaleY = ObjectAnimator.ofFloat(ivAppIcon_AL, View.SCALE_Y, 0.5f, 1f);
        iconFade.setDuration(1500);
        iconScaleX.setDuration(1500);
        iconScaleY.setDuration(1500);

        ObjectAnimator iconTranslateY = ObjectAnimator.ofFloat(ivAppIcon_AL, View.Y, getResources().getDisplayMetrics().heightPixels / 13);
        ObjectAnimator containerFade = ObjectAnimator.ofFloat(llInput_AL, View.ALPHA, 0f, 1f);
        iconTranslateY.setDuration(1000);
        containerFade.setDuration(500);

        iconScaleX.setInterpolator(new OvershootInterpolator());
        iconScaleY.setInterpolator(new OvershootInterpolator());
        iconFade.setInterpolator(new LinearInterpolator());
        iconTranslateY.setInterpolator(new DecelerateInterpolator());
        containerFade.setInterpolator(new AccelerateInterpolator());

        containerFade.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                llInput_AL.setVisibility(View.VISIBLE);
            }
        });

        animatorSet1 = new AnimatorSet();
        animatorSet1.playTogether(iconFade, iconScaleX, iconScaleY);
        animatorSet2 = new AnimatorSet();
        animatorSet2.playSequentially(iconTranslateY, containerFade);

        animatorSet1.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                isAnimationFinished = true;
                if(userInfo != null)
                    startHomeScreen(userInfo);
                else if(!cookieManager.isCookieExists())
                    animatorSet2.start();
            }
        });

        animatorSet1.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (animatorSet1 != null) animatorSet1.cancel();
        if (animatorSet2 != null) animatorSet2.cancel();
    }
}
