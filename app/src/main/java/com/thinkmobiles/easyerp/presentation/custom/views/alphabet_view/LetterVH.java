package com.thinkmobiles.easyerp.presentation.custom.views.alphabet_view;

import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.michenko.simpleadapter.OnCardClickListener;
import com.michenko.simpleadapter.RecyclerVH;
import com.thinkmobiles.easyerp.R;

/**
 * Created by Lynx on 1/20/2017.
 */

public class LetterVH extends RecyclerVH<LetterDH> {

    private TextView tvLetter_LIL;

    public LetterVH(View itemView, @Nullable OnCardClickListener listener, int viewType) {
        super(itemView, listener, viewType);

        tvLetter_LIL = findView(R.id.tvLetter_LIL);
    }

    @Override
    public void bindData(LetterDH data) {
        tvLetter_LIL.setText(data.getLetter());
        switch (data.getLetterState()) {
            case ENABLED:
                tvLetter_LIL.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.color_text_black));
                break;
            case DISABLED:
                tvLetter_LIL.setTextColor(ContextCompat.getColor(itemView.getContext(), android.R.color.darker_gray));
                break;
            case SELECTED:
                tvLetter_LIL.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.color_blue));
                break;
        }
    }
}
