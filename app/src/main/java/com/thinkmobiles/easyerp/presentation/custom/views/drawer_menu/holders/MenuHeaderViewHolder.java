package com.thinkmobiles.easyerp.presentation.custom.views.drawer_menu.holders;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.data.model.user.UserInfo;
import com.thinkmobiles.easyerp.presentation.custom.transformations.CropCircleTransformation;
import com.thinkmobiles.easyerp.presentation.custom.views.drawer_menu.IMenuProviderFunctions;
import com.thinkmobiles.easyerp.presentation.custom.views.drawer_menu.models.MenuConfigs;
import com.thinkmobiles.easyerp.presentation.custom.views.drawer_menu.models.MenuItem;
import com.thinkmobiles.easyerp.presentation.managers.ImageHelper;

/**
 * @author michael.soyma@thinkmobiles.com (Created on 1/16/2017.)
 */
public final class MenuHeaderViewHolder extends BaseViewHolder<UserInfo> implements ITransformContent {

    private ImageView userAvatarView;
    private TextView userNameView;
    private TextView userMailView;
    private View dividerView;
    private TextView moduleLabelView;

    private int currentChosenModuleId = -1;
    private int currentChosenItemId = -1;
    private int currentChosenItemForModuleId = -1;

    private float sizeFullAvatar, sizeMiniAvatar;

    private final IMenuProviderFunctions.IHeaderMenuProviderFunctions headerMenuProviderFunctions;

    public MenuHeaderViewHolder(View itemView, IMenuProviderFunctions.IHeaderMenuProviderFunctions headerMenuProviderFunctions) {
        super(itemView);
        this.headerMenuProviderFunctions = headerMenuProviderFunctions;

        sizeMiniAvatar = itemView.getResources().getDimension(R.dimen.user_avatar_size_mini);
        sizeFullAvatar = itemView.getResources().getDimension(R.dimen.user_avatar_size);

        userAvatarView = findWithId(R.id.ivAvatar_VDMH);
        userNameView = findWithId(R.id.tvUserName_VDMH);
        userMailView = findWithId(R.id.tvUserMail_VDMH);
        dividerView = findWithId(R.id.vDivider_VDMH);
        moduleLabelView = findWithId(R.id.tvMenuModule_VDMH);

        moduleLabelView.setOnClickListener(view -> this.headerMenuProviderFunctions.showModules());
        itemView.findViewById(R.id.rlUserContainer_VDMH).setOnClickListener(view -> this.headerMenuProviderFunctions.selectCurrentUser());
    }

    private void updateModuleLabelView() {
        MenuItem module = MenuConfigs.getMenuModuleById(currentChosenModuleId);
        if (module != null) {
            moduleLabelView.setText(module.getLabel());
        }
    }

    @Override
    public void injectData(UserInfo userInfo) {
        super.injectData(userInfo);

        if(!TextUtils.isEmpty(userInfo.imageSrc)) {
            ImageHelper.getBitmapFromBase64(userInfo.imageSrc, new CropCircleTransformation())
                    .subscribe(userAvatarView::setImageBitmap, t -> {});
        }
        if(!TextUtils.isEmpty(userInfo.profile.profileName))
            userNameView.setText(userInfo.profile.profileName);
        if(!TextUtils.isEmpty(userInfo.email))
            userMailView.setText(userInfo.email);
    }

    public int getCurrentChosenModuleId() {
        return currentChosenModuleId;
    }

    public void setCurrentChosenModuleId(int currentChosenModuleId) {
        this.currentChosenModuleId = currentChosenModuleId;
        updateModuleLabelView();
    }

    public int getCurrentChosenItemId() {
        return currentChosenItemId;
    }

    public void setCurrentChosenItemId(int moduleId, int currentChosenItemId) {
        this.currentChosenItemId = currentChosenItemId;
        this.currentChosenItemForModuleId = moduleId;
    }

    public int getCurrentChosenItemForModuleId() {
        return currentChosenItemForModuleId;
    }

    public void setModulesContentState(boolean show) {
        moduleLabelView.setCompoundDrawablesWithIntrinsicBounds(0, 0, show ? R.drawable.ic_arrow_up : R.drawable.ic_arrow_down, 0);
        moduleLabelView.setBackgroundResource(show ? R.drawable.bg_white_with_round_rect : R.drawable.bg_transp_white_with_round_rect);
    }

    @Override
    public void setTransformCoefficient(float value) {
        final float coeffScale = sizeMiniAvatar / sizeFullAvatar;
        userAvatarView.setPivotX(0);
        userAvatarView.setPivotY(sizeFullAvatar);
        userAvatarView.setScaleX(coeffScale + (1 - coeffScale) * value);
        userAvatarView.setScaleY(coeffScale + (1 - coeffScale) * value);

        final float alpha = Math.max(value - .6f, 0f) / .4f;

        dividerView.setAlpha(alpha);
        moduleLabelView.setAlpha(alpha);
        userNameView.setAlpha(alpha);
        userMailView.setAlpha(alpha);
    }

}
