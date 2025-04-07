import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Character {
    String name;
    int hp;
    int shield;

    public Character(String name, int hp) {
        this.name = name;
        this.hp = hp;
        this.shield = 0;
    }

    public void report() {
        System.out.println("\n" + name);
        System.out.println("hp: " + hp);
        System.out.println("shield: " + shield);
    }

    public void apply(Card card) {
        this.hp -= card.damage;
        this.shield += card.shield;
    }
}

class Card {
    String name;
    int damage;
    int shield;
    int state; // 0 = DECK, 1 = HAND, 2 = DISCARD

    public Card(String name, int damage, int shield) {
        this.name = name;
        this.damage = damage;
        this.shield = shield;
        this.state = 0; // Initially in deck
    }

    public void apply(Character character) {
        character.hp -= damage;
        character.shield += shield;
    }

    public void display() {
        System.out.println("\n" + name);
        System.out.println("Damage amount: " + damage);
        System.out.println("Shield amount: " + shield);
    }
}

class Fighter extends Card {
    public Fighter() {
        super("Fighter", 3, 0);
    }
}

class ShieldBearer extends Card {
    public ShieldBearer() {
        super("ShieldBearer", 0, 3);
    }
}

class Healer extends Card {
    public Healer() {
        super("Healer", -2, 0);
    }
}

class Deck {
    List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        setDefaultDeck();
    }

    public void setDefaultDeck() {
        cards.add(new Fighter());
        cards.add(new Fighter());
        cards.add(new Fighter());
        cards.add(new ShieldBearer());
        cards.add(new ShieldBearer());
        cards.add(new Healer());
    }

    public void showDeck() {
        for (Card card : cards) {
            if (card.state == 0) {
                card.display();
            }
        }
    }

    public void showHand() {
        for (Card card : cards) {
            if (card.state == 1) {
                card.display();
            }
        }
    }

    public void showDiscard() {
        for (Card card : cards) {
            if (card.state == 2) {
                card.display();
            }
        }
    }

    public void shuffle() {
        System.out.println("Shuffling...");
        for (Card card : cards) {
            if (card.state == 2) {
                card.state = 0;
            }
        }
    }

    public int cardsInDeck() {
        int count = 0;
        for (Card card : cards) {
            if (card.state == 0) {
                count++;
            }
        }
        return count;
    }

    public void discard(int cardNum) {
        List<Card> hand = new ArrayList<>();
        for (Card card : cards) {
            if (card.state == 1) {
                hand.add(card);
            }
        }
        if (!hand.isEmpty()) {
            hand.get(cardNum).state = 2;
        }
    }

    public void deal(int numCards) {
        Random rand = new Random();
        for (int i = 0; i < numCards; i++) {
            if (cardsInDeck() <= 0) {
                shuffle();
            }

            boolean assigned = false;
            while (!assigned) {
                Card card = cards.get(rand.nextInt(cards.size()));
                if (card.state == 0) {
                    card.state = 1;
                    assigned = true;
                }
            }
        }
    }
}

public class CardBattleGame {
    public static void main(String[] args) {
        Deck deck = new Deck();
        System.out.println("Initial state:");
        deck.showDeck();

        System.out.println("Dealing 3 cards...");
        deck.deal(3);
        deck.showHand();

        System.out.println("Discarding 3 cards...");
        deck.discard(0);
        deck.discard(0);
        deck.discard(0);
        deck.showDiscard();

        System.out.println("Dealing 4 cards - should trigger a shuffle...");
        deck.deal(4);
        deck.showHand();
    }
}

