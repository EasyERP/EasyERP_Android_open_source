package com.thinkmobiles.easyerp.presentation.base.rules.master.selectable;

import com.thinkmobiles.easyerp.presentation.base.rules.master.list.MasterListView;

/**
 * @author Alex Michenko (Created on 22.02.17).
 *         Company: Thinkmobiles
 *         Email: alex.michenko@thinkmobiles.com
 */

public interface SelectableView extends MasterListView {
    void clearSelectedItem();
    void changeSelectedItem(int oldPosition, int newPosition);
    boolean withItemSelecting();
}
