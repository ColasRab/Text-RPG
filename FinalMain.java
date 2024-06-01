import java.util.Scanner;

public class FinalMain {

    static int gamemode = 0;


    public static void main(String[] args) {
        Player MC = new Player();
        Scanner scan = new Scanner(System.in);
        GameVariables gv = new GameVariables();

        Implementation.start(MC, scan, gv);  

        Implementation.game(scan, gv, MC);

        scan.close();
    }
    
}
