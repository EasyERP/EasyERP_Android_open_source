package com.thinkmobiles.easyerp.presentation.custom.views.drawer_menu.holders;

import android.view.View;
import android.widget.ImageView;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.custom.views.drawer_menu.IMenuProviderFunctions;
import com.thinkmobiles.easyerp.presentation.custom.views.drawer_menu.models.MenuItem;

/**
 * Created by Asus_Dev on 1/16/2017.
 */
public final class MiniMenuItemViewHolder extends BaseViewHolder<MenuItem> {

    private ImageView thumbItemView;

    private final IMenuProviderFunctions.IMenuItemsProviderFunctions itemsProviderFunctions;

    public MiniMenuItemViewHolder(View itemView, final IMenuProviderFunctions.IMenuItemsProviderFunctions itemsProviderFunctions) {
        super(itemView);
        this.itemsProviderFunctions = itemsProviderFunctions;

        thumbItemView = findWithId(R.id.ivThumbItem_VDMMI);

        itemView.setOnClickListener(view -> {
            if (data.isEnabled())
                itemsProviderFunctions.select(data.getId());
        });
    }

    @Override
    public void injectData(MenuItem _data) {
        super.injectData(_data);

        thumbItemView.setImageResource(data.getIconRes());
        itemView.setSelected(data.isSelected());
    }

}
