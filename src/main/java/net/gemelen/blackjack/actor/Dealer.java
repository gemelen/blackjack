package net.gemelen.blackjack.actor;

import akka.actor.UntypedActor;
import net.gemelen.blackjack.data.*;
import java.util.*;
import static net.gemelen.blackjack.actor.Messages.*;

public class Dealer extends UntypedActor {
    private List<Deck.CARDS> shoe;
    private Map<Integer, PlayerRecord> players;
    private Map<Integer, PlayerRecordView> playersView;

    public Dealer(int shoeSize) {
        this.shoe = new Deck().getShoe(shoeSize);

        DataGrid grid = new DataGrid();

        this.players = new HashMap<>();
        this.playersView = grid.getPlayers();

        // join dealer
        getSelf().tell(new Join(0), getSelf());

        PlayerRecord me = players.get(0);
        Hand hand = new Hand(0, 36);
        List<Hand> hands = new ArrayList<>();
        hands.add(hand);
        me.setHands(hands);

        // deal first card to dealer hand
        getSelf().tell(new Hit(0, me.getHands().get(0).getHandId()), getSelf());
    }

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof Join) {
            Join player = (Join) message;
            joinPlayer(player.getId());

        } else if (message instanceof Bet) {
            Bet bet = (Bet) message;
            placeBet(bet.getAmount(), bet.getId());

        } else if (message instanceof Hit) {
            Hit hit = (Hit) message;
            Deck.CARDS next = getNextCard();
            dealCard(next, hit.getId(), hit.getHandId());

            getSender().tell(new Messages.DealCard(next, hit.getHandId()), self());

        } else if (message instanceof Stand) {
            Stand stand = (Stand) message;
            PlayerRecord player = players.get(stand.getId());
            player.setDone(true);
            for (Hand hand : player.getHands()) {
                hand.setDone(true);
            }

            if (allDone()) {
                getSelf().tell(new Resolve(), self());
            }
        } else if (message instanceof Resolve) {
            for (PlayerRecord playerRecord : players.values()) {
                for (Hand hand : playerRecord.getHands()) {
                    if (hand.getValue() > 21) {
//                        Busted busted = new Busted();
//                        getContext().system().actorSelection(String.format("/users/%s/*", getSelf().path().name())).tell();
                        hand.getHandId();
                    }
                }
            }
            // resolve and send win/busted messages
        } else {
            unhandled(message);
        }
    }

    private Deck.CARDS getNextCard() {
        Deck.CARDS card;
        try {
            card = this.shoe.get(this.shoe.size() - 1);
            this.shoe.remove(this.shoe.size() - 1);
        } catch (Exception e) {
            card = null;
        }
        return card;
    }

    private void joinPlayer(int playerId) {
        PlayerRecord record = new PlayerRecord(playerId, System.currentTimeMillis());
        this.players.put(playerId, record);
        this.playersView.put(playerId, new PlayerRecordView(record));
    }

    private void dealCard(Deck.CARDS card, int playerId, int handId) {
        PlayerRecord player = players.get(playerId);
        player.getHandById(handId).addCard(card);
        this.playersView.remove(player.getId());
        this.playersView.put(player.getId(), new PlayerRecordView(player));
    }

    private boolean allDone() {
        boolean result = true;
        for (PlayerRecord record : this.players.values()) {
            result = result && record.isDone();
        }
        return result;
    }

    private void placeBet(int amount, int playerId) {
        PlayerRecord player = this.players.get(playerId);
        if (player != null) {
            Hand hand = new Hand(amount, playerId + 36);
            List<Hand> hands = player.getHands();
            if (hands == null) {
                hands = new ArrayList<>();
            }
            hands.add(hand);
            player.setHands(hands);
            this.playersView.remove(playerId);
            this.playersView.put(playerId, new PlayerRecordView(player));
        }
    }
}
