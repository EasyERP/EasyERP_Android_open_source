package com.thinkmobiles.easyerp.data.model.crm.companies;

import com.thinkmobiles.easyerp.data.model.ResponseGetTotalItems;
import com.thinkmobiles.easyerp.data.model.crm.common.images.ImageItem;

/**
 * Created by Lynx on 2/2/2017.
 */

public class CommonCompaniesResponse {
    public ResponseGetCompanies responseGetCompanies;
    public ResponseGetTotalItems<ImageItem> responseGetImages;

    public CommonCompaniesResponse(ResponseGetCompanies responseGetCompanies, ResponseGetTotalItems<ImageItem> responseGetImages) {
        this.responseGetCompanies = responseGetCompanies;
        this.responseGetImages = responseGetImages;
    }
}
