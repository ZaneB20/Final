public void deal(int numCards) {
    Random rand = new Random();
    for (int i = 0; i < numCards; i++) {
        if (!cards.isEmpty()) {
            Card card = cards.get(rand.nextInt(cards.size()));
            card.display();
        }
    }
}

public Card getCardFromHand(int index) {
    if (index >= 0 && index < cards.size()) {
        return cards.get(index);
    }
    return null;
}

public void discard(int index) {
    if (index >= 0 && index < cards.size()) {
        cards.remove(index);
    }
}

public void showHand() {
    System.out.println("\nYour Hand:");
    for (int i = 0; i < cards.size(); i++) {
        System.out.println(i + ": " + cards.get(i).getName());
    }
}