package net.gemelen.blackjack.shell;

import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.ConfigFactory;
import net.gemelen.blackjack.actor.Player;
import net.gemelen.blackjack.data.DataGrid;
import org.apache.felix.shell.Command;

import java.io.PrintStream;

public class JoinCommand implements Command {
    @Override
    public String getName() {
        return "join";
    }

    @Override
    public String getUsage() {
        return "join <player name> <chips amount>";
    }

    @Override
    public String getShortDescription() {
        return "joins new player to game and bets amount of chips";
    }

    @Override
    public void execute(String s, PrintStream printStream, PrintStream errorStream) {
        String[] cs = s.split("\\ ");
        if (cs.length < 3) {
            errorStream.println(getUsage());
        } else {
            try {
                String name = cs[1];    //FIXME
                Integer amount;
                amount = Integer.valueOf(cs[2]);

                final DataGrid dataGrid = DataGrid.getInstance();
                String systemName = dataGrid.getCasino().get("system");
                final ActorSystem system = ActorSystem.create(systemName, ConfigFactory.load());
                system.actorOf(Props.create(Player.class, amount.intValue()));

            } catch (NumberFormatException e) {
                errorStream.println(String.format("Incorrect chips amount argument: %s", cs[2]));
            }
        }
    }
}
