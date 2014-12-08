package net.gemelen.blackjack;

public interface InstantiateDealer {
    /**
     * Creates a dealer actor with the specified name in the specified ActorSystem
     * @param actorSystemName   -
     * @param dealerID          -
     * @return                  - true in case of successful creation, false otherwise
     */
    boolean instantiate(String actorSystemName, String dealerID);
}
