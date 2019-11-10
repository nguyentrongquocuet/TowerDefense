package towerdefense.gameEntity.enemy;

import towerdefense.GameField;
import towerdefense.Player;

public class SmallerEnemy extends Enemy{
	public SmallerEnemy(Player player, GameField gameField) {
		super(70, 15, 1.2f, 10, 15, "res\\GameEntity\\Enemy\\SmallerEnemy.png");
		this.player=player;
		this.gameField=gameField;
	}
	public static String getPath() {
		return "res\\GameEntity\\Enemy\\SmallerEnemy.png";
	}
}
