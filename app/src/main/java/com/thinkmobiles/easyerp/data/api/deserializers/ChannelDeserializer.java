package com.thinkmobiles.easyerp.data.api.deserializers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.thinkmobiles.easyerp.data.model.integrations.Channel;
import com.thinkmobiles.easyerp.data.model.integrations.PriceList;
import com.thinkmobiles.easyerp.data.model.integrations.WarehouseSettings;

import java.lang.reflect.Type;

/**
 * @author Michael Soyma (Created on 5/8/2017).
 *         Company: Thinkmobiles
 *         Email:  michael.soyma@thinkmobiles.com
 */
public class ChannelDeserializer implements JsonDeserializer<Channel> {
    @Override
    public Channel deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        final GsonBuilder builder = new GsonBuilder();
        builder.excludeFieldsWithoutExposeAnnotation();
        final Gson gson = builder.create();

        final Channel channel = gson.fromJson(json, typeOfT);
        WarehouseSettings warehouseSettings;
        PriceList priceList;
        try {
            warehouseSettings = new Gson().fromJson(json.getAsJsonObject().get("warehouseSettings"), WarehouseSettings.class);
        } catch (JsonParseException e) {
            warehouseSettings = null;
        }
        try {
            priceList = new Gson().fromJson(json.getAsJsonObject().get("priceList"), PriceList.class);
        } catch (JsonParseException e) {
            priceList = null;
        }
        channel.warehouseSettings = warehouseSettings;
        channel.priceList = priceList;
        return channel;
    }
}
