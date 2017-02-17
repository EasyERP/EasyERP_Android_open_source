package com.thinkmobiles.easyerp.data.api.deserializers;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.thinkmobiles.easyerp.data.model.crm.filter.FilterInfo;
import com.thinkmobiles.easyerp.data.model.crm.filter.FilterItem;
import com.thinkmobiles.easyerp.data.model.crm.filter.ResponseFilters;
import com.thinkmobiles.easyerp.presentation.utils.filter.FilterComparator;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.function.Predicate;

/**
 * @author Alex Michenko (Created on 16.02.17).
 *         Company: Thinkmobiles
 *         Email: alex.michenko@thinkmobiles.com
 */

public class FilterDeserializer implements JsonDeserializer<ResponseFilters> {
    @Override
    public ResponseFilters deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        ResponseFilters responseFilters = new ResponseFilters();

        FilterComparator comparator = new FilterComparator();
        Type listType = new TypeToken<ArrayList<FilterItem>>(){}.getType();
        Gson gson = new Gson();

        JsonObject response = json.getAsJsonObject();
        JsonObject filterInfo = response.getAsJsonObject("filterInfo");
        JsonArray array = filterInfo.getAsJsonArray("array");
        for (int i = 0; i < array.size(); ++i) {
            String filterType = array.get(i).getAsString();
            JsonObject filter = filterInfo.getAsJsonObject(filterType);
            String filterName = filter.get("displayName").getAsString();
            String filterKey = filter.get("backend").getAsString();
            ArrayList<FilterItem> items = gson.fromJson(response.get(filterType), listType);
            Collections.sort(items, comparator);

            responseFilters.filters.add(new FilterInfo(items, filterName, filterType, filterKey, i));
        }

        return responseFilters;
    }
}
