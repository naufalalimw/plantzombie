public class Zombie1 extends Zombie{

    public Zombie1(Point p){
        this.HP= 100;
        this.attack = 2;
        this.position = p;
    }

    public void attack(Plant taneman) {
        int damage = super.getAttack();
        taneman.takeDamage(damage);
    }

    public void move() {
        super.translation(-1, 0);
    }
}