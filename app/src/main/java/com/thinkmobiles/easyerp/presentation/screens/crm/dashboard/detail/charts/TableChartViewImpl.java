package com.thinkmobiles.easyerp.presentation.screens.crm.dashboard.detail.charts;

import android.graphics.Color;
import android.text.TextUtils;
import android.widget.FrameLayout;

import com.thinkmobiles.easyerp.data.model.crm.invoice.Invoice;
import com.thinkmobiles.easyerp.data.model.crm.invoice.ResponseGetInvoice;
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
        final ResponseGetInvoice responseGetInvoice = (ResponseGetInvoice) data;
        final List<TableDataView.TableItemData> dataList = new ArrayList<>();

        final DecimalFormat sumFormat = new DollarFormatter().getFormat();
        for (Invoice invoice : responseGetInvoice.data)
            dataList.add(new TableDataView.TableItemData(
                    String.format("%s\n%s",
                            new DateManager.DateConverter(invoice.invoiceDate).setDstPattern(DateManager.PATTERN_DASHBOARD_PREVIEW).toString(),
                            new DateManager.DateConverter(invoice.invoiceDate).setDstPattern(DateManager.PATTERN_DASHBOARD_DAY_VIEW).toString()),
                    TextUtils.isEmpty(invoice.supplier.name) ? "Not Assigned" : invoice.supplier.name,
                    String.format("%s %s", invoice.currency.id.symbol, sumFormat.format(invoice.paymentInfo.total / 100d))));

        return dataList;
    }

}
