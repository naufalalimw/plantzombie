public class PeaPlant extends Plant {
	
	//konstruktor
	public PeaPlant() {
		this.hp = 100;
		this.attack = 20;
		this.price = 50;
	}

	//implementasi abstract methods
	public void attack(){
		Point shotPosition = new Point(this.position.getAbsis()+1,this.position.getOrdinat());
		Shot newShot = new Shot(this.attack);
		Shots.add(newShot);
	}
}