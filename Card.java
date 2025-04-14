public abstract class Card {
    protected String name;
    protected int damage;
    protected int shield;

    public Card(String name, int damage, int shield) {
        this.name = name;
        this.damage = damage;
        this.shield = shield;
    }

    public String getName() { return name; }
    public int getDamage() { return damage; }
    public int getShield() { return shield; }

    public abstract void use(Character target); 

    public void display() {
        System.out.println("\n" + name);
        System.out.println("Damage: " + damage);
        System.out.println("Shield: " + shield);
    }
}