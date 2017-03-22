package com.thinkmobiles.easyerp.presentation.screens.hr.applications.details;

import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentFragment;
import com.thinkmobiles.easyerp.presentation.base.rules.content.ContentPresenter;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Lynx on 3/22/2017.
 */

@EFragment
public class ApplicationDetailsFragment extends ContentFragment {

    private ApplicationDetailsContract.ApplicationDetailsPresenter presenter;

    @FragmentArg
    protected String applicationID;

    //region View inject
    @ViewById
    protected NestedScrollView nsvContent_FAD;
    @ViewById
    protected ImageView ivApplicationAvatar_FAD;
    @ViewById
    protected TextView tvApplicationName_FAD;
    @ViewById
    protected TextView tvHeaderDepartment_FAD;
    @ViewById
    protected TextView tvHeaderJobPosition_FAD;
    @ViewById
    protected ImageView ivFacebook_FAD;
    @ViewById
    protected ImageView ivLinkedIn_FAD;
    @ViewById
    protected ImageView ivSkype_FAD;
    @ViewById
    protected ImageView ivEmail_FAD;
    @ViewById
    protected EditText etFirstName_FAD;
    @ViewById
    protected EditText etLastName_FAD;
    @ViewById
    protected EditText etExpectedSalary_FAD;
    @ViewById
    protected EditText etProposedSalary_FAD;
    @ViewById
    protected EditText etDob_FAD;
    @ViewById
    protected EditText etPersonalMobile_FAD;
    @ViewById
    protected EditText etPersonalEmail_FAD;
    @ViewById
    protected EditText etSkype_FAD;
    @ViewById
    protected EditText etLinkedIn_FAD;
    @ViewById
    protected EditText etFacebook_FAD;
    @ViewById
    protected EditText etWorkEmail_FAD;
    @ViewById
    protected EditText etWorkPhone_FAD;
    @ViewById
    protected EditText etSource_FAD;
    @ViewById
    protected EditText etNextActionDay_FAD;
    @ViewById
    protected EditText etJobPosition_FAD;
    @ViewById
    protected EditText etDepartment_FAD;
    @ViewById
    protected EditText etManager_FAD;
    @ViewById
    protected EditText etJobType_FAD;
    @ViewById
    protected EditText etWeeklySchedule_FAD;
    @ViewById
    protected EditText etContract_FAD;
    @ViewById
    protected EditText etScheduledPay_FAD;
    @ViewById
    protected EditText etStage_FAD;

    @ViewById
    protected EditText etGender_FAD;
    @ViewById
    protected EditText etEmploymentType_FAD;
    @ViewById
    protected EditText etMaritalStatus_FAD;
    @ViewById
    protected EditText etNationality_FAD;
    @ViewById
    protected EditText eIdentificationNumber_FAD;
    @ViewById
    protected EditText etPassportNumber_FAD;
    @ViewById
    protected EditText etBankAccountNumber_FAD;
    @ViewById
    protected EditText etOrderID_FAD;

    @ViewById
    protected EditText etStreet_FPD;
    @ViewById
    protected EditText etCity_FPD;
    @ViewById
    protected EditText etState_FPD;
    @ViewById
    protected EditText etZipcode_FPD;
    @ViewById
    protected EditText etCountry_FPD;
    @ViewById
    protected FrameLayout btnHistory;
    @ViewById
    protected ImageView ivIconArrow;

    @ViewById
    protected RecyclerView rvAttachments_FAD;
    @ViewById
    protected RecyclerView rvHistory;

    @ViewById
    protected LinearLayout llContainerPersonalInformation_FAD;
    @ViewById
    protected LinearLayout llAddressContainer_FAD;

    @ViewById
    protected TextView tvEmptyPersonalInformation_FAD;
    @ViewById
    protected TextView tvEmptyAddress_FAD;
    @ViewById
    protected TextView tvEmptyAttachments_FAD;

    //endregion

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_application_details;
    }

    @Override
    protected ContentPresenter getPresenter() {
        return presenter;
    }
}
