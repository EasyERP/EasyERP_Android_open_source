package com.thinkmobiles.easyerp.presentation.adapters.crm;

import android.content.Context;
import android.database.AbstractCursor;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;

import com.thinkmobiles.easyerp.presentation.holders.data.crm.FilterDH;

import java.util.ArrayList;

/**
 * @author Alex Michenko (Created on 17.02.17).
 *         Company: Thinkmobiles
 *         Email: alex.michenko@thinkmobiles.com
 */

public class SuggestionAdapter extends SimpleCursorAdapter {

    private static final String[] mFields = {"_id", "name"};
    private static final String[] mVisible = {"name"};
    private static final int[] mViewIds = {android.R.id.text1};


    private ArrayList<FilterDH> items = new ArrayList<>();
    private ArrayList<FilterDH> temp = new ArrayList<>();

    public void setItems(ArrayList<FilterDH> items) {
        //TODO: NPE
//        if (items != null) {
            this.items.clear();
            this.items.addAll(items);
//        }
    }

    public FilterDH getSuggestion(int position) {
        return temp.get(position);
    }

    public SuggestionAdapter(Context context) {
        super(context, android.R.layout.simple_list_item_1, null, mVisible, mViewIds, 0);
    }

    @Override
    public Cursor runQueryOnBackgroundThread(CharSequence constraint) {
        return new SuggestionsCursor(constraint);
    }

    private class SuggestionsCursor extends AbstractCursor {

        public SuggestionsCursor(CharSequence constraint) {
            if (constraint != null && constraint.toString().trim().length() != 0) {
                String query = constraint.toString().toLowerCase();
                temp.clear();
                for (FilterDH item : items) {
                    if (item.name.toLowerCase().contains(query)) {
                        temp.add(item);
                    }
                }
            }
        }

        @Override
        public int getCount() {
            return temp.size();
        }

        @Override
        public String[] getColumnNames() {
            return mFields;
        }

        @Override
        public long getLong(int column) {
            if (column == 0) {
                return getPosition();
            }
            throw new UnsupportedOperationException("unimplemented");
        }

        @Override
        public String getString(int column) {
            if (column == 1) {
                return temp.get(getPosition()).name;
            }
            throw new UnsupportedOperationException("unimplemented");
        }

        @Override
        public short getShort(int column) {
            throw new UnsupportedOperationException("unimplemented");
        }

        @Override
        public int getInt(int column) {
            throw new UnsupportedOperationException("unimplemented");
        }

        @Override
        public float getFloat(int column) {
            throw new UnsupportedOperationException("unimplemented");
        }

        @Override
        public double getDouble(int column) {
            throw new UnsupportedOperationException("unimplemented");
        }

        @Override
        public boolean isNull(int column) {
            return false;
        }
    }
}
