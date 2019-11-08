package towerdefense.gameEntity.gameTile;

import towerdefense.Position;
import towerdefense.gameEntity.GameEntity;

public abstract class GameTile implements GameEntity{
	public String texturePath;
	public Position pos;
	int id;
	public void setTexturePath(String s) {
		this.texturePath=s;
	}
	public void setPos(float x, float y) {
		pos.setPosition(x, y);
	}
}
