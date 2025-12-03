package participants;

import models.Wallet;

public class Player extends GameParticipant {
    private Wallet wallet;

    public Player(String name, double startingBalance) {
        super(name);
        this.wallet = new Wallet(startingBalance);
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void displayStatus() {
        System.out.println("\n" + name + "'s Hand:");
        System.out.println(hand + " (Value: " + hand.getValue() + ")");
        System.out.printf("Balance: $%.2f\n", wallet.getBalance());
    }
}
