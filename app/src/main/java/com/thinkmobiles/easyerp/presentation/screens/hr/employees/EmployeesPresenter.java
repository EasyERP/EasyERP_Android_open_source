package com.thinkmobiles.easyerp.presentation.screens.hr.employees;

import com.thinkmobiles.easyerp.data.model.ResponseGetTotalItems;
import com.thinkmobiles.easyerp.data.model.crm.common.images.ImageItem;
import com.thinkmobiles.easyerp.data.model.hr.employees.ResponseCommonEmployees;
import com.thinkmobiles.easyerp.data.model.hr.employees.item.EmployeeItem;
import com.thinkmobiles.easyerp.presentation.base.rules.master.alphabetical.AlphabeticalModel;
import com.thinkmobiles.easyerp.presentation.base.rules.master.alphabetical.AlphabeticalView;
import com.thinkmobiles.easyerp.presentation.base.rules.master.alphabetical.MasterAlphabeticalPresenterHelper;
import com.thinkmobiles.easyerp.presentation.holders.data.hr.EmployeeDH;
import com.thinkmobiles.easyerp.presentation.managers.ErrorManager;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import java.util.ArrayList;

/**
 * Created by Lynx on 3/13/2017.
 */

public class EmployeesPresenter extends MasterAlphabeticalPresenterHelper implements EmployeesContract.EmployeePresenter {

    private EmployeesContract.EmployeeView view;
    private EmployeesContract.EmployeeModel model;

    private ResponseCommonEmployees responseEmployees;

    public EmployeesPresenter(EmployeesContract.EmployeeView view, EmployeesContract.EmployeeModel model) {
        this.view = view;
        this.model = model;

        view.setPresenter(this);
    }

    @Override
    public void clickItem(int position) {
        String id = responseEmployees.responseGetEmployees.data.get(position).id;
        if (super.selectItem(id, position))
            view.openDetailsScreen(id);
    }

    @Override
    protected boolean hasContent() {
        return responseEmployees != null;
    }

    @Override
    protected void retainInstance() {
        setData(responseEmployees, true);
    }

    @Override
    protected void loadPage(int page) {
        final boolean needClear = page == 1;
        compositeSubscription.add(
                model.getEmployees(helper, selectedLetter, page)
                .flatMap(employeeItemResponseGetTotalItems -> model.getEmployeeImages(prepareIDsForImagesRequest(employeeItemResponseGetTotalItems)),
                        ResponseCommonEmployees::new)
                .subscribe(responseCommonEmployees -> {
                    currentPage = page;
                    totalItems = responseCommonEmployees.responseGetEmployees.total;
                    saveData(responseCommonEmployees, needClear);
                    setData(responseCommonEmployees, needClear);
                }, t -> error(t))
        );
    }

    @Override
    protected int getCountItems() {
        return responseEmployees.responseGetEmployees.data.size();
    }

    @Override
    protected AlphabeticalView getView() {
        return view;
    }

    @Override
    protected AlphabeticalModel getModel() {
        return model;
    }

    private void saveData(ResponseCommonEmployees responseCommonEmployees, boolean needClear) {
        if (needClear) responseEmployees = responseCommonEmployees;
        else if (responseEmployees != null) {
            responseEmployees.responseGetImages.data.addAll(responseCommonEmployees.responseGetImages.data);
            responseEmployees.responseGetEmployees.data.addAll(responseCommonEmployees.responseGetEmployees.data);
        }
    }

    private void setData(ResponseCommonEmployees responseCommonEmployees, boolean needClear) {
        view.displaySelectedLetter(selectedLetter);
        view.setDataList(prepareDataHolders(responseCommonEmployees), needClear);
        if (responseEmployees.responseGetEmployees.data.isEmpty()) {
            view.displayErrorState(ErrorManager.getErrorType(null));
        } else {
            view.showProgress(Constants.ProgressType.NONE);
        }
    }

    private ArrayList<EmployeeDH> prepareDataHolders(ResponseCommonEmployees responseCommonEmployees) {
        ArrayList<EmployeeDH> result = new ArrayList<>();
        for (EmployeeItem employeeItem : responseCommonEmployees.responseGetEmployees.data) {
            for (ImageItem imageItem : responseCommonEmployees.responseGetImages.data) {
                if (employeeItem.id.equalsIgnoreCase(imageItem.id)) {
                    final EmployeeDH employeeDH = new EmployeeDH(employeeItem, imageItem.imageSrc);
                    makeSelectedDHIfNeed(employeeDH, responseEmployees.responseGetEmployees.data.indexOf(employeeItem));
                    result.add(employeeDH);
                }
            }
        }
        selectFirstElementIfNeed(result);
        return result;
    }

    private ArrayList<String> prepareIDsForImagesRequest(ResponseGetTotalItems<EmployeeItem> responseGetEmployees) {
        ArrayList<String> employeesID = new ArrayList<>();
        if (responseGetEmployees.total > 0 && responseGetEmployees.data != null && responseGetEmployees.data.size() > 0) {
            for (EmployeeItem employeeItem : responseGetEmployees.data) {
                employeesID.add(employeeItem.id);
            }
        }
        return employeesID;
    }
}
