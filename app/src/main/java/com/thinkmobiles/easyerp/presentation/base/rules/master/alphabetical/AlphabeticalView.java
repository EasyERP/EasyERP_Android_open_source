package com.thinkmobiles.easyerp.presentation.base.rules.master.alphabetical;

import com.thinkmobiles.easyerp.data.model.crm.common.alphabet.AlphabetItem;
import com.thinkmobiles.easyerp.presentation.base.rules.master.filterable.FilterableView;

import java.util.ArrayList;

/**
 * @author Alex Michenko (Created on 23.02.17).
 *         Company: Thinkmobiles
 *         Email: alex.michenko@thinkmobiles.com
 */

public interface AlphabeticalView extends FilterableView {
    void displayEnabledLetters(ArrayList<AlphabetItem> enabledAlphabetItems);
    void displaySelectedLetter(String selectedLetter);
}
