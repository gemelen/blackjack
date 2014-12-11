package net.gemelen.blackjack.actor;

import net.gemelen.blackjack.data.Deck;

public class Messages {
    /**
     * outer -> Dealer
     */
    public static final class GoToWork {}
    public static final class JoinSelf {}

    /**
     * Player -> Dealer on join to game
     */
    public static final class Join {
        private int id;

        public Join(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }
    }

    /**
     * Player -> Dealer on place a bet
     */
    public static final class Bet {
        private int amount;
        private int id;

        public Bet(int amount, int id) {
            this.amount = amount;
            this.id = id;
        }

        public int getAmount() {
            return amount;
        }

        public int getId() {
            return id;
        }
    }

    /**
     * Player -> Dealer: take another card
     */
    public static final class Hit {
        private int id;
        private int handId;

        public Hit(int id, int handId) {
            this.id = id;
            this.handId = handId;
        }

        public int getId() {
            return id;
        }

        public int getHandId() {
            return handId;
        }
    }

    /**
     * Player -> Dealer: ends the turn
     */
    public static final class Stand {
        private int id;
        private int handId;

        public Stand(int id, int handId) {
            this.id = id;
            this.handId = handId;
        }

        public int getId() {
            return id;
        }

        public int getHandId() {
            return handId;
        }
    }

    /**
     * Dealer -> Player
     */
    public static final class DealCard {
        private Deck.CARDS card;
        private int handId;

        public DealCard(Deck.CARDS card, int handId) {
            this.card = card;
            this.handId = handId;
        }

        public Deck.CARDS getCard() {
            return card;
        }

        public int getHandId() {
            return handId;
        }
    }

    /**
     * Dealer -> Dealer
     */
    public static final class PlayNext {}

    /**
     * Dealer -> Dealer
     */
    public static final class Resolve {}

    /**
     * Dealer -> Player
     */
    public static final class Busted {
        private int handId;

        public Busted(int handId) {
            this.handId = handId;
        }

        public int getHandId() {
            return handId;
        }
    }

    public static class Win {
        private int amount;
        private boolean isTie;

        public Win(int amount, boolean isTie) {
            this.amount = amount;
            this.isTie = isTie;
        }

        public int getAmount() {
            return amount;
        }

        public boolean isTie() {
            return isTie;
        }
    }
}
