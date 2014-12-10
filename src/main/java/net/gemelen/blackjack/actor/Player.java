package net.gemelen.blackjack.actor;

import akka.actor.UntypedActor;
import net.gemelen.blackjack.data.DataGrid;
import net.gemelen.blackjack.data.PlayerRecordView;

import java.util.Map;

import static net.gemelen.blackjack.actor.Messages.*;

public class Player extends UntypedActor {
    private int amount;
    private final int id;
    private DataGrid grid = DataGrid.getInstance();
    private final String dealerName = grid.getCasino().get("dealer");
    private Map<Integer, PlayerRecordView> players = grid.getPlayers();

    public Player(int amount) {
        this.amount = amount;
        this.id = this.hashCode();
        join();
        placeBet(amount);
    }

    public void join() {
        Join join = new Join(this.id);
        getContext().system().actorSelection(String.format("/user/%s", this.dealerName)).tell(join, self());
    }

    public void placeBet(int amount) {
        Bet bet = new Bet(amount, this.id);
        getContext().system().actorSelection(String.format("/user/%s", this.dealerName)).tell(bet, self());
    }

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof DealCard) {
            DealCard dealCard = (DealCard) message;

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
