package participants;

import models.Hand;

public abstract class GameParticipant {
    protected Hand hand;
    protected String name;

    public GameParticipant(String name) {
        this.name = name;
        this.hand = new Hand();
    }

    public Hand getHand() {
        return hand;
    }

    public String getName() {
        return name;
    }

    public void clearHand() {
        hand.clear();
    }
}
