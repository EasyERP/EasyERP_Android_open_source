package com.thinkmobiles.easyerp.presentation.base;

import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

/**
 * @author Michael Soyma (Created on 5/10/2017).
 *         Company: Thinkmobiles
 *         Email:  michael.soyma@thinkmobiles.com
 */
public interface BaseMasterFlowDelegate {
    boolean isTablet();
    boolean isPortrait();
    Toolbar getToolbar();
    Toolbar getToolbarDetail();
    void resetDetailToolbarToBase();
    void resetToolbar(Menu menu);
    void replaceFragmentContent(final BaseMasterFlowFragment fragment, final String title);
    void replaceFragmentContentDetail(final BaseMasterFlowFragment fragment);
    boolean onOptionsItemSelected(MenuItem item);
}