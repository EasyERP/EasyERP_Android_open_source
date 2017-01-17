package com.thinkmobiles.easyerp.presentation.custom.views.drawer_menu.models;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.base.BaseFragment;
import com.thinkmobiles.easyerp.presentation.screens.leads.LeadsFragment_;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Asus_Dev on 1/16/2017.
 */

public abstract class MenuConfigs {

    public static List<MenuItem> menuModules = new ArrayList<>();
    public static Map<Integer, List<MenuItem>> menuModuleItems = new HashMap<>();

    static {
        menuModules.add(new MenuItem(0, R.drawable.ic_reports, "Reports"));
        menuModules.add(new MenuItem(1, R.drawable.ic_crm, "CRM"));
        menuModules.add(new MenuItem(2, R.drawable.ic_project, "Project"));
        menuModules.add(new MenuItem(3, R.drawable.ic_hr, "HR"));
        menuModules.add(new MenuItem(4, R.drawable.ic_purchases, "Purchases"));
        menuModules.add(new MenuItem(5, R.drawable.ic_accounting, "Accounting"));
        menuModules.add(new MenuItem(6, R.drawable.ic_payroll, "Payroll"));
        menuModules.add(new MenuItem(7, R.drawable.ic_expenses, "Expenses"));
        menuModules.add(new MenuItem(8, R.drawable.ic_inventory, "Inventory"));
        menuModules.add(new MenuItem(9, R.drawable.ic_settings, "Settings"));

        menuModuleItems.put(0, getReportsModule());
        menuModuleItems.put(1, getCRMModule());
        menuModuleItems.put(2, getProjectModule());
        menuModuleItems.put(3, getHRModule());
        menuModuleItems.put(4, getPurchasesModule());
        menuModuleItems.put(5, getAccountingModule());
        menuModuleItems.put(6, getPayrollModule());
        menuModuleItems.put(7, getExpensesModule());
        menuModuleItems.put(8, getInventoryModule());
        menuModuleItems.put(9, getSettingsModule());
    }

