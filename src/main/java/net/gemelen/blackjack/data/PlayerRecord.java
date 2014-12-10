package net.gemelen.blackjack.data;

import akka.actor.ActorRef;

import java.util.List;

public class PlayerRecord {
    private int id;
    private List<Hand> hands;
    private boolean done;
    private long order;
    private ActorRef actor;

    public PlayerRecord(int id, long order) {
        this.id = id;
        this.order = order;
    }

    public int getId() {
        return id;
    }

    public List<Hand> getHands() {
        return hands;
    }

    public void setHands(List<Hand> hands) {
        this.hands = hands;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public long getOrder() {
        return order;
    }

    public ActorRef getActor() {
        return actor;
    }

    public void setActor(ActorRef actor) {
        this.actor = actor;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayerRecord that = (PlayerRecord) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

}
