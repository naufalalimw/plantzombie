public class PeaPlant extends Plant {
	//konstruktor
	public PeaPlant(Point p) {
		this.hp = 100;
		this.position = p;
		this.attack = 1;
		this.price = 50;
	}

	//implementasi abstract methods
	public void attack(){
		Point shotPosition = new Point(this.position.getAbsis()+1,this.position.getOrdinat());
		Shot newShot = new Shot(this.attack, shotPosition , 2);
	}
}