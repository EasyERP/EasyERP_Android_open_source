package com.thinkmobiles.easyerp.presentation.adapters.crm;

import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.thinkmobiles.easyerp.presentation.holders.data.crm.FilterDH;

import org.androidannotations.annotations.EBean;

import java.util.ArrayList;


@EBean
public class SearchAdapter extends BaseAdapter implements Filterable {

    private SearchFilter filter;

    private ArrayList<FilterDH> items = new ArrayList<>();
    private ArrayList<FilterDH> temp = new ArrayList<>();

    public void setItems(ArrayList<FilterDH> items) {
        this.items.clear();
        this.items.addAll(items);
    }

    @Override
    public int getCount() {
        return temp.size();
    }

    @Override
    public FilterDH getItem(int i) {
        return temp.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        SearchHolder holder;
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(android.R.layout.simple_list_item_1, viewGroup, false);
            holder = new SearchHolder(view);
            view.setTag(holder);
        } else {
            holder = (SearchHolder) view.getTag();
        }
        holder.bindData(getItem(i).name);
        return view;
    }

    @Override
    public Filter getFilter() {
        if (filter == null) {
            filter = new SearchFilter();
        }
        return filter;
    }

    private class SearchHolder {

        private TextView text;

        public SearchHolder(View parent) {
            parent.setBackgroundColor(ContextCompat.getColor(parent.getContext(), android.R.color.white));
            this.text = (TextView) parent.findViewById(android.R.id.text1);
        }

        public void bindData(String name) {
            text.setText(name);
        }
    }

    private class SearchFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if (constraint == null || constraint.toString().trim().length() == 0) {
                results.count = items.size();
                results.values = items;
            } else {
                String query = constraint.toString().toLowerCase();
                ArrayList<FilterDH> filterItems = new ArrayList<>();

                for (FilterDH item : items) {
                    if (item.name.toLowerCase().contains(query)) {
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
                temp = (ArrayList<FilterDH>) filterResults.values;
            } catch (Exception e) {
                temp = new ArrayList<>();
            }
            notifyDataSetChanged();
        }
    }
}
