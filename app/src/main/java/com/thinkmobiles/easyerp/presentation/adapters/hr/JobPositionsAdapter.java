package com.thinkmobiles.easyerp.presentation.adapters.hr;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectableAdapter;
import com.thinkmobiles.easyerp.presentation.holders.data.hr.JobPositionDH;
import com.thinkmobiles.easyerp.presentation.holders.view.hr.JobPositionVH;

import org.androidannotations.annotations.EBean;

/**
 * @author Michael Soyma (Created on 3/14/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
@EBean
public class JobPositionsAdapter extends SelectableAdapter<JobPositionDH, JobPositionVH> {
    @Override
    protected int getItemLayout() {
        return R.layout.view_list_item_job_position;
    }
}
