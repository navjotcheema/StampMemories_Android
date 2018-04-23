package com.stampmemories.sectioned;

/**
 * Created by AppsterBiz on 09-Mar-18.
 */

public class SectionSlots {
    private int numSlotsLeft, slotsToBook;

    public SectionSlots(int numSlotsLeft, int slotsToBook) {
        this.numSlotsLeft = numSlotsLeft;
        this.slotsToBook = slotsToBook;
    }

    public int getNumSlotsLeft() {
        return numSlotsLeft;
    }

    public void setNumSlotsLeft(int numSlotsLeft) {
        this.numSlotsLeft = numSlotsLeft;
    }

    public int getSlotsToBook() {
        return slotsToBook;
    }

    public void setSlotsToBook(int slotsToBook) {
        this.slotsToBook = slotsToBook;
    }
}
