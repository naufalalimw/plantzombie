public abstract class Plant implements Creature{
    protected int hp;
    // protected Point position;
    protected int attack;
    protected int price;
    protected Point position;

    //Implementasi Interface
    public void takeDamage(int damage){
        this.hp = this.hp - damage;
    }
    
    //getter
    public int getHP(){
        return this.hp;
    }
    
    public int getAttack(){
        return this.attack;
    }

    // public Point getPosition(){
    //     return this.position;
    // }

    public int getPrice(){
        return this.price;
    }

    //setter
    // public void setHP(int hp){
    //     this.hp = hp;
    // }

    // public void setPosition(Point p){
    //     this.position = p;
    // }

    // public void setPosition(int x, int y){
    //     Point newPosition = new Point(x,y);
    //     this.position = newPosition;
    // }

    // public void setAttack(int att){
    //     this.attack = att;
    // }
    
    //methods
    public boolean isDead(){
        return (this.hp <= 0);
    }
    
    //abstract Methods
    public abstract void attack(); //method untuk menyerang


}