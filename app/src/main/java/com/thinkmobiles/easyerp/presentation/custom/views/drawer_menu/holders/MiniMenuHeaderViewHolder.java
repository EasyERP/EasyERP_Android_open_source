package com.thinkmobiles.easyerp.presentation.custom.views.drawer_menu.holders;

import android.view.View;
import android.widget.ImageView;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.custom.views.drawer_menu.IMenuProviderFunctions;

/**
 * Created by Asus_Dev on 1/16/2017.
 */
public final class MiniMenuHeaderViewHolder extends BaseViewHolder<Object> {

    private ImageView userAvatarView;

    private final IMenuProviderFunctions.IHeaderMenuProviderFunctions headerMenuProviderFunctions;

    public MiniMenuHeaderViewHolder(View itemView, IMenuProviderFunctions.IHeaderMenuProviderFunctions headerMenuProviderFunctions) {
        super(itemView);
        this.headerMenuProviderFunctions = headerMenuProviderFunctions;

        userAvatarView = findWithId(R.id.ivAvatar_VDMMH);

        itemView.setOnClickListener(view -> headerMenuProviderFunctions.selectCurrentUser());
    }


    @Override
    public void injectData(Object _data) {
        super.injectData(_data);
    }

}
