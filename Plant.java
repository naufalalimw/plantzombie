public abstract class Plant implements Creature{
    private int HP;
    private Point position;
    private int attack;

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

    public Point getPosition(){
        return this.position;
    }
    //abstract Methods
    public abstract void attack(); //method untuk menyerang


}