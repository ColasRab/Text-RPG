public class GameVariables {
    private static int gamemode = 0, encounters;

    public void setGamemode(int Gamemode) {
        gamemode = Gamemode;
    }

    public void setEncounters(int enc) {
        encounters = enc;
    }

    public int getGamemode(){
        return gamemode;
    }

    public int getEncounters(){
        return encounters;
    }
}
