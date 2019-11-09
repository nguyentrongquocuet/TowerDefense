package towerdefense.gameEntity.gameTile;

import java.awt.Image;

import javax.swing.ImageIcon;

import towerdefense.GameField;
import towerdefense.Position;

public class MachineGunTower extends Tower{
	static Image image= new ImageIcon(getPath()).getImage();
	public MachineGunTower(Position pos, GameField gameField) {
		super(20,50, 200, 85, 100, "res\\GameEntity\\GameTile\\Tower\\MachineGun.png","res\\GameEntity\\GameTile\\Tower\\MachineBullet.png", gameField);
		this.pos=pos;
		this.getBullet().setPos(pos.x, pos.y);
		this.getBullet().setStockPosition(new Position(pos.x,pos.y));
	}
	public static String getPath() {
		return "res\\GameEntity\\GameTile\\Tower\\MachineGun.png";
	}
	public static int getCost() {
		return 100;
	}
	public static int getRange() {
		return 85;	
		}
	public static Image getImage() {
		return image;
	}
}
