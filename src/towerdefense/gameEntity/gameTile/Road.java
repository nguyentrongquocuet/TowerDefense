package towerdefense.gameEntity.gameTile;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Road extends GameTile{
	static Image image = new ImageIcon(getPath()).getImage();
	public Road() {
		this.texturePath="res\\GameEntity\\GameTile\\Road.png";
	}
	public static String getPath() {
		return "res\\GameEntity\\GameTile\\Road.png";
	}
	public static Image getImage() {
		return image;
	}
}
