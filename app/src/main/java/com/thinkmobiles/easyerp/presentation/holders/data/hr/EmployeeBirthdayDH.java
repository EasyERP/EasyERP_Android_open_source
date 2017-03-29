package com.thinkmobiles.easyerp.presentation.holders.data.hr;

import com.thinkmobiles.easyerp.data.model.hr.birthdays.EmployeeWithBirthday;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectableDHHelper;

/**
 * @author Michael Soyma (Created on 3/28/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public class EmployeeBirthdayDH extends SelectableDHHelper {

    private EmployeeWithBirthday employeeWithBirthday;
    private String base64Image;
    private String headerTitle;

    public EmployeeBirthdayDH(EmployeeWithBirthday employeeWithBirthday, String base64Image) {
        this.employeeWithBirthday = employeeWithBirthday;
        this.base64Image = base64Image;
    }

    public EmployeeBirthdayDH(EmployeeWithBirthday employeeWithBirthday, String base64Image, String headerTitle) {
        this.employeeWithBirthday = employeeWithBirthday;
        this.base64Image = base64Image;
        this.headerTitle = headerTitle;
    }

    public EmployeeWithBirthday getEmployeeWithBirthday() {
        return employeeWithBirthday;
    }

    public String getBase64Image() {
        return base64Image;
    }

    public String getHeaderTitle() {
        return headerTitle;
    }

    @Override
    public String getId() {
        return employeeWithBirthday.id;
    }
}
