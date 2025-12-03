package participants;


public class Dealer extends GameParticipant {

    public Dealer() {
        super("Dealer");
    }

    public boolean shouldHit() {
        return hand.getValue() < 17;
    }

    public void displayStatus() {
        System.out.println("\n" + super.getName() + "'s Hand:");
        System.out.println(hand + " (Value: " + hand.getValue() + ")");
    }

    public void displayStatusHidden() {
        System.out.println("\n" + name + "'s Hand:");
        System.out.println(hand.getCards().getFirst() + ", [Hidden]");
    }
}
