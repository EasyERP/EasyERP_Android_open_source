package com.thinkmobiles.easyerp.presentation.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by samson on 26.01.17.
 */

public class FilterQuery {

    final public Map<String, String> queryMap;
    public ArrayList<String> contactNames;

    public FilterQuery() {
        this.queryMap = new HashMap<>();
    }


    public static class Builder {

        private FilterQuery query;

        public Builder() {
            query = new FilterQuery();
        }

        public Builder addContactName(String name) {
            if (query.contactNames == null) {
                createContactNameKey();
            }
            query.contactNames.add(name);
            return this;
        }

        public Builder setSingleContactName(String name) {
            createContactNameKey();
            query.contactNames.add(name);
            return this;
        }

        public Builder addContactNames(ArrayList<String> names) {
            if (query.contactNames == null) {
                createContactNameKey();
            }
            query.contactNames.addAll(names);
            return this;
        }

        public Builder removeContactName(String name) {
            if (query.contactNames != null) {
                query.contactNames.remove(name);
                if (query.contactNames.isEmpty()) {
                    removeAllContactNames();
                }
            }
            return this;
        }

        public Builder removeContactNames(ArrayList<String> names) {
            if (query.contactNames != null) {
                query.contactNames.removeAll(names);
                if (query.contactNames.isEmpty()) {
                    removeAllContactNames();
                }
            }
            return this;
        }

        public Builder removeAllContactNames() {
            query.contactNames = null;
            removeFilterType("contactName");
            return this;
        }

        public Builder createContactNameKey() {
            query.contactNames = new ArrayList<>();
            addToKeys("contactName", "contactName");
            return this;
        }

        private void addToKeys(String key, String value) {
            query.queryMap.put(
                    String.format("filter[%s][key]=%s", key, value),
                    value
            );
        }

        private void removeFilterType(String type) {
            for(Iterator<Map.Entry<String, String>> it = query.queryMap.entrySet().iterator(); it.hasNext(); ) {
                Map.Entry<String, String> entry = it.next();
                if(entry.getKey().contains(type)) {
                    it.remove();
                }
            }
        }


        public FilterQuery build() {
            return query;
        }
    }

}
