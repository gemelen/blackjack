package net.gemelen.blackjack;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.ConfigFactory;
import net.gemelen.blackjack.actor.Dealer;
import net.gemelen.blackjack.data.DataGrid;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class TwentyOne implements InstantiateDealer, BundleActivator {
  @Override
  public boolean instantiate(String actorSystemName, String dealerID) {
    boolean result = true;
    final int deckAmount = 2;

    try {
      final DataGrid dataGrid = new DataGrid();
      dataGrid.getCasino().put("system", actorSystemName);
      dataGrid.getCasino().put("dealer", dealerID);

      final ActorSystem system = ActorSystem.create(actorSystemName, ConfigFactory.load());
      final ActorRef dealer = system.actorOf(Props.create(Dealer.class, deckAmount), dealerID);
    } catch (Throwable t) {
      result = false;
    }

    return result;
  }

  @Override
  public void start(BundleContext context) throws Exception {
    // started
  }

  @Override
  public void stop(BundleContext context) throws Exception {
    // stopped
  }
}
