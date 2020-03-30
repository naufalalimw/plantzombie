public class Zombie1 extends Zombie{

    public Zombie1(Point p){
        this.HP= 100;
        this.attack = 2;
    }

    public void attack(Plant taneman) {
        int damage = super.getAttack();
        taneman.takeDamage(damage);
    }
}