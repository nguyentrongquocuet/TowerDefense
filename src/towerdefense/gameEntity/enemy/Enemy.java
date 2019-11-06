package towerdefense.gameEntity.enemy;

import towerdefense.DelayFrame;
import towerdefense.GameMaps;
import towerdefense.Position;
import towerdefense.gameEntity.GameEntity;

public abstract class Enemy implements GameEntity {
	boolean first;
	boolean moveUp;
	boolean moveDown;
	boolean moveRight;
	boolean moveLeft;
	GameMaps gameMaps;
	DelayFrame delayFrame;
	public int health;
	public int realTimeHealth;
	public int armor;
	public int speed;
	public int reward;
	public int i;
	public boolean active;
	int delay = 0;
	public int j;
	public int damage;
	public int id;
	public Position pos;
	public boolean alive;
	public String texturePath;
	boolean run;
	int frame;
	public void setTexturePath(String s) {
		this.texturePath = s;
	}

	public Enemy() {
	}

	public Enemy(int id, int health, int armor, int speed, int damage, int reward, String texturePath) {
		delayFrame= new DelayFrame();
		frame=0;
		alive = false;
		active = false;
		gameMaps = new GameMaps();
		this.id = id;
		i = gameMaps.spawnerMatrixPosition.x;
		j = gameMaps.spawnerMatrixPosition.y;
		this.health = health;
		this.realTimeHealth=this.health; 
		this.armor = armor;
		this.speed = speed;
		this.damage = damage;
		this.reward = reward;
		this.texturePath = texturePath;
		pos = gameMaps.spawnerPosition;
		run = true;
		// this.run();
	}

	public void update() {
		if (pos.equals(gameMaps.targetPosition)||health <= 0) {
			alive = false;
			//run = false;
			//active=false;
		}
	}

	public void setPos_1(Position pos) {
		this.pos = pos;
	}

	public void setPos(int x, int y) {
		pos.setPosition(x, y);
	}

	public String getTexturePath() {
		return texturePath;
	}

	public boolean canMove(int x, int y) {
		if (x >= 0 && x < 13 && y >= 0 && y < 26) {
			return true;
		}
		return false;
	}

	public void move() {
		if (canMove(i - 1, j) && gameMaps.map[i - 1][j] > 0) {
			gameMaps.map[i][j] = 0;
			i = i - 1;
			moveUp =true; return;
		}
		if (canMove(i + 1, j) && gameMaps.map[i + 1][j] > 0) {
			gameMaps.map[i][j] = 0;
			i++;
			moveDown=true; return;
		}
		if (canMove(i, j - 1) && gameMaps.map[i][j - 1] > 0) {
			gameMaps.map[i][j] = 0;
			j--;
			moveLeft=true; return;
		}
		if (canMove(i, j + 1) && gameMaps.map[i][j + 1] > 0) {
			gameMaps.map[i][j] = 0;
			j++;
			moveRight =true; return;
		}
	}
	
	private void refresh() {
		moveDown=false;
		moveLeft=false;
		moveRight=false;
		moveUp=false;
	}

	private void moveRight() {
		if(moveRight) pos.x += 1;
	}

	private void moveLeft() {
		if(moveLeft)pos.x -= 1;
	}

	private void moveDown() {
		if(moveDown)pos.y += 1;
	}

	private void moveUp() {
		if(moveUp)pos.y -= 1;
	}

	public void run() {
		if (active && alive && delayFrame.delayed(speed/5)) {
			//alive=true;
			if(frame%40==0){
				refresh();
				move();
				moveUp();
				moveDown();
				moveLeft();
				moveRight();
				frame++;
			}else {
				frame++;
				moveUp();
				moveDown();
				moveLeft();
				moveRight();
			}
			update();
		}
	}

}
