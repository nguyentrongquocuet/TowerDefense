package towerdefense.gameEntity;

import static towerdefense.Clock.*;
import towerdefense.GameMaps;
import towerdefense.Position;
import towerdefense.gameEntity.enemy.Enemy;

public class Bullet implements GameEntity{
	public String texturePath;
	public GameMaps gameMaps;
	public Enemy enemy;
	public Position pos;
	public int bulletSpeed;
	public int shootSpeed;
	public int damage;
	public int range;
	public boolean active;
	
	public Bullet(int bulletSpeed, int shootSpeed, int damage, int range, String path) {
		this.shootSpeed=shootSpeed;
		active=false;
		this.bulletSpeed=bulletSpeed;
		this.damage=damage;
		this.range=range;
		this.texturePath=path;
		pos= new Position();
	}
	
	public void setTexturePath(String path) {
		this.texturePath= path;
	}

	public void setPos(float x, float y) {
		pos.setPosition(x, y);
	}
	
	public void move() {
		System.out.println("shooting "+ getTotalTime());
		active=true;
			if(delay(10*shootSpeed*delta())) {
				pos.x+=enemy.pos.x-pos.x;
				pos.y+=enemy.pos.y-pos.y;
			} else {
				active=false;
			}
		if(pos.equals(enemy.pos)) {
			enemy.realTimeHealth-=damage;
			active=false;
		}
	}

}
