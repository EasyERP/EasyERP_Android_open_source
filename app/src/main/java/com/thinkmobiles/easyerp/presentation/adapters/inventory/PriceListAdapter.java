package com.thinkmobiles.easyerp.presentation.adapters.inventory;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.data.model.inventory.product.detail.PriceList;
import com.thinkmobiles.easyerp.presentation.holders.view.inventory.PriceListVH;

import org.androidannotations.annotations.EBean;

import java.util.ArrayList;

/**
 * Created by samson on 15.03.17.
 */
@EBean
public class PriceListAdapter extends BaseAdapter {

    private ArrayList<PriceList> listDHs = new ArrayList<>();

    public void setListDHs(ArrayList<PriceList> listDHs) {
        this.listDHs = new ArrayList<>(listDHs);
    }

    @Override
    public int getCount() {
        return listDHs.size();
    }

    @Override
    public PriceList getItem(int position) {
        return listDHs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PriceListVH holder;
        if (convertView != null) {
            holder = (PriceListVH) convertView.getTag();
        } else {
            convertView = View.inflate(parent.getContext(), R.layout.view_list_item_product_price, null);
            holder = new PriceListVH(convertView);
            convertView.setTag(holder);
        }
        holder.bindData(getItem(position));
        return convertView;
    }
}
