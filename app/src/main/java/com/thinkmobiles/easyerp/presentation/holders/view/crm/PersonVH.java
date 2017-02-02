package com.thinkmobiles.easyerp.presentation.holders.view.crm;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.michenko.simpleadapter.OnCardClickListener;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.data.model.crm.persons.person_item.PersonModel;
import com.thinkmobiles.easyerp.presentation.base.rules.MasterFlowSelectableVHHelper;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.PersonDH;
import com.thinkmobiles.easyerp.presentation.managers.ImageHelper;

/**
 * Created by Lynx on 1/23/2017.
 */

public final class PersonVH extends MasterFlowSelectableVHHelper<PersonDH> {

    private final ImageView ivPersonImage_VLIP;
    private final TextView tvPersonName_VLIP;
    private final TextView tvPersonCountry_VLIP;
    private final TextView tvPersonEmail_VLIP;
    private final TextView tvPersonPhone_VLIP;

    private final String noData;

    public PersonVH(View itemView, @Nullable OnCardClickListener listener, int viewType) {
        super(itemView, listener, viewType);

        ivPersonImage_VLIP = findView(R.id.ivPersonImage_VLIP);
        tvPersonName_VLIP = findView(R.id.tvPersonName_VLIP);
        tvPersonCountry_VLIP = findView(R.id.tvPersonCountry_VLIP);
        tvPersonEmail_VLIP = findView(R.id.tvPersonEmail_VLIP);
        tvPersonPhone_VLIP = findView(R.id.tvPersonPhone_VLIP);

        noData = itemView.getContext().getString(R.string.no_data);
    }

    @Override
    public void bindData(PersonDH data) {
        super.bindData(data);

        PersonModel personModel = data.getPersonModel();
        ImageHelper.getBitmapFromBase64(data.getBase64Image())
                .subscribe(bitmap -> {
                    if(bitmap != null)
                        ivPersonImage_VLIP.setImageBitmap(bitmap);
                    else
                        ivPersonImage_VLIP.setImageResource(R.drawable.ic_avatar_placeholder_with_padding);
                });
        tvPersonName_VLIP.setText(TextUtils.isEmpty(personModel.fullName) ? noData : personModel.fullName);
        tvPersonEmail_VLIP.setText(TextUtils.isEmpty(personModel.email) ? noData : personModel.email);
        if(personModel.address != null && !TextUtils.isEmpty(personModel.address.country)) {
            tvPersonCountry_VLIP.setText(personModel.address.country);
        } else {
            tvPersonCountry_VLIP.setText(noData);
        }
        if(personModel.phones != null && !TextUtils.isEmpty(personModel.phones.phone)) {
            tvPersonPhone_VLIP.setText(personModel.phones.phone);
        } else {
            tvPersonPhone_VLIP.setText(noData);
        }
    }
}
