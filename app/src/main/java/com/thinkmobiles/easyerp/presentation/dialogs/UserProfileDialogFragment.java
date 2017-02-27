package com.thinkmobiles.easyerp.presentation.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.data.model.user.UserInfo;
import com.thinkmobiles.easyerp.presentation.base.BasePresenter;
import com.thinkmobiles.easyerp.presentation.base.BaseView;
import com.thinkmobiles.easyerp.presentation.custom.transformations.CropCircleTransformation;
import com.thinkmobiles.easyerp.presentation.managers.DateManager;
import com.thinkmobiles.easyerp.presentation.managers.GoogleAnalyticHelper;
import com.thinkmobiles.easyerp.presentation.managers.ImageHelper;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;

/**
 * @author Michael Soyma (Created on 2/9/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
@EFragment
public class UserProfileDialogFragment extends DialogFragment implements BaseView {

    private IUserProfileCallback userProfileCallback;

    private ImageView ivAvatar_DMP;
    private EditText etUserName_DMP, etEmail_DMP;
    private TextView tvProfile_DMP, tvLastAccess_DMP;

    @FragmentArg
    protected UserInfo userInfo;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.userProfileCallback = (IUserProfileCallback) activity;
        }
        catch (final ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement IUserProfileCallback");
        }
    }

    private void initUI(final View rootView) {
        ivAvatar_DMP = (ImageView) rootView.findViewById(R.id.ivAvatar_DMP);
        etUserName_DMP = (EditText) rootView.findViewById(R.id.etUserName_DMP);
        etEmail_DMP = (EditText) rootView.findViewById(R.id.etEmail_DMP);
        tvProfile_DMP = (TextView) rootView.findViewById(R.id.tvProfile_DMP);
        tvLastAccess_DMP = (TextView) rootView.findViewById(R.id.tvLastAccess_DMP);

        ImageHelper.getBitmapFromBase64(userInfo.imageSrc, new CropCircleTransformation()).subscribe(bitmap -> ivAvatar_DMP.setImageBitmap(bitmap));
        etUserName_DMP.setText(userInfo.login);
        etEmail_DMP.setText(userInfo.email);
        tvProfile_DMP.setText(userInfo.profile.profileName);
        tvLastAccess_DMP.setText(new DateManager.DateConverter(userInfo.lastAccess).toString());
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        GoogleAnalyticHelper.trackScreenView(this, getResources().getConfiguration());
        View parent = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_me_profile, null);
        initUI(parent);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.DefaultTheme_NoTitleDialogWithAnimation)
                .setView(parent)
                .setNeutralButton(R.string.change_password, null)
                .setPositiveButton(R.string.dialog_btn_close, null)
                .setCancelable(true);

        final AlertDialog dialog = builder.create();
        dialog.setOnShowListener(dialogInterface -> dialog.getButton(DialogInterface.BUTTON_NEUTRAL).setOnClickListener(view -> changePassword()));

        return dialog;
    }

    private void changePassword() {
        GoogleAnalyticHelper.trackClick(this, GoogleAnalyticHelper.EventType.CLICK_BUTTON, "Change password");
        dismiss();
        if (userProfileCallback != null)
            userProfileCallback.showChangeProfile();
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void setPresenter(BasePresenter presenter) {

    }

    @Override
    public String getScreenName() {
        return "User profile screen";
    }

    public interface IUserProfileCallback {
        void showChangeProfile();
    }
}
