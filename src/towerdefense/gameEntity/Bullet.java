package towerdefense.gameEntity;

import static towerdefense.Clock.*;
import towerdefense.GameMaps;
import towerdefense.Position;
import towerdefense.gameEntity.enemy.Enemy;

public class Bullet implements GameEntity{
	public float drX, drY;
	public boolean readyToFire=false;
	public String texturePath;
	public GameMaps gameMaps;
	public Enemy enemy;
	public Position pos;
	public int bulletSpeed;
	public int shootSpeed;
	public int damage;
	public int range;
	public boolean active;
	Position stockPosition;
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
	
	public void setStockPosition(Position stockPosition) {
		this.stockPosition = stockPosition;
	}

	public void move() {
		
		if(active&&readyToFire) {
		pos.x+=drX*deltaMove()/bulletSpeed;
		pos.y+=drY*deltaMove()/bulletSpeed;
		
		if(pos.distance(enemy.pos)<=10) {
			enemy.realTimeHealth-=damage;
			active=false;
			readyToFire=false;
			return;
		} else if(stockPosition.distance(enemy.pos)>range) {active=false; readyToFire=false; return;}
		}
	}

}
