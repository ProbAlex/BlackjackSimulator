package game;

import models.Deck;
import participants.Player;
import participants.Dealer;
import java.util.Scanner;

public class Game {
    private Deck deck;
    private Player player;
    private Dealer dealer;
    private Scanner scanner;

    public Game() {
        deck = new Deck();
        player = new Player("Player", 1000.0);
        dealer = new Dealer();
        scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("=================================");
        System.out.println("   Welcome to Blackjack!");
        System.out.println("=================================\n");

        while (player.getWallet().getBalance() > 0) { //gameplay loop
            playRound();
            if (player.getWallet().getBalance() > 0) {
                System.out.print("\nPlay another round? (y/n): ");
                String choice = scanner.nextLine().trim().toLowerCase();
                if (!choice.equals("y")) {
                    break;
                }
                System.out.println();
            }
        }

        System.out.println("\n=================================");
        System.out.println("   Game Over!");
        System.out.printf("   Final Balance: $%.2f\n", player.getWallet().getBalance());
        System.out.println("=================================");
    }

    private void playRound() {
        player.clearHand();
        dealer.clearHand();

        System.out.printf("Balance: $%.2f\n", player.getWallet().getBalance());
        System.out.print("Enter bet amount: $");

        double bet;
        try {
            bet = scanner.nextDouble();
            if (!player.getWallet().canBet(bet) || bet <= 0) {
                System.out.println("Invalid bet amount!");
                return;
            }
            player.getWallet().placeBet(bet);
        } catch (Exception e){
            System.out.println("Invalid bet amount!");
            return;
        }

        // Initial deal (alternating like normal dealing)
        player.getHand().addCard(deck.drawCard());
        dealer.getHand().addCard(deck.drawCard());
        player.getHand().addCard(deck.drawCard());
        dealer.getHand().addCard(deck.drawCard());

        displayHands(true);

        // Check for blackjack, and ends function if blackjack
        if (checkBlackjack(bet)){return;}

        // Player's turn -- loop exits once the player stands or busts
        while (!player.getHand().isBusted()) {
            System.out.print("\nHit or Stand? (h/s): ");
            String choice = scanner.nextLine().trim().toLowerCase();

            if (choice.equals("h")) {
                player.getHand().addCard(deck.drawCard());
                displayHands(true);

                if (player.getHand().isBusted()) {
                    System.out.println("\nBust! You lose!");
                    return;
                }
                if (checkBlackjack(bet)){return;}

            } else if (choice.equals("s")) {
                break;
            } else {
                System.out.println("Invalid choice!");
            }
        }

        // Dealer's turn
        System.out.println("\nDealer's turn...");
        displayHands(false);

        while (dealer.shouldHit()) {
            dealer.getHand().addCard(deck.drawCard());
            System.out.println("Dealer hits!");
            displayHands(false);
        }

        // Determine winner
        determineWinner(bet);
    }

    private void displayHands(boolean hideDealer) {
        System.out.println("\n---------------------------------");
        if (hideDealer) {
            dealer.displayStatusHidden();
        } else {
            dealer.displayStatus();
        }
        player.displayStatus();
        System.out.println("---------------------------------");
    }

    private void determineWinner(double bet) {
        int playerValue = player.getHand().getValue();
        int dealerValue = dealer.getHand().getValue();

        System.out.println();
        if (dealer.getHand().isBusted()) {
            System.out.println("Dealer busts! You win!");
            player.getWallet().winBet(bet);
        } else if (playerValue > dealerValue) {
            System.out.println("You win!");
            player.getWallet().winBet(bet);
        } else if (playerValue < dealerValue) {
            System.out.println("Dealer wins!");
        } else {
            System.out.println("Push!");
            player.getWallet().winBet(bet); //pushes are rigged in player's favor
        }
    }

    private boolean checkBlackjack(double bet){
        if (player.getHand().isBlackjack()) {
            if (dealer.getHand().isBlackjack()) {
                System.out.println("\nBoth have Blackjack!");
                player.getWallet().winBet(bet);
            } else {
                System.out.println("\nBlackjack! You win!");
                player.getWallet().winBet(bet * 2);
            }
            return true;
        }
        return false;
    }
}
