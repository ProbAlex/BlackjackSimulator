package interfaces;

public interface BettingCapable {
    boolean canBet(double amount);
    void placeBet(double amount);
    void winBet(double amount);
    double getBalance();
}
