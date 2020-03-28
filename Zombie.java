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
        this.position.setAbsis(this.position.getAbsis()+x);
        this.position.setOrdinat(this.position.getOrdinat()+y);

    }
    //abstract Methods
    public abstract void attack(Plant taneman); //method untuk menyerang
    public abstract void move(); //method untuk bergerak


}