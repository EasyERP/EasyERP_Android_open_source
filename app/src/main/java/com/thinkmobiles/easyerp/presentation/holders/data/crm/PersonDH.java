package com.thinkmobiles.easyerp.presentation.holders.data.crm;

import com.thinkmobiles.easyerp.data.model.crm.persons.person_item.PersonModel;
import com.thinkmobiles.easyerp.presentation.base.rules.MasterFlowSelectableDHHelper;

/**
 * Created by Lynx on 1/23/2017.
 */

public class PersonDH extends MasterFlowSelectableDHHelper<String> {
    private String base64Image;
    private PersonModel personModel;

    public PersonDH(String base64Image, PersonModel personModel) {
        this.base64Image = base64Image;
        this.personModel = personModel;
    }

    public String getBase64Image() {
        return base64Image;
    }

    public PersonModel getPersonModel() {
        return personModel;
    }

    @Override
    public String getId() {
        return personModel.id;
    }
}
