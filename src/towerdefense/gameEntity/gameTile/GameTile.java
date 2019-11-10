package towerdefense.gameEntity.gameTile;

import java.awt.Graphics;

import javax.swing.ImageIcon;

import towerdefense.Position;
import towerdefense.gameEntity.GameEntity;

public abstract class GameTile implements GameEntity{
	public String texturePath;
	public Position pos;
	public void setTexturePath(String s) {
		this.texturePath=s;
	}
	public void setPos(float x, float y) {
		pos.setPosition(x, y);
	}
	public GameTile() {
		pos= new Position();
	}
	
	public void draw(Graphics g) {
		g.drawImage(new ImageIcon(texturePath).getImage(), (int)pos.x-20, (int)pos.y-20, null);
	}
}
