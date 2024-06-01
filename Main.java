import java.util.Scanner;

public class Main {
    static int gamemode = 0, input = -1, ongoing = 1, encounters;
    static int player_maxhp, player_maxmp, player_hp, player_mp, player_atk, player_guard, player_potions, player_exp, player_level, player_nextexp;
    static int enemy_hp, enemy_atk, enemy_guard, enemy_exp;
    static String player_name, enemy_name;

    public static void main(String[] args) {
        initialize();

        while (ongoing == 1) {

            switch (gamemode) {

                case 0: {

                    restoreplayer();
                    System.out.println("Welcome to the inn.");
                    input = -1;
                    while (input == -1) {
                        System.out.println("\nType an action:\n[0] = Head out\n[1] = Check stats\n[2] = Exit\n");
                        Scanner scanner = new Scanner(System.in);
                        input = scanner.nextInt();
                        linebreak();
                        switch (input) {

                            case 0: {
                                gamemode = 1;
                                encounters = 2;
                                break;
                            }

                            case 1: {
                                printstats();
                                input = -1;
                                break;
                            }

                            case 2: {
                                ongoing = 0;
                                break;
                            }
                        }
                    }
                    break;
                }

                case 1: {
                    System.out.println("\nTraveling...\n");

                    make_enemy(player_level - 1);
                    System.out.println("\nAn enemy " + enemy_name + " appears!\n");
                    linebreak();

                    gamemode = 2;
                    input = -1;
                    break;
                }

                case 2: {

                    while (input == -1) {
                        int cancelled = 0;
                        System.out.println("\nChoose an action:\n[0] = Attack\n[1] = Cast spell (5 MP)\n[2] = Use potion(" + player_potions + ")\n[3] = Defend\n[4] = Battle stats\n");
                        Scanner scanner = new Scanner(System.in);
                        input = scanner.nextInt();
                        System.out.println();
                        switch (input) {

                            case 0: {
                                linebreak();
                                System.out.println("\n" + player_name + " attacks!\n");

                                enemy_hp -= player_atk;
                                System.out.println(enemy_name + " took " + player_atk + " damage!\n");
                                linebreak();
                                break;
                            }

                            case 1: {
                                linebreak();
                                if (player_mp >= 5) {

                                    int damage = (int) (player_atk * 1.5) + player_level;

                                    player_mp -= 5;
                                    System.out.println("\n" + player_name + " casts a magic spell!\n");

                                    enemy_hp -= damage;
                                    System.out.println(enemy_name + " took " + damage + " damage!\n");
                                } else {
                                    System.out.println("\nNot enough mana; spell failed...\n");

                                    cancelled = 1;
                                }
                                linebreak();
                                break;
                            }
                            case 2: {
                                if (player_potions <= 0) {
                                    System.out.println("\nYou're out of potions!");
                                    cancelled = 1;
                                    break;
                                }
                                int potionchoice;
                                linebreak();
                                demoralize: {
                                    System.out.println("\n");
                                }
                                System.out.println("\nChoose which potion to use:\n[0] = Health potion\n[1] = Mana potion\n[2] = Return\n");
                                Scanner scanner2 = new Scanner(System.in);
                                potionchoice = scanner2.nextInt();
                                player_potions--;
                                switch (potionchoice) {

                                    case 0: {

                                        player_hp = player_maxhp;
                                        System.out.println(player_name + " drank a health potion!\n");
                                        System.out.println(player_name + "'s HP got fully replenished!\n");
                                        linebreak();
                                        break;
                                    }

                                    case 1: {

                                        player_mp = player_maxmp;
                                        System.out.println(player_name + " drank a mana potion!\n");
                                        System.out.println(player_name + "'s MP got fully replenished!\n");
                                        linebreak();
                                        break;
                                    }

                                    case 2: {

                                        cancelled = 1;
                                        player_potions++;
                                        break;
                                    }

                                    default: {
                                        player_potions++;
                                        System.out.println("\nAre you incapable of reading instructions?");
                                        goto demoralize;
                                        break;
                                    }
                                }
                                break;
                            }

                            case 3: {
                                linebreak();
                                System.out.println("\n" + player_name + " defends!\n");
                                System.out.println(player_name + " restored " + player_level + " MP!\n");
                                linebreak();

                                player_guard = 1;

                                player_mp += player_level;
                                if (player_mp > player_maxmp) player_mp = player_maxmp;
                                break;
                            }

                            case 4: {

                                linebreak();
                                System.out.println("\n" + enemy_name + "'s HP: " + enemy_hp + "\n");
                                System.out.println(player_name + "'s HP: " + player_hp + "\n" + player_name + "'s MP: " + player_mp + "\n");
                                linebreak();
                                input = -1;
                                continue;
                            }

                            default: {
                                linebreak();
                                System.out.println("\nPlease refrain from messing up the instructions just like how you mess up everything in your life.\n");
                                cancelled = 1;
                                linebreak();
                                break;
                            }
                        }
                        System.out.println("\n");

                        if (enemy_hp > 0 && cancelled == 0) {

                            int damage = enemy_atk / (1 + player_guard);
                            System.out.println(enemy_name + " attacks!\n");
                            player_hp -= damage;
                            System.out.println(player_name + " took " + damage + " damage!\n");

                            player_guard = 0;
                            System.out.println("\n");
                        }

                        checkmortality();
                    }
                    break;
                }
            }
        }
    }

