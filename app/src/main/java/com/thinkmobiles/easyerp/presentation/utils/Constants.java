package com.thinkmobiles.easyerp.presentation.utils;

/**
 * Created by Lynx on 1/13/2017.
 */

public abstract class Constants {
    public static final String BASE_URL                     = "https://testdemo.easyerp.com/";
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
    //End User

    //------------------------------------------------------ CRM

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
    public static final String GET_INVOICE_BY_WORKFLOWS     = "invoice/getInvoiceByWorkflows";
    public static final String GET_REVENUE_BY_SALES         = "invoice/revenueBySales";
    public static final String GET_REVENUE_BY_CUSTOMER      = "invoice/revenueByCustomer";
    public static final String GET_REVENUE_BY_COUNTRY       = "invoice/revenueByCountry";
    //End Invoice

    //Order
    public static final String GET_ORDER                    = "order";
    public static final String GET_ORDER_BY_WORKFLOWS       = "order/getByWorkflows";
    //End Order

    //Payments
    public static final String GET_PAYMENTS                 = "payments";
    //End Payments

    //Filters
    public static final String GET_FILTER                   = "filter";
    //End Filters

    //------------------------------------------------------ END CRM

    public static final int DELAY_CLICK                     = 600;

    //Request codes
    public static final int REQUEST_CODE_FILTER_CONTACT_NAME = 15;
    public static final int REQUEST_CODE_FILTER_WORKFLOW    = 16;
    public static final int REQUEST_CODE_FILTER_ASSIGNED_TO = 17;
    public static final int REQUEST_CODE_FILTER_CREATED_BY  = 18;
    public static final int REQUEST_CODE_FILTER_SOURCE      = 19;

    //Bundle keys
    public static final String KEY_FILTER_LIST              = "keyFilterList";
    public static final String KEY_FILTER_NAME              = "keyFilterName";

    public enum ErrorCodes {
        OK,
        FIELD_EMPTY,
        FIELD_INVALID
    }

    public enum ProgressType {
        CENTER, BOTTOM, NONE
    }

    public static final String DEMO_LOGIN                   = "superAdmin";
    public static final String DEMO_PASSWORD                = "111111";
    public static final String DEMO_DB_ID                   = "CRM";
}
