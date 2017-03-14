package com.thinkmobiles.easyerp.data.model.crm.persons;

import com.thinkmobiles.easyerp.data.model.ResponseGetTotalItems;
import com.thinkmobiles.easyerp.data.model.crm.common.images.ImageItem;

/**
 * Created by Lynx on 1/23/2017.
 */

public class CommonPersonsResponse {
    public ResponseGetPersons responseGetPersons;
    public ResponseGetTotalItems<ImageItem> responseGetImages;

    public CommonPersonsResponse(ResponseGetPersons responseGetPersons, ResponseGetTotalItems<ImageItem> responseGetImages) {
        this.responseGetPersons = responseGetPersons;
        this.responseGetImages = responseGetImages;
    }
}
