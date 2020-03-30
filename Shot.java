public class Shot {
	private int damage;
	private Point position;
	private int speed;			//jarak gerakan peluru tiap turn

	public Shot(int damage, Point position, int speed) {
		this.damage = damage;
		this.position = position;
		this.speed = speed;
	}

	//method gerakan peluru
	public void move(){
		this.position.setAbsis(position.getAbsis()+speed);
	}
}