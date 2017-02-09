package com.thinkmobiles.easyerp.presentation.dialogs;

import android.app.Dialog;
import android.app.DialogFragment;
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
import com.thinkmobiles.easyerp.presentation.managers.DateManager;
import com.thinkmobiles.easyerp.presentation.managers.ImageHelper;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;

/**
 * @author Michael Soyma (Created on 2/9/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
@EFragment
public class UserProfileDialogFragment extends DialogFragment {

    private ImageView ivAvatar_DMP;
    private EditText etUserName_DMP, etEmail_DMP;
    private TextView tvProfile_DMP, tvLastAccess_DMP;

    @FragmentArg
    protected UserInfo userInfo;

    private void initUI(final View rootView) {
        ivAvatar_DMP = (ImageView) rootView.findViewById(R.id.ivAvatar_DMP);
        etUserName_DMP = (EditText) rootView.findViewById(R.id.etUserName_DMP);
        etEmail_DMP = (EditText) rootView.findViewById(R.id.etEmail_DMP);
        tvProfile_DMP = (TextView) rootView.findViewById(R.id.tvProfile_DMP);
        tvLastAccess_DMP = (TextView) rootView.findViewById(R.id.tvLastAccess_DMP);

        ImageHelper.getBitmapFromBase64(userInfo.imageSrc).subscribe(bitmap -> ivAvatar_DMP.setImageBitmap(bitmap));
        etUserName_DMP.setText(userInfo.login);
        etEmail_DMP.setText(userInfo.email);
        tvProfile_DMP.setText(userInfo.profile.profileName);
        tvLastAccess_DMP.setText(new DateManager.DateConverter(userInfo.lastAccess).toString());
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View parent = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_me_profile, null);
        initUI(parent);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.DefaultTheme_NoTitleDialogWithAnimation)
                .setView(parent)
                .setPositiveButton(R.string.dialog_btn_close, (dialogInterface, i) -> dismiss())
                .setCancelable(true);
        return builder.create();
    }
}
