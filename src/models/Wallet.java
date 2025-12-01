package models;

import interfaces.BettingCapable;

public class Wallet implements BettingCapable {
    private double balance;

    public Wallet(double startingBalance) {
        this.balance = startingBalance;
    }

    public double getBalance() {
        return balance;
    }

    public boolean canBet(double amount) {
        return balance >= amount;
    }

    public void placeBet(double amount) {
        if (!canBet(amount)) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        balance -= amount;
    }

    public void winBet(double amount) {
        balance += 2*amount;
    }
}
