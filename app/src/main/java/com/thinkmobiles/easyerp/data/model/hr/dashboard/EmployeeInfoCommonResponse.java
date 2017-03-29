package com.thinkmobiles.easyerp.data.model.hr.dashboard;

/**
 * @author Michael Soyma (Created on 3/22/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public final class EmployeeInfoCommonResponse {
    public EmployeeCountForDashboard employeeCountForDashboard;
    public VacationStatistic vacationStatistic;

    public EmployeeInfoCommonResponse(EmployeeCountForDashboard employeeCountForDashboard, VacationStatistic vacationStatistic) {
        this.employeeCountForDashboard = employeeCountForDashboard;
        this.vacationStatistic = vacationStatistic;
    }
}
