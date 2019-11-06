package towerdefense.gameEntity.gameTile;

import towerdefense.gameEntity.Bullet;

public class SniperTower extends Tower{
	public SniperTower() {
		super(3, 10, 320,130, new Bullet(20, 45, 100), "res\\GameEntity\\GameTile\\Tower\\SniperTower.png");
	}
	public static String getPath() {
		return "res\\GameEntity\\GameTile\\Tower\\SniperTower.png";
	}
	public static int getCost() {
		return 130;
	}
}
