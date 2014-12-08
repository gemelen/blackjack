package net.gemelen.blackjack.data;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private List<Deck.CARDS> cards = new ArrayList<>();
    private int value = 0;
    private int betAmount;
    private int handId;
    private boolean done;

    public Hand(int betAmount, int handId) {
        this.betAmount = betAmount;
        this.handId = handId;
    }

    public int getValue() {
        return value;
    }

    public boolean isBlackJack() {
        return cards.size() == 2 && this.value == 21;
    }

    public boolean isBusted() {
        return this.value > 21;
    }

    public int getBetAmount() {
        return betAmount;
    }

    public int getHandId() {
        return handId;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public void addCard(Deck.CARDS card) {
        this.cards.add(card);
        this.value += card.getValue();
    }
}
