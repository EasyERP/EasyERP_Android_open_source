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

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.base.BasePresenter;
import com.thinkmobiles.easyerp.presentation.base.BaseView;
import com.thinkmobiles.easyerp.presentation.managers.GoogleAnalyticHelper;
import com.thinkmobiles.easyerp.presentation.managers.ValidationManager;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.res.DimensionRes;
import org.androidannotations.annotations.res.StringRes;

/**
 * @author Michael Soyma (Created on 2/21/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
@EFragment
public class ForgotPasswordDialogFragment extends DialogFragment implements BaseView {

    private IForgotPasswordCallback forgotPasswordCallback;

    private TextInputLayout tilDbId_DFP, tilUserNameOrEmail_DFP;
    private View ivDatabaseID_DFP, ivUserOrEmail_DFP;
    private EditText etDbId_DFP, etUserNameOrEmail_DFP;

    @FragmentArg
    protected String databaseID;
    @FragmentArg
    protected String username;

    @StringRes(R.string.err_db_id_required)
    protected String errEmptyDbID;
    @StringRes(R.string.err_login_or_email_required)
    protected String errEmptyUsername;
    @DimensionRes(R.dimen.default_padding_middle)
    protected float fixMarginForIcons;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.forgotPasswordCallback = (IForgotPasswordCallback) activity;
        }
        catch (final ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement IForgotPasswordCallback");
        }
    }

    private void initUI(final View rootView) {
        tilDbId_DFP = (TextInputLayout) rootView.findViewById(R.id.tilDbId_DFP);
        tilUserNameOrEmail_DFP = (TextInputLayout) rootView.findViewById(R.id.tilUserNameOrEmail_DFP);

        etDbId_DFP = (EditText) rootView.findViewById(R.id.etDbId_DFP);
        etUserNameOrEmail_DFP = (EditText) rootView.findViewById(R.id.etUserNameOrEmail_DFP);

        ivDatabaseID_DFP = rootView.findViewById(R.id.ivDatabaseID_DFP);
        ivUserOrEmail_DFP = rootView.findViewById(R.id.ivUserOrEmail_DFP);

        etDbId_DFP.setText(databaseID);
        etUserNameOrEmail_DFP.setText(username);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        GoogleAnalyticHelper.trackScreenView(this, getResources().getConfiguration());
        View parent = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_forgot_password, null);
        initUI(parent);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.DefaultTheme_NoTitleDialogWithAnimation)
                .setView(parent)
                .setPositiveButton(R.string.dialog_btn_send, null)
                .setNegativeButton(R.string.dialog_btn_cancel, null)
                .setCancelable(true);

        final AlertDialog dialog = builder.create();
        dialog.setOnShowListener(dialogInterface -> dialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(view -> send()));

        return dialog;
    }

    private void send() {
        GoogleAnalyticHelper.trackClick(this, GoogleAnalyticHelper.EventType.CLICK_BUTTON, "Send");
        final String dbID = etDbId_DFP.getText().toString();
        final String userNameOrEmail = etUserNameOrEmail_DFP.getText().toString();
        if (!showError(ValidationManager.isDbIDValid(dbID), tilDbId_DFP, ivDatabaseID_DFP, errEmptyDbID)) {
            if (!showError(ValidationManager.isLoginValid(userNameOrEmail), tilUserNameOrEmail_DFP, ivUserOrEmail_DFP, errEmptyUsername)) {
                dismiss();
                if (forgotPasswordCallback != null)
                    forgotPasswordCallback.forgotPassword(dbID, userNameOrEmail);
            }
        }
    }

    private boolean showError(final Constants.ErrorCodes errorCode, final TextInputLayout textInputLayout, final View imageInfoView, final String emptyField) {
        final LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) imageInfoView.getLayoutParams();
        boolean hasError;
        switch (errorCode) {
            case FIELD_EMPTY:
                textInputLayout.setError(emptyField);
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
        return "Forgot password screen";
    }

    public interface IForgotPasswordCallback {
        void forgotPassword(final String dbId, final String usernameOrEmail);
    }
}
