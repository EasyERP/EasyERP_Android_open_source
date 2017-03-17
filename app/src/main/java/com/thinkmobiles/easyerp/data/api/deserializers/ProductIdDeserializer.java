package com.thinkmobiles.easyerp.data.api.deserializers;


import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.thinkmobiles.easyerp.data.model.inventory.product.detail.ProductId;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by samson on 13.03.17.
 */

public class ProductIdDeserializer implements JsonDeserializer<ArrayList<ProductId>> {
    @Override
    public ArrayList<ProductId> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        ArrayList<ProductId> response = new ArrayList<>();
        Gson gson = new Gson();

        JsonObject root = json.getAsJsonObject();

        for (Map.Entry<String, JsonElement> field : root.entrySet()) {
            String[] ids = field.getKey().split("/");
            ProductId productId = gson.fromJson(field.getValue(), ProductId.class);
            if (ids.length == 2) {
                productId.id = ids[0];
                productId.subId = ids[1];
            } else {
                productId.id = "";
                productId.subId = "";
            }
            response.add(productId);
        }

        return response;
    }
}
