package net.gemelen.blackjack;

import net.gemelen.blackjack.impl.InstantiateDealerImpl;
import net.gemelen.blackjack.shell.JoinCommand;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;


public class TwentyOne implements BundleActivator {
  @Override
  public void start(BundleContext context) throws Exception {
    context.registerService(InstantiateDealer.class.getName(), new InstantiateDealerImpl(), null);

    context.registerService(org.apache.felix.shell.Command.class.getName(), new JoinCommand(), null);
    System.out.println("Blackjack: bundle started");
  }

  @Override
  public void stop(BundleContext context) throws Exception {
    // stopped
  }
}
