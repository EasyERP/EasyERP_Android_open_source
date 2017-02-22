package com.thinkmobiles.easyerp.presentation.custom.views.alphabet_view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.data.model.crm.common.alphabet.AlphabetItem;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

/**
 * Created by Lynx on 1/20/2017.)
 */

@EViewGroup(R.layout.view_alphabet)
public class AlphabetView extends FrameLayout {

    private OnLetterSelectedListener listener;

    @Bean
    protected AlphabetListAdapter alphabetListAdapter;

    @ViewById
    protected RecyclerView rvAlphabet_VA;

    @AfterViews
    protected void initUI() {
        initBaseLetters();
        alphabetListAdapter.setOnCardClickListener((view, position, viewType) -> listener.onLetterSelected(alphabetListAdapter.getItem(position).getLetter()));

        rvAlphabet_VA.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rvAlphabet_VA.setAdapter(alphabetListAdapter);
    }

    public AlphabetView(Context context) {
        super(context);
    }

    public AlphabetView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setListener(OnLetterSelectedListener listener) {
        this.listener = listener;
    }

    public void setEnabledLetters(ArrayList<AlphabetItem> enabledLetters) {
        for (AlphabetItem alphabetItem : enabledLetters) {
            for (LetterDH dh : alphabetListAdapter.getListDH()) {
                if (dh.getLetter().equalsIgnoreCase(alphabetItem.id)) {
                    dh.setEnabled(true);
                }
            }
        }
        alphabetListAdapter.updateList();
    }

    public void selectLetterWithoutListener(String letter) {
        for (LetterDH dh : alphabetListAdapter.getListDH()) {
            if (letter.equalsIgnoreCase(dh.getLetter())) {
                dh.setSelected(true);
            } else {
                dh.setSelected(false);
            }
        }
        alphabetListAdapter.updateList();
    }

    private void initBaseLetters() {
        ArrayList<LetterDH> allLetters = new ArrayList<>();
        allLetters.add(new LetterDH("All", true));
        allLetters.add(new LetterDH("0-9"));

        for (char c = 'A'; c <= 'Z'; c++) {
            allLetters.add(new LetterDH(String.valueOf(c)));
        }
        alphabetListAdapter.setListDH(allLetters);
    }
}
