package com.thinkmobiles.easyerp.presentation.custom.views.alphabet_view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.thinkmobiles.easyerp.R;
import com.thinkmobiles.easyerp.data.model.crm.persons.alphabet.AlphabetItem;

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
        alphabetListAdapter.setOnCardClickListener((view, position, viewType) -> selectLetter(position));

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
        for(AlphabetItem alphabetItem : enabledLetters) {
            for(LetterDH dh : alphabetListAdapter.getListDH()) {
                if(dh.getLetter().equalsIgnoreCase(alphabetItem.id)) {
                    dh.setLetterState(LetterState.ENABLED);
                }
            }
        }
        alphabetListAdapter.notifyDataSetChanged();
    }

    private void selectLetter(int position) {
        LetterDH selectedDH = alphabetListAdapter.getItem(position);
        if(selectedDH.getLetterState() == LetterState.ENABLED) {
            enableItem(position);
            listener.onLetterSelected(selectedDH.getLetter());
        }
    }

    private void enableItem(int position) {
        for(int i = 0; i < alphabetListAdapter.getItemCount(); i++) {
            if(alphabetListAdapter.getItem(i).getLetterState() == LetterState.SELECTED) {
                alphabetListAdapter.getItem(i).setLetterState(LetterState.ENABLED);
                alphabetListAdapter.notifyItemChanged(i);
            }
        }
        alphabetListAdapter.getItem(position).setLetterState(LetterState.SELECTED);
        alphabetListAdapter.notifyItemChanged(position);
    }

    private void initBaseLetters() {
        ArrayList<LetterDH> allLetters = new ArrayList<>();
        allLetters.add(new LetterDH(LetterState.SELECTED, "All"));
        allLetters.add(new LetterDH("0-9"));
        allLetters.add(new LetterDH("A"));
        allLetters.add(new LetterDH("B"));
        allLetters.add(new LetterDH("C"));
        allLetters.add(new LetterDH("D"));
        allLetters.add(new LetterDH("E"));
        allLetters.add(new LetterDH("F"));
        allLetters.add(new LetterDH("G"));
        allLetters.add(new LetterDH("H"));
        allLetters.add(new LetterDH("I"));
        allLetters.add(new LetterDH("J"));
        allLetters.add(new LetterDH("K"));
        allLetters.add(new LetterDH("L"));
        allLetters.add(new LetterDH("M"));
        allLetters.add(new LetterDH("N"));
        allLetters.add(new LetterDH("O"));
        allLetters.add(new LetterDH("P"));
        allLetters.add(new LetterDH("Q"));
        allLetters.add(new LetterDH("R"));
        allLetters.add(new LetterDH("S"));
        allLetters.add(new LetterDH("T"));
        allLetters.add(new LetterDH("U"));
        allLetters.add(new LetterDH("V"));
        allLetters.add(new LetterDH("W"));
        allLetters.add(new LetterDH("X"));
        allLetters.add(new LetterDH("Y"));
        allLetters.add(new LetterDH("Z"));
        alphabetListAdapter.setListDH(allLetters);
    }
}
