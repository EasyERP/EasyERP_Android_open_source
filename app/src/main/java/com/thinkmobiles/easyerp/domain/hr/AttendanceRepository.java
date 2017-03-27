package com.thinkmobiles.easyerp.domain.hr;

import com.thinkmobiles.easyerp.data.api.Rest;
import com.thinkmobiles.easyerp.data.model.hr.attendance_detail.ResponseGetAttendanceDetails;
import com.thinkmobiles.easyerp.data.services.AttendanceService;
import com.thinkmobiles.easyerp.presentation.base.NetworkRepository;
import com.thinkmobiles.easyerp.presentation.screens.hr.attendance.details.AttendanceDetailsContract;
import com.thinkmobiles.easyerp.presentation.utils.filter.FilterComparator;

import org.androidannotations.annotations.EBean;

import java.util.Collections;

import rx.Observable;

/**
 * Created by Alex Michenko on 27.03.2017.
 */

@EBean
public class AttendanceRepository extends NetworkRepository implements AttendanceDetailsContract.AttendanceDetailsModel {

    public AttendanceService attendanceService;

    public AttendanceRepository() {
        attendanceService = Rest.getInstance().getAttendanceService();
    }

    @Override
    public Observable<ResponseGetAttendanceDetails> getAttendanceDetails(int year, String id) {
        return getNetworkObservable(Observable.zip(attendanceService.getAttendanceDetails(year, id),
                attendanceService.getEnableYears(),
                (monthDetailResponseGetTotalItems, filterItems) -> {
                    Collections.sort(filterItems, new FilterComparator());
                    return new ResponseGetAttendanceDetails(filterItems, monthDetailResponseGetTotalItems.data);
                }));
    }

}
