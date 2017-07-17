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
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.jakewharton.rxbinding.view.RxView;
import com.linkedin.platform.LISessionManager;
import com.linkedin.platform.listeners.AuthListener;
import com.linkedin.platform.utils.Scope;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.data.model.social.SocialType;
import com.thinkmobiles.easyerp.data.model.user.UserInfo;
import com.thinkmobiles.easyerp.domain.auth.LoginRepository;
import com.thinkmobiles.easyerp.domain.auth.SocialRepository;
import com.thinkmobiles.easyerp.domain.auth.UserRepository;
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
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringRes;

import java.util.List;
import java.util.concurrent.TimeUnit;

@EActivity(R.layout.activity_login)
public class LoginActivity extends AppCompatActivity implements LoginContract.LoginView, ForgotPasswordDialogFragment.IForgotPasswordCallback {

    private LoginContract.LoginPresenter presenter;

    private GoogleApiClient googleApiClient;

    private boolean isCookieExpired = true;
    private boolean isAnimationFinished = false;
    private boolean isSecondAnimationFinished = false;
    private boolean isProfileComplete = true;

    private AnimatorSet animatorSet1, animatorSet2;
    private UserInfo userInfo;

    @Extra
    protected boolean loginActivated = true;

    @ViewById
    protected View llAppLogo_AL;
    @ViewById
    protected View flInput_VIFL;
    @ViewById
    protected View flInput_VIFSD;

    @ViewById
    protected TextView tvTabLogIn_VIFL, tvTabSignUp_VIFL;
    @ViewById
    protected View llContainerLogIn_VIFL, llContainerSignUp_VIFL;
    @ViewById
    protected FrameLayout flContainerChangedContent_VIFL;

    @ViewById
    protected TextInputLayout tilLogin_VIFL, tilPassword_VIFL;
    @ViewById
    protected TextInputLayout tilFirstName_VIFL, tilLastName_VIFL, tilEmail_VIFL, tilPasswordSignUp_VIFL;
    @ViewById
    protected EditText etLogin_VIFL, etPassword_VIFL;
    @ViewById
    protected EditText etFirstName_VIFL, etLastName_VIFL, etEmail_VIFL, etPasswordSignUp_VIFL;
    @ViewById
    protected EditText etFirstName_VIFSD, etLastName_VIFSD, etPhone_VIFSD, etCompanyName_VIFSD, etWeb_VIFSD;
    @ViewById
    protected Button btnLogin_VIFL, btnSignUp_VIFL, btnContinue_VIFSD;
    @ViewById
    protected TextView tvOrViaSocial_VIFL, tvForgotPassword_VIFL, tvTermsAndCondition_VIFL;
    @ViewById
    protected View tvBtnFacebook_VIFL, tvBtnLinkedIn_VIFL, tvBtnGooglePlus_VIFL;

    @StringRes(R.string.err_login_required)
    protected String errEmptyLogin;
    @StringRes(R.string.err_password_required)
    protected String errEmptyPassword;
    @StringRes(R.string.err_f_name_required)
    protected String errEmptyFirstName;
    @StringRes(R.string.err_l_name_required)
    protected String errEmptyLastName;
    @StringRes(R.string.err_email_required)
    protected String errEmptyEmail;
    @StringRes(R.string.err_password_wrong_symbols)
    protected String errInvalidChars;
    @StringRes(R.string.err_password_weak)
    protected String errWeakPassword;
    @StringRes(R.string.err_email_wrong)
    protected String errInvalidEmail;
    @StringRes(R.string.err_password_short)
    protected String errPasswordShort;
    @StringRes(R.string.terms_and_conditions_privacy_policy)
    protected String termsAndConditionsString;

    @Bean
    protected LoginRepository loginRepository;
    @Bean
    protected UserRepository userRepository;
    @Bean
    protected SocialRepository socialRepository;
    @Bean
    protected CookieManager cookieManager;

    private ProgressDialog progressDialog;

