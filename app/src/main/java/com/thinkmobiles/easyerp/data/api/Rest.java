package com.thinkmobiles.easyerp.data.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.thinkmobiles.easyerp.BuildConfig;
import com.thinkmobiles.easyerp.data.api.deserializers.FilterDeserializer;
import com.thinkmobiles.easyerp.data.api.deserializers.ProductIdDeserializer;
import com.thinkmobiles.easyerp.data.api.interceptors.AddCookieInterceptor;
import com.thinkmobiles.easyerp.data.api.interceptors.BadCookieInterceptor;
import com.thinkmobiles.easyerp.data.api.interceptors.ReceiveCookieInterceptor;
import com.thinkmobiles.easyerp.data.model.ResponseError;
import com.thinkmobiles.easyerp.data.model.crm.filter.ResponseFilters;
import com.thinkmobiles.easyerp.data.model.inventory.product.detail.ProductId;
import com.thinkmobiles.easyerp.data.services.ApplicationService;
import com.thinkmobiles.easyerp.data.services.AttendanceService;
import com.thinkmobiles.easyerp.data.services.CompaniesService;
import com.thinkmobiles.easyerp.data.services.DashboardService;
import com.thinkmobiles.easyerp.data.services.EmployeesService;
import com.thinkmobiles.easyerp.data.services.FilterService;
import com.thinkmobiles.easyerp.data.services.GoodsOutNotesService;
import com.thinkmobiles.easyerp.data.services.ImageService;
import com.thinkmobiles.easyerp.data.services.InvoiceService;
import com.thinkmobiles.easyerp.data.services.JobPositionService;
import com.thinkmobiles.easyerp.data.services.LeadService;
import com.thinkmobiles.easyerp.data.services.LoginService;
import com.thinkmobiles.easyerp.data.services.OpportunityService;
import com.thinkmobiles.easyerp.data.services.OrderService;
import com.thinkmobiles.easyerp.data.services.PaymentsService;
import com.thinkmobiles.easyerp.data.services.PersonsService;
import com.thinkmobiles.easyerp.data.services.ProductService;
import com.thinkmobiles.easyerp.data.services.ReportsService;
import com.thinkmobiles.easyerp.data.services.StockService;
import com.thinkmobiles.easyerp.data.services.TransfersService;
import com.thinkmobiles.easyerp.data.services.UserService;
import com.thinkmobiles.easyerp.data.services.VacationService;
import com.thinkmobiles.easyerp.presentation.managers.CookieManager;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;

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
    private ImageService imageService;

    private ProductService productService;
    private StockService stockService;
    private GoodsOutNotesService goodsOutNotesService;
    private TransfersService transfersService;

    private EmployeesService employeesService;
    private ApplicationService applicationService;
    private JobPositionService jobPositionService;
    private VacationService vacationService;
    private AttendanceService attendanceService;

    private ReportsService reportsService;

    private Converter<ResponseBody, ResponseError> converter;

    private Rest() {
        Gson malformedGson = new GsonBuilder()
                .setLenient()
                .registerTypeAdapter(ResponseFilters.class, new FilterDeserializer())
                .registerTypeAdapter(new TypeToken<ArrayList<ProductId>>(){}.getType(), new ProductIdDeserializer())
                .create();

        receiveCookieInterceptor = new ReceiveCookieInterceptor();
        addCookieInterceptor = new AddCookieInterceptor();
        badCookieInterceptor = new BadCookieInterceptor();

        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder().addInterceptor(receiveCookieInterceptor);
        BuildConfig.STETHO.configureInterceptor(clientBuilder);

        Retrofit.Builder builder = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(malformedGson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(Constants.BASE_URL)
                .client(clientBuilder.build());

        retrofit = builder.build();

        clientBuilder
                .addInterceptor(addCookieInterceptor)
                .addInterceptor(badCookieInterceptor);

        retrofitFull = builder
                .client(clientBuilder.build())
                .build();

        converter = retrofitFull.responseBodyConverter(ResponseError.class, new Annotation[0]);
    }

    public static Rest getInstance() {
        return restInstance == null ? restInstance = new Rest() : restInstance;
    }

    public LoginService getLoginService() {
        return loginService == null ? loginService = createService(LoginService.class, false) : loginService;
    }

    public UserService getUserService() {
        return userService == null ? userService = createService(UserService.class) : userService;
    }

    public LeadService getLeadService() {
        return leadService == null ? leadService = createService(LeadService.class) : leadService;
    }

    public DashboardService getDashboardService() {
        return dashboardService == null ? dashboardService = createService(DashboardService.class) : dashboardService;
    }

    public InvoiceService getInvoiceService() {
        return invoiceService == null ? invoiceService = createService(InvoiceService.class) : invoiceService;
    }

    public OrderService getOrderService() {
        return orderService == null ? orderService = createService(OrderService.class) : orderService;
    }

    public PaymentsService getPaymentService() {
        return paymentsService == null ? paymentsService = createService(PaymentsService.class) : paymentsService;
    }

    public PersonsService getPersonsService() {
        return personsService == null ? personsService = createService(PersonsService.class) : personsService;
    }

    public OpportunityService getOpportunityService() {
        return opportunityService == null ? opportunityService = createService(OpportunityService.class) : opportunityService;
    }

    public CompaniesService getCompaniesService() {
        return companiesService == null ? companiesService = createService(CompaniesService.class) : companiesService;
    }

    public ImageService getImageService() {
        return imageService == null ? imageService = createService(ImageService.class) : imageService;
    }

    public ProductService getProductService() {
        return productService == null ? productService = createService(ProductService.class) : productService;
    }

    public StockService getStockService() {
        return stockService == null ? stockService = createService(StockService.class) : stockService;
    }

    public FilterService getFilterService() {
        return filterService == null ? filterService = createService(FilterService.class) : filterService;
    }

    public GoodsOutNotesService getGoodsOutNotesService() {
        return goodsOutNotesService == null ? goodsOutNotesService = createService(GoodsOutNotesService.class) : goodsOutNotesService;
    }

    public TransfersService getTransfersService() {
        return transfersService == null ? transfersService = createService(TransfersService.class) : transfersService;
    }

    public ApplicationService getApplicationService() {
        return applicationService == null ? applicationService = createService(ApplicationService.class) : applicationService;
    }

    public EmployeesService getEmployeesService() {
        return employeesService == null ? employeesService = createService(EmployeesService.class) : employeesService;
    }

    public JobPositionService getJobPositionService() {
        return jobPositionService == null ? jobPositionService = createService(JobPositionService.class) : jobPositionService;
    }

    public AttendanceService getAttendanceService() {
        return attendanceService == null ? attendanceService = createService(AttendanceService.class) : attendanceService;
    }

    public VacationService getVacationService() {
        return vacationService == null ? vacationService = createService(VacationService.class) : vacationService;
    }

    public ReportsService getReportsService() {
        return reportsService == null ? reportsService = createService(ReportsService.class) : reportsService;
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
