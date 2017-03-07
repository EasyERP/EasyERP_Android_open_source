package com.thinkmobiles.easyerp.presentation.screens.login;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.data.model.user.UserInfo;
import com.thinkmobiles.easyerp.domain.LoginRepository;
import com.thinkmobiles.easyerp.domain.UserRepository;
import com.thinkmobiles.easyerp.presentation.dialogs.ForgotPasswordDialogFragment;
import com.thinkmobiles.easyerp.presentation.dialogs.ForgotPasswordDialogFragment_;
import com.thinkmobiles.easyerp.presentation.managers.CookieManager;
import com.thinkmobiles.easyerp.presentation.managers.GoogleAnalyticHelper;
import com.thinkmobiles.easyerp.presentation.screens.home.HomeActivity_;
import com.thinkmobiles.easyerp.presentation.screens.web.WebActivity_;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.DimensionRes;
import org.androidannotations.annotations.res.StringRes;

import java.util.concurrent.TimeUnit;

@EActivity(R.layout.activity_login)
public class LoginActivity extends AppCompatActivity implements LoginContract.LoginView, ForgotPasswordDialogFragment.IForgotPasswordCallback {

    private LoginContract.LoginPresenter presenter;

    private boolean isCookieExpired = true;
    private boolean isAnimationFinished = false;

    private AnimatorSet animatorSet1, animatorSet2;
    private UserInfo userInfo;

    @ViewById
    protected View vAppIcon_AL;
    @ViewById
    protected LinearLayout llInput_VIFL;

    @ViewById
    protected TextInputLayout tilLogin_VIFL;
    @ViewById
    protected TextInputLayout tilPassword_VIFL;
    @ViewById
    protected TextInputLayout tilDbId_VIFL;
    @ViewById
    protected EditText etLogin_VIFL;
    @ViewById
    protected View ivLogin_VIFL;
    @ViewById
    protected EditText etPassword_VIFL;
    @ViewById
    protected View ivPassword_VIFL;
    @ViewById
    protected EditText etDbId_VIFL;
    @ViewById
    protected View ivDbId_VIFL;
    @ViewById
    protected Button btnLogin_VIFL;
    @ViewById
    protected Button btnDemoMode_VIFL;
    @ViewById
    protected TextView tvForgotPassword_VIFL;
    @ViewById
    protected TextView tvTermsAndCondition_VIFL;

    @StringRes(R.string.err_login_required)
    protected String errEmptyLogin;
    @StringRes(R.string.err_password_required)
    protected String errEmptyPassword;
    @StringRes(R.string.err_password_wrong_symbols)
    protected String errPasswordWrongSymbols;
    @StringRes(R.string.err_password_short)
    protected String errPasswordShort;
    @StringRes(R.string.err_db_id_required)
    protected String errEmptyDbID;
    @StringRes(R.string.terms_and_conditions_privacy_policy)
    protected String termsAndConditionsString;

    @DimensionRes(R.dimen.default_padding_middle)
    protected float fixMarginForIcons;

    @Bean
    protected LoginRepository loginRepository;
    @Bean
    protected UserRepository userRepository;
    @Bean
    protected CookieManager cookieManager;

    private ProgressDialog progressDialog;

    @AfterInject
    @Override
    public void initPresenter() {
        new LoginPresenter(this, loginRepository, userRepository, cookieManager);

        isCookieExpired = cookieManager.isCookieExpired();
        if(isCookieExpired) presenter.clearCookies();
    }

