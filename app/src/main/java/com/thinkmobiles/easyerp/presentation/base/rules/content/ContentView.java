package com.thinkmobiles.easyerp.presentation.base.rules.content;

import com.thinkmobiles.easyerp.presentation.utils.Constants;

/**
 * @author Alex Michenko (Created on 22.02.17).
 *         Company: Thinkmobiles
 *         Email: alex.michenko@thinkmobiles.com
 */

public interface ContentView {
    void displayErrorState(final Constants.ErrorType errorType);
    void displayErrorToast(final Constants.ErrorType errorType);
    void showProgress(Constants.ProgressType type);
}
