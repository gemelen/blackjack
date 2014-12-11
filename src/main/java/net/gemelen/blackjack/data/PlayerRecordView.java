package net.gemelen.blackjack.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayerRecordView {
    private int id;
    private List<Hand> hands;

    public PlayerRecordView(int id) {
        this.id = id;
    }

    public PlayerRecordView(PlayerRecord record) {
        this.id = record.getId();
        this.hands = new ArrayList<>(record.getHands() == null ? 0 : record.getHands().size());
        if (record.getHands() != null ) {
            Collections.copy(this.hands, record.getHands());
        }
        // if player is dealer then we should fake his first card with CARDS,HOLECARD in shared players list
    }

    public List<Hand> getHands() {
        return hands;
    }

    public void setHands(List<Hand> hands) {
        this.hands = hands;
    }

    public Hand getHandById(int handId) {
        Hand current = null;
        for (Hand hand : hands) {
            if (hand.getHandId() == handId) {
                current = hand;
            }
        }

        return current;
    }
}
