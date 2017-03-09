package com.thinkmobiles.easyerp.data.model.inventory.transfers;

import com.google.gson.annotations.SerializedName;
import com.thinkmobiles.easyerp.data.model.inventory.goods_out_notes.details.LocationsDeliver;

import java.util.ArrayList;

/**
 * Created by Lynx on 3/9/2017.
 */

public class OrderRowItem {

    @SerializedName("_id")
    public String id;
    public int quantity;
    public int cost;
    public ArrayList<BatcherDeliverItem> batchesDeliver;
    public ArrayList<LocationsDeliver> locationsDeliver;
    public TransferProduct product;

}
