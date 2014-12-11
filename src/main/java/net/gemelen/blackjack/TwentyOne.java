package net.gemelen.blackjack;

import net.gemelen.blackjack.data.DataGrid;
import net.gemelen.blackjack.shell.Commands;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import java.util.Dictionary;
import java.util.Hashtable;

public class TwentyOne implements BundleActivator{
    @Override
    public void start(BundleContext context) {
        Dictionary<String, Object> dict = new Hashtable<>();
        dict.put("osgi.command.scope", "bj");
        dict.put("osgi.command.function", new String[]{"init", "join"});
        context.registerService(Commands.class.getName(), new Commands(), dict);
    }

    @Override
    public void stop(BundleContext context) {
        DataGrid.getInstance().stop();
    }
}
