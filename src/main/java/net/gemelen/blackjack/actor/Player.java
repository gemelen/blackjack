package net.gemelen.blackjack.actor;

import akka.actor.UntypedActor;
import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import net.gemelen.blackjack.data.Deck;
import net.gemelen.blackjack.data.PlayerRecordView;

import java.util.Map;

import static net.gemelen.blackjack.actor.Messages.*;

public class Player extends UntypedActor {
    private int amount;
    private final int id;
    private final String dealerName;
    private HazelcastInstance grid;
    private Map<Integer, PlayerRecordView> players;

    public Player(String dealerName, int amount) {
        this.dealerName = dealerName;
        this.amount = amount;
        this.id = this.hashCode();

        Config cfg = new Config();
        this.grid = Hazelcast.newHazelcastInstance(cfg);
    }

    public void join() {
        Join join = new Join(this.id);
        getContext().system().actorSelection(String.format("/user/%s", this.dealerName)).tell(join, self());
    }

    public void placeBet(int amount) {
        Bet bet = new Bet(amount, this.id);
        getContext().system().actorSelection(String.format("/user/%s", this.dealerName)).tell(bet, self());
    }

    public void makeDecision(int handId, Deck.CARDS card) {
        players = this.grid.getMap("players");
        PlayerRecordView me = players.get(this.id);
        if (me != null) {

        }
    }

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof DealCard) {
            DealCard dealCard = (DealCard) message;
            makeDecision(dealCard.getHandId(), dealCard.getCard());
        } else if (message instanceof Win) {
            Win win = (Win) message;
            this.amount += win.getAmount();
        } else if (message instanceof Busted) {
            Busted busted = (Busted) message;
            PlayerRecordView me = players.get(this.id);
            this.amount -= me.getHandById(busted.getHandId()).getBetAmount();
        } else {
            unhandled(message);
        }
    }
}
