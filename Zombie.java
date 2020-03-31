public abstract class Zombie implements Creature{
    protected int HP;
    protected int attack;
    protected int step;

    //Implementasi Interface
    public void takeDamage(int damage){
        this.HP = this.HP - damage;
    }
    
    public int getHP(){
        return this.HP;
    }
    
    public int getAttack(){
        return this.attack;
    }

    public boolean isDead(){
        return (this.HP <= 0);
    }

    //abstract Methods
    public abstract void attack(Plant taneman); //method untuk menyerang


}