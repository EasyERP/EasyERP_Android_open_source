package com.thinkmobiles.easyerp.presentation.custom.views.alphabet_view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.thinkmobiles.easyerp.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Lynx on 1/20/2017.
 */

@EViewGroup(R.layout.view_alphabet)
public class AlphabetView extends FrameLayout {

    @ViewById
    protected RecyclerView rvAlphabet_VA;

    @AfterViews
    protected void initUI() {
        
    }

    public AlphabetView(Context context) {
        super(context);
    }

    public AlphabetView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
}
