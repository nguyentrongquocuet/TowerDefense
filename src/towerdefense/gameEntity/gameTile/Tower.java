package towerdefense.gameEntity.gameTile;

import towerdefense.gameEntity.Bullet;

public abstract class Tower extends GameTile{
	public int speed;
	public int range;
	public Bullet bullet;
	public int cost;
	public Tower(int id, int speed, int range, int cost, Bullet bullet, String path) {
		this.cost=cost;
		this.id=id;
		this.speed=speed;
		this.range=range;
		this.bullet=bullet;
		this.texturePath=path;
	}
}
