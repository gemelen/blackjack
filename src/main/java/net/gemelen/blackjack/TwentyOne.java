package net.gemelen.blackjack;

import net.gemelen.blackjack.impl.InstantiateDealerImpl;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import java.util.Hashtable;

public class TwentyOne implements BundleActivator {
  @Override
  public void start(BundleContext context) throws Exception {
    Hashtable<String, String> props = new Hashtable<String, String>();
    context.registerService(InstantiateDealer.class.getName(), new InstantiateDealerImpl(), props);
    System.out.println("Blackjack: bundle started");
  }

  @Override
  public void stop(BundleContext context) throws Exception {
    // stopped
  }
}
