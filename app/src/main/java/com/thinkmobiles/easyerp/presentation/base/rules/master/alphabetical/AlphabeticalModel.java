package com.thinkmobiles.easyerp.presentation.base.rules.master.alphabetical;

import com.thinkmobiles.easyerp.data.model.crm.common.alphabet.ResponseGetAlphabet;
import com.thinkmobiles.easyerp.presentation.base.rules.master.filterable.FilterableModel;

import rx.Observable;

/**
 * @author Alex Michenko (Created on 23.02.17).
 *         Company: Thinkmobiles
 *         Email: alex.michenko@thinkmobiles.com
 */

public interface AlphabeticalModel extends FilterableModel {
    Observable<ResponseGetAlphabet> getAlphabet();
}
