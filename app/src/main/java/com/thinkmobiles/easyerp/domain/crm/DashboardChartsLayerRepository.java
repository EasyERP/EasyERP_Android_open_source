package com.thinkmobiles.easyerp.domain.crm;

import com.thinkmobiles.easyerp.data.api.Rest;
import com.thinkmobiles.easyerp.data.model.crm.dashboard.detail.DashboardChartType;
import com.thinkmobiles.easyerp.data.model.crm.dashboard.detail.IChartModel;
import com.thinkmobiles.easyerp.data.services.InvoiceService;
import com.thinkmobiles.easyerp.data.services.OrderService;

import org.androidannotations.annotations.EBean;

import java.util.List;

import rx.Observable;

/**
 * Created by Asus_Dev on 1/19/2017.
 */
@EBean(scope = EBean.Scope.Singleton)
class DashboardChartsLayerRepository {

    private InvoiceService invoiceService;
    private OrderService orderService;

    DashboardChartsLayerRepository() {
        invoiceService = Rest.getInstance().getInvoiceService();
        orderService = Rest.getInstance().getOrderService();
    }

    Observable<?> getDashboardChartObservable(
            final String dataSet,
            final DashboardChartType chartType,
            final String filterDateFrom,
            final String filterDateTo,
            final boolean forSales) {
        switch (dataSet) {
            case "totalSalesRevenue":
                switch (chartType) {
                    case OVERVIEW: return invoiceService.getInvoiceByWorkflows(filterDateFrom, filterDateTo, forSales, "list", 5, -1, "invoice");
                    case TABLE: return invoiceService.getInvoice(filterDateFrom, filterDateTo, forSales, "list", 5, -1, "invoice");
                }
                break;
            case "revenueBySales":
                switch (chartType) {
                    case HORIZONTALBAR: return invoiceService.getInvoiceBySales(filterDateFrom, filterDateTo, forSales);
                }
                break;
            case "revenueByCustomer":
                switch (chartType) {
                    case DONUT: return invoiceService.getInvoiceByCustomer(filterDateFrom, filterDateTo, forSales);
                }
                break;
            case "purchaseOrders":
                switch (chartType) {
                    case OVERVIEW: return orderService.getOrderByWorkflows(filterDateFrom, filterDateTo, forSales);
                }
                break;
            case "totalPurchaseRevenue":
                switch (chartType) {
                    case OVERVIEW: return invoiceService.getInvoiceByWorkflows(filterDateFrom, filterDateTo, forSales, "list", 5, -1, "purchaseInvoices");
                    case TABLE: return invoiceService.getInvoice(filterDateFrom, filterDateTo, forSales, "list", 5, -1, "purchaseInvoices");
                }
                break;
            case "purchaseRevenueBySales":
                switch (chartType) {
                    case HORIZONTALBAR: return invoiceService.getInvoiceByCountry(filterDateFrom, filterDateTo, forSales);
                }
                break;
            case "purchaseRevenueByCustomer":
                switch (chartType) {
                    case DONUT: return invoiceService.getInvoiceByCustomer(filterDateFrom, filterDateTo, forSales);
                }
                break;
            case "orders":
                switch (chartType) {
                    case OVERVIEW: return orderService.getOrderByWorkflows(filterDateFrom, filterDateTo, forSales);
                }
                break;
            case "pastDueInvoices":
                switch (chartType) {
                    case TABLE: return invoiceService.getInvoice(filterDateFrom, filterDateTo, forSales, "list", 5, -1, "invoice");
                }
                break;
            case "purchaseRevenueByCountry":
                switch (chartType) {
                    case DONUT: return invoiceService.getInvoiceBySales(filterDateFrom, filterDateTo, forSales);
                }
                break;
            case "revenueByCountry":
                switch (chartType) {
                    case DONUT: return invoiceService.getInvoiceByCountry(filterDateFrom, filterDateTo, forSales);
                }
                break;
            case "pastDuePurchaseInvoices":
                switch (chartType) {
                    case TABLE: return invoiceService.getInvoice(filterDateFrom, filterDateTo, forSales, "list", 5, -1, "purchaseInvoices");
                }
                break;
        }
        return null;
    }

}
