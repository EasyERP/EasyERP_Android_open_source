package com.thinkmobiles.easyerp.presentation.holders.view.hr;

import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.michenko.simpleadapter.OnCardClickListener;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.data.model.hr.applications.Application;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectableVHHelper;
import com.thinkmobiles.easyerp.presentation.custom.RoundRectDrawable;
import com.thinkmobiles.easyerp.presentation.holders.data.hr.ApplicationDH;
import com.thinkmobiles.easyerp.presentation.managers.ColorHelper;
import com.thinkmobiles.easyerp.presentation.managers.DateManager;

/**
 * @author Michael Soyma (Created on 3/13/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public class ApplicationVH extends SelectableVHHelper<ApplicationDH> {

    private TextView tvApplicationName_VLIA, tvStage_VLIA, tvJobType_VLIA, tvAppliedJob_VLIA,
            tvEmail_VLIA, tvSkype_VLIA, tvPhone_VLIA, tvCratedBy_VLIA;

    private final String createdByFormatter;
    private final String noEmail, noPhone, noSkype;

    public ApplicationVH(View itemView, @Nullable OnCardClickListener listener, int viewType) {
        super(itemView, listener, viewType);

        tvApplicationName_VLIA = findView(R.id.tvApplicationName_VLIA);
        tvJobType_VLIA = findView(R.id.tvJobType_VLIA);
        tvStage_VLIA = findView(R.id.tvStage_VLIA);
        tvAppliedJob_VLIA = findView(R.id.tvAppliedJob_VLIA);
        tvEmail_VLIA = findView(R.id.tvEmail_VLIA);
        tvSkype_VLIA = findView(R.id.tvSkype_VLIA);
        tvPhone_VLIA = findView(R.id.tvPhone_VLIA);
        tvCratedBy_VLIA = findView(R.id.tvCratedBy_VLIA);

        createdByFormatter = itemView.getResources().getString(R.string.pattern_created_by);
        noEmail = itemView.getResources().getString(R.string.no_email);
        noPhone = itemView.getResources().getString(R.string.no_phone);
        noSkype = itemView.getResources().getString(R.string.no_skype);
    }

    @Override
    public void bindData(ApplicationDH data) {
        super.bindData(data);

        final Application application = data.getApplication();
        tvApplicationName_VLIA.setText(application.name.getFullName());
        tvJobType_VLIA.setText(application.jobType);
        tvAppliedJob_VLIA.setText(application.jobPosition.name);
        tvEmail_VLIA.setText(TextUtils.isEmpty(application.personalEmail) ? noEmail : application.personalEmail);
        tvSkype_VLIA.setText(TextUtils.isEmpty(application.skype) ? noSkype : application.skype);
        tvPhone_VLIA.setText(TextUtils.isEmpty(application.workPhones.getNotNullPhone()) ? noPhone : application.workPhones.getNotNullPhone());

        tvStage_VLIA.setText(application.workflow.name);
        tvStage_VLIA.setBackgroundDrawable(new RoundRectDrawable(ContextCompat.getColor(itemView.getContext(), ColorHelper.getStatusColorRes(application.workflow.status))));

        String createdString;
        String date = new DateManager.DateConverter(application.createdBy.date).setDstPattern(DateManager.PATTERN_DATE_MONTH_PREVIEW).toString();
        if (!TextUtils.isEmpty(application.createdBy.user))
            createdString = String.format(createdByFormatter, date, application.createdBy.user);
        else
            createdString = String.format("Created: %s", date);
        tvCratedBy_VLIA.setText(createdString);
    }
}
