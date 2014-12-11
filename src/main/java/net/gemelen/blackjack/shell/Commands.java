package net.gemelen.blackjack.shell;

import akka.actor.ActorSystem;
import akka.actor.Props;
import net.gemelen.blackjack.InstantiateDealer;
import net.gemelen.blackjack.actor.Player;
import net.gemelen.blackjack.data.DataGrid;
import net.gemelen.blackjack.impl.InstantiateDealerImpl;
import org.osgi.framework.BundleContext;

public class Commands {
    public void init(String[] args) {
        if (args.length < 2) {
            System.out.println("instantiate <actorSystem name> <dealer Id>");
        } else {
            InstantiateDealer impl = new InstantiateDealerImpl();
            String res = impl.instantiate(args[0], args[1]) ? "instantiated" : "not instantiated";
            System.out.println(res);
        }
    }

    public void join(String[] args) {
        if (args.length < 2) {
            System.out.println("join <player name> <chips amount>");
        } else {
            try {
                Integer amount;
                amount = Integer.valueOf(args[1]);

                final DataGrid dataGrid = DataGrid.getInstance();
                ActorSystem system = (ActorSystem) dataGrid.getCasino().get("system");
                system.actorOf(Props.create(Player.class, amount.intValue()));
            } catch (NumberFormatException e) {
                System.out.println(String.format("Incorrect chips amount argument: %s", args[1]));
            }
        }
    }
}
