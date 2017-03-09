package com.thinkmobiles.easyerp.presentation.utils;

import static com.thinkmobiles.easyerp.BuildConfig.PRODUCTION;

/**
 * Created by Lynx on 1/13/2017.
 */

public abstract class Constants {
    private static final String BASE_TEST_URL               = "https://testdemo.easyerp.com/";
    private static final String BASE_RELEASE_URL            = "https://demo.easyerp.com/";
    public static final String BASE_URL                     = PRODUCTION ? BASE_RELEASE_URL : BASE_TEST_URL;

    public static final String PRIVACY_POLICY               = "https://easyerp.com/privacy-policy/?content=show";
    public static final String TERMS_AND_CONDITIONS         = "https://easyerp.com/terms-and-conditions/?content=show";

    public static final String HEADER_SET_COOKIE            = "Set-Cookie";
    public static final String HEADER_COOKIE                = "Cookie";

    //Login
    public static final String POST_LOGIN                   = "users/login";
    public static final String POST_FORGOT_PASSWORD         = "users/forgotPassword";
    //End Login

    //User
    public static final String GET_CURRENT_USER             = "users/current";
    public static final String GET_ORGANIZATION_SETTINGS    = "organizationSettings";
    public static final String GET_LOGOUT                   = "logout";
    public static final String PATCH_CHANGE_PASSWORD        = "users/current/{userId}";
    //End User

    //------------------------------------------------------ CRM

    public static final int COUNT_LIST_ITEMS                = 25;
    public static final String CRM_DASHBOARD_BASE_ID        = "582bfabf5a43a4bc2524bf09";
    public static final String GET_DASHBOARD_CHARTS         = "customDashboard/{dashboardId}";

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

    //GoodsOutNotes
    public static final String GET_GOODS_OUT_NOTES          = "goodsOutNotes";
    public static final String GET_GOODS_OUT_NOTES_DETAILS  = "goodsOutNotes/{id}";
    //End GoodsOutNotes

    //StockReturn
    public static final String GET_STOCK_RETURNS            = "stockReturns";
    public static final String GET_STOCK_RETURNS_DETAILS    = "stockReturns/{id}";
    //End StockReturn

    //Transactions
    public static final String GET_TRANSACTIONS             = "stockTransactions";
    //End Transactions

    //StockCorrection
    public static final String GET_STOCK_CORRECTION         = "warehouse/stockCorrection";
    //End StockCorrection

    //------------------------------------------------------ END INVENTORY

    public static final int DELAY_CLICK                     = 600;

    //Bundle keys
    public static final String KEY_FILTER_LIST              = "keyFilterList";

    public enum ErrorCodes {
        OK,
        FIELD_EMPTY,
        INVALID_CHARS,
        SHORTNESS
    }

    public enum ProgressType {
        CENTER, BOTTOM, NONE
    }

    public static final String DEMO_LOGIN                   = "superAdmin";
    public static final String DEMO_PASSWORD                = "111111";
    public static final String DEMO_DB_ID                   = "CRM";

    public enum ErrorType {
        LIST_EMPTY, NETWORK, UNKNOWN
    }

    public static final String UXCAM_ACCOUNT_KEY            = "d2bf58cf5b3e5a0";
}
