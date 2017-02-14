package com.thinkmobiles.easyerp.presentation.holders.view.crm;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.michenko.simpleadapter.OnCardClickListener;
import com.michenko.simpleadapter.RecyclerVH;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.data.model.crm.leads.detail.Customer;
import com.thinkmobiles.easyerp.presentation.custom.transformations.CropCircleTransformation;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.ContactDH;
import com.thinkmobiles.easyerp.presentation.managers.ImageHelper;

/**
 * Created by Lynx on 2/13/2017.
 */

public class ContactVH extends RecyclerVH<ContactDH> {

    private ImageView ivContactImage_VLIC;
    private TextView tvContactName_VLIC;
    private TextView tvContactCountry_VLIC;
    private TextView tvContactJobPosition_VLIC;
    private TextView tvContactEmail_VLIC;

    public ContactVH(View itemView, @Nullable OnCardClickListener listener, int viewType) {
        super(itemView, listener, viewType);

        ivContactImage_VLIC = findView(R.id. ivContactImage_VLIC);
        tvContactName_VLIC = findView(R.id.tvContactName_VLIC);
        tvContactCountry_VLIC = findView(R.id.tvContactCountry_VLIC);
        tvContactJobPosition_VLIC = findView(R.id.tvContactJobPosition_VLIC);
        tvContactEmail_VLIC = findView(R.id.tvContactEmail_VLIC);
    }

    @Override
    public void bindData(ContactDH data) {
        Customer item = data.getCustomer();
        ivContactImage_VLIC.setImageResource(R.drawable.placeholder);
        if(!TextUtils.isEmpty(item.imageSrc)) {
            ImageHelper.getBitmapFromBase64(item.imageSrc, new CropCircleTransformation())
                    .subscribe(ivContactImage_VLIC::setImageBitmap);
        } else {
            ivContactImage_VLIC.setImageResource(R.drawable.placeholder);
        }
        tvContactName_VLIC.setText(TextUtils.isEmpty(item.fullName) ? null : item.fullName);
        if(item.address != null && !TextUtils.isEmpty(item.address.country)) {
            tvContactCountry_VLIC.setText(item.address.country);
        } else {
            tvContactCountry_VLIC.setText(null);
        }
        if(TextUtils.isEmpty(item.jobPosition)) {
            tvContactJobPosition_VLIC.setText(null);
            tvContactJobPosition_VLIC.setVisibility(View.GONE);
        } else {
            tvContactJobPosition_VLIC.setText(item.jobPosition);
            tvContactJobPosition_VLIC.setVisibility(View.VISIBLE);
        }
        if(TextUtils.isEmpty(item.email)) {
            tvContactEmail_VLIC.setText(null);
            tvContactEmail_VLIC.setVisibility(View.GONE);
        } else {
            tvContactEmail_VLIC.setText(item.email);
            tvContactEmail_VLIC.setVisibility(View.VISIBLE);
        }
    }
}
