package towerdefense.gameEntity.gameTile;


import java.awt.Image;

import javax.swing.ImageIcon;

import towerdefense.GameField;
import towerdefense.Position;

public class NormalTower extends Tower{
	static Image image= new ImageIcon(getPath()).getImage();
	static Image interview= new ImageIcon("res\\GameEntity\\GameTile\\Tower\\NormalInterview.png").getImage();
	public NormalTower(Position pos, GameField gameField) {
		super(15, 40, 333, 100, 70, "res\\GameEntity\\GameTile\\Tower\\NormalTower.png", "res\\GameEntity\\GameTile\\Tower\\NormalBullet.png", gameField);
		this.pos=pos;
		this.getBullet().setPos(pos.x, pos.y);
		this.getBullet().setStockPosition(new Position(pos.x,pos.y));
	}
	
	public static String getPath() {
		return "res\\GameEntity\\GameTile\\Tower\\NormalTower.png";
	}
	
	public static int getSpeed() {
		return 40;
	}
	
	public static int getShootSpeed() {
		return 333;
	}
	
	public static int getDamage() {
		return 15;
	}
	
	public static int getCost() {
		return 70;
	}
	
	public static int getRange() {
		return 100;	
		}
	
	public static Image getImage() {
		return image;
	}
	
	public static Image getInterview() {
		return interview;
	}
}
