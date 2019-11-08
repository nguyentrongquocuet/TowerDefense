package towerdefense.gameEntity.gameTile;

import towerdefense.GameField;
import towerdefense.Position;

public class NormalTower extends Tower{
	public NormalTower(Position pos, GameField gameField) {
		super(15, 35, 20, 180, 70, "res\\GameEntity\\GameTile\\Tower\\NormalTower.png", "res\\GameEntity\\GameTile\\Tower\\NormalBullet.png", gameField);
		this.pos=pos;
		bullet.setPos(pos.x, pos.y);
	}
	
	public static String getPath() {
		return "res\\GameEntity\\GameTile\\Tower\\NormalTower.png";
	}
	
	public static int getCost() {
		return 70;
	}
	
	public static int getRange() {
		return 180;	
		}
}
