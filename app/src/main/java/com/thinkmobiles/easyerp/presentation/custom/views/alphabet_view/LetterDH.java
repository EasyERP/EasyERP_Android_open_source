package com.thinkmobiles.easyerp.presentation.custom.views.alphabet_view;

import com.michenko.simpleadapter.RecyclerDH;

/**
 * Created by Lynx on 1/20/2017.
 */

public class LetterDH extends RecyclerDH {

    private String letter;
    private LetterState letterState = LetterState.DISABLED;

    public LetterDH(String letter) {
        this.letter = letter;
    }

    public LetterDH(LetterState letterState, String letter) {
        this.letterState = letterState;
        this.letter = letter;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public LetterState getLetterState() {
        return letterState;
    }

    public void setLetterState(LetterState letterState) {
        this.letterState = letterState;
    }
}
