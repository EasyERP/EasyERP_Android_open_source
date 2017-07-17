package com.thinkmobiles.easyerp.presentation.utils;

import static com.thinkmobiles.easyerp.BuildConfig.PRODUCTION;

/**
 * Created by Lynx on 1/13/2017.
 */

public abstract class Constants {
    public static final String UXCAM_ACCOUNT_KEY            = "d2bf58cf5b3e5a0";

    private static final String BASE_TEST_URL               = "https://testdemo.easyerp.com/";
    private static final String BASE_RELEASE_URL            = "https://live.easyerp.com/";
    public static final String BASE_URL                     = PRODUCTION ? BASE_RELEASE_URL : BASE_TEST_URL;

    public static final String PRIVACY_POLICY               = "https://easyerp.com/privacy-policy/?content=show";
    public static final String TERMS_AND_CONDITIONS         = "https://easyerp.com/terms-and-conditions/?content=show";

    public static final String HEADER_SET_COOKIE            = "Set-Cookie";
    public static final String HEADER_COOKIE                = "Cookie";

    //------------------------------------------------------ LINKED IN

    public static final String GET_LINKEDIN_PROFILE         = "https://api.linkedin.com/v1/people/~:(id,first-name,last-name,email-address,location,public-profile-url)";

    //------------------------------------------------------ END LINKED IN

    //Login
    public static final String POST_LOGIN                   = "users/login";
    public static final String POST_LOGIN_SOCIAL            = PRODUCTION ? "saas/social" : "users/login/social";
    public static final String POST_SIGN_UP                 = PRODUCTION ? "saas" : "users/signUp";
    public static final String POST_UPDATE_USER             = "users/{userId}";
    public static final String POST_FORGOT_PASSWORD         = "users/forgotPassword";
    //End Login

    //User
    public static final String GET_CURRENT_USER             = "users/current";
    public static final String GET_ORGANIZATION_SETTINGS    = "organizationSettings";
    public static final String GET_LOGOUT                   = "logout";
    public static final String PATCH_CHANGE_PASSWORD        = "users/current/{userId}";
    //End User

    //Vacation
    public static final String GET_VACATION_BY_STATISTIC    = "vacation/getStatistic";
    public static final String GET_VACATION_YEARS           = "vacation/getYears";
    public static final String GET_VACATION                 = "vacation";
    //End Vacation

    //------------------------------------------------------ CRM

    public static final String CRM_DASHBOARD_BASE_ID        = "582bfabf5a43a4bc2524bf09";
    public static final String GET_DASHBOARD_CHARTS         = "dashboards/{dashboardId}";

    //Leads
    public static final String GET_LEADS                    = "leads";
    //End Leads

    //Persons
    public static final String GET_PERSONS_ALPHABET         = "persons/getPersonAlphabet";
    public static final String GET_CUSTOMER_IMAGES          = "customers/getCustomersImages";
    public static final String GET_PERSONS                  = "persons";
    public static final String GET_PERSON_DETAILS           = "persons/{PersonId}";
    //End Persons

    //Opportunities
    public static final String GET_OPPORTUNITIES            = "opportunities";
    public static final String GET_OPPORTUNITY_DETAILS      = "opportunities/{OpportunityID}";
    //End Opportunities

    //Companies
    public static final String GET_COMPANIES_ALPHABET       = "customers/getCompaniesAlphabet";
    public static final String GET_COMPANIES                = "companies";
    public static final String GET_COMPANY_DETAILS          = "companies/{companyID}";
    //End Companies

    //Invoice
    public static final String GET_INVOICE                  = "invoice";
    public static final String GET_PURCHASE_INVOICE         = "purchaseInvoices";
    public static final String GET_INVOICE_BY_WORKFLOWS     = "invoice/getInvoiceByWorkflows";
    public static final String GET_REVENUE_BY_SALES         = "invoice/revenueBySales";
    public static final String GET_REVENUE_BY_CUSTOMER      = "invoice/revenueByCustomer";
    public static final String GET_REVENUE_BY_COUNTRY       = "invoice/revenueByCountry";
    //End Invoice

    //Order
    public static final String GET_ORDER                    = "order";
    public static final String GET_PURCHASE_ORDER           = "purchaseOrders";
    public static final String GET_ORDER_BY_WORKFLOWS       = "order/getByWorkflows";
    //End Order

    //Payments
    public static final String GET_PAYMENTS                 = "payments";
    //End Payments

    //Filters
    public static final String GET_FILTER                   = "filter";
    //End Filters

    //------------------------------------------------------ END CRM


    //------------------------------------------------------ INVENTORY

