package com.thinkmobiles.easyerp.presentation.holders.view.crm;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.michenko.simpleadapter.OnCardClickListener;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.data.model.crm.companies.CompanyListItem;
import com.thinkmobiles.easyerp.presentation.base.rules.MasterFlowSelectableVHHelper;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.CompanyDH;
import com.thinkmobiles.easyerp.presentation.managers.ImageHelper;

/**
 * Created by Lynx on 2/2/2017.
 */

public final class CompanyVH extends MasterFlowSelectableVHHelper<CompanyDH> {

    private final ImageView ivCompanyImage_VLIC;
    private final TextView tvCompanyName_VLIC;
    private final TextView tvCompanyCountry_VLIC;
    private final TextView tvCompanyEmail_VLIC;
    private final TextView tvCompanyPhone_VLIC;

    public CompanyVH(View itemView, @Nullable OnCardClickListener listener, int viewType) {
        super(itemView, listener, viewType);

        ivCompanyImage_VLIC = findView(R.id.ivCompanyImage_VLIC);
        tvCompanyName_VLIC = findView(R.id.tvCompanyName_VLIC);
        tvCompanyCountry_VLIC = findView(R.id.tvCompanyCountry_VLIC);
        tvCompanyEmail_VLIC = findView(R.id.tvCompanyEmail_VLIC);
        tvCompanyPhone_VLIC = findView(R.id.tvCompanyPhone_VLIC);

    }

    @Override
    public void bindData(CompanyDH data) {
        super.bindData(data);

        ivCompanyImage_VLIC.setImageResource(R.drawable.ic_avatar_placeholder_with_padding);
        CompanyListItem company = data.getData();
        ImageHelper.getBitmapFromBase64(data.getCompanyImageBase64())
                .subscribe(bitmap -> {
                    if(bitmap != null)
                        ivCompanyImage_VLIC.setImageBitmap(bitmap);
                });
        tvCompanyName_VLIC.setText(TextUtils.isEmpty(company.fullName) ? null : company.fullName);
        tvCompanyEmail_VLIC.setText(TextUtils.isEmpty(company.email) ? null : company.email);
        if(company.address != null && !TextUtils.isEmpty(company.address.country)) {
            tvCompanyCountry_VLIC.setText(company.address.country);
        } else {
            tvCompanyCountry_VLIC.setText(null);
        }
        if(company.phones != null && !TextUtils.isEmpty(company.phones.phone)) {
            tvCompanyPhone_VLIC.setText(company.phones.phone);
        } else {
            tvCompanyPhone_VLIC.setText(null);
        }
    }
}
