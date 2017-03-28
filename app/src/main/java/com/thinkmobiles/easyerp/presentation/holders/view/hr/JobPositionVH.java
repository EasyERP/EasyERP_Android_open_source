package com.thinkmobiles.easyerp.presentation.holders.view.hr;

import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.michenko.simpleadapter.OnCardClickListener;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.data.model.hr.job_positions.JobPosition;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectableVHHelper;
import com.thinkmobiles.easyerp.presentation.custom.RoundRectDrawable;
import com.thinkmobiles.easyerp.presentation.holders.data.hr.JobPositionDH;
import com.thinkmobiles.easyerp.presentation.managers.DateManager;
import com.thinkmobiles.easyerp.presentation.managers.TagHelper;

/**
 * @author Michael Soyma (Created on 3/14/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public final class JobPositionVH extends SelectableVHHelper<JobPositionDH> {

    private TextView tvJobName_VLIJP, tvStatus_VLIJP, tvDepartment_VLIJP,
            tvCurrentNumber_VLIJP, tvExpectedInRecruitment_VLIJP, tvEditedAt_VLIJP;

    public JobPositionVH(View itemView, @Nullable OnCardClickListener listener, int viewType) {
        super(itemView, listener, viewType);

        tvJobName_VLIJP = findView(R.id.tvJobName_VLIJP);
        tvStatus_VLIJP = findView(R.id.tvStatus_VLIJP);
        tvDepartment_VLIJP = findView(R.id.tvDepartment_VLIJP);
        tvCurrentNumber_VLIJP = findView(R.id.tvCurrentNumber_VLIJP);
        tvExpectedInRecruitment_VLIJP = findView(R.id.tvExpectedInRecruitment_VLIJP);
        tvEditedAt_VLIJP = findView(R.id.tvEditedAt_VLIJP);
    }

    @Override
    public void bindData(JobPositionDH data) {
        super.bindData(data);

        final JobPosition jobPosition = data.getJobPosition();
        tvJobName_VLIJP.setText(jobPosition.name);

        tvStatus_VLIJP.setText(jobPosition.workflow.name);
        tvStatus_VLIJP.setBackgroundDrawable(new RoundRectDrawable(ContextCompat.getColor(itemView.getContext(), TagHelper.getStatusColorRes(jobPosition.workflow.status))));

        tvDepartment_VLIJP.setText(jobPosition.department.name);
        tvCurrentNumber_VLIJP.setText(String.valueOf(jobPosition.numberOfEmployees));
        tvExpectedInRecruitment_VLIJP.setText(String.valueOf(jobPosition.expectedRecruitment));
        tvEditedAt_VLIJP.setText(new DateManager.DateConverter(jobPosition.editedBy.date).setDstPattern(DateManager.PATTERN_DASHBOARD_DAY_VIEW).toString());
    }
}
