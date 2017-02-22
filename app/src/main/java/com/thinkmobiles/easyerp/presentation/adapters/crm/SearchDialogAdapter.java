package com.thinkmobiles.easyerp.presentation.adapters.crm;

import android.widget.Filter;
import android.widget.Filterable;

import com.michenko.simpleadapter.SimpleRecyclerAdapter;
import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.presentation.holders.data.crm.FilterDH;
import com.thinkmobiles.easyerp.presentation.holders.view.crm.FilterVH;

import org.androidannotations.annotations.EBean;

import java.util.ArrayList;

@EBean
public class SearchDialogAdapter extends SimpleRecyclerAdapter<FilterDH, FilterVH> implements Filterable {

    private SearchFilter filter;

    private ArrayList<FilterDH> originList;

    public void setOriginList(ArrayList<FilterDH> originList) {
        this.originList = originList;
        setListDH(originList);
    }

    @Override
    protected int getItemLayout() {
        return R.layout.view_list_item_filter;
    }

    @Override
    public Filter getFilter() {
        if (filter == null) {
            filter = new SearchFilter();
        }
        return filter;
    }

    private class SearchFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if (constraint == null || constraint.toString().trim().length() == 0) {
                results.count = originList.size();
                results.values = originList;
            } else {
                String query = constraint.toString().toLowerCase();
                ArrayList<FilterDH> filterItems = new ArrayList<>();

                for (FilterDH item : originList) {
                    if ( item.name != null && item.name.toLowerCase().contains(query)) {
                        filterItems.add(item);
                    }
                }

                results.count = filterItems.size();
                results.values = filterItems;
            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            try {
                setListDH((ArrayList<FilterDH>) filterResults.values);
            } catch (Exception e) {
                setListDH(new ArrayList<>());
            }
            notifyDataSetChanged();
        }
    }
}
