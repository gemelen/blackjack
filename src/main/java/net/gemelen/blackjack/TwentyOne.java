package net.gemelen.blackjack;

import net.gemelen.blackjack.impl.InstantiateDealerImpl;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import java.util.Hashtable;
import java.util.logging.Logger;

public class TwentyOne implements BundleActivator {
  private final static Logger log = Logger.getAnonymousLogger();

  @Override
  public void start(BundleContext context) throws Exception {
    Hashtable<String, String> props = new Hashtable<String, String>();
    context.registerService(InstantiateDealer.class.getName(), new InstantiateDealerImpl(), props);
    log.fine("started");
  }

  @Override
  public void stop(BundleContext context) throws Exception {
    // stopped
  }
}
