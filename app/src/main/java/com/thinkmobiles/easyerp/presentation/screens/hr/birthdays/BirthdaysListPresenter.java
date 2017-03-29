package com.thinkmobiles.easyerp.presentation.screens.hr.birthdays;

import com.thinkmobiles.easyerp.data.model.crm.common.images.ImageItem;
import com.thinkmobiles.easyerp.data.model.hr.birthdays.Birthdays;
import com.thinkmobiles.easyerp.data.model.hr.birthdays.BirthdaysCommonResponse;
import com.thinkmobiles.easyerp.data.model.hr.birthdays.BirthdaysResponse;
import com.thinkmobiles.easyerp.data.model.hr.birthdays.EmployeeWithBirthday;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.MasterSelectablePresenterHelper;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectableView;
import com.thinkmobiles.easyerp.presentation.holders.data.hr.EmployeeBirthdayDH;
import com.thinkmobiles.easyerp.presentation.managers.ErrorManager;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Michael Soyma (Created on 3/28/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public final class BirthdaysListPresenter extends MasterSelectablePresenterHelper implements BirthdaysListContract.BirthdaysListPresenter {

    private BirthdaysListContract.BirthdaysListView view;
    private BirthdaysListContract.BirthdaysListModel model;

    private BirthdaysCommonResponse birthdaysCommonResponse;

    public BirthdaysListPresenter(BirthdaysListContract.BirthdaysListView view, BirthdaysListContract.BirthdaysListModel model) {
        this.view = view;
        this.model = model;

        this.view.setPresenter(this);
    }

    @Override
    public void clickItem(int position) {
        final Birthdays birthdays = birthdaysCommonResponse.birthdaysResponse.data;
        String id = null;
        if (position >= 0 && position < birthdays.weekly.size())
            id = birthdays.weekly.get(position).id;
        else if (position >= birthdays.weekly.size() && position < birthdays.weekly.size() + birthdays.nextweek.size())
            id = birthdays.nextweek.get(position - birthdays.weekly.size()).id;
        else if (position >= birthdays.weekly.size() + birthdays.nextweek.size() && position < birthdays.weekly.size() + birthdays.nextweek.size() + birthdays.monthly.size())
            id = birthdays.monthly.get(position - birthdays.weekly.size() - birthdays.nextweek.size()).id;

        if (super.selectItem(id, position))
            view.openEmployeeDetail(id);
    }

    @Override
    protected SelectableView getView() {
        return view;
    }

    @Override
    protected void loadPage(int page) {
        compositeSubscription.add(
                model.getBirthdaysInEmployees()
                        .flatMap(birthdaysResponse -> model.getEmployeeImages(prepareIDsForImagesRequest(birthdaysResponse.data)),
                                BirthdaysCommonResponse::new).subscribe(
                        birthdaysCommonResponse -> {
                            totalItems = Constants.COUNT_LIST_ITEMS_WITHOUT_PAGINATION;
                            this.birthdaysCommonResponse = birthdaysCommonResponse;
                            updateData();
                        }, t -> error(t)
                )
        );
    }

    @Override
    protected int getCountItems() {
        return Constants.COUNT_LIST_ITEMS_WITHOUT_PAGINATION;
    }

    @Override
    protected boolean hasContent() {
        return birthdaysCommonResponse != null;
    }

    @Override
    protected void retainInstance() {
        updateData();
    }

    private void updateData() {
        final BirthdaysResponse birthdaysResponse = birthdaysCommonResponse.birthdaysResponse;
        view.setDataList(prepareBirthdaysDHs(birthdaysResponse.data, birthdaysCommonResponse.responseGetImages.data), true);
        if (birthdaysResponse.data.weekly.isEmpty() && birthdaysResponse.data.nextweek.isEmpty() && birthdaysResponse.data.monthly.isEmpty()) {
            view.displayErrorState(ErrorManager.getErrorType(null));
        } else {
            view.showProgress(Constants.ProgressType.NONE);
        }
    }

    private ArrayList<EmployeeBirthdayDH> prepareBirthdaysDHs(final Birthdays birthdays, final List<ImageItem> images) {
        final ArrayList<EmployeeBirthdayDH> result = new ArrayList<>();
        int prevPosition = -1;

        if (birthdays.weekly != null) {
            prepareBirthdaysDHs(result, birthdays.weekly, images, "Current Week", prevPosition);
            prevPosition = prevPosition + birthdays.weekly.size();
        }
        if (birthdays.nextweek != null) {
            prepareBirthdaysDHs(result, birthdays.nextweek, images, "Next Week", prevPosition);
            prevPosition = prevPosition + birthdays.nextweek.size();
        }
        if (birthdays.monthly != null) {
            prepareBirthdaysDHs(result, birthdays.monthly, images, "Current Month", prevPosition);
        }

        selectFirstElementIfNeed(result);
        return result;
    }

    private void prepareBirthdaysDHs(final ArrayList<EmployeeBirthdayDH> result,
                                     final List<EmployeeWithBirthday> employeeWithBirthdays,
                                     final List<ImageItem> images,
                                     final String header,
                                     final int prevPosition) {
        boolean withHeader = true;
        int position = prevPosition;
        for (EmployeeWithBirthday item : employeeWithBirthdays) {
            position++;
            String base64Image = null;
            for (ImageItem imageItem : images)
                if (item.id.equalsIgnoreCase(imageItem.id))
                    base64Image = imageItem.imageSrc;
            final EmployeeBirthdayDH employeeBirthdayDH = withHeader ? new EmployeeBirthdayDH(item, base64Image, header) : new EmployeeBirthdayDH(item, base64Image);
            makeSelectedDHIfNeed(employeeBirthdayDH, position);
            result.add(employeeBirthdayDH);
            withHeader = false;
        }
    }

    private ArrayList<String> prepareIDsForImagesRequest(final Birthdays birthdays) {
        ArrayList<String> employeesWithBirthdayID = new ArrayList<>();
        if (birthdays != null) {
            for (EmployeeWithBirthday employeeWithBirthday : birthdays.weekly)
                employeesWithBirthdayID.add(employeeWithBirthday.id);
            for (EmployeeWithBirthday employeeWithBirthday : birthdays.nextweek)
                employeesWithBirthdayID.add(employeeWithBirthday.id);
            for (EmployeeWithBirthday employeeWithBirthday : birthdays.monthly)
                employeesWithBirthdayID.add(employeeWithBirthday.id);
        }
        return employeesWithBirthdayID;
    }
}
