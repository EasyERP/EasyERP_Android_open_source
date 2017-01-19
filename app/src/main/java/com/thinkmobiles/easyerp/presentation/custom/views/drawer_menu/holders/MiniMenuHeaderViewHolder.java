package com.thinkmobiles.easyerp.presentation.custom.views.drawer_menu.holders;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.data.model.user.UserInfo;
import com.thinkmobiles.easyerp.presentation.custom.transformations.CropCircleTransformation;
import com.thinkmobiles.easyerp.presentation.custom.views.drawer_menu.IMenuProviderFunctions;
import com.thinkmobiles.easyerp.presentation.managers.ImageHelper;

/**
 * Created by Asus_Dev on 1/16/2017.
 */
public final class MiniMenuHeaderViewHolder extends BaseViewHolder<UserInfo> {

    private ImageView userAvatarView;

    private final IMenuProviderFunctions.IHeaderMenuProviderFunctions headerMenuProviderFunctions;

    public MiniMenuHeaderViewHolder(View itemView, IMenuProviderFunctions.IHeaderMenuProviderFunctions headerMenuProviderFunctions) {
        super(itemView);
        this.headerMenuProviderFunctions = headerMenuProviderFunctions;

        userAvatarView = findWithId(R.id.ivAvatar_VDMMH);

        itemView.setOnClickListener(view -> headerMenuProviderFunctions.selectCurrentUser());
    }


    @Override
    public void injectData(UserInfo userInfo) {
        super.injectData(userInfo);

        if(!TextUtils.isEmpty(userInfo.imageSrc)) {
            ImageHelper.getBitmapFromBase64(userInfo.imageSrc, new CropCircleTransformation())
                    .subscribe(userAvatarView::setImageBitmap);
        }
    }

}