    private static List<MenuItem> getReportsModule() {
        final List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MenuItem(0, R.drawable.ic_menu_item_placeholder, "General", false));
        menuItems.add(new MenuItem(1, R.drawable.ic_menu_item_placeholder, "Products Reports", false));
        return menuItems;
    }

    private static List<MenuItem> getCRMModule() {
        final List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MenuItem(0, R.drawable.ic_menu_item_placeholder, "Dashboard", true));
        menuItems.add(new MenuItem(1, R.drawable.ic_menu_item_placeholder, "Custom Dashboard", false));
        menuItems.add(new MenuItem(2, R.drawable.ic_menu_item_placeholder, "Leads", true));
        menuItems.add(new MenuItem(3, R.drawable.ic_menu_item_placeholder, "Opportunities", true));
        menuItems.add(new MenuItem(4, R.drawable.ic_menu_item_placeholder, "Persons", true));
        menuItems.add(new MenuItem(5, R.drawable.ic_menu_item_placeholder, "Companies", true));
        menuItems.add(new MenuItem(6, R.drawable.ic_menu_item_placeholder, "Reports", true));
        menuItems.add(new MenuItem(7, R.drawable.ic_menu_item_placeholder, "Orders", true));
        menuItems.add(new MenuItem(8, R.drawable.ic_menu_item_placeholder, "Invoices", true));
        menuItems.add(new MenuItem(9, R.drawable.ic_menu_item_placeholder, "Payments", true));
        menuItems.add(new MenuItem(10, R.drawable.ic_menu_item_placeholder, "Invoice Aging", false));
        menuItems.add(new MenuItem(11, R.drawable.ic_menu_item_placeholder, "Tasks", false));
        return menuItems;
    }

    private static List<MenuItem> getProjectModule() {
        final List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MenuItem(0, R.drawable.ic_menu_item_placeholder, "Dashboard", false));
        menuItems.add(new MenuItem(1, R.drawable.ic_menu_item_placeholder, "Projects", false));
        menuItems.add(new MenuItem(2, R.drawable.ic_menu_item_placeholder, "Planing", false));
        menuItems.add(new MenuItem(3, R.drawable.ic_menu_item_placeholder, "Contract Jobs", false));
        menuItems.add(new MenuItem(4, R.drawable.ic_menu_item_placeholder, "Tasks", false));
        menuItems.add(new MenuItem(5, R.drawable.ic_menu_item_placeholder, "Jobs Dashboard", false));
        menuItems.add(new MenuItem(6, R.drawable.ic_menu_item_placeholder, "Inventory Report", false));
        menuItems.add(new MenuItem(7, R.drawable.ic_menu_item_placeholder, "Time Card", false));
        return menuItems;
    }

    private static List<MenuItem> getHRModule() {
        final List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MenuItem(0, R.drawable.ic_menu_item_placeholder, "Employees", false));
        menuItems.add(new MenuItem(1, R.drawable.ic_menu_item_placeholder, "Applications", false));
        menuItems.add(new MenuItem(2, R.drawable.ic_menu_item_placeholder, "Job Positions", false));
        menuItems.add(new MenuItem(3, R.drawable.ic_menu_item_placeholder, "Birthdays", false));
        menuItems.add(new MenuItem(4, R.drawable.ic_menu_item_placeholder, "Vacations", false));
        menuItems.add(new MenuItem(5, R.drawable.ic_menu_item_placeholder, "Attendance", false));
        menuItems.add(new MenuItem(6, R.drawable.ic_menu_item_placeholder, "HR Dashboard", false));
        return menuItems;
    }

    private static List<MenuItem> getPurchasesModule() {
        final List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MenuItem(0, R.drawable.ic_menu_item_placeholder, "Orders", false));
        menuItems.add(new MenuItem(1, R.drawable.ic_menu_item_placeholder, "Invoices", false));
        menuItems.add(new MenuItem(2, R.drawable.ic_menu_item_placeholder, "Payments", false));
        return menuItems;
    }

    private static List<MenuItem> getAccountingModule() {
        final List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MenuItem(0, R.drawable.ic_menu_item_placeholder, "Manual Entry", false));
        menuItems.add(new MenuItem(1, R.drawable.ic_menu_item_placeholder, "Chart of Account", false));
        menuItems.add(new MenuItem(2, R.drawable.ic_menu_item_placeholder, "Journal", false));
        menuItems.add(new MenuItem(3, R.drawable.ic_menu_item_placeholder, "Journal Entry", false));
        menuItems.add(new MenuItem(4, R.drawable.ic_menu_item_placeholder, "Balance Sheet", false));
        menuItems.add(new MenuItem(5, R.drawable.ic_menu_item_placeholder, "Cash Flow", false));
        menuItems.add(new MenuItem(6, R.drawable.ic_menu_item_placeholder, "Cash Month", false));
        menuItems.add(new MenuItem(7, R.drawable.ic_menu_item_placeholder, "Profit and Loss", false));
        menuItems.add(new MenuItem(8, R.drawable.ic_menu_item_placeholder, "Trial Balance", false));
        menuItems.add(new MenuItem(9, R.drawable.ic_menu_item_placeholder, "Cash Book", false));
        menuItems.add(new MenuItem(10, R.drawable.ic_menu_item_placeholder, "Cash Transfer", false));
        return menuItems;
    }

    private static List<MenuItem> getPayrollModule() {
        final List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MenuItem(0, R.drawable.ic_menu_item_placeholder, "Payout", false));
        menuItems.add(new MenuItem(1, R.drawable.ic_menu_item_placeholder, "Holidays", false));
        menuItems.add(new MenuItem(2, R.drawable.ic_menu_item_placeholder, "Salary Report", false));
        menuItems.add(new MenuItem(3, R.drawable.ic_menu_item_placeholder, "Bonus Type", false));
        menuItems.add(new MenuItem(4, R.drawable.ic_menu_item_placeholder, "Payroll Expenses", false));
        menuItems.add(new MenuItem(5, R.drawable.ic_menu_item_placeholder, "Payroll Payments", false));
        menuItems.add(new MenuItem(6, R.drawable.ic_menu_item_placeholder, "Dividend Declarations", false));
        menuItems.add(new MenuItem(7, R.drawable.ic_menu_item_placeholder, "Dividend Payments", false));
        return menuItems;
    }

    private static List<MenuItem> getExpensesModule() {
        final List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MenuItem(0, R.drawable.ic_menu_item_placeholder, "Cost Center", false));
        menuItems.add(new MenuItem(1, R.drawable.ic_menu_item_placeholder, "Expenses", false));
        menuItems.add(new MenuItem(2, R.drawable.ic_menu_item_placeholder, "Expenses Payments", false));
        menuItems.add(new MenuItem(3, R.drawable.ic_menu_item_placeholder, "Write Off", false));
        return menuItems;
    }

    private static List<MenuItem> getInventoryModule() {
        final List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MenuItem(0, R.drawable.ic_menu_item_placeholder, "Products", false));
        menuItems.add(new MenuItem(1, R.drawable.ic_menu_item_placeholder, "Goods-out Notes", false));
        menuItems.add(new MenuItem(2, R.drawable.ic_menu_item_placeholder, "Stock Correction", false));
        menuItems.add(new MenuItem(3, R.drawable.ic_menu_item_placeholder, "Transactions", false));
        menuItems.add(new MenuItem(4, R.drawable.ic_menu_item_placeholder, "Stock Detail", false));
        return menuItems;
    }

    private static List<MenuItem> getSettingsModule() {
        final List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MenuItem(0, R.drawable.ic_menu_item_placeholder, "General", false));
        menuItems.add(new MenuItem(1, R.drawable.ic_menu_item_placeholder, "Organization Profile", false));
        menuItems.add(new MenuItem(2, R.drawable.ic_menu_item_placeholder, "Accounting", false));
        menuItems.add(new MenuItem(3, R.drawable.ic_menu_item_placeholder, "Products", false));
        menuItems.add(new MenuItem(4, R.drawable.ic_menu_item_placeholder, "Employees Details", false));
        menuItems.add(new MenuItem(5, R.drawable.ic_menu_item_placeholder, "Integrations", false));
        menuItems.add(new MenuItem(6, R.drawable.ic_menu_item_placeholder, "Access Profiles", false));
        menuItems.add(new MenuItem(7, R.drawable.ic_menu_item_placeholder, "System Users", false));
        menuItems.add(new MenuItem(8, R.drawable.ic_menu_item_placeholder, "Departments", false));
        menuItems.add(new MenuItem(9, R.drawable.ic_menu_item_placeholder, "Workflows", false));
        return menuItems;
    }

    public static BaseFragment getFragmentByMenuId(final int moduleId, final int itemId) {
        switch (moduleId) {
            case 1: return getFragmentByIdWithCRMModule(itemId);
        }
        return null;
    }

    private static BaseFragment getFragmentByIdWithCRMModule(final int itemId) {
        switch (itemId) {
           case 0: return null;
           case 1: return null;
           case 2: return LeadsFragment_.builder().build();
           case 3: return null;
           case 4: return null;
           case 5: return null;
           case 6: return null;
           case 7: return null;
           case 8: return null;
           case 9: return null;
           case 10: return null;
           case 11: return null;
        }
        return null;
    }

}
