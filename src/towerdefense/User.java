package towerdefense;

public class User {
	Screen screen;
	Player player;
	boolean createdBefore=false;
	static final int DEFAULT_HEALTH = 100;
	static final int DEFAULT_COIN = 100;

	public User(Screen screen) {
		this.screen = screen;
		this.screen.status = 0;// main menu
		createdBefore=true;
	}

	// new game
	public void creatPlayer() {
		player = new Player();
	}
}
