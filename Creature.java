class SpecialAttack{
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
}

public class Creature {
    private int HP, MP, Vit, Inte, Atk, Def, Spd, Level, Exp;
    private String name;

    public Creature(){
        Vit = 0;
        Inte = 0;
        HP = 0;
        MP = 0;
        Atk = 0;
        Level = 0;
        Exp = 0;
        name = " ";
    }

    public Creature(int Vit, int Inte, int Atk, int Def, int Spd, int Level, int Exp, String name){
        this.HP = Vit;
        this.MP = Inte;
        this.Vit = Vit;
        this.Inte = Inte;
        this.Atk = Atk;
        this.Def = Def;
        this.Spd = Spd;
        this.Level = Level;
        this.Exp = Exp;
        this.name = name;
    }

    public void setAtk(int atk){
        this.Atk = atk;
    }
    public void setHP(int hp){
        this.HP = hp;
    }
    public void setMP(int mp){
        this.MP = mp;
    }
    public void setVit(int vit){
        this.Vit = vit;
    }
    public void setInte(int inte){
        this.Inte = inte;
    }
    public void setDef(int def){
        this.Def = def;
    }
    public void setExperience(int exp){
        this.Exp = exp;
    }
    public void setLevel(int lvl){
        this.Level = lvl;
    }


    public int getHP(){
        return HP;
    }
    public int getMP(){
        return MP;
    }
    public int getVit(){
        return Vit;
    }
    public int getInte(){
        return Inte;
    }
    public int getAtk(){
        return Atk;
    }
    public int getDef(){
        return Def;
    }
    public int getSpd(){
        return Spd;
    }
    public int getLevel(){
        return Level;
    }
    public int getExp(){
        return Exp;
    }
    public String getName(){
        return name;
    }
    public SpecialAttack Atk(Creature enemy){
        SpecialAttack curr = new SpecialAttack();
        switch (enemy.getName()) {
            case "Red Slime":
            if (enemy.getMP() > 10){
                curr = curr.sp(90, 10, "Red Gush");
                enemy.setMP(enemy.getMP() - 10);
            }
                break;
            case "Golden Slime":
                if (enemy.getMP() > 10){
                    curr = curr.sp(0, 10, "Golden Solidify");
                    enemy.setMP(enemy.getMP() - 10);
                    enemy.setDef((int)(enemy.getDef()*1.5));
                }
                if (enemy.getMP() > 10){
                    curr = curr.sp(90, 10, "Golden Gush");
                    enemy.setMP(enemy.getMP() - 10);
                }
            default:
                break;
        }
        
        return curr;
    }
}
