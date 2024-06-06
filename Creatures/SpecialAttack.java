package Creatures;


public class SpecialAttack { 
    private int damage, MPCost;
    private String attackName;

    public SpecialAttack sp(int damage, int MPCost, String attackName){
        this.damage = damage;
        this.MPCost = MPCost;
        this.attackName = attackName;
        return this;
    }

    public int getDamage(){
        return damage;
    }

    public String getName(){
        return attackName;
    }

    public int MPCost(){
        return MPCost;
    }
}