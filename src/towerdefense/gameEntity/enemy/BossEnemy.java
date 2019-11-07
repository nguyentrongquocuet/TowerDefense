package towerdefense.gameEntity.enemy;

import towerdefense.Player;

public class BossEnemy extends Enemy{
	public BossEnemy(Player player) {
		super(4, 300, 30, 8, 30, 200, "res\\GameEntity\\Enemy\\BossEnemy.png");
		this.player=player;
	}
	public static String getPath() {
		return "res\\GameEntity\\Enemy\\BossEnemy.png";
	}
}
