package participants;

import models.Wallet;
import interfaces.Displayable;

public class Player extends GameParticipant implements Displayable {
    private Wallet wallet;

    public Player(String name, double startingBalance) {
        super(name);
        this.wallet = new Wallet(startingBalance);
    }

    public Wallet getWallet() {
        return wallet;
    }

    public boolean shouldHit() {
        return false; // Player decides manually
    }

    public void displayStatus() {
        System.out.println("\n" + super.getName() + "'s Hand:");
        System.out.println(hand + " (Value: " + hand.getValue() + ")");
        System.out.printf("Balance: $%.2f\n", wallet.getBalance());
    }
}
