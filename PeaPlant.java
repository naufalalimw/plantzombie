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
		
	}
}