    public static void checkmortality() {
        if (player_hp <= 0) {
            System.out.println("\n\nGAME OVER");

            ongoing = 0;

            input = 0;
        } else if (enemy_hp <= 0) {
            linebreak();
            System.out.println("\n" + enemy_name + " has been defeated!\nYou gained " + enemy_exp + " exp.\n");

            while (enemy_exp > 0) {
                player_exp++;
                enemy_exp--;
                if (player_exp >= player_nextexp) {
                    player_exp -= player_nextexp;
                    player_level++;
                    System.out.println("LEVEL INCREASED TO " + player_level + "\n");
                    player_atk += 2;
                    player_maxhp += 4;
                    player_maxmp += 2;
                    player_nextexp *= 1.5;
                }
            }

            if (encounters > 0) {

                encounters--;

                gamemode = 1;
                input = 0;
            } else {

                gamemode = 0;
                input = 0;
            }
        } else {

            input = -1;
        }
    }

    public static void make_enemy(int type) {
        switch (type) {
            case 0: {
                enemy_name = "Slime";
                enemy_exp = 2;
                enemy_atk = 4;
                enemy_guard = 0;
                enemy_hp = 6;
                break;
            }
            case 1: {
                enemy_name = "Bat";
                enemy_exp = 4;
                enemy_atk = 6;
                enemy_guard = 0;
                enemy_hp = 9;
                break;
            }
            case 2: {
                enemy_name = "Goblin";
                enemy_exp = 6;
                enemy_atk = 8;
                enemy_guard = 0;
                enemy_hp = 13;
                break;
            }
            case 3: {
                enemy_name = "Undead";
                enemy_exp = 10;
                enemy_atk = 10;
                enemy_guard = 0;
                enemy_hp = 20;
                break;
            }
            case 4: {
                enemy_name = "Gargoyle";
                enemy_exp = 16;
                enemy_atk = 14;
                enemy_guard = 0;
                enemy_hp = 25;
                break;
            }
            default: {
                enemy_name = "Dragon";
                enemy_exp = 10000;
                enemy_atk = 20;
                enemy_guard = 0;
                enemy_hp = 40;
                break;
            }
        }
    }

    public static void linebreak() {
        System.out.println("-------------------------------------");
    }

    public static int printstats() {
        int confirm;
        System.out.println("\n" + player_name + "'s Stats\n");
        System.out.println("Level: " + player_level + "\n");
        System.out.println("Max HP: " + player_maxhp + "\n");
        System.out.println("Max MP: " + player_maxmp + "\n");
        System.out.println("Attack: " + player_atk + "\n");
        System.out.println("Exprience: " + player_exp + "\n");
        System.out.println("Exprience to next level: " + player_nextexp + "\n");
        System.out.println("\nEnter 1 to go back.");

        try {
            Scanner scanner = new Scanner(System.in);
            confirm = scanner.nextInt();
        } catch (Exception e) {
            
        }
        stubborness: {
            Scanner scanner = new Scanner(System.in);
            confirm = scanner.nextInt();
        }
        if (confirm != 1) {
            System.out.println("The instructions CLEARLY said enter 1, now do it properly.\n");
            goto stubborness;
        }
        linebreak();
        return 0;
    }

    public static void restoreplayer() {
        player_hp = player_maxhp;
        player_mp = player_maxmp;
        player_potions = 1 + (player_level / 2);
    }

    public static void initialize() {
        player_maxhp = 20;
        player_hp = player_maxhp;
        player_potions = 1;
        player_maxmp = 5;
        player_mp = player_maxmp;
        player_atk = 3;
        player_guard = 0;
        player_level = 1;
        player_exp = 0;
        player_nextexp = 5;
        System.out.print("Please type your nickname: ");
        Scanner scanner = new Scanner(System.in);
        player_name = scanner.nextLine();
        System.out.println("Hello " + player_name + "! Welcome to the RPG!\n");
        gamemode = 0;
    }
}


