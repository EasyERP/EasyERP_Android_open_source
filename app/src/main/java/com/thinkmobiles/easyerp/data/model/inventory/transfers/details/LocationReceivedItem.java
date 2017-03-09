package com.thinkmobiles.easyerp.data.model.inventory.transfers.details;

import com.google.gson.annotations.SerializedName;
import com.thinkmobiles.easyerp.data.model.inventory.goods_out_notes.details.LocationsDeliver;

/**
 * Created by Lynx on 3/9/2017.
 */

public class LocationReceivedItem {
    public int quantity;
    @SerializedName("_id")
    public int id;
    public LocationsDeliver location;
}
