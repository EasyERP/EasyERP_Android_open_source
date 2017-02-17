package com.thinkmobiles.easyerp.data.api;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thinkmobiles.easyerp.data.api.deserializers.FilterDeserializer;
import com.thinkmobiles.easyerp.data.api.interceptors.AddCookieInterceptor;
import com.thinkmobiles.easyerp.data.api.interceptors.BadCookieInterceptor;
import com.thinkmobiles.easyerp.data.api.interceptors.ReceiveCookieInterceptor;
import com.thinkmobiles.easyerp.data.model.ResponseError;
import com.thinkmobiles.easyerp.data.model.crm.filter.ResponseFilters;
import com.thinkmobiles.easyerp.data.services.CompaniesService;
import com.thinkmobiles.easyerp.data.services.CustomerService;
import com.thinkmobiles.easyerp.data.services.DashboardService;
import com.thinkmobiles.easyerp.data.services.FilterService;
import com.thinkmobiles.easyerp.data.services.InvoiceService;
import com.thinkmobiles.easyerp.data.services.LeadService;
import com.thinkmobiles.easyerp.data.services.LoginService;
import com.thinkmobiles.easyerp.data.services.OpportunityService;
import com.thinkmobiles.easyerp.data.services.OrderService;
import com.thinkmobiles.easyerp.data.services.PaymentsService;
import com.thinkmobiles.easyerp.data.services.PersonsService;
import com.thinkmobiles.easyerp.data.services.UserService;
import com.thinkmobiles.easyerp.presentation.managers.CookieManager;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
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
    private Retrofit retrofitFull;
    private ReceiveCookieInterceptor receiveCookieInterceptor;
    private AddCookieInterceptor addCookieInterceptor;
    private BadCookieInterceptor badCookieInterceptor;

    private LoginService loginService;
    private LeadService leadService;
    private UserService userService;
    private DashboardService dashboardService;
    private InvoiceService invoiceService;
    private OrderService orderService;
    private PaymentsService paymentsService;
    private PersonsService personsService;
    private OpportunityService opportunityService;
    private FilterService filterService;
    private CompaniesService companiesService;
    private CustomerService customerService;

    private Converter<ResponseBody, ResponseError> converter;

    private Rest() {
        Gson malformedGson = new GsonBuilder()
                .setLenient()
                .registerTypeAdapter(ResponseFilters.class, new FilterDeserializer())
                .create();

        receiveCookieInterceptor = new ReceiveCookieInterceptor();
        addCookieInterceptor = new AddCookieInterceptor();
        badCookieInterceptor = new BadCookieInterceptor();

        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .addInterceptor(receiveCookieInterceptor);

        Retrofit.Builder builder = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(malformedGson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(Constants.BASE_URL)
                .client(clientBuilder.build());

        retrofit = builder.build();

        clientBuilder.addInterceptor(addCookieInterceptor)
                .addInterceptor(badCookieInterceptor);

        retrofitFull = builder
                .client(clientBuilder.build())
                .build();

        converter = retrofitFull.responseBodyConverter(ResponseError.class, new Annotation[0]);
    }

    public static Rest getInstance() {
        if (restInstance == null) restInstance = new Rest();
        return restInstance;
    }

    public LoginService getLoginService() {
        return loginService == null ? createService(LoginService.class, false) : loginService;
    }

    public UserService getUserService() {
        if (userService == null) {
            userService = createService(UserService.class);
        }
        return userService;
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

    public PaymentsService getPaymentService() {
        return paymentsService == null ? createService(PaymentsService.class) : paymentsService;
    }

    public PersonsService getPersonsService() {
        return personsService == null ? createService(PersonsService.class) : personsService;
    }

    public OpportunityService getOpportunityService() {
        if (opportunityService == null) {
            opportunityService = createService(OpportunityService.class);
        }
        return opportunityService;
    }

    public CompaniesService getCompaniesService() {
        return companiesService == null ? createService(CompaniesService.class) : companiesService;
    }

    public CustomerService getCustomerService() {
        return customerService == null ? createService(CustomerService.class) : customerService;
    }

    public FilterService getFilterService() {
        if (filterService == null) {
            filterService = createService(FilterService.class);
        }
        return filterService;
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
        return hasAllInterceptors ? retrofitFull.create(service) : retrofit.create(service);
    }

    public void setCookieManager(CookieManager cookieManager) {
        receiveCookieInterceptor.setCookieManager(cookieManager);
        addCookieInterceptor.setCookieManager(cookieManager);
        badCookieInterceptor.setCookieManager(cookieManager);
    }

}
