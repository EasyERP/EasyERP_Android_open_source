package com.thinkmobiles.easyerp.presentation.screens.hr.attendance;

import com.thinkmobiles.easyerp.data.model.ResponseGetTotalItems;
import com.thinkmobiles.easyerp.data.model.crm.common.images.ImageItem;
import com.thinkmobiles.easyerp.data.model.hr.employees.ResponseCommonEmployees;
import com.thinkmobiles.easyerp.data.model.hr.employees.item.EmployeeItem;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.MasterSelectablePresenterHelper;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectableView;
import com.thinkmobiles.easyerp.presentation.holders.data.hr.EmployeeDH;
import com.thinkmobiles.easyerp.presentation.managers.ErrorManager;
import com.thinkmobiles.easyerp.presentation.utils.Constants;
import com.thinkmobiles.easyerp.presentation.utils.StringUtil;

import java.util.ArrayList;

/**
 * @author Michael Soyma (Created on 3/20/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public class AttendanceListPresenter extends MasterSelectablePresenterHelper implements AttendanceListContract.AttendanceListPresenter {

    private AttendanceListContract.AttendanceListView view;
    private AttendanceListContract.AttendanceListModel model;

    private ResponseCommonEmployees responseEmployees;

    public AttendanceListPresenter(AttendanceListContract.AttendanceListView view, AttendanceListContract.AttendanceListModel model) {
        this.view = view;
        this.model = model;

        this.view.setPresenter(this);
    }

    @Override
    public void clickItem(int position) {
        final EmployeeItem employeeItem = responseEmployees.responseGetEmployees.data.get(position);
        if (super.selectItem(employeeItem.id, position))
            view.openAttendanceDetail(employeeItem.id, StringUtil.getFullName(employeeItem.name.first, employeeItem.name.last));
    }

    @Override
    protected SelectableView getView() {
        return view;
    }

    @Override
    protected void loadPage(int page) {
        compositeSubscription.add(
                model.getAllEmployees()
                        .flatMap(employeeItemResponseGetTotalItems -> model.getEmployeeImages(prepareIDsForImagesRequest(employeeItemResponseGetTotalItems)),
                                ResponseCommonEmployees::new)
                        .subscribe(responseCommonEmployees -> {
                            totalItems = responseCommonEmployees.responseGetEmployees.data.size();
                            saveData(responseCommonEmployees, true);
                            setData(responseCommonEmployees, true);
                        }, t -> error(t))
        );

    }

    @Override
    protected int getCountItems() {
        return responseEmployees.responseGetEmployees.data.size();
    }

    @Override
    protected boolean hasContent() {
        return responseEmployees != null;
    }

    @Override
    protected void retainInstance() {
        setData(responseEmployees, true);
    }

    private void saveData(ResponseCommonEmployees responseCommonEmployees, boolean needClear) {
        if (needClear) responseEmployees = responseCommonEmployees;
        else if (responseEmployees != null) {
            responseEmployees.responseGetImages.data.addAll(responseCommonEmployees.responseGetImages.data);
            responseEmployees.responseGetEmployees.data.addAll(responseCommonEmployees.responseGetEmployees.data);
        }
    }

    private void setData(ResponseCommonEmployees responseCommonEmployees, boolean needClear) {
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
            String base64Image = null;
            for (ImageItem imageItem : responseCommonEmployees.responseGetImages.data)
                if (employeeItem.id.equalsIgnoreCase(imageItem.id))
                    base64Image = imageItem.imageSrc;
            final EmployeeDH employeeDH = new EmployeeDH(employeeItem, base64Image);
            makeSelectedDHIfNeed(employeeDH, responseEmployees.responseGetEmployees.data.indexOf(employeeItem));
            result.add(employeeDH);
        }
        selectFirstElementIfNeed(result);
        return result;
    }

    private ArrayList<String> prepareIDsForImagesRequest(ResponseGetTotalItems<EmployeeItem> responseGetEmployees) {
        ArrayList<String> employeesID = new ArrayList<>();
        if (responseGetEmployees.data.size() > 0 && responseGetEmployees.data != null && responseGetEmployees.data.size() > 0) {
            for (EmployeeItem employeeItem : responseGetEmployees.data) {
                employeesID.add(employeeItem.id);
            }
        }
        return employeesID;
    }
}
