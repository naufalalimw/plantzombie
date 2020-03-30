public class Zombie2 extends Zombie{

    public Zombie2(){
        this.HP= 50;
        this.attack = 3;
    }

    public void attack(Plant taneman) {
        int damage = super.getAttack();
        taneman.takeDamage(damage);
    }
}