    @AfterInject
    @Override
    public void initPresenter() {
        new LoginPresenter(this, loginRepository, userRepository, socialRepository, cookieManager);

        isCookieExpired = cookieManager.isCookieExpired();
        if (isCookieExpired) presenter.clearCookies();
    }

    @AfterViews
    protected void initUI() {
        GoogleAnalyticHelper.trackScreenView(this, getResources().getConfiguration());
        llAppLogo_AL.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                llAppLogo_AL.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                runSplashAnimation();
                if(cookieManager.isCookieExists())
                    presenter.getCurrentUser();
            }
        });

        RxView.clicks(btnLogin_VIFL)
                .throttleFirst(Constants.DELAY_CLICK, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> {
                    GoogleAnalyticHelper.trackClick(this, GoogleAnalyticHelper.EventType.CLICK_BUTTON, "Log In");
                    presenter.login();
                });
        RxView.clicks(btnSignUp_VIFL)
                .throttleFirst(Constants.DELAY_CLICK, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> {
                    GoogleAnalyticHelper.trackClick(this, GoogleAnalyticHelper.EventType.CLICK_BUTTON, "Sign Up");
                    presenter.signUp();
                });
        RxView.clicks(btnContinue_VIFSD)
                .throttleFirst(Constants.DELAY_CLICK, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> {
                    GoogleAnalyticHelper.trackClick(this, GoogleAnalyticHelper.EventType.CLICK_BUTTON, "Continue");
                    presenter.updateUserDetails();
                });
        RxView.clicks(tvBtnFacebook_VIFL)
                .throttleFirst(Constants.DELAY_CLICK, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> {
                    GoogleAnalyticHelper.trackClick(this, GoogleAnalyticHelper.EventType.CLICK_BUTTON, "Auth via Facebook");
                    presenter.loginSocial(SocialType.FACEBOOK);
                });
        RxView.clicks(tvBtnLinkedIn_VIFL)
                .throttleFirst(Constants.DELAY_CLICK, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> {
                    GoogleAnalyticHelper.trackClick(this, GoogleAnalyticHelper.EventType.CLICK_BUTTON, "Auth via LinkedIn");
                    presenter.loginSocial(SocialType.LIKENDIN);
                });
        RxView.clicks(tvBtnGooglePlus_VIFL)
                .throttleFirst(Constants.DELAY_CLICK, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> {
                    GoogleAnalyticHelper.trackClick(this, GoogleAnalyticHelper.EventType.CLICK_BUTTON, "Auth via Google+");
                    presenter.loginSocial(SocialType.GPLUS);
                });
        RxView.clicks(tvForgotPassword_VIFL)
                .throttleFirst(Constants.DELAY_CLICK, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> {
                        GoogleAnalyticHelper.trackClick(this, GoogleAnalyticHelper.EventType.CLICK_BUTTON, "Forgot password");
                        ForgotPasswordDialogFragment_.builder()
                            .username(etLogin_VIFL.getText().toString())
                            .build().show(getFragmentManager(), null);
                });

        flContainerChangedContent_VIFL.getLayoutTransition().setDuration(200);
        initSelectedStatesInTabs();
        tvTermsAndCondition_VIFL.setText(buildTermsAndConditions());
        tvTermsAndCondition_VIFL.setMovementMethod(LinkMovementMethod.getInstance());

        flInput_VIFSD.setVisibility(View.GONE);
    }

    @Click({R.id.tvTabLogIn_VIFL, R.id.tvTabSignUp_VIFL})
    protected void onTabClick(final View tabView) {
        switch (tabView.getId()) {
            case R.id.tvTabLogIn_VIFL:
                loginActivated = true;
                initSelectedStatesInTabs();
                break;
            case R.id.tvTabSignUp_VIFL:
                loginActivated = false;
                initSelectedStatesInTabs();
                break;
        }
    }

    private void initSelectedStatesInTabs() {
        tvTabLogIn_VIFL.setSelected(loginActivated);
        tvTabSignUp_VIFL.setSelected(!loginActivated);
        if (loginActivated) {
            llContainerLogIn_VIFL.setVisibility(View.VISIBLE);
            llContainerSignUp_VIFL.setVisibility(View.GONE);
        } else {
            llContainerSignUp_VIFL.setVisibility(View.VISIBLE);
            llContainerLogIn_VIFL.setVisibility(View.GONE);
        }
        tvOrViaSocial_VIFL.setText(loginActivated ? R.string.or_log_in_with : R.string.or_sign_up_with);
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
    public void loginWithFacebook(List<String> readPermissions) {
        LoginManager.getInstance().logInWithReadPermissions(this, readPermissions);
    }

    @Override
    public void loginWithLinkedIn(Scope scope, AuthListener authListener) {
        LISessionManager sessionManager = LISessionManager.getInstance(getApplicationContext());
        if (sessionManager.getSession() != null && sessionManager.getSession().isValid()) {
            sessionManager.init(sessionManager.getSession().getAccessToken());
            authListener.onAuthSuccess();
        }
        else sessionManager.init(this, scope, authListener, true, R.style.DefaultTheme_NoTitleDialogWithAnimation);
    }

    @Override
    public void loginWithGoogle(GoogleSignInOptions gso, GoogleApiClient.OnConnectionFailedListener connectionFailedListener) {
        if (googleApiClient == null || !googleApiClient.isConnected()) {
            googleApiClient = new GoogleApiClient.Builder(this)
                    .enableAutoManage(this, connectionFailedListener)
                    .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                    .build();
        }
        startActivityForResult(Auth.GoogleSignInApi.getSignInIntent(googleApiClient), Constants.RequestCodes.RC_GOOGLE_SIGN_IN);
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
        if (flInput_VIFL.getVisibility() != View.VISIBLE)
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
    public String getFirstName() {
        return etFirstName_VIFSD.getText().toString().trim();
    }

    @Override
    public String getLastName() {
        return etLastName_VIFSD.getText().toString().trim();
    }

    @Override
    public String getSignUpEmail() {
        return etEmail_VIFL.getText().toString().trim();
    }

    @Override
    public String getSignUpPassword() {
        return etPasswordSignUp_VIFL.getText().toString().trim();
    }

    @Override
    public String getPhone() {
        return etPhone_VIFSD.getText().toString().trim();
    }

    @Override
    public String getCompanyName() {
        return etCompanyName_VIFSD.getText().toString().trim();
    }

    @Override
    public String getCompanySite() {
        return etWeb_VIFSD.getText().toString().trim();
    }

    @Override
    public void displayLoginError(Constants.ErrorCode code) {
        displayError(code, tilLogin_VIFL, errEmptyLogin);
    }

    @Override
    public void displayPasswordError(Constants.ErrorCode code) {
        displayError(code, tilPassword_VIFL, errEmptyPassword);
    }

    @Override
    public void displayFirstNameError(Constants.ErrorCode code) {
        displayError(code, tilFirstName_VIFL, errEmptyFirstName);
    }

    @Override
    public void displayLastNameError(Constants.ErrorCode code) {
        displayError(code, tilLastName_VIFL, errEmptyLastName);
    }

    @Override
    public void displaySignUpEmailError(Constants.ErrorCode code) {
        displayError(code, tilEmail_VIFL, errEmptyEmail);
    }

    @Override
    public void displaySignUpPasswordError(Constants.ErrorCode code) {
        displayError(code, tilPasswordSignUp_VIFL, errEmptyPassword);
    }

    @Override
    public void signUpDoSuccess() {
        cookieManager.clearCookie();
        Toast.makeText(this, R.string.sign_up_success, Toast.LENGTH_LONG).show();
        loginActivated = true;
        initSelectedStatesInTabs();
    }

    private void displayError(final Constants.ErrorCode errorCode, TextInputLayout til, final String emptyMsg) {
        switch (errorCode) {
            case FIELD_EMPTY:
                til.setError(emptyMsg);
                til.setErrorEnabled(true);
                break;
            case INVALID_CHARS:
                til.setError(errInvalidChars);
                til.setErrorEnabled(true);
                break;
            case INVALID_EMAIL:
                til.setError(errInvalidEmail);
                til.setErrorEnabled(true);
                break;
            case SHORTNESS:
                til.setError(errPasswordShort);
                til.setErrorEnabled(true);
                break;
            case WEAK_PASSWORD:
                til.setError(errWeakPassword);
                til.setErrorEnabled(true);
                break;
            case OK:
                til.setError(null);
                til.setErrorEnabled(false);
                break;
        }
    }

    @Override
    public void startHomeScreen(UserInfo userInfo) {
        this.userInfo = userInfo;
        if (isAnimationFinished) {
            HomeActivity_.intent(this)
                    .userInfo(userInfo)
                    .flags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK)
                    .start();
        }
    }

    @Override
    public void showSignUpDetails() {
        isProfileComplete = false;
        if (!isSecondAnimationFinished)
            animatorSet2.start();
        else
            showDetailsTab();
    }

    private void showDetailsTab() {
        flInput_VIFL.setVisibility(View.GONE);
        flInput_VIFSD.setVisibility(View.VISIBLE);
    }

    @Override
    public void setPresenter(LoginContract.LoginPresenter presenter) {
        this.presenter = presenter;
        this.presenter.subscribe();
    }

    @Override
    public String getScreenName() {
        return "Login screen";
    }

    private void runSplashAnimation() {
        ObjectAnimator iconFade = ObjectAnimator.ofFloat(llAppLogo_AL, View.ALPHA, 0.4f, 1f);
        ObjectAnimator iconScaleX = ObjectAnimator.ofFloat(llAppLogo_AL, View.SCALE_X, 0.5f, 1f);
        ObjectAnimator iconScaleY = ObjectAnimator.ofFloat(llAppLogo_AL, View.SCALE_Y, 0.5f, 1f);
        iconFade.setDuration(800);
        iconScaleX.setDuration(800);
        iconScaleY.setDuration(800);

        ObjectAnimator iconTranslate;
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            iconTranslate = ObjectAnimator.ofFloat(llAppLogo_AL, View.X, llAppLogo_AL.getX() - .25f * getResources().getDisplayMetrics().widthPixels);
        } else {
            iconTranslate = ObjectAnimator.ofFloat(llAppLogo_AL, View.Y, llAppLogo_AL.getY() - .25f * getResources().getDisplayMetrics().heightPixels);
        }
        ObjectAnimator containerFade = ObjectAnimator.ofFloat(flInput_VIFL, View.ALPHA, 0f, 1f);
        iconTranslate.setDuration(1000);
        containerFade.setDuration(400);

        iconScaleX.setInterpolator(new OvershootInterpolator());
        iconScaleY.setInterpolator(new OvershootInterpolator());
        iconFade.setInterpolator(new LinearInterpolator());
        iconTranslate.setInterpolator(new DecelerateInterpolator());
        containerFade.setInterpolator(new AccelerateInterpolator());

        containerFade.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                isSecondAnimationFinished = true;
                if (isProfileComplete)
                    flInput_VIFL.setVisibility(View.VISIBLE);
                else
                    flInput_VIFSD.setVisibility(View.VISIBLE);
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
                else if (!cookieManager.isCookieExists())
                    animatorSet2.start();
            }
        });

        animatorSet1.start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (!presenter.onActivityResult(requestCode, resultCode, data))
            LISessionManager.getInstance(getApplicationContext()).onActivityResult(this, requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (animatorSet1 != null) animatorSet1.cancel();
        if (animatorSet2 != null) animatorSet2.cancel();

        presenter.unsubscribe();
        if (googleApiClient != null)
            googleApiClient.disconnect();
    }

    @Override
    public void forgotPassword(String usernameOrEmail) {
        presenter.forgotPassword(usernameOrEmail);
    }
}
