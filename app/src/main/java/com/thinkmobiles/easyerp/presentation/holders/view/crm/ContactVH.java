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
 * Created by Lynx on 2/3/2017.
 */

public final class ContactVH extends RecyclerVH<ContactDH> {

    private final ImageView ivContactImage_VLIC;
    private final TextView tvContactName_VLIC;
    private final TextView tvContactEmail_VLIC;

    public ContactVH(View itemView, @Nullable OnCardClickListener listener, int viewType) {
        super(itemView, listener, viewType);

        ivContactImage_VLIC = findView(R.id. ivContactImage_VLIC);
        tvContactName_VLIC = findView(R.id.tvContactName_VLIC);
        tvContactEmail_VLIC = findView(R.id.tvContactEmail_VLIC);
    }

    @Override
    public void bindData(ContactDH data) {
        Customer customer = data.getCustomer();
        if(!TextUtils.isEmpty(customer.imageSrc)) {
            ImageHelper.getBitmapFromBase64(customer.imageSrc, new CropCircleTransformation())
                    .subscribe(ivContactImage_VLIC::setImageBitmap);
        }
        tvContactName_VLIC.setText(customer.fullName);
        tvContactEmail_VLIC.setText(customer.email);
    }
}
