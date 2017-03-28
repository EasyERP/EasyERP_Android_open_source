package com.thinkmobiles.easyerp.data.model.hr.birthdays;

import com.thinkmobiles.easyerp.data.model.ResponseGetTotalItems;
import com.thinkmobiles.easyerp.data.model.crm.common.images.ImageItem;

/**
 * @author Michael Soyma (Created on 3/28/2017).
 *         Company: Thinkmobiles
 *         Email: michael.soyma@thinkmobiles.com
 */
public final class BirthdaysCommonResponse {

    public BirthdaysResponse birthdaysResponse;
    public ResponseGetTotalItems<ImageItem> responseGetImages;

    public BirthdaysCommonResponse(BirthdaysResponse birthdaysResponse, ResponseGetTotalItems<ImageItem> responseGetImages) {
        this.birthdaysResponse = birthdaysResponse;
        this.responseGetImages = responseGetImages;
    }
}
