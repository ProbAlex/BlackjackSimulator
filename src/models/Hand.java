package models;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private List<Card> cards;

    public Hand() {
        cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int getValue() {
        int value = 0;
        int aceCount = 0;

        for (Card card : cards) {
            value += card.getValue();
            if (card.getValue() == 11) {
                aceCount++;
            }
        }

        while (value > 21 && aceCount > 0) { //handles aces being both 11 & 1
            value -= 10;
            aceCount--;
        }

        return value;
    }

    public boolean isBusted() {
        return getValue() > 21;
    }

    public boolean isBlackjack() {
        return getValue() == 21;
    }

    public void clear() {
        cards.clear();
    }

    public List<Card> getCards() {
        return cards;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Card card : cards) {
            sb.append(card).append(", ");
        }
        if (!sb.isEmpty()) {
            sb.setLength(sb.length() - 2); //removes last comma
        }
        return sb.toString();
    }
}
