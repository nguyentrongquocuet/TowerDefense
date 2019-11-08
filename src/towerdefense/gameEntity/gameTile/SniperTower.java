package towerdefense.gameEntity.gameTile;

import towerdefense.GameField;
import towerdefense.Position;

public class SniperTower extends Tower{
	public SniperTower(Position pos, GameField gameField) {
		super(30, 50, 10, 320,130, "res\\GameEntity\\GameTile\\Tower\\SniperTower.png", "res\\GameEntity\\GameTile\\Tower\\SniperBullet.png", gameField);
		this.pos=pos;
		this.bullet.setPos(pos.x, pos.y);
	}
	public static String getPath() {
		return "res\\GameEntity\\GameTile\\Tower\\SniperTower.png";
	}
	public static int getCost() {
		return 130;
	}
	public static int getRange() {
		return 320;	
		}
}
