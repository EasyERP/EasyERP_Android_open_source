package com.thinkmobiles.easyerp.data.model.crm.persons;

import com.thinkmobiles.easyerp.data.model.ResponseGetTotalItems;
import com.thinkmobiles.easyerp.data.model.crm.common.images.ImageItem;
import com.thinkmobiles.easyerp.data.model.crm.persons.person_item.PersonModel;

/**
 * Created by Lynx on 1/23/2017.
 */

public class CommonPersonsResponse {
    public ResponseGetTotalItems<PersonModel> responseGetPersons;
    public ResponseGetTotalItems<ImageItem> responseGetImages;

    public CommonPersonsResponse(ResponseGetTotalItems<PersonModel> responseGetPersons, ResponseGetTotalItems<ImageItem> responseGetImages) {
        this.responseGetPersons = responseGetPersons;
        this.responseGetImages = responseGetImages;
    }
}
