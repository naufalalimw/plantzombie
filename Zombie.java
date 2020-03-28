public abstract class Zombie implements Creature{
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

    public void translation(int x, int y) {
        position.setAbsis(position.getAbsis()+x);
        position.setOrdinat(position.getOrdinat()+y);
        
    }
    //abstract Methods
    public abstract void attack(Plant taneman); //method untuk menyerang
    public abstract void move(); //method untuk bergerak


}