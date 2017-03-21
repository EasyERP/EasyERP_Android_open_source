package com.thinkmobiles.easyerp.domain;

import com.thinkmobiles.easyerp.domain.crm.DashboardRepository;
import com.thinkmobiles.easyerp.domain.crm.InvoiceRepository;
import com.thinkmobiles.easyerp.domain.crm.OrderRepository;
import com.thinkmobiles.easyerp.domain.crm.PaymentsRepository;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

/**
 * @author Alex Michenko (Created on 01.03.17).
 *         Company: Thinkmobiles
 *         Email: alex.michenko@thinkmobiles.com
 */

public abstract class DomainHelper {

    public static InvoiceRepository getInvoiceRepository(int moduleId) {
        switch (moduleId) {
            case 4:
                return new InvoiceRepository(Constants.GET_PURCHASE_INVOICE, "purchaseInvoices");
            case 1:
            default:
                return new InvoiceRepository(Constants.GET_INVOICE, "invoice");
        }
    }

    public static OrderRepository getOrderRepository(int moduleId) {
        switch (moduleId) {
            case 4:
                return new OrderRepository(Constants.GET_PURCHASE_ORDER, "purchaseOrders");
            case 1:
            default:
                return new OrderRepository(Constants.GET_ORDER, "order");
        }
    }

    public static PaymentsRepository getPaymentsRepository(int moduleId) {
        switch (moduleId) {
            case 4:
                return new PaymentsRepository(Constants.GET_PAYMENTS, "purchasePayments");
            case 1:
            default:
                return new PaymentsRepository(Constants.GET_PAYMENTS, "customerPayments");
        }
    }

    public static DashboardRepository getDashboardRepository(int moduleId) {
        switch (moduleId) {
            case 4:
                return new DashboardRepository("purchaseDashboard");
            case 3:
                return new DashboardRepository("hrDashboard");
            case 1:
            default:
                return new DashboardRepository("reportsDashboard");
        }
    }
}
