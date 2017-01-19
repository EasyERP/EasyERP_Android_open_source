package com.thinkmobiles.easyerp.presentation.base.rules;

import android.support.annotation.DrawableRes;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.thinkmobiles.easyerp.R;

import org.androidannotations.annotations.EBean;

/**
 * Created by Asus_Dev on 1/19/2017.
 */
@EBean
public class ErrorViewHelper {

    private View rootLayout;
    private ImageView placeholderIconView;
    private TextView errorMsgView;

    public void init(final View errorLayout, final View.OnClickListener tryAgainListener) {
        rootLayout = errorLayout;
        if (rootLayout != null) {
            placeholderIconView = (ImageView) rootLayout.findViewById(R.id.ivPlaceholderIcon_VEWHI);
            errorMsgView = (TextView) rootLayout.findViewById(R.id.tvErrorLabel_VEWHI);

            if (tryAgainListener != null)
                rootLayout.findViewById(R.id.btnTryAgain_CEWHI).setOnClickListener(tryAgainListener);
        }
    }

    public void showErrorMsg(final String msg, final ErrorType errorType) {
        if (rootLayout != null) {
            placeholderIconView.setImageResource(getPlaceholderIcon(errorType));
            errorMsgView.setText(msg);
            rootLayout.setVisibility(View.VISIBLE);
        }
    }

    public void hideError() {
        if (rootLayout != null) {
            placeholderIconView.setImageResource(0);
            errorMsgView.setText(null);
            rootLayout.setVisibility(View.GONE);
        }
    }

    private @DrawableRes int getPlaceholderIcon(final ErrorType errorType) {
        switch (errorType) {
            case LIST_EMPTY:
                return R.drawable.ic_empty_list;
            case NETWORK:
                return R.drawable.ic_error;
        }
        return 0;
    }

    public enum ErrorType {
        LIST_EMPTY, NETWORK
    }

}
