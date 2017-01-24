package com.thinkmobiles.easyerp.data.model.crm.persons;

import com.thinkmobiles.easyerp.data.model.crm.persons.images.ResponseGetCustomersImages;

/**
 * Created by Lynx on 1/23/2017.
 */

public class CommonPersonsResponse {
    public ResponseGetPersons responseGetPersons;
    public ResponseGetCustomersImages responseGetCustomersImages;

    public CommonPersonsResponse(ResponseGetPersons responseGetPersons, ResponseGetCustomersImages responseGetCustomersImages) {
        this.responseGetPersons = responseGetPersons;
        this.responseGetCustomersImages = responseGetCustomersImages;
    }
}
