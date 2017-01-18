package com.thinkmobiles.easyerp.presentation.screens.login;

import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.domain.UserRepository;
import com.thinkmobiles.easyerp.presentation.screens.home.HomeActivity_;
import com.thinkmobiles.easyerp.presentation.utils.AppSharedPreferences_;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;

import java.util.concurrent.TimeUnit;

@EActivity(R.layout.activity_login)
public class LoginActivity extends AppCompatActivity implements LoginContract.LoginView {

    private LoginContract.LoginPresenter presenter;

    @ViewById
    protected EditText etLogin_AL;
    @ViewById
    protected EditText etPassword_AL;
    @ViewById
    protected EditText etDbId_AL;
    @ViewById
    protected CheckBox cbRememberMe_AL;
    @ViewById
    protected Button btnLogin_AK;
    @ViewById
    protected Button btnGetCookie_AK;

    @Pref
    protected AppSharedPreferences_ sharedPreferences;

    @Bean
    protected UserRepository userRepository;

    @AfterInject
    protected void initPresenter() {
        new LoginPresenter(this, userRepository);
    }

    @AfterViews
    protected void initUI() {
        RxView.clicks(btnLogin_AK)
                .throttleFirst(Constants.DELAY_CLICK, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> presenter.login());
        RxView.clicks(btnGetCookie_AK)
                .throttleFirst(Constants.DELAY_CLICK, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> {
                    Toast.makeText(this, sharedPreferences.geCoockies().get(), Toast.LENGTH_SHORT).show();
                });
    }

    @Override
    public void displayError(String error) {
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
    public boolean isRememberMe() {
        return cbRememberMe_AL.isChecked();
    }

    @Override
    public void startHomeScreen() {
        HomeActivity_.intent(this)
                .start();
        finish();
    }

    @Override
    public void setPresenter(LoginContract.LoginPresenter presenter) {
        this.presenter = presenter;
    }
}
