package net.gemelen.blackjack.shell;

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
        return "joins new player to game";
    }

    @Override
    public void execute(String s, PrintStream printStream, PrintStream printStream1) {

    }
}
