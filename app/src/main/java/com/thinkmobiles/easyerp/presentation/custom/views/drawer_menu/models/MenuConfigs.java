package com.thinkmobiles.easyerp.presentation.custom.views.drawer_menu.models;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.base.BaseMasterFlowFragment;
import com.thinkmobiles.easyerp.presentation.screens.crm.companies.CompaniesFragment_;
import com.thinkmobiles.easyerp.presentation.screens.crm.dashboard.DashboardListFragment_;
import com.thinkmobiles.easyerp.presentation.screens.crm.invoices.InvoicesFragment_;
import com.thinkmobiles.easyerp.presentation.screens.crm.leads.LeadsFragment_;
import com.thinkmobiles.easyerp.presentation.screens.crm.opportunities.OpportunitiesFragment_;
import com.thinkmobiles.easyerp.presentation.screens.crm.orders.OrdersFragment_;
import com.thinkmobiles.easyerp.presentation.screens.crm.payments.PaymentsFragment_;
import com.thinkmobiles.easyerp.presentation.screens.crm.persons.PersonsFragment_;
import com.thinkmobiles.easyerp.presentation.screens.inventory.stock_corrections.StockCorrectionsListFragment_;
import com.thinkmobiles.easyerp.presentation.screens.inventory.stock_returns.StockReturnsListFragment_;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author michael.soyma@thinkmobiles.com (Created on 1/16/2017.)
 */

public abstract class MenuConfigs {

    public static List<MenuItem> menuModules = new ArrayList<>();
    public static Map<Integer, List<MenuItem>> menuModuleItems = new HashMap<>();

    static {
        menuModules.add(new MenuItem(0, R.drawable.ic_reports, "Reports", false));
        menuModules.add(new MenuItem(1, R.drawable.ic_crm, "CRM", true));
        menuModules.add(new MenuItem(2, R.drawable.ic_project, "Project", false));
        menuModules.add(new MenuItem(3, R.drawable.ic_hr, "HR", false));
        menuModules.add(new MenuItem(4, R.drawable.ic_purchases, "Purchases", true));
        menuModules.add(new MenuItem(5, R.drawable.ic_accounting, "Accounting", false));
        menuModules.add(new MenuItem(6, R.drawable.ic_payroll, "Payroll", false));
        menuModules.add(new MenuItem(7, R.drawable.ic_expenses, "Expenses", false));
        menuModules.add(new MenuItem(8, R.drawable.ic_inventory, "Inventory", true));
        menuModules.add(new MenuItem(9, R.drawable.ic_settings, "Settings", false));

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

    public static MenuItem getMenuModuleById(final int moduleId) {
        for (MenuItem module: menuModules)
            if (module.getId() == moduleId)
                return module;
        return null;
    }

    private static List<MenuItem> getReportsModule() {
        final List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MenuItem(0, R.drawable.ic_menu_item_placeholder, "General", false));
        menuItems.add(new MenuItem(1, R.drawable.ic_menu_item_placeholder, "Products Reports", false));
        return menuItems;
    }

    private static List<MenuItem> getCRMModule() {
        final List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MenuItem(0, R.drawable.ic_dashboard, "Dashboard", true));
//        menuItems.add(new MenuItem(1, R.drawable.ic_menu_item_placeholder, "Custom Dashboard", false));
        menuItems.add(new MenuItem(2, R.drawable.ic_leads, "Leads", true));
        menuItems.add(new MenuItem(3, R.drawable.ic_opportunities, "Opportunities", true));
        menuItems.add(new MenuItem(4, R.drawable.ic_persons, "Persons", true));
        menuItems.add(new MenuItem(5, R.drawable.ic_companies, "Companies", true));
        menuItems.add(new MenuItem(6, R.drawable.ic_reports_crm, "Reports", false));
        menuItems.add(new MenuItem(7, R.drawable.ic_orders, "Orders", true));
        menuItems.add(new MenuItem(8, R.drawable.ic_invoices, "Invoices", true));
        menuItems.add(new MenuItem(9, R.drawable.ic_paymens, "Payments", true));
        menuItems.add(new MenuItem(10, R.drawable.ic_invoice_aging, "Invoice Aging", false));
        menuItems.add(new MenuItem(11, R.drawable.ic_tasks, "Tasks", false));
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
        menuItems.add(new MenuItem(0, R.drawable.ic_dashboard, "Dashboard", true));
        menuItems.add(new MenuItem(1, R.drawable.ic_orders, "Orders", true));
        menuItems.add(new MenuItem(2, R.drawable.ic_invoices, "Invoices", true));
        menuItems.add(new MenuItem(3, R.drawable.ic_paymens, "Payments", true));
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
        menuItems.add(new MenuItem(1, R.drawable.ic_menu_item_placeholder, "Goods-Out Notes", false));
        menuItems.add(new MenuItem(2, R.drawable.ic_menu_item_placeholder, "Stock Returns", true));
        menuItems.add(new MenuItem(3, R.drawable.ic_menu_item_placeholder, "Stock Correction", true));
        menuItems.add(new MenuItem(4, R.drawable.ic_menu_item_placeholder, "Transfers", false));
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

    public static BaseMasterFlowFragment getFragmentByMenuId(final int moduleId, final int itemId) {
        switch (moduleId) {
            case 1: return getFragmentByIdWithCRMModule(itemId);
            case 4: return getFragmentByIdWithPurchaseModule(itemId);
            case 8: return getFragmentByIdWithInventoryModule(itemId);
        }
        return null;
    }

    public static String getModuleLabel(final int moduleId) {
        for (MenuItem item: menuModules)
            if (item.getId() == moduleId)
                return item.getLabel();
        return null;
    }

    public static String getItemLabel(final int moduleId, final int itemId) {
        for (MenuItem item: menuModuleItems.get(moduleId))
            if (item.getId() == itemId)
                return item.getLabel();
        return null;
    }

    private static BaseMasterFlowFragment getFragmentByIdWithCRMModule(final int itemId) {
        final int crmModuleId = 1;
        switch (itemId) {
           case 0: return DashboardListFragment_.builder().moduleId(crmModuleId).build();
           case 1: return null;
           case 2: return LeadsFragment_.builder().build();
           case 3: return OpportunitiesFragment_.builder().build();
           case 4: return PersonsFragment_.builder().build();
           case 5: return CompaniesFragment_.builder().build();
           case 6: return null;
           case 7: return OrdersFragment_.builder().moduleId(crmModuleId).build();
           case 8: return InvoicesFragment_.builder().moduleId(crmModuleId).build();
           case 9: return PaymentsFragment_.builder().moduleId(crmModuleId).build();
           case 10: return null;
           case 11: return null;
        }
        return null;
    }

    private static BaseMasterFlowFragment getFragmentByIdWithPurchaseModule(final int itemId) {
        final int purchaseModuleId = 4;
        switch (itemId) {
            case 0: return DashboardListFragment_.builder().moduleId(purchaseModuleId).build();
            case 1: return OrdersFragment_.builder().moduleId(purchaseModuleId).build();
            case 2: return InvoicesFragment_.builder().moduleId(purchaseModuleId).build();
            case 3: return PaymentsFragment_.builder().moduleId(purchaseModuleId).build();
        }
        return null;
    }

    private static BaseMasterFlowFragment getFragmentByIdWithInventoryModule(final int itemId) {
        switch (itemId) {
            case 2: return StockReturnsListFragment_.builder().build();
            case 3: return StockCorrectionsListFragment_.builder().build();
        }
        return null;
    }
}
