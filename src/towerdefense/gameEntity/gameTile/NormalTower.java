package towerdefense.gameEntity.gameTile;

import towerdefense.gameEntity.Bullet;

public class NormalTower extends Tower{

	public NormalTower() {
		super(1, 20, 180, 70, new Bullet(10, 30, 70), "res\\GameEntity\\GameTile\\Tower\\NormalTower.png");
	}
	public static String getPath() {
		return "res\\GameEntity\\GameTile\\Tower\\NormalTower.png";
	}
	public static int getCost() {
		return 70;
	}
	
}
