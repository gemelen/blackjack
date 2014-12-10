package net.gemelen.blackjack.impl;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.ConfigFactory;
import net.gemelen.blackjack.InstantiateDealer;
import net.gemelen.blackjack.actor.Dealer;
import net.gemelen.blackjack.data.DataGrid;

public class InstantiateDealerImpl implements InstantiateDealer {
    @Override
    public boolean instantiate(String actorSystemName, String dealerID) {
        boolean result = true;
        final int deckAmount = 2;

        try {
            final DataGrid dataGrid = DataGrid.getInstance();
            dataGrid.getCasino().put("system", actorSystemName);
            dataGrid.getCasino().put("dealer", dealerID);

            final ActorSystem system = ActorSystem.create(actorSystemName, ConfigFactory.load());
            final ActorRef dealer = system.actorOf(Props.create(Dealer.class, deckAmount), dealerID);
        } catch (Throwable t) {
            result = false;
        }

        return result;
    }
}
