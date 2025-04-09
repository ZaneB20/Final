public class GameManager {
    private Deck deck;
    private Character player;
    private Enemy enemy;
    private Scanner scanner;

    public GameManager() {
        deck = new Deck();
        player = new Character("Hero", 20);
        enemy = new Enemy(); // First enemy
        scanner = new Scanner(System.in);
    }

    public void startGame() {
        System.out.println("Starting game...");

        while (playerIsAlive() && !Enemy.allEnemiesDefeated()) {
            System.out.println("\nYour current stats:");
            player.report();
            enemy.report();

            deck.deal(3);
            deck.showHand();

            System.out.println("\nChoose a card to use (0-2): ");
            int choice = getValidInput();

            Card selectedCard = deck.getCardFromHand(choice);
            if (selectedCard != null) {
                selectedCard.use(enemy);
                deck.discard(choice);
            }

            if (enemy.isDefeated()) {
                System.out.println("\nYou defeated " + enemy.getName() + "!");
                Enemy.nextEnemy();
                if (!Enemy.allEnemiesDefeated()) {
                    enemy = new Enemy(); // Spawn the next enemy
                } else {
                    break; // Win condition met!
                }
            } else {
                enemy.attack(player);
            }

            System.out.println("\nPress Enter to continue...");
            scanner.nextLine();
        }

        System.out.println(Enemy.allEnemiesDefeated() ? "You win!" : "Game Over!");
    }

    private boolean playerIsAlive() {
        return player.getHp() > 0;
    }

    private int getValidInput() {
        int choice = -1;
        while (true) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice >= 0 && choice < 3) {
                    break;
                }
                System.out.println("Invalid choice. Enter a number between 0 and 2:");
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number:");
            }
        }
        return choice;
    }
}