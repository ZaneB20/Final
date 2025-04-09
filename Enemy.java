import java.util.Random;

public class Enemy {
    private String name;
    private int hp;
    private int attackPower;
    
    private static final String[] ENEMY_NAMES = { "Bear", "Goblin", "Orc", "Elf", "Wolf" };
    private static int defeatedCount = 0; // Tracks defeated enemies

    public Enemy() {
        Random random = new Random();
        name = ENEMY_NAMES[defeatedCount]; // Pick sequential enemies
        hp = 15; // Default HP
        attackPower = 3; // Default attack power
    }

    public void attack(Character player) {
        System.out.println(name + " attacks!");
        player.takeDamage(attackPower);
    }

    public boolean isDefeated() {
        return hp <= 0;
    }

    public void takeDamage(int amount) {
        hp -= amount;
        System.out.println(name + " takes " + amount + " damage!");
    }

    public void report() {
        System.out.println("\nEnemy: " + name);
        System.out.println("HP: " + hp);
    }

    public static boolean allEnemiesDefeated() {
        return defeatedCount >= ENEMY_NAMES.length;
    }

    public static void nextEnemy() {
        if (!allEnemiesDefeated()) {
            defeatedCount++;
        }
    }
}