package com.thinkmobiles.easyerp.presentation.screens.crm.dashboard.detail.charts;

import android.graphics.Color;
import android.text.TextUtils;
import android.widget.FrameLayout;

import com.thinkmobiles.easyerp.data.model.crm.dashboard.detail.invoice.GetInvoiceResponse;
import com.thinkmobiles.easyerp.data.model.crm.dashboard.detail.invoice.InvoiceItem;
import com.thinkmobiles.easyerp.presentation.custom.views.charts.TableDataView;
import com.thinkmobiles.easyerp.presentation.managers.ColorGenerateManager;
import com.thinkmobiles.easyerp.presentation.managers.DateManager;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author michael.soyma@thinkmobiles.com (Created on 1/25/2017.)
 */

public class TableChartViewImpl implements IChartView<List<TableDataView.TableItemData>> {

    private TableDataView tableDataView;

    @Override
    public void render(FrameLayout parent, Object data) {
        parent.removeAllViews();

        tableDataView = new TableDataView(parent.getContext());

        final List<TableDataView.TableItemData> preparedData = prepareData(data);
        tableDataView.setData(preparedData);
        tableDataView.setColors(ColorGenerateManager.generateGradientBetweenColors(preparedData.size(), Color.parseColor("#1D455A"), Color.parseColor("#083045")));
        tableDataView.setStrokeColor(Color.parseColor("#EED369"));
        tableDataView.animateY(1000);
        parent.addView(tableDataView);
    }

    @Override
    public List<TableDataView.TableItemData> prepareData(Object data) {
        final GetInvoiceResponse getInvoiceResponse = (GetInvoiceResponse) data;
        final List<TableDataView.TableItemData> dataList = new ArrayList<>();

        final DecimalFormat sumFormat = new DollarFormatter().getFormat();
        for (InvoiceItem invoiceItem: getInvoiceResponse.data)
            dataList.add(new TableDataView.TableItemData(
                    String.format("%s\n%s",
                            new DateManager.DateConverter(invoiceItem.invoiceDate).setDstPattern(DateManager.PATTERN_DASHBOARD_PREVIEW).toString(),
                            new DateManager.DateConverter(invoiceItem.invoiceDate).setDstPattern(DateManager.PATTERN_DASHBOARD_DAY_VIEW).toString()),
                    TextUtils.isEmpty(invoiceItem.supplier.name) ? "Not Assigned" : invoiceItem.supplier.name,
                    String.format("%s %s", invoiceItem.currency.id.symbol, sumFormat.format(invoiceItem.paymentInfo.total / 100d))));

        return dataList;
    }

}