    @AfterViews
    protected void initUI() {
        GoogleAnalyticHelper.trackScreenView(this, getResources().getConfiguration());
        vAppIcon_AL.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                vAppIcon_AL.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                runSplashAnimation();
                if(cookieManager.isCookieExists())
                    presenter.getCurrentUser();
            }
        });

        RxView.clicks(btnLogin_VIFL)
                .throttleFirst(Constants.DELAY_CLICK, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> {
                    GoogleAnalyticHelper.trackClick(this, GoogleAnalyticHelper.EventType.CLICK_BUTTON, "Login");
                    presenter.login();
                });
        RxView.clicks(btnDemoMode_VIFL)
                .throttleFirst(Constants.DELAY_CLICK, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> {
                    GoogleAnalyticHelper.trackClick(this, GoogleAnalyticHelper.EventType.CLICK_BUTTON, "Demo");
                    presenter.launchDemoMode();
                });
        RxView.clicks(tvForgotPassword_VIFL)
                .throttleFirst(Constants.DELAY_CLICK, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> {
                        GoogleAnalyticHelper.trackClick(this, GoogleAnalyticHelper.EventType.CLICK_BUTTON, "Forgot password");
                        ForgotPasswordDialogFragment_.builder()
                            .databaseID(etDbId_VIFL.getText().toString())
                            .username(etLogin_VIFL.getText().toString())
                            .build().show(getFragmentManager(), null);
                });


        tvTermsAndCondition_VIFL.setText(buildTermsAndConditions());
        tvTermsAndCondition_VIFL.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private Spannable buildTermsAndConditions() {
        final SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(termsAndConditionsString);
        final String termsAndConditions = "Terms & Conditions";
        final String privacyPolicy = "Privacy Policy";
        final int termsAndConditionsPos = termsAndConditionsString.indexOf(termsAndConditions);
        final int privacyPolicyPos = termsAndConditionsString.indexOf(privacyPolicy);

        spannableStringBuilder.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View view) {
                WebActivity_.intent(view.getContext())
                        .title(termsAndConditions)
                        .url(Constants.TERMS_AND_CONDITIONS)
                        .start();
            }
        }, termsAndConditionsPos, termsAndConditionsPos + termsAndConditions.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        spannableStringBuilder.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View view) {
                WebActivity_.intent(view.getContext())
                        .title(privacyPolicy)
                        .url(Constants.PRIVACY_POLICY)
                        .start();
            }
        }, privacyPolicyPos, privacyPolicyPos + privacyPolicy.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        return spannableStringBuilder;
    }

    @Override
    public void showProgress(String msg) {
        progressDialog = new ProgressDialog(this, R.style.DefaultTheme_NoTitleDialogWithAnimation);
        progressDialog.setCancelable(false);
        progressDialog.setMessage(msg);
        progressDialog.show();
    }

    @Override
    public void dismissProgress() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    @Override
    public void showErrorToast(String msg) {
        if (llInput_VIFL.getVisibility() != View.VISIBLE)
            animatorSet2.start();
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showInfoToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public String getLogin() {
        return etLogin_VIFL.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return etPassword_VIFL.getText().toString().trim();
    }

    @Override
    public String getDbID() {
        return etDbId_VIFL.getText().toString().trim();
    }

    @Override
    public void displayLoginError(Constants.ErrorCodes code) {
        final LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) ivLogin_VIFL.getLayoutParams();
        switch (code) {
            case FIELD_EMPTY:
                tilLogin_VIFL.setError(errEmptyLogin);
                tilLogin_VIFL.setErrorEnabled(true);
                params.setMargins(params.getMarginStart(), params.topMargin, params.getMarginEnd(), (int) fixMarginForIcons);
                break;
            case OK:
                tilLogin_VIFL.setError(null);
                tilLogin_VIFL.setErrorEnabled(false);
                params.setMargins(params.getMarginStart(), params.topMargin, params.getMarginEnd(), 0);
                break;
        }
        ivLogin_VIFL.setLayoutParams(params);
    }

    @Override
    public void displayPasswordError(Constants.ErrorCodes code) {
        final LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) ivPassword_VIFL.getLayoutParams();
        switch (code) {
            case FIELD_EMPTY:
                tilPassword_VIFL.setError(errEmptyPassword);
                tilPassword_VIFL.setErrorEnabled(true);
                params.setMargins(params.getMarginStart(), params.topMargin, params.getMarginEnd(), (int) fixMarginForIcons);
                break;
            case INVALID_CHARS:
                tilPassword_VIFL.setError(errPasswordWrongSymbols);
                tilPassword_VIFL.setErrorEnabled(true);
                params.setMargins(params.getMarginStart(), params.topMargin, params.getMarginEnd(), (int) fixMarginForIcons);
                break;
            case SHORTNESS:
                tilPassword_VIFL.setError(errPasswordShort);
                tilPassword_VIFL.setErrorEnabled(true);
                params.setMargins(params.getMarginStart(), params.topMargin, params.getMarginEnd(), (int) fixMarginForIcons);
                break;
            case OK:
                tilPassword_VIFL.setError(null);
                tilPassword_VIFL.setErrorEnabled(false);
                params.setMargins(params.getMarginStart(), params.topMargin, params.getMarginEnd(), 0);
                break;
        }
        ivPassword_VIFL.setLayoutParams(params);
    }

    @Override
    public void displayDbIdError(Constants.ErrorCodes code) {
        final LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) ivDbId_VIFL.getLayoutParams();
        switch (code) {
            case FIELD_EMPTY:
                tilDbId_VIFL.setError(errEmptyDbID);
                tilDbId_VIFL.setErrorEnabled(true);
                params.setMargins(params.getMarginStart(), params.topMargin, params.getMarginEnd(), (int) fixMarginForIcons);
                break;
            case OK:
                tilDbId_VIFL.setError(null);
                tilDbId_VIFL.setErrorEnabled(false);
                params.setMargins(params.getMarginStart(), params.topMargin, params.getMarginEnd(), 0);
                break;
        }
        ivDbId_VIFL.setLayoutParams(params);
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

    @Override
    public String getScreenName() {
        return "Login screen";
    }

    private void runSplashAnimation() {
        ObjectAnimator iconFade = ObjectAnimator.ofFloat(vAppIcon_AL, View.ALPHA, 0.4f, 1f);
        ObjectAnimator iconScaleX = ObjectAnimator.ofFloat(vAppIcon_AL, View.SCALE_X, 0.5f, 1f);
        ObjectAnimator iconScaleY = ObjectAnimator.ofFloat(vAppIcon_AL, View.SCALE_Y, 0.5f, 1f);
        iconFade.setDuration(1200);
        iconScaleX.setDuration(1200);
        iconScaleY.setDuration(1200);

        ObjectAnimator iconTranslate;
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            iconTranslate = ObjectAnimator.ofFloat(vAppIcon_AL, View.X, vAppIcon_AL.getX() - .25f * getResources().getDisplayMetrics().widthPixels);
        } else {
            iconTranslate = ObjectAnimator.ofFloat(vAppIcon_AL, View.Y, vAppIcon_AL.getY() - .25f * getResources().getDisplayMetrics().heightPixels);
        }
        ObjectAnimator containerFade = ObjectAnimator.ofFloat(llInput_VIFL, View.ALPHA, 0f, 1f);
        iconTranslate.setDuration(1000);
        containerFade.setDuration(500);

        iconScaleX.setInterpolator(new OvershootInterpolator());
        iconScaleY.setInterpolator(new OvershootInterpolator());
        iconFade.setInterpolator(new LinearInterpolator());
        iconTranslate.setInterpolator(new DecelerateInterpolator());
        containerFade.setInterpolator(new AccelerateInterpolator());

        containerFade.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                llInput_VIFL.setVisibility(View.VISIBLE);
            }
        });

        animatorSet1 = new AnimatorSet();
        animatorSet1.playTogether(iconFade, iconScaleX, iconScaleY);
        animatorSet2 = new AnimatorSet();
        animatorSet2.play(iconTranslate).before(containerFade);

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
        presenter.unsubscribe();
    }

    @Override
    public void forgotPassword(String dbId, String usernameOrEmail) {
        presenter.forgotPassword(usernameOrEmail, dbId);
    }
}
