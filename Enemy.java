import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public String getName() {
    return name;
}

public class Enemy {
    private String name;
    private int hp;
    private int attackPower;
    private int shield;

    private static final String[] ENEMY_NAMES = { "Bear", "Goblin", "Orc", "Elf", "Wolf" };
    private static final int[] ENEMY_HP = { 20, 10, 20, 15, 10 };
    private static final int[] ENEMY_ATTACK = { 10, 5, 10, 10, 5 };
    private static final int[] ENEMY_SHIELD = { 5, 0, 20, 0, 0 };

    private static List<Integer> remainingEnemies = new ArrayList<>(); // Tracks remaining enemies

    public Enemy() {
        if (remainingEnemies.isEmpty()) { // Refill the list if empty
            for (int i = 0; i < ENEMY_NAMES.length; i++) {
                remainingEnemies.add(i);
            }
        }

        Random random = new Random();
        int index = remainingEnemies.remove(random.nextInt(remainingEnemies.size())); // Random selection

        this.name = ENEMY_NAMES[index];
        this.hp = ENEMY_HP[index];
        this.attackPower = ENEMY_ATTACK[index];
        this.shield = ENEMY_SHIELD[index];
    }

    public void attack(Character player) {
        System.out.println(name + " attacks for " + attackPower + " damage!");
        player.takeDamage(attackPower);
    }

    public boolean isDefeated() {
        return hp <= 0;
    }

    public void takeDamage(int amount) {
        int damageTaken = Math.max(amount - shield, 0); // Shield reduces damage
        hp -= damageTaken;
        System.out.println(name + " takes " + damageTaken + " damage!");
    }

    public void report() {
        System.out.println("\nEnemy: " + name);
        System.out.println("HP: " + hp);
        System.out.println("Shield: " + shield);
    }

    public static boolean allEnemiesDefeated() {
        return remainingEnemies.isEmpty();
    }
}