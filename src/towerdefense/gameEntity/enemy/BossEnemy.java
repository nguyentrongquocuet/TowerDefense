package towerdefense.gameEntity.enemy;

import towerdefense.GameField;
import towerdefense.Player;

public class BossEnemy extends Enemy{
	public BossEnemy(Player player, GameField gameField) {
		super( 300, 30, 1f, 30, 100, "res\\GameEntity\\Enemy\\BossEnemy.png");
		this.player=player;
		this.gameField=gameField;
	}
	public static String getPath() {
		return "res\\GameEntity\\Enemy\\BossEnemy.png";
	}
}
