/* YOWWWWWWWWWWWWWWWW GAME PROGRESS
- Travelling (Done)
- More Enemies
- Inventory System
- Equipment System
- Set a scene**/





import java.util.Scanner;

public class Implementation {

    public static Boolean letzG = true;
    public static int input = -1;

    public static void linebreak() {
        System.out.println("-------------------------------------");
    }

    public static int start(Player MC, Scanner scan, GameVariables gv) {
        System.out.print("Please type your nickname: ");
        MC.setUsername(scan.nextLine());
        System.out.println("Hello " + MC.getUsername() + "! Welcome to the RPG!\n");
        return 0;
    }

    public static int printStats(Scanner scan, Player mc) {
        int confirm = -1;
        System.out.println("\n" + mc.getUsername() + "'s Stats\n");
        System.out.println("Level: " + mc.getLevel());
        System.out.println("Vit: " + mc.getVit());
        System.out.println("Int: " + mc.getInte());
        System.out.println("Attack: " + mc.getAtk());
        System.out.println("Exprience: " + mc.getExp());
        System.out.println("Exprience to next level: " + mc.getNextExperience());
        linebreak();

        System.out.println("Press 1 to Return.");

        while (confirm != 1) {
            try {
                confirm = scan.nextInt();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (confirm != 1) {
                System.out.println("The instructions CLEARLY said enter 1, now do it properly.\n");
            }
        }
        linebreak();
        return 0;
    }

    public static Creature makeEnemy(int makeEnemy) {
        Creature enemy = new Creature();
        switch (makeEnemy) {
            case 0:
                enemy = new RedSlime();
                break;
            case 1:
                enemy = new Bat(); // ZUBAAAAAAt
                break;
            case 2:
                enemy = new Goblin(); // Should
                break;
            case 3:
                enemy = new Undead();
                break;
        }

        return enemy;
    }

    public static void restorePlayer(Player mc){
        mc.setHP(mc.getVit());
        mc.setMP(mc.getMP());
    }

    public static void checkmortality(Player mc, GameVariables gv, Creature lmao) {

        if (mc.getHP() <= 0) {
            System.out.println("\n\nGAME OVER");

            letzG = false;

            input = 0;

        } else if (lmao.getHP() <= 0) {
            linebreak();
            System.out.println("\n" + lmao.getName() + " has been defeated!\nYou gained " + lmao.getExp() + " exp.\n");
            while (lmao.getExp() > 0) {
                int playerExp = mc.getExp();
                int lmaoExp = lmao.getExp();
                mc.setExperience(++playerExp);
                lmao.setExperience(--lmaoExp);
                if (playerExp >= mc.getNextExperience()) {
                    mc.setExperience(mc.getExp() - mc.getNextExperience());
                    int addLevel = mc.getLevel();
                    mc.setLevel(addLevel++);
                    System.out.println("LEVEL INCREASED TO " + mc.getLevel() + "\n");
                    mc.levelUp(mc);
                }
            }

            if (gv.getEncounters() > 0) {

                gv.setEncounters(gv.getEncounters() - 1);

                gv.setGamemode(1);
                input = 0;
            } else {

                gv.setGamemode(0);
                input = 0;
            }
        } else {

            input = -1;
        }
    }

    public static void game(Scanner scan, GameVariables gv, Player mc) {
        while (letzG) {
            Creature enemy = new Creature();
            while (letzG) {
                switch (gv.getGamemode()) {
                    case 0: {
                        restorePlayer(mc);
                        System.out.println("Welcome to the inn.");
                        int input = -1;
                        while (input == -1) {
                            System.out.println("\nType an action:\n[0] = Head out\n[1] = Check stats\n[2] = Exit\n");
                            try {
                                input = scan.nextInt();
                            } catch (Exception e) {
                                input = -1;
                            }

                            linebreak();

                            switch (input) {
                                case 0:
                                    gv.setGamemode(1);
                                    gv.setEncounters(2);
                                    break;

                                case 1:
                                    printStats(scan, mc);
                                    input = -1;
                                    break;

                                case 2:
                                    letzG = false;
                                default:
                                    break;
                            }
                        }
                    }

                    case 1:
                        System.out.println("\nTravelling...\n");
                        enemy = makeEnemy(mc.getLevel() - 1);
                        System.out.println("\nAn enemy " + enemy.getName() + " appears!\n");
                        linebreak();

                        gv.setGamemode(2);
                        input = -1;
                        break;

                    case 2:
                        enemy = makeEnemy(mc.getLevel() - 1);
                        while (input == -1) {
                            SpecialAttack curr = new SpecialAttack();
                            curr = enemy.Atk(enemy);
                            System.out.println(
                                    "\nChoose an action:\n[0] = Attack\n[1] = Cast spell (5 MP)\n[2] = Defend\n[4] = Battle stats\n");
                            input = scan.nextInt();

                            switch (input) {
                                case 0:
                                    linebreak();
                                    System.out.println("\n" + mc.getUsername() + " attacks!\n");

                                    enemy.setHP(enemy.getHP() - mc.getAtk());
                                    System.out.println(enemy.getName() + " took " + mc.getAtk() + " damage!\n");
                                    linebreak();
                                    break;

                                case 1:
                                    linebreak();
                                    if (mc.getMP() >= 5) {

                                        int damage = (int) (mc.getAtk() * 1.5) + mc.getLevel();

                                        mc.setMP(mc.getMP() - 5);
                                        System.out.println("\n" + mc.getName() + " casts a magic spell!\n");

                                        enemy.setHP(enemy.getHP() - damage);
                                        System.out.println(enemy.getName() + " took " + damage + " damage!\n");
                                    } else {
                                        System.out.println("\nNot enough mana; spell failed...\n");
                                    }
                                    linebreak();
                                    break;

                                case 2:
                                    linebreak();
                                    System.out.println("\n" + mc.getName() + " defends!\n");
                                    System.out.println(mc.getName() + " restored " + mc.getLevel() + " MP!\n");
                                    linebreak();

                                    mc.setDef(1);

                                    mc.setMP(mc.getMP() + mc.getLevel());
                                    if (mc.getMP() > mc.getInte())
                                        mc.setMP(mc.getInte());
                                    break;

                                case 3:
                                    linebreak();
                                    System.out.println("\n" + enemy.getName() + "'s HP: " + enemy.getHP() + "\n");
                                    System.out.println(
                                            mc.getUsername() + "'s HP: " + mc.getHP() + "\n" + mc.getUsername()
                                                    + "'s MP: "
                                                    + mc.getMP() + "\n");
                                    linebreak();
                                    input = -1;
                                    continue;

                                default:
                                    linebreak();
                                    System.out.println(
                                            "\nPlease refrain from messing up the instructions just like how you mess up everything in your life.\n");
                                    linebreak();
                                    break;
                            }

                            System.out.println("\n");

                            if (enemy.getHP() > 0) {

                                int damage = curr.getDamage() / (1 + mc.getDef()); // dapat ito kayang tanggapin kahit sp attack
                                System.out.println(enemy.getName() + " attacks!\n");
                                mc.setHP(mc.getHP() - damage);
                                System.out.println(mc.getUsername() + " took " + damage + " damage!\n");

                                mc.setDef(0);
                                System.out.println("\n");
                            }

                            checkmortality(mc, gv, enemy);
                        }
                        break;
                }
            }
        }
    }
}
