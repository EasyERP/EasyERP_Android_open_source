package com.thinkmobiles.easyerp.domain.crm;

import android.net.Uri;

import com.thinkmobiles.easyerp.data.api.Rest;
import com.thinkmobiles.easyerp.data.model.crm.filter.ResponseFilters;
import com.thinkmobiles.easyerp.data.model.crm.payments.ResponseGetPayments;
import com.thinkmobiles.easyerp.data.model.user.organization.ResponseGetOrganizationSettings;
import com.thinkmobiles.easyerp.data.services.FilterService;
import com.thinkmobiles.easyerp.data.services.PaymentsService;
import com.thinkmobiles.easyerp.data.services.UserService;
import com.thinkmobiles.easyerp.presentation.base.NetworkRepository;
import com.thinkmobiles.easyerp.presentation.screens.crm.payments.PaymentsContract;
import com.thinkmobiles.easyerp.presentation.screens.crm.payments.details.PaymentDetailsContract;
import com.thinkmobiles.easyerp.presentation.utils.Constants;
import com.thinkmobiles.easyerp.presentation.utils.filter.FilterQuery;
import com.thinkmobiles.easyerp.presentation.utils.filter.FilterTypeQuery;

import org.androidannotations.annotations.EBean;

import rx.Observable;

/**
 * @author Michael Soyma (Created on 2/2/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
@EBean
public class PaymentsRepository extends NetworkRepository implements PaymentsContract.PaymentsModel, PaymentDetailsContract.PaymentDetailsModel {

    private PaymentsService paymentsService;
    private UserService userService;
    private FilterService filterService;

    public PaymentsRepository() {
        paymentsService = Rest.getInstance().getPaymentService();
        userService = Rest.getInstance().getUserService();
        filterService = Rest.getInstance().getFilterService();
    }

    @Override
    public Observable<ResponseGetPayments> getFilteredPayments(FilterQuery query, int page) {
        Uri.Builder builder = Uri.parse(Constants.BASE_URL).buildUpon();
        builder.appendPath(Constants.GET_PAYMENTS)
                .appendQueryParameter("page", String.valueOf(page))
                .appendQueryParameter("count", String.valueOf(countListItems))
                .appendQueryParameter("viewType", "list")
                .appendQueryParameter("contentType", "customerPayments");

        if (query != null) {
            for (int i = 0; i < query.filters.size(); ++i) {
                FilterTypeQuery filter = query.filters.valueAt(i);
                if (filter.getValues() != null) {
                    builder.appendQueryParameter(String.format("filter[%s][key]", filter.getType()), filter.getKey());
                    String queryName = String.format("filter[%s][value][]", filter.getType());
                    for (String value : filter.getValues()) {
                        builder.appendQueryParameter(queryName, value);
                    }
                }
            }
        }

        return getNetworkObservable(paymentsService.getFilteredPayments(builder.build().toString()));
    }

    @Override
    public Observable<ResponseFilters> getPaymentFilters() {
        return getNetworkObservable(filterService.getListFilters("customerPayments"));
    }

    @Override
    public Observable<ResponseGetOrganizationSettings> getOrganizationSettings() {
        return getNetworkObservable(userService.getOrganizationSettings());
    }
}
