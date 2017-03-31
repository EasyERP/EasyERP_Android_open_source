package com.thinkmobiles.easyerp.domain.hr;

import android.text.TextUtils;

import com.thinkmobiles.easyerp.data.api.Rest;
import com.thinkmobiles.easyerp.data.model.ResponseGetTotalItems;
import com.thinkmobiles.easyerp.data.model.crm.common.alphabet.AlphabetItem;
import com.thinkmobiles.easyerp.data.model.crm.common.alphabet.ResponseGetAlphabet;
import com.thinkmobiles.easyerp.data.model.crm.common.images.ImageItem;
import com.thinkmobiles.easyerp.data.model.crm.filter.FilterItem;
import com.thinkmobiles.easyerp.data.model.crm.filter.ResponseFilters;
import com.thinkmobiles.easyerp.data.model.hr.attendance_detail.MonthDetail;
import com.thinkmobiles.easyerp.data.services.ImageService;
import com.thinkmobiles.easyerp.data.services.VacationService;
import com.thinkmobiles.easyerp.presentation.base.NetworkRepository;
import com.thinkmobiles.easyerp.presentation.screens.hr.vacations.VacationsListContract;
import com.thinkmobiles.easyerp.presentation.screens.hr.vacations.details.VacationDetailsContract;

import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Lynx on 3/29/2017.
 */

@EBean(scope = EBean.Scope.Singleton)
public class VacationsRepository extends NetworkRepository implements VacationsListContract.VacationsListModel, VacationDetailsContract.VacationDetailsModel {

    private VacationService vacationService;
    private ImageService imageService;

    public VacationsRepository() {
        vacationService = Rest.getInstance().getVacationService();
        imageService = Rest.getInstance().getImageService();
    }

    @Override
    public Observable<ResponseGetAlphabet> getAlphabet() {
        return getNetworkObservable(vacationService.getVacationYears()
                .flatMap(new Func1<ArrayList<FilterItem>, Observable<ResponseGetAlphabet>>() {
                    @Override
                    public Observable<ResponseGetAlphabet> call(ArrayList<FilterItem> filterItems) {
                        ResponseGetAlphabet responseGetAlphabet = new ResponseGetAlphabet();
                        responseGetAlphabet.data = new ArrayList<>();
                        if (filterItems != null && !filterItems.isEmpty()) {
                            for (FilterItem filterItem : filterItems) {
                                responseGetAlphabet.data.add(new AlphabetItem(filterItem.name));
                            }
                        }
                        Collections.sort(responseGetAlphabet.data, (o1, o2) -> o1.id.compareTo(o2.id));
                        return Observable.just(responseGetAlphabet);
                    }
                }));
    }


    @Override
    public Observable<ResponseFilters> getFilters() {
        return null;
    }

    public Observable<ArrayList<MonthDetail>> getVacationDetails(int year, int month) {
        return getNetworkObservable(vacationService.getVacationDetails("list", "Vacation", year, month)
                .flatMap(monthDetails -> getNetworkObservable(imageService.getEmployeesImages(prepareIDsForImageRequest(monthDetails))),
                        this::addImagesToVacationDetails));

    }

    private ArrayList<MonthDetail> addImagesToVacationDetails(ArrayList<MonthDetail> model, ResponseGetTotalItems<ImageItem> images) {
        if (model != null && !model.isEmpty() && images != null && images.data != null && !images.data.isEmpty()) {
            for (ImageItem imageItem : images.data) {
                for (MonthDetail monthDetail : model) {
                    if (imageItem.id.equals(monthDetail.employee.id))
                        monthDetail.employee.employeeBase64Image = imageItem.imageSrc;
                }
            }
        }
        return model;
    }

    private ArrayList<String> prepareIDsForImageRequest(ArrayList<MonthDetail> model) {
        ArrayList<String> result = new ArrayList<>();
        if (model != null && !model.isEmpty()) {
            for (MonthDetail monthDetail : model) {
                if (monthDetail != null && monthDetail.employee != null && !TextUtils.isEmpty(monthDetail.employee.id))
                    result.add(monthDetail.employee.id);
            }
        }
        return result;
    }
}
