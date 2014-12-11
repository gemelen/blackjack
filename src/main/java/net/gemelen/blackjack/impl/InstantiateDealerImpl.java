package net.gemelen.blackjack.impl;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.osgi.OsgiActorSystemFactory;
import com.typesafe.config.ConfigFactory;
import net.gemelen.blackjack.InstantiateDealer;
import net.gemelen.blackjack.actor.Dealer;
import net.gemelen.blackjack.actor.Messages;
import net.gemelen.blackjack.data.DataGrid;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import scala.Option;

public class InstantiateDealerImpl implements InstantiateDealer {
    private final DataGrid dataGrid = DataGrid.getInstance();

    @Override
    public boolean instantiate(String actorSystemName, String dealerID) {
        boolean result = true;
        final int deckAmount = 2;

        try {
            dataGrid.getCasino().put("systemName", actorSystemName);
            dataGrid.getCasino().put("dealerID", dealerID);

            ClassLoader loader = OsgiActorSystemFactory.akkaActorClassLoader();
            Option<ClassLoader> option = Option.apply(loader);
            BundleContext context = FrameworkUtil.getBundle(this.getClass()).getBundleContext();
            OsgiActorSystemFactory factory = new OsgiActorSystemFactory(context, option, ConfigFactory.load());
            final ActorSystem system = factory.createActorSystem(actorSystemName);
            final ActorRef dealer = system.actorOf(Props.create(Dealer.class, deckAmount), dealerID);
            dealer.tell(new Messages.JoinSelf(), ActorRef.noSender());
        } catch (Throwable t) {
            t.printStackTrace();
            System.out.println(t.getMessage());
            result = false;
        }

        return result;
    }
}
