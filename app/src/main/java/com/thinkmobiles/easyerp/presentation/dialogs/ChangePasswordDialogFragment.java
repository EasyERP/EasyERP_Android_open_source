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
import android.widget.LinearLayout;
import android.widget.Toast;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.base.BasePresenter;
import com.thinkmobiles.easyerp.presentation.base.BaseView;
import com.thinkmobiles.easyerp.presentation.managers.GoogleAnalyticHelper;
import com.thinkmobiles.easyerp.presentation.managers.ValidationManager;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.res.DimensionRes;
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
    private View ivOldPassword_DCP, ivNewPassword_DCP, ivConfirmPassword_DCP;
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
    @DimensionRes(R.dimen.default_padding_middle)
    protected float fixMarginForIcons;

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

        ivOldPassword_DCP = rootView.findViewById(R.id.ivOldPassword_DCP);
        ivNewPassword_DCP = rootView.findViewById(R.id.ivNewPassword_DCP);
        ivConfirmPassword_DCP = rootView.findViewById(R.id.ivConfirmPassword_DCP);

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
        if (!showError(ValidationManager.isPasswordValid(oldPassword), tilOldPassword_DCP, ivOldPassword_DCP, errPasswordOldRequired)) {
            if (!showError(ValidationManager.isPasswordValid(newPassword), tilNewPassword_DCP, ivNewPassword_DCP, errPasswordNewRequired)) {
                if (!showError(ValidationManager.isPasswordValid(confirmPassword), tilConfirmPassword_DCP, ivConfirmPassword_DCP, errPasswordConfirmRequired)) {
                    if (newPassword.equals(confirmPassword)) {
                        dismiss();
                        if (changePasswordCallback != null)
                            changePasswordCallback.changePassword(oldPassword, newPassword);
                    } else Toast.makeText(getActivity(), errPasswordConfirmEqual, Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private boolean showError(final Constants.ErrorCodes errorCode, final TextInputLayout textInputLayout, final View imageInfoView, final String emptyMsg) {
        final LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) imageInfoView.getLayoutParams();
        boolean hasError;
        switch (errorCode) {
            case INVALID_CHARS:
                textInputLayout.setError(errPasswordWrongSymbols);
                textInputLayout.setErrorEnabled(true);
                params.setMargins(params.getMarginStart(), params.topMargin, params.getMarginEnd(), (int) fixMarginForIcons);
                hasError = true;
                break;
            case SHORTNESS:
                textInputLayout.setError(errPasswordShort);
                textInputLayout.setErrorEnabled(true);
                params.setMargins(params.getMarginStart(), params.topMargin, params.getMarginEnd(), (int) fixMarginForIcons);
                hasError = true;
                break;
            case FIELD_EMPTY:
                textInputLayout.setError(emptyMsg);
                textInputLayout.setErrorEnabled(true);
                params.setMargins(params.getMarginStart(), params.topMargin, params.getMarginEnd(), (int) fixMarginForIcons);
                hasError = true;
                break;
            case OK:
                textInputLayout.setError(null);
                textInputLayout.setErrorEnabled(false);
                params.setMargins(params.getMarginStart(), params.topMargin, params.getMarginEnd(), 0);
                hasError = false;
                break;
            default:
                params.setMargins(params.getMarginStart(), params.topMargin, params.getMarginEnd(), 0);
                hasError = false;
                break;
        }
        imageInfoView.setLayoutParams(params);
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
