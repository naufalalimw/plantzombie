public interface Creature{
    public void takeDamage(int damage); //Menerima damage dari serangan
    public int getHP();                 //getter HP
    public int getAttack();             //Getter attack
    public boolean isDead();            //mengecek apakah sudah mati
    public Point getPosition();         //getter position
}