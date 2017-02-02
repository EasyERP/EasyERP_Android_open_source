package com.thinkmobiles.easyerp.presentation.holders.view.crm;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.michenko.simpleadapter.OnCardClickListener;
import com.michenko.simpleadapter.RecyclerVH;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.data.model.crm.companies.CompanyListItem;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.CompanyDH;
import com.thinkmobiles.easyerp.presentation.managers.ImageHelper;

/**
 * Created by Lynx on 2/2/2017.
 */

public class CompanyVH extends RecyclerVH<CompanyDH> {

    private RelativeLayout rlCompanyItemContainer_VLIC;
    private ImageView ivCompanyImage_VLIC;
    private TextView tvCompanyName_VLIC;
    private TextView tvCompanyCountry_VLIC;
    private TextView tvCompanyEmail_VLIC;
    private TextView tvCompanyPhone_VLIC;

    private String noData;

    public CompanyVH(View itemView, @Nullable OnCardClickListener listener, int viewType) {
        super(itemView, listener, viewType);

        rlCompanyItemContainer_VLIC = findView(R.id.rlCompanyItemContainer_VLIC);
        ivCompanyImage_VLIC = findView(R.id.ivCompanyImage_VLIC);
        tvCompanyName_VLIC = findView(R.id.tvCompanyName_VLIC);
        tvCompanyCountry_VLIC = findView(R.id.tvCompanyCountry_VLIC);
        tvCompanyEmail_VLIC = findView(R.id.tvCompanyEmail_VLIC);
        tvCompanyPhone_VLIC = findView(R.id.tvCompanyPhone_VLIC);

        noData = itemView.getContext().getString(R.string.no_data);
    }

    @Override
    public void bindData(CompanyDH data) {
        CompanyListItem personModel = data.getData();
        ImageHelper.getBitmapFromBase64(data.getCompanyImageBase64())
                .subscribe(bitmap -> {
                    if(bitmap != null)
                        ivCompanyImage_VLIC.setImageBitmap(bitmap);
                    else
                        ivCompanyImage_VLIC.setImageResource(R.drawable.ic_avatar_placeholder_with_padding);
                });
        tvCompanyName_VLIC.setText(TextUtils.isEmpty(personModel.fullName) ? noData : personModel.fullName);
        tvCompanyEmail_VLIC.setText(TextUtils.isEmpty(personModel.email) ? noData : personModel.email);
        if(personModel.address != null && !TextUtils.isEmpty(personModel.address.country)) {
            tvCompanyCountry_VLIC.setText(personModel.address.country);
        } else {
            tvCompanyCountry_VLIC.setText(noData);
        }
        if(personModel.phones != null && !TextUtils.isEmpty(personModel.phones.phone)) {
            tvCompanyPhone_VLIC.setText(personModel.phones.phone);
        } else {
            tvCompanyPhone_VLIC.setText(noData);
        }
        rlCompanyItemContainer_VLIC.setSelected(data.isSelected());
    }
}
