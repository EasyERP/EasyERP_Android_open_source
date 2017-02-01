package com.thinkmobiles.easyerp.presentation.utils.filter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class FilterQuery {

    final public Map<String, String> queryMap;
    public ArrayList<String> contactName;
    public ArrayList<String> assignedTo;
    public ArrayList<String> source;
    public ArrayList<String> createdBy;
    public ArrayList<String> workflow;

    public FilterQuery() {
        this.queryMap = new HashMap<>();
    }

    public static class Builder {

        private FilterTypeQuery contactName;
        private FilterTypeQuery createdBy;
        private FilterTypeQuery assignedTo;
        private FilterTypeQuery source;
        private FilterTypeQuery workflow;

        public Builder() {
            contactName = new FilterTypeQuery("contactName", "contactName");
            createdBy = new FilterTypeQuery("createdBy", "createdBy.user._id");
            assignedTo = new FilterTypeQuery("salesPerson", "salesPerson._id");
            source = new FilterTypeQuery("source", "source");
            workflow = new FilterTypeQuery("workflow", "workflow._id");
        }

        public FilterTypeQuery forContactName() {
            return contactName;
        }

        public FilterTypeQuery forCreatedBy() {
            return createdBy;
        }

        public FilterTypeQuery forAssignedTo() {
            return assignedTo;
        }

        public FilterTypeQuery forSource() {
            return source;
        }

        public FilterTypeQuery forWorkflow() {
            return workflow;
        }

        public FilterQuery build() {
            FilterQuery query = new FilterQuery();

            query.contactName = contactName.getValues();
            addKey(query.queryMap, query.contactName, contactName);
            query.assignedTo = assignedTo.getValues();
            addKey(query.queryMap, query.assignedTo, assignedTo);
            query.createdBy = createdBy.getValues();
            addKey(query.queryMap, query.createdBy, createdBy);
            query.source = source.getValues();
            addKey(query.queryMap, query.source, source);
            query.workflow = workflow.getValues();
            addKey(query.queryMap, query.workflow, workflow);

            return query;
        }

        private void addKey(Map<String, String> queryMap, ArrayList<String> values, FilterTypeQuery typeQuery) {
            if (values != null) {
                queryMap.put(String.format("filter[%s][key]", typeQuery.getKey()), typeQuery.getTypeFilter());
            }
        }
    }

}
