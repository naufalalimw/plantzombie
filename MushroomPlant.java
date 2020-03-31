public class MushroomPlant extends Plant {
	
	// konstruktor
	public MushroomPlant() {
		this.hp = 50;
		this.attack = 1;
		this.price = 25;
	}

	// implements abstrack methods
	public void attack(){
		Point shotPosition = new Point(this.position.getAbsis()+1,this.position.getOrdinat());
		Shot newShot = new Shot(this.attack);
		Shots.add(newShot);
	}
}