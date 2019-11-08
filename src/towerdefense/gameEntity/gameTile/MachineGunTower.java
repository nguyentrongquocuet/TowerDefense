package towerdefense.gameEntity.gameTile;

import towerdefense.GameField;
import towerdefense.Position;

public class MachineGunTower extends Tower{
	public MachineGunTower(Position pos, GameField gameField) {
		super(20, 20, 35, 150, 100, "res\\GameEntity\\GameTile\\Tower\\MachineGun.png","res\\GameEntity\\GameTile\\Tower\\MachineBullet.png", gameField);
		this.pos=pos;
		this.bullet.setPos(pos.x, pos.y);
	}
	public static String getPath() {
		return "res\\GameEntity\\GameTile\\Tower\\MachineGun.png";
	}
	public static int getCost() {
		return 100;
	}
	public static int getRange() {
		return 150;	
		}
}
