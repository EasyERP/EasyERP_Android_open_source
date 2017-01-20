package com.thinkmobiles.easyerp.presentation.utils;

/**
 * Created by Lynx on 1/13/2017.
 */

public abstract class Constants {
    public static final String BASE_URL                     = "http://testdemo.easyerp.com/";
    public static final String HEADER_SET_COOKIE            = "Set-Cookie";
    public static final String HEADER_COOKIE                = "Cookie";

    //Login
    public static final String POST_LOGIN                   = "users/login";
    //End Login

    //User
    public static final String GET_CURRENT_USER             = "users/current";
    //End User

    //CRM
    public static final String CRM_DASHBOARD_BASE_ID        = "582bfabf5a43a4bc2524bf09";

    public static final String GET_LEADS                    = "leads";
    public static final String GET_DASHBOARD_CHARTS         = "customDashboard/{dashboardId}";

    public static final String GET_PERSONS_ALPHABET         = "persons/getPersonAlphabet?&contentType=Persons";
    public static final String GET_CUSTOMER_IMAGES          = "customers/getCustomersImages?ids[]=587f407cab1707af208fdbca";
    public static final String GET_PERSONS                  = "persons";
    public static final String GET_ALL_PERSONS              = "persons/?viewType=list&count=50&contentType=Persons&page=1";
    public static final String GET_PERSONS_BY_LETTER        = "persons/?viewType=list&filter[letter][key]=name.first&filter[letter][value]=M&filter[letter][type]=letter&contentType=Persons&count=50&page=1";
    // End CRM

    //Invoice
    public static final String GET_INVOICE                  = "invoice";
    public static final String GET_INVOICE_BY_WORKFLOWS     = "invoice/getInvoiceByWorkflows";
    public static final String GET_REVENUE_BY_SALES         = "invoice/revenueBySales";
    public static final String GET_REVENUE_BY_CUSTOMER      = "invoice/revenueByCustomer";
    public static final String GET_REVENUE_BY_COUNTRY       = "invoice/revenueByCountry";
    //End Invoice

    //Order
    public static final String GET_ORDER_BY_WORKFLOWS       = "order/getByWorkflows";
    //End Order

    public static final int DELAY_CLICK                     = 600;

    public enum ErrorCodes {
        OK,
        FIELD_EMPTY,
        FIELD_INVALID
    }
}
