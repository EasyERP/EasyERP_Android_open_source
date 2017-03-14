package com.thinkmobiles.easyerp.presentation.screens.hr.applications;

import com.thinkmobiles.easyerp.data.model.hr.applications.Application;
import com.thinkmobiles.easyerp.presentation.base.rules.master.filterable.FilterableModel;
import com.thinkmobiles.easyerp.presentation.base.rules.master.filterable.FilterableView;
import com.thinkmobiles.easyerp.presentation.base.rules.master.filterable.MasterFilterablePresenterHelper;
import com.thinkmobiles.easyerp.presentation.holders.data.hr.ApplicationDH;
import com.thinkmobiles.easyerp.presentation.managers.ErrorManager;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Michael Soyma (Created on 3/13/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public class ApplicationsListPresenter extends MasterFilterablePresenterHelper implements ApplicationsListContract.ApplicationsListPresenter {

    private final ApplicationsListContract.ApplicationsListView view;
    private final ApplicationsListContract.ApplicationsListModel model;

    private final List<Application> applications = new ArrayList<>();

    public ApplicationsListPresenter(ApplicationsListContract.ApplicationsListView view, ApplicationsListContract.ApplicationsListModel model) {
        this.view = view;
        this.model = model;

        this.view.setPresenter(this);
    }

    @Override
    public void clickItem(int position) {
        String id = applications.get(position).id;
        if(super.selectItem(id, position))
            view.openApplicationDetail(id);
    }

    @Override
    protected boolean hasContent() {
        return !applications.isEmpty();
    }

    @Override
    protected void retainInstance() {
        setData(applications, true);
    }

    @Override
    protected void loadPage(int page) {
        final boolean needClear = page == 1;
        compositeSubscription.add(
                model.getApplications(helper, page).subscribe(
                        applicationResponseGetTotalItems -> {
                            currentPage = page;
                            totalItems = applicationResponseGetTotalItems.total;
                            saveData(applicationResponseGetTotalItems.data, needClear);
                            setData(applicationResponseGetTotalItems.data, needClear);
                        }, t -> error(t))
        );
    }

    @Override
    protected int getCountItems() {
        return applications.size();
    }

    @Override
    protected FilterableView getView() {
        return view;
    }

    @Override
    protected FilterableModel getModel() {
        return model;
    }
    private void saveData(final List<Application> applications, boolean needClear) {
        if (needClear)
            this.applications.clear();
        this.applications.addAll(applications);
    }

    private void setData(final List<Application> applications, boolean needClear) {
        view.setDataList(prepareApplicationDHs(applications), needClear);
        if (this.applications.isEmpty()) {
            view.displayErrorState(ErrorManager.getErrorType(null));
        } else {
            view.showProgress(Constants.ProgressType.NONE);
        }
    }

    private ArrayList<ApplicationDH> prepareApplicationDHs(final List<Application> applications) {
        final ArrayList<ApplicationDH> result = new ArrayList<>();
        for (Application item : applications) {
            final ApplicationDH applicationDH = new ApplicationDH(item);
            makeSelectedDHIfNeed(applicationDH, this.applications.indexOf(item));
            result.add(applicationDH);
        }
        selectFirstElementIfNeed(result);
        return result;
    }
}
