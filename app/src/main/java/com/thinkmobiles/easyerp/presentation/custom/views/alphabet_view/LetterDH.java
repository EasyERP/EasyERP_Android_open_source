package com.thinkmobiles.easyerp.presentation.custom.views.alphabet_view;

import com.michenko.simpleadapter.RecyclerDH;

/**
 * Created by Lynx on 1/20/2017.
 */

public class LetterDH extends RecyclerDH {

    private String letter;
    private boolean enabled;
    private boolean selected;

    public LetterDH(String letter) {
        this(letter, false);
    }

    public LetterDH(String letter, boolean enabled) {
        this.letter = letter;
        this.enabled = enabled;
    }

    public String getLetter() {
        return letter.trim();
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
