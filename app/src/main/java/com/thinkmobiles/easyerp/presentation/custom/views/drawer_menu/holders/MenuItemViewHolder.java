package com.thinkmobiles.easyerp.presentation.custom.views.drawer_menu.holders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.custom.views.drawer_menu.IMenuProviderFunctions;
import com.thinkmobiles.easyerp.presentation.custom.views.drawer_menu.models.MenuItem;

/**
 * Created by Asus_Dev on 1/16/2017.
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
                itemsProviderFunctions.select(data.getId());
        });
    }

    @Override
    public void injectData(MenuItem _data) {
        super.injectData(_data);

        thumbItemView.setImageResource(data.getIconRes());
        labelItemView.setText(data.getLabel());
        labelItemView.setTextColor(itemView.getResources().getColor(data.isEnabled() ? R.color.white_main_text_color : R.color.divider_white_color));

        itemView.setSelected(data.isSelected());
    }

    @Override
    public void setTransformCoefficient(float value) {
        labelItemView.setAlpha(value);
    }
}
