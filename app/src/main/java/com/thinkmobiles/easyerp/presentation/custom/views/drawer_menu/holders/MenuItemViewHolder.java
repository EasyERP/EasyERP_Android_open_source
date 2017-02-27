package com.thinkmobiles.easyerp.presentation.custom.views.drawer_menu.holders;

import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.custom.views.drawer_menu.IMenuProviderFunctions;
import com.thinkmobiles.easyerp.presentation.custom.views.drawer_menu.models.MenuItem;

/**
 * @author michael.soyma@thinkmobiles.com (Created on 1/16/2017.)
 */
public final class MenuItemViewHolder extends BaseViewHolder<MenuItem> implements ITransformContent {

    private ImageView thumbItemView;
    private TextView labelItemView;

    private final IMenuProviderFunctions.IMenuItemsProviderFunctions itemsProviderFunctions;

    public MenuItemViewHolder(View itemView, final IMenuProviderFunctions.IMenuItemsProviderFunctions itemsProviderFunctions) {
        super(itemView);
        this.itemsProviderFunctions = itemsProviderFunctions;

        thumbItemView = findWithId(R.id.ivThumbItem_VDMI);
        labelItemView = findWithId(R.id.tvLabelItem_VDMI);

        itemView.setOnClickListener(view -> {
            if (data.isEnabled())
                this.itemsProviderFunctions.select(data.getId());
        });
    }

    @Override
    public void injectData(MenuItem _data) {
        super.injectData(_data);

        itemView.setSelected(data.isSelected());
        thumbItemView.setEnabled(data.isEnabled());

        thumbItemView.setImageResource(data.getIconRes());
        labelItemView.setText(data.getLabel());
        labelItemView.setTextColor(ContextCompat.getColor(getItemView().getContext(), data.isEnabled() ? R.color.white_main_text_color : R.color.menu_item_idle_state));
    }

    @Override
    public void setTransformCoefficient(float value) {
        labelItemView.setAlpha(value);
    }
}
