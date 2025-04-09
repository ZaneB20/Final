public class Character {
    private String name;
    private int hp;
    private int shield;

    public Character(String name, int hp) {
        this.name = name;
        this.hp = hp;
        this.shield = 0;
    }

    public void apply(Card card) {
        this.hp -= card.getDamage();
        this.shield += card.getShield();
    }

    public void report() {
        System.out.println("\n" + name);
        System.out.println("HP: " + hp);
        System.out.println("Shield: " + shield);
    }
}

