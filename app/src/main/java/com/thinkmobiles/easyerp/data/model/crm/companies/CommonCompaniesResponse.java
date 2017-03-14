package com.thinkmobiles.easyerp.data.model.crm.companies;

import com.thinkmobiles.easyerp.data.model.ResponseGetTotalItems;
import com.thinkmobiles.easyerp.data.model.crm.common.images.ImageItem;

/**
 * Created by Lynx on 2/2/2017.
 */

public class CommonCompaniesResponse {
    public ResponseGetTotalItems<CompanyListItem> responseGetCompanies;
    public ResponseGetTotalItems<ImageItem> responseGetImages;

    public CommonCompaniesResponse(ResponseGetTotalItems<CompanyListItem> responseGetCompanies, ResponseGetTotalItems<ImageItem> responseGetImages) {
        this.responseGetCompanies = responseGetCompanies;
        this.responseGetImages = responseGetImages;
    }
}
