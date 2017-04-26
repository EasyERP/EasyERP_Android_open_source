package com.thinkmobiles.easyerp.presentation.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.base.BasePresenter;
import com.thinkmobiles.easyerp.presentation.base.BaseView;
import com.thinkmobiles.easyerp.presentation.managers.GoogleAnalyticHelper;
import com.thinkmobiles.easyerp.presentation.managers.ValidationManager;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.res.StringRes;

/**
 * @author Michael Soyma (Created on 2/21/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
@EFragment
public class ChangePasswordDialogFragment extends DialogFragment implements BaseView {

    private IChangePasswordCallback changePasswordCallback;

    private TextInputLayout tilOldPassword_DCP, tilNewPassword_DCP, tilConfirmPassword_DCP;
    private EditText etOldPassword_DCP, etNewPassword_DCP, etConfirmPassword_DCP;

    @StringRes(R.string.err_password_old_required)
    protected String errPasswordOldRequired;
    @StringRes(R.string.err_password_new_required)
    protected String errPasswordNewRequired;
    @StringRes(R.string.err_password_confirm_required)
    protected String errPasswordConfirmRequired;
    @StringRes(R.string.err_password_confirm_equal)
    protected String errPasswordConfirmEqual;
    @StringRes(R.string.err_password_wrong_symbols)
    protected String errPasswordWrongSymbols;
    @StringRes(R.string.err_password_short)
    protected String errPasswordShort;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.changePasswordCallback = (IChangePasswordCallback) activity;
        }
        catch (final ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement IChangePasswordCallback");
        }
    }

    private void initUI(final View rootView) {
        tilOldPassword_DCP = (TextInputLayout) rootView.findViewById(R.id.tilOldPassword_DCP);
        tilNewPassword_DCP = (TextInputLayout) rootView.findViewById(R.id.tilNewPassword_DCP);
        tilConfirmPassword_DCP = (TextInputLayout) rootView.findViewById(R.id.tilConfirmPassword_DCP);

        etOldPassword_DCP = (EditText) rootView.findViewById(R.id.etOldPassword_DCP);
        etNewPassword_DCP = (EditText) rootView.findViewById(R.id.etNewPassword_DCP);
        etConfirmPassword_DCP = (EditText) rootView.findViewById(R.id.etConfirmPassword_DCP);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        GoogleAnalyticHelper.trackScreenView(this, getResources().getConfiguration());
        View parent = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_change_password, null);
        initUI(parent);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.DefaultTheme_NoTitleDialogWithAnimation)
                .setView(parent)
                .setPositiveButton(R.string.dialog_btn_change, null)
                .setNegativeButton(R.string.dialog_btn_cancel, null)
                .setCancelable(true);

        final AlertDialog dialog = builder.create();
        dialog.setOnShowListener(dialogInterface -> dialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view -> change()));

        return dialog;
    }

    private void change() {
        GoogleAnalyticHelper.trackClick(this, GoogleAnalyticHelper.EventType.CLICK_BUTTON, "Change");
        final String oldPassword = etOldPassword_DCP.getText().toString();
        final String newPassword = etNewPassword_DCP.getText().toString();
        final String confirmPassword = etConfirmPassword_DCP.getText().toString();
        if (!showError(ValidationManager.isPasswordValid(oldPassword), tilOldPassword_DCP, errPasswordOldRequired)) {
            if (!showError(ValidationManager.isPasswordValid(newPassword), tilNewPassword_DCP, errPasswordNewRequired)) {
                if (!showError(ValidationManager.isPasswordValid(confirmPassword), tilConfirmPassword_DCP, errPasswordConfirmRequired)) {
                    if (newPassword.equals(confirmPassword)) {
                        dismiss();
                        if (changePasswordCallback != null)
                            changePasswordCallback.changePassword(oldPassword, newPassword);
                    } else Toast.makeText(getActivity(), errPasswordConfirmEqual, Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private boolean showError(final Constants.ErrorCodes errorCode, final TextInputLayout textInputLayout, final String emptyMsg) {
        boolean hasError;
        switch (errorCode) {
            case INVALID_CHARS:
                textInputLayout.setError(errPasswordWrongSymbols);
                textInputLayout.setErrorEnabled(true);
                hasError = true;
                break;
            case SHORTNESS:
                textInputLayout.setError(errPasswordShort);
                textInputLayout.setErrorEnabled(true);
                hasError = true;
                break;
            case FIELD_EMPTY:
                textInputLayout.setError(emptyMsg);
                textInputLayout.setErrorEnabled(true);
                hasError = true;
                break;
            case OK:
                textInputLayout.setError(null);
                textInputLayout.setErrorEnabled(false);
                hasError = false;
                break;
            default:
                hasError = false;
                break;
        }
        return hasError;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void setPresenter(BasePresenter presenter) {

    }

    @Override
    public String getScreenName() {
        return "Change password screen";
    }

    public interface IChangePasswordCallback {
        void changePassword(final String oldPassword, final String newPassword);
    }
}
