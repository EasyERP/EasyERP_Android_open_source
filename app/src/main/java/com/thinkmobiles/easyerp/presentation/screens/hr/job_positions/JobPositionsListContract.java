package com.thinkmobiles.easyerp.presentation.screens.hr.job_positions;

import com.thinkmobiles.easyerp.data.model.ResponseGetTotalItems;
import com.thinkmobiles.easyerp.data.model.hr.job_positions.JobPosition;
import com.thinkmobiles.easyerp.presentation.base.BaseModel;
import com.thinkmobiles.easyerp.presentation.base.BaseView;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectablePresenter;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectableView;

import rx.Observable;

/**
 * @author Michael Soyma (Created on 3/14/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public interface JobPositionsListContract {
    interface JobPositionsListView extends BaseView<JobPositionsListPresenter>, SelectableView {
        void openJobPositionDetail(final String id);
    }
    interface JobPositionsListPresenter extends SelectablePresenter {

    }
    interface JobPositionsListModel extends BaseModel {
        Observable<ResponseGetTotalItems<JobPosition>> getJobPositions(final int page);
    }
}
