package com.thinkmobiles.easyerp.presentation.holders.view.reports;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.michenko.simpleadapter.OnCardClickListener;
import com.michenko.simpleadapter.RecyclerVH;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.data.model.reports.general.Report;
import com.thinkmobiles.easyerp.presentation.holders.data.reports.ReportDH;
import com.thinkmobiles.easyerp.presentation.managers.DateManager;
import com.thinkmobiles.easyerp.presentation.utils.Constants;

import java.util.concurrent.TimeUnit;

import static com.thinkmobiles.easyerp.presentation.managers.DateManager.PATTERN_DATE_SIMPLE_PREVIEW;

/**
 * @author Michael Soyma (Created on 4/10/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public class ReportVH extends RecyclerVH<ReportDH> {

    private TextView tvName_VLIGR, tvEditedBy_VLIGR, tvType_VLIGR,
            tvDateRange_VLIGR, tvAccess_VLIGR;
    private CheckBox cbFavorite_VLIGR;
    private ImageView ivDescription_VLIGR;

    public ReportVH(View itemView, @Nullable OnCardClickListener listener, int viewType) {
        super(itemView, listener, viewType);

        tvName_VLIGR = findView(R.id.tvName_VLIGR);
        tvEditedBy_VLIGR = findView(R.id.tvEditedBy_VLIGR);
        tvType_VLIGR = findView(R.id.tvType_VLIGR);
        tvDateRange_VLIGR = findView(R.id.tvDateRange_VLIGR);
        tvAccess_VLIGR = findView(R.id.tvAccess_VLIGR);
        cbFavorite_VLIGR = findView(R.id.cbFavorite_VLIGR);
        ivDescription_VLIGR = findView(R.id.ivDescription_VLIGR);

        if(listener != null) {
            RxView.clicks(cbFavorite_VLIGR)
                    .throttleFirst(Constants.DELAY_CLICK, TimeUnit.MILLISECONDS)
                    .subscribe(aVoid -> listener.onClick(cbFavorite_VLIGR, getAdapterPosition(), getItemViewType()));

            RxView.clicks(ivDescription_VLIGR)
                    .throttleFirst(Constants.DELAY_CLICK, TimeUnit.MILLISECONDS)
                    .subscribe(aVoid -> listener.onClick(ivDescription_VLIGR, getAdapterPosition(), getItemViewType()));
        }
    }

    @Override
    public void bindData(ReportDH data) {
        itemView.setBackgroundResource(getAdapterPosition() % 2 == 0 ? R.color.color_bg_product_details : android.R.color.white);

        final Report report = data.getReport();
        cbFavorite_VLIGR.setChecked(data.isFavorite(report.id));
        tvName_VLIGR.setText(report.name);
        tvEditedBy_VLIGR.setText(report.editedBy.user.login);
        tvType_VLIGR.setText(report.reportType);
        tvDateRange_VLIGR.setText(String.format("%s\n%s",
                new DateManager.DateConverter(report.dateRange.from).setDstPattern(PATTERN_DATE_SIMPLE_PREVIEW).toString(),
                new DateManager.DateConverter(report.dateRange.to).setDstPattern(PATTERN_DATE_SIMPLE_PREVIEW).toString()));
        tvAccess_VLIGR.setText(report.publicAccess ? R.string.public_label : R.string.private_label);
    }
}
