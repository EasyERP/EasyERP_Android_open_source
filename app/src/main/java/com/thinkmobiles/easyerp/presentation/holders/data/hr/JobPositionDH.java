package com.thinkmobiles.easyerp.presentation.holders.data.hr;

import com.thinkmobiles.easyerp.data.model.hr.job_positions.JobPosition;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectableDHHelper;

/**
 * @author Michael Soyma (Created on 3/14/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public class JobPositionDH extends SelectableDHHelper {

    private final JobPosition jobPosition;

    public JobPositionDH(JobPosition jobPosition) {
        this.jobPosition = jobPosition;
    }

    public JobPosition getJobPosition() {
        return jobPosition;
    }

    @Override
    public String getId() {
        return jobPosition.id;
    }
}
