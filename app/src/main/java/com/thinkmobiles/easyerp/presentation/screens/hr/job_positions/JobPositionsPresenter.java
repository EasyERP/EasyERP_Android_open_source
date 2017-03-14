package com.thinkmobiles.easyerp.presentation.screens.hr.job_positions;

import com.thinkmobiles.easyerp.data.model.hr.job_positions.JobPosition;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.MasterSelectablePresenterHelper;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectableView;
import com.thinkmobiles.easyerp.presentation.holders.data.hr.JobPositionDH;
import com.thinkmobiles.easyerp.presentation.managers.ErrorManager;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Michael Soyma (Created on 3/14/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public final class JobPositionsPresenter extends MasterSelectablePresenterHelper implements JobPositionsListContract.JobPositionsListPresenter {

    private JobPositionsListContract.JobPositionsListView view;
    private JobPositionsListContract.JobPositionsListModel model;

    private List<JobPosition> jobPositions = new ArrayList<>();

    public JobPositionsPresenter(JobPositionsListContract.JobPositionsListView view, JobPositionsListContract.JobPositionsListModel model) {
        this.view = view;
        this.model = model;

        this.view.setPresenter(this);
    }

    @Override
    public void clickItem(int position) {
        final JobPosition jobPosition = jobPositions.get(position);
        if (super.selectItem(jobPosition.id, position))
            view.openJobPositionDetail(jobPosition.id);
    }

    @Override
    protected SelectableView getView() {
        return view;
    }

    @Override
    protected void loadPage(int page) {
        final boolean needClear = page == 1;
        compositeSubscription.add(
                model.getJobPositions(page)
                        .subscribe(
                                jobPositionsResponse -> {
                                    currentPage = page;
                                    totalItems = jobPositionsResponse.total;
                                    saveData(jobPositionsResponse.data, needClear);
                                    setData(jobPositionsResponse.data, needClear);
                                },
                                t -> error(t))
        );
    }

    @Override
    protected int getCountItems() {
        return jobPositions.size();
    }

    @Override
    protected boolean hasContent() {
        return !jobPositions.isEmpty();
    }

    @Override
    protected void retainInstance() {
        setData(jobPositions, true);
    }

    private void saveData(final List<JobPosition> jobPositions, boolean needClear) {
        if (needClear)
            this.jobPositions.clear();
        this.jobPositions.addAll(jobPositions);
    }

    private void setData(final List<JobPosition> jobPositions, boolean needClear) {
        view.setDataList(prepareJobPositionDHs(jobPositions), needClear);
        if (this.jobPositions.isEmpty()) {
            view.displayErrorState(ErrorManager.getErrorType(null));
        } else {
            view.showProgress(Constants.ProgressType.NONE);
        }
    }

    private ArrayList<JobPositionDH> prepareJobPositionDHs(final List<JobPosition> jobPositions) {
        final ArrayList<JobPositionDH> result = new ArrayList<>();
        for (JobPosition item : jobPositions) {
            final JobPositionDH jobPositionDH = new JobPositionDH(item);
            makeSelectedDHIfNeed(jobPositionDH, this.jobPositions.indexOf(item));
            result.add(jobPositionDH);
        }
        selectFirstElementIfNeed(result);
        return result;
    }
}
