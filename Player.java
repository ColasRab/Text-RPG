public class Player extends Creature {
    private String Username;
    private int targetExp = 5;
    public Player(){
        super(20, 5, 3, 0, 5, 1, 0, "");
    }

    public void setUsername(String username) {
        this.Username = username;
    }

    public void setTargetExp(int targetExp){
        this.targetExp = targetExp;
    }

    public String getUsername(){
        return this.Username;
    }

    public int getNextExperience(){
        return targetExp;
    }
    

    public void levelUp(Creature player){
        player.setAtk(getAtk() + 2);
        player.setVit(getVit() + 4);
        player.setInte(getInte() + 2);
        setTargetExp((int)(getNextExperience() * 1.5));
    }
}
