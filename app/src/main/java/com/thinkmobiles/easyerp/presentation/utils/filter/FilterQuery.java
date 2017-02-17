package com.thinkmobiles.easyerp.presentation.utils.filter;

import android.util.SparseArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class FilterQuery {

    final public Map<String, String> queryMap;
    public ArrayList<String> contactName;
    public ArrayList<String> assignedTo;
    public ArrayList<String> source;
    public ArrayList<String> createdBy;
    public ArrayList<String> workflow;
    public ArrayList<String> customer;
    public ArrayList<String> name;
    public ArrayList<String> project;
    public ArrayList<String> supplier;

    public SparseArray<FilterTypeQuery> filters;

    public FilterQuery() {
        this.queryMap = new HashMap<>();
        this.filters = new SparseArray<>();
    }

    public void putFilter(int code, String type, String key) {
        filters.put(code, new FilterTypeQuery(type, key));
    }

    public FilterTypeQuery forFilter(int code) {
        return  filters.get(code);
    }

    public static class Builder {

        private final FilterTypeQuery contactName;
        private final FilterTypeQuery createdBy;
        private final FilterTypeQuery assignedTo;
        private final FilterTypeQuery source;
        private final FilterTypeQuery workflow;
        private final FilterTypeQuery customer;
        private final FilterTypeQuery name;
        private final FilterTypeQuery project;
        private final FilterTypeQuery supplier;

        private final SparseArray<FilterTypeQuery> filters;

        public Builder() {
            contactName = new FilterTypeQuery("contactName", "contactName");
            createdBy = new FilterTypeQuery("createdBy", "createdBy.user._id");
            assignedTo = new FilterTypeQuery("salesPerson", "salesPerson._id");
            source = new FilterTypeQuery("source", "source");
            workflow = new FilterTypeQuery("workflow", "workflow._id");
            customer = new FilterTypeQuery("customer", "customer");
            name = new FilterTypeQuery("name", "_id");
            project = new FilterTypeQuery("project", "project._id");
            supplier = new FilterTypeQuery("supplier", "supplier._id");

            filters = new SparseArray<>();
        }

        public Builder putFilter(int code, String type, String key) {
            filters.put(code, new FilterTypeQuery(type, key));
            return this;
        }

        public FilterTypeQuery forFilter(int code) {
            return  filters.get(code);
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

        public FilterTypeQuery forCustomer() {
            return customer;
        }

        public FilterTypeQuery forName() {
            return name;
        }

        public FilterTypeQuery forSupplier() {
            return supplier;
        }

        public FilterTypeQuery forProject() {
            return project;
        }

        public FilterQuery build() {
            FilterQuery query = new FilterQuery();

            query.contactName = contactName.save(query.queryMap);
            query.assignedTo = assignedTo.save(query.queryMap);
            query.createdBy = createdBy.save(query.queryMap);
            query.source = source.save(query.queryMap);
            query.workflow = workflow.save(query.queryMap);
            query.name = name.save(query.queryMap);
            query.customer = customer.save(query.queryMap);
            query.supplier = supplier.save(query.queryMap);
            query.project = project.save(query.queryMap);

            query.filters = filters;

            return query;
        }
    }

}
