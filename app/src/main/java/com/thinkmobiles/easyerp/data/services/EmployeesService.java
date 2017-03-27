package com.thinkmobiles.easyerp.data.services;

import com.thinkmobiles.easyerp.data.model.ResponseGetTotalItems;
import com.thinkmobiles.easyerp.data.model.crm.common.alphabet.ResponseGetAlphabet;
import com.thinkmobiles.easyerp.data.model.hr.dashboard.DepartmentSalary;
import com.thinkmobiles.easyerp.data.model.hr.dashboard.EmployeeCountForDashboard;
import com.thinkmobiles.easyerp.data.model.hr.dashboard.EmployeeGenderDepartmentInfo;
import com.thinkmobiles.easyerp.data.model.hr.employees.ResponseEmployeeDetails;
import com.thinkmobiles.easyerp.data.model.hr.employees.item.EmployeeItem;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by Lynx on 3/13/2017.
 */

public interface EmployeesService {

    @GET(Constants.GET_EMPLOYEES_ALPHABET)
    Observable<ResponseGetAlphabet> getEmployeesAlphabet();

    @GET
    Observable<ResponseGetTotalItems<EmployeeItem>> getEmployees(@Url String url);

    @GET(Constants.GET_EMPLOYEES_ALL_FOR_DB)
    Observable<ResponseGetTotalItems<EmployeeItem>> getAllEmployeesForDB();

    @GET(Constants.GET_EMPLOYEES)
    Observable<ResponseEmployeeDetails> getEmployeeDetails(@Query("id") String id);

    @GET(Constants.GET_EMPLOYEES_COUNT_FOR_DASHBOARD)
    Observable<EmployeeCountForDashboard> getEmployeesCountForDashboard(@Query("month") final int month, @Query("year") final int year);

    @GET(Constants.GET_EMPLOYEES_FOR_CHART_BY_GENDER)
    Observable<List<EmployeeGenderDepartmentInfo>> getEmployeesForChartByGender();

    @GET(Constants.GET_EMPLOYEES_FOR_CHART_BY_SALARY)
    Observable<ResponseGetTotalItems<Integer>> getEmployeesForChartBySalary(@Query("month") final int month, @Query("year") final int year);

    @GET(Constants.GET_EMPLOYEES_FOR_CHART_BY_DEPARTMENT)
    Observable<ResponseGetTotalItems<DepartmentSalary>> getEmployeesForChartByDepartment(@Query("month") final int month, @Query("year") final int year);
}
