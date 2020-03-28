public class Zombie2 extends Zombie{

    public Zombie2(Point p){
        this.HP= 50;
        this.attack = 3;
        this.position = p;
    }

    public void attack(Plant taneman) {
        int damage = super.getAttack();
        taneman.takeDamage(damage);
    }

    public void move() {
        super.translation(-3, 0);
    }
}