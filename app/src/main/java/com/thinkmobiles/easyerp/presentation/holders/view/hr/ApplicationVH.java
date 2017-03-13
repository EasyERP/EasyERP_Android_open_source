package com.thinkmobiles.easyerp.presentation.holders.view.hr;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.michenko.simpleadapter.OnCardClickListener;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.data.model.hr.applications.Application;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectableVHHelper;
import com.thinkmobiles.easyerp.presentation.holders.data.hr.ApplicationDH;
import com.thinkmobiles.easyerp.presentation.managers.DateManager;

/**
 * @author Michael Soyma (Created on 3/13/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public class ApplicationVH extends SelectableVHHelper<ApplicationDH> {

    private TextView tvApplicationName_VLIA, tvJobType_VLIA, tvAppliedJob_VLIA,
            tvEmail_VLIA, tvPhone_VLIA, tvCratedBy_VLIA, tvEditedBy_VLIA;

    private final String createdByFormatter, editedByFormatter;
    private final String noEmail, noPhone;

    public ApplicationVH(View itemView, @Nullable OnCardClickListener listener, int viewType) {
        super(itemView, listener, viewType);

        tvApplicationName_VLIA = findView(R.id.tvApplicationName_VLIA);
        tvJobType_VLIA = findView(R.id.tvJobType_VLIA);
        tvAppliedJob_VLIA = findView(R.id.tvAppliedJob_VLIA);
        tvEmail_VLIA = findView(R.id.tvEmail_VLIA);
        tvPhone_VLIA = findView(R.id.tvPhone_VLIA);
        tvCratedBy_VLIA = findView(R.id.tvCratedBy_VLIA);
        tvEditedBy_VLIA = findView(R.id.tvEditedBy_VLIA);

        createdByFormatter = itemView.getResources().getString(R.string.pattern_created_by);
        editedByFormatter = itemView.getResources().getString(R.string.pattern_edited_by);
        noEmail = itemView.getResources().getString(R.string.no_email);
        noPhone = itemView.getResources().getString(R.string.no_phone);
    }

    @Override
    public void bindData(ApplicationDH data) {
        super.bindData(data);

        final Application application = data.getApplication();
        tvApplicationName_VLIA.setText(application.name.getFullName());
        tvJobType_VLIA.setText(application.jobType);
        tvAppliedJob_VLIA.setText(application.jobPosition.name);
        tvEmail_VLIA.setText(TextUtils.isEmpty(application.personalEmail) ? noEmail : application.personalEmail);
        tvPhone_VLIA.setText(TextUtils.isEmpty(application.workPhones.getNotNullPhone()) ? noPhone : application.workPhones.getNotNullPhone());

        tvCratedBy_VLIA.setText(String.format(createdByFormatter,
                new DateManager.DateConverter(application.createdBy.date).setDstPattern(DateManager.PATTERN_DATE_SIMPLE_PREVIEW).toString(),
                application.createdBy.user));
        tvEditedBy_VLIA.setText(String.format(editedByFormatter,
                new DateManager.DateConverter(application.editedBy.date).setDstPattern(DateManager.PATTERN_DATE_SIMPLE_PREVIEW).toString(),
                application.editedBy.user));
    }
}
