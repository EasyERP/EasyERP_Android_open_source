package com.thinkmobiles.easyerp.data.api;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thinkmobiles.easyerp.BuildConfig;
import com.thinkmobiles.easyerp.data.api.interceptors.AddCookieInterceptor;
import com.thinkmobiles.easyerp.data.api.interceptors.BadCookieInterceptor;
import com.thinkmobiles.easyerp.data.api.interceptors.ReceiveCookieInterceptor;
import com.thinkmobiles.easyerp.data.model.ResponseError;
import com.thinkmobiles.easyerp.data.services.DashboardService;
import com.thinkmobiles.easyerp.data.services.FilterService;
import com.thinkmobiles.easyerp.data.services.InvoiceService;
import com.thinkmobiles.easyerp.data.services.LeadService;
import com.thinkmobiles.easyerp.data.services.LoginService;
import com.thinkmobiles.easyerp.data.services.OpportunityService;
import com.thinkmobiles.easyerp.data.services.OrderService;
import com.thinkmobiles.easyerp.data.services.PersonsService;
import com.thinkmobiles.easyerp.data.services.UserService;
import com.thinkmobiles.easyerp.presentation.managers.CookieManager;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Lynx on 1/13/2017.
 */

public class Rest {

    private static Rest restInstance;
    private Retrofit retrofit;
    private OkHttpClient client;
    private Gson malformedGson;
    private CookieManager cookieManager;

    private LoginService loginService;
    private LeadService leadService;
    private UserService userService;
    private DashboardService dashboardService;
    private InvoiceService invoiceService;
    private OrderService orderService;
    private PersonsService personsService;
    private OpportunityService opportunityService;
    private FilterService filterService;

    private Converter<ResponseBody, ResponseError> converter;

    private Rest() {

    }

    public static Rest getInstance() {
        if(restInstance == null) restInstance = new Rest();
        return restInstance;
    }

    public LoginService getLoginService() {
        return loginService == null ? createService(LoginService.class, false) : loginService;
    }

    public UserService getUserService() {
        return userService == null ? createService(UserService.class) : userService;
    }

    public LeadService getLeadService() {
        return leadService == null ? createService(LeadService.class) : leadService;
    }

    public DashboardService getDashboardService() {
        return dashboardService == null ? createService(DashboardService.class) : dashboardService;
    }

    public InvoiceService getInvoiceService() {
        return invoiceService == null ? createService(InvoiceService.class) : invoiceService;
    }

    public OrderService getOrderService() {
        return orderService == null ? createService(OrderService.class) : orderService;
    }

    public PersonsService getPersonsService() {
        return personsService == null ? createService(PersonsService.class) : personsService;
    }

    public FilterService getFilterService() {
        if (filterService == null) {
            filterService = createService(FilterService.class);
        }
        return filterService;
    }

    public OpportunityService getOpportunityService() {
        return opportunityService == null ? createService(OpportunityService.class) : opportunityService;
    }

    public ResponseError parseError(ResponseBody responseBody) {
        try {
            return converter.convert(responseBody);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private <T> T createService(Class<T> service) {
        return createService(service, true);
    }

    private <T> T createService(Class<T> service, boolean hasAllInterceptors) {
        malformedGson = new GsonBuilder()
                .setLenient()
                .create();

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(!BuildConfig.PRODUCTION ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);


        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .addInterceptor(loggingInterceptor)
                .addInterceptor(new ReceiveCookieInterceptor(cookieManager));

        if(hasAllInterceptors) {
            clientBuilder.addInterceptor(new AddCookieInterceptor(cookieManager))
                    .addInterceptor(new BadCookieInterceptor(cookieManager));
        }

        client = clientBuilder.build();
        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(malformedGson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .baseUrl(Constants.BASE_URL)
                .build();
        converter = retrofit.responseBodyConverter(ResponseError.class, new Annotation[0]);
        return retrofit.create(service);
    }

    public void setCookieManager(CookieManager cookieManager) {
        this.cookieManager = cookieManager;
    }

}
