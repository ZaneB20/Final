import java.util.*;

public class Deck {
    private List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        setDefaultDeck();
    }

    private void setDefaultDeck() {
        cards.add(new Fighter());
        cards.add(new Fighter());
        cards.add(new ShieldBearer());
        cards.add(new ShieldBearer());
        cards.add(new Healer());
    }

    public void shuffle() {
        Collections.shuffle(cards);
        System.out.println("Deck shuffled!");
    }

    public Card drawCard() {
        if (!cards.isEmpty()) {
            return cards.remove(0);
        }
        return null;
    }

    public void showDeck() {
        for (Card card : cards) {
            card.display();
        }
    }
}

