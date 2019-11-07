package towerdefense.gameEntity.enemy;

import towerdefense.Player;

public class SmallerEnemy extends Enemy{
	public SmallerEnemy(Player player) {
		super(2, 70, 15, 13, 10, 40, "res\\GameEntity\\Enemy\\SmallerEnemy.png");
		this.player=player;
	}
	public static String getPath() {
		return "res\\GameEntity\\Enemy\\SmallerEnemy.png";
	}
}