    //Products
    public static final String GET_INVENTORY_PRODUCTS       = "products";
    public static final String GET_PRODUCT_DETAILS          = "products/{id}";
    public static final String GET_PRODUCT_STOCK_INVENTORY  = "products/stockInventory/{id}";
    public static final String GET_PRODUCT_CHANNELS         = "channels";
    public static final String GET_PRODUCT_CATEGORIES       = "category";
    public static final String GET_PRODUCT_TYPES            = "products/getProductsTypeForDd";
    //End Products

    //GoodsOutNotes
    public static final String GET_GOODS_OUT_NOTES          = "goodsOutNotes";
    public static final String GET_GOODS_OUT_NOTES_DETAILS  = "goodsOutNotes/{id}";
    //End GoodsOutNotes

    //StockReturn
    public static final String GET_STOCK_RETURNS            = "stockReturns";
    public static final String GET_STOCK_RETURNS_DETAILS    = "stockReturns/{id}";
    //End StockReturn

    //Transactions
    public static final String GET_TRANSFERS                = "stockTransactions";
    public static final String GET_TRANSFERS_DETAILS        = "stockTransactions/{transferID}";
    //End Transactions

    //StockCorrection
    public static final String GET_STOCK_CORRECTION         = "warehouse/stockCorrection";
    public static final String GET_STOCK_CORRECTION_DETAILS = "warehouse/stockCorrection/{id}";
    //End StockCorrection

    //------------------------------------------------------ END INVENTORY

    //------------------------------------------------------ HR

    //Employees
    public static final String GET_EMPLOYEES_IMAGES                     = "employees/getEmployeesImages";
    public static final String GET_EMPLOYEES_ALPHABET                   = "employees/getEmployeesAlphabet";
    public static final String GET_EMPLOYEES_ALL_FOR_DB                 = "employees/getForDd";
    public static final String GET_EMPLOYEES_COUNT_FOR_DASHBOARD        = "employees/getEmployeesCountForDashboard";
    public static final String GET_EMPLOYEES_FOR_CHART_BY_GENDER        = "employees/EmployeesForChart";
    public static final String GET_EMPLOYEES_FOR_CHART_BY_SALARY        = "employees/getSalaryForChart";
    public static final String GET_EMPLOYEES_FOR_CHART_BY_DEPARTMENT    = "employees/getSalaryByDepartment";
    public static final String GET_EMPLOYEES_BIRTHDAYS                  = "employees/birthdays";
    public static final String GET_EMPLOYEES                            = "employees";
    //End Employees

    //Applications
    public static final String GET_APPLICATIONS             = "applications";
    public static final String GET_PAYROLL_TYPES            = "payrollStructureTypes/forDd";
    //End Applications

    //Job Positions
    public static final String GET_JOB_POSITIONS            = "JobPositions";
    //End Job Positions

    //------------------------------------------------------ END HR

    //------------------------------------------------------ REPORTS

    public static final String GET_REPORTS                  = "reports";
    public static final String GET_REPORTS_FAVORITE         = "reports/favorite/{reportId}";
    public static final String GET_REPORTS_UNFAVORITE       = "reports/unfavorite/{reportId}";

    public static final String KEY_QUERY_REPORT_CATEGORY    = "reportCategory[]";

    //------------------------------------------------------ END REPORTS

    //------------------------------------------------------ INTEGRATIONS

    public static final String GET_CHANNELS                 = "channels";
    public static final String GET_CHANNELS_BY_NAME         = "channels/{channelName}";
    public static final String PATCH_CHANNEL_CONNECT_STATE  = "channels/{channelId}";

    //------------------------------------------------------ END INTEGRATIONS

    public static final int COUNT_LIST_ITEMS                    = 25;
    public static final int COUNT_LIST_ITEMS_WITHOUT_PAGINATION = Integer.MAX_VALUE;
    public static final int DELAY_CLICK                         = 600;

    //Bundle keys
    public static final String KEY_FILTER_LIST              = "keyFilterList";
    public static final String KEY_REPORT_TYPES_LIST        = "keyReportTypesList";
    public static final String KEY_CHANNEL                  = "keyChannel";

    public enum ErrorCode {
        OK,
        FIELD_EMPTY,
        INVALID_CHARS,
        INVALID_EMAIL,
        SHORTNESS,
        WEAK_PASSWORD,
        INVALID_PHONE,
        INVALID_SITE
    }

    public enum ProgressType {
        CENTER, BOTTOM, NONE
    }

    public static final String DB_TEST_ID                   = "ishtvandb";

    public enum ErrorType {
        LIST_EMPTY, NETWORK, UNKNOWN
    }

    public static class RequestCodes {
        public static final int RC_CHOOSE_REPORT_TYPES      = 8001;
        public static final int RC_GOOGLE_SIGN_IN           = 8002;
    }

    public static class Actions {
        public static final String ACTION_UPDATE_CHANNEL    = "action_update_channel";
    }
}
