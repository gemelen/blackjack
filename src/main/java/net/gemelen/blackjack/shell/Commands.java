package net.gemelen.blackjack.shell;

import akka.actor.ActorSystem;
import akka.actor.Props;
import net.gemelen.blackjack.InstantiateDealer;
import net.gemelen.blackjack.actor.Player;
import net.gemelen.blackjack.data.DataGrid;
import net.gemelen.blackjack.impl.InstantiateDealerImpl;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

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
                String systemName = (String) dataGrid.getCasino().get("systemName");
                BundleContext context = FrameworkUtil.getBundle(this.getClass()).getBundleContext();
                ServiceReference ref = context.getServiceReference(ActorSystem.class.getName());
                ActorSystem system = (ActorSystem) context.getService(ref);
                system.actorOf(Props.create(Player.class, amount.intValue()));
            } catch (NumberFormatException e) {
                System.out.println(String.format("Incorrect chips amount argument: %s", args[1]));
            }
        }
    }
}
