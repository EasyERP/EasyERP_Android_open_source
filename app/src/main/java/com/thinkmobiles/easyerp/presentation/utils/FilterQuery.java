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
    public ArrayList<String> assignedTo;
    public ArrayList<String> source;
    public ArrayList<String> createdBy;
    public ArrayList<String> workflow;

    public FilterQuery() {
        this.queryMap = new HashMap<>();
    }


    public static class Builder {

        private FilterQuery query;

        public Builder() {
            query = new FilterQuery();
        }

        public Builder setSingleContactName(String name) {
            createContactNameKey();
            query.contactNames.add(name);
            return this;
        }

        public Builder addContactName(String name) {
            if (query.contactNames == null) {
                createContactNameKey();
            }
            query.contactNames.add(name);
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

        public Builder removeAllWorkflows() {
            query.workflow = null;
            removeFilterType("workflow");
            return this;
        }

        public Builder createWorkflowKey() {
            query.workflow = new ArrayList<>();
            addToKeys("workflow", "workflow._id");
            return this;
        }

        public Builder addWorkflow(String id) {
            if (query.workflow == null) {
                createWorkflowKey();
            }
            query.workflow.add(id);
            return this;
        }

        public Builder removeAllAssignedTo() {
            query.assignedTo = null;
            removeFilterType("salesPerson");
            return this;
        }

        public Builder createAssignedToKey() {
            query.assignedTo = new ArrayList<>();
            addToKeys("salesPerson", "salesPerson._id");
            return this;
        }

        public Builder addAssignedTo(String id) {
            if (query.assignedTo == null) {
                createAssignedToKey();
            }
            query.assignedTo.add(id);
            return this;
        }

        public Builder removeAllCreatedBy() {
            query.createdBy = null;
            removeFilterType("createdBy");
            return this;
        }

        public Builder createCreatedByKey() {
            query.createdBy = new ArrayList<>();
            addToKeys("createdBy", "createdBy.user._id");
            return this;
        }

        public Builder addCreatedBy(String id) {
            if (query.createdBy == null) {
                createCreatedByKey();
            }
            query.createdBy.add(id);
            return this;
        }

        public Builder removeAllSource() {
            query.source = null;
            removeFilterType("source");
            return this;
        }

        public Builder createSourceKey() {
            query.source = new ArrayList<>();
            addToKeys("source", "source");
            return this;
        }

        public Builder addSource(String id) {
            if (query.source == null) {
                createSourceKey();
            }
            query.source.add(id);
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
