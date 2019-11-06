package towerdefense.gameEntity;

import towerdefense.GameMaps;
import towerdefense.Position;
import towerdefense.gameEntity.enemy.Enemy;
import towerdefense.gameEntity.gameTile.Tower;

public class Bullet implements GameEntity{
	public String texturePath;
	public GameMaps gameMaps;
	public Enemy enemy;
	public Tower tower;
	public Position pos;
	public int speed;
	public int damage;
	public int range;
	public Bullet(int speed, int damage, int range) {
		this.enemy=enemy;
		this.speed=speed;
		this.damage=damage;
		this.range=range;
	}
	public void setTexturePath(String path) {
		this.texturePath= path;
	}

	public void setPos(int x, int y) {
		pos.setPosition(x, y);
	}
}
