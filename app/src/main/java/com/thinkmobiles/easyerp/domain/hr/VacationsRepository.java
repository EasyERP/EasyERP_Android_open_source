package com.thinkmobiles.easyerp.domain.hr;

import com.thinkmobiles.easyerp.data.api.Rest;
import com.thinkmobiles.easyerp.data.model.crm.common.alphabet.AlphabetItem;
import com.thinkmobiles.easyerp.data.model.crm.common.alphabet.ResponseGetAlphabet;
import com.thinkmobiles.easyerp.data.model.crm.filter.FilterItem;
import com.thinkmobiles.easyerp.data.model.crm.filter.ResponseFilters;
import com.thinkmobiles.easyerp.data.model.hr.attendance_detail.MonthDetail;
import com.thinkmobiles.easyerp.data.services.VacationService;
import com.thinkmobiles.easyerp.presentation.base.NetworkRepository;
import com.thinkmobiles.easyerp.presentation.screens.hr.vacations.VacationsListContract;

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
public class VacationsRepository extends NetworkRepository implements VacationsListContract.VacationsListModel {

    private VacationService vacationService;

    public VacationsRepository() {
        vacationService = Rest.getInstance().getVacationService();
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
                        Collections.sort(responseGetAlphabet.data, new Comparator<AlphabetItem>() {
                            @Override
                            public int compare(AlphabetItem o1, AlphabetItem o2) {
                                return o1.id.compareTo(o2.id);
                            }
                        });
                        return Observable.just(responseGetAlphabet);
                    }
                }));
    }


    @Override
    public Observable<ResponseFilters> getFilters() {
        return null;
    }

    public Observable<ArrayList<MonthDetail>> getVacationDetails(int page, int year, int month) {
        return getNetworkObservable(vacationService.getVacationDetails("list", page, 50, "Vacation", year, month));
    }
}
