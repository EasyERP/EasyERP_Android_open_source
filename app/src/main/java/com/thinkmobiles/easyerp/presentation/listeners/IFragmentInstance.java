package com.thinkmobiles.easyerp.presentation.listeners;

import android.os.Bundle;

import com.thinkmobiles.easyerp.presentation.base.BaseMasterFlowFragment;

import java.io.Serializable;

/**
 * @author Alex Michenko (Created on 28.02.17).
 *         Company: Thinkmobiles
 *         Email: alex.michenko@thinkmobiles.com
 */

public interface IFragmentInstance extends Serializable {
    BaseMasterFlowFragment getInstance(Bundle args);
}
