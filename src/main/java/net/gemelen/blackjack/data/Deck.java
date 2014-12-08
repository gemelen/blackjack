package net.gemelen.blackjack.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private static final int DECK_SIZE = 52;
    private static final int SUIT_SIZE = 13;

    public static enum CARDS {
        ACE(1),
        TWO(2),
        THREE(3),
        FOUR(4),
        FIVE(5),
        SIX(6),
        SEVEN(7),
        EIGHT(8),
        NINE(9),
        TEN(10),
        JACK(10),
        QUEEN(10),
        KING(10);

        private int value;

        CARDS(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    private static final List<CARDS> SUIT = new ArrayList<>(SUIT_SIZE);
    static {
        SUIT.add(CARDS.ACE);
        SUIT.add(CARDS.TWO);
        SUIT.add(CARDS.THREE);
        SUIT.add(CARDS.FOUR);
        SUIT.add(CARDS.FIVE);
        SUIT.add(CARDS.SIX);
        SUIT.add(CARDS.SEVEN);
        SUIT.add(CARDS.EIGHT);
        SUIT.add(CARDS.NINE);
        SUIT.add(CARDS.TEN);
        SUIT.add(CARDS.JACK);
        SUIT.add(CARDS.QUEEN);
        SUIT.add(CARDS.KING);
    };

    private static List<CARDS> stdDeck = new ArrayList<>(DECK_SIZE);
    static {
        stdDeck.addAll(SUIT);
        stdDeck.addAll(SUIT);
        stdDeck.addAll(SUIT);
        stdDeck.addAll(SUIT);
    }

    /**
     * Return shoe full of shuffled cards
     * @param deckAmount - amount of standard decks in shoe
     * @return           - card shoe
     */
    public List<CARDS> getShoe(int deckAmount) {
        List<CARDS> deck = null;

        if (deckAmount > 0) {
            deck = new ArrayList<>(DECK_SIZE * deckAmount);
            for (int i = 0; i < deckAmount; i++) {
                deck.addAll(stdDeck);
            }
            Collections.shuffle(deck);
        }

        return deck;
    }
}
