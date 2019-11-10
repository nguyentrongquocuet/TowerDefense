package towerdefense.gameEntity.enemy;

import towerdefense.GameField;
import towerdefense.Player;

public class NormalEnemy extends Enemy{
	public NormalEnemy(Player player, GameField gameField) {
		super(100, 20, 1f, 15, 25, "res\\GameEntity\\Enemy\\NormalEnemy.png");
		this.player=player;
		this.gameField=gameField;
	}
	public void setTexturePath(String s) {
		this.texturePath=s;
	}
	public static String getPath() {
		return "res\\GameEntity\\Enemy\\NormalEnemy.png";
	}
}
