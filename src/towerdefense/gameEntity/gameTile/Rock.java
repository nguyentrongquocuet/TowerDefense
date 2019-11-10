package towerdefense.gameEntity.gameTile;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Rock extends GameTile{
	public Rock() {
		this.texturePath="res\\GameEntity\\GameTile\\Rock.png";
	}
	public static String getPath() {
		return "res\\GameEntity\\GameTile\\Rock.png";
	}
	static Image image = new ImageIcon(getPath()).getImage();
	public static Image getImage() {
		return image;
	}
}
