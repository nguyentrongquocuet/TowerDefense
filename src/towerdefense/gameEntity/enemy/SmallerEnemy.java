package towerdefense.gameEntity.enemy;

public class SmallerEnemy extends Enemy{
	public SmallerEnemy() {
		super(2, 70, 15, 13, 10, 40, "res\\GameEntity\\Enemy\\SmallerEnemy.png");
	}
	public static String getPath() {
		return "res\\GameEntity\\Enemy\\SmallerEnemy.png";
	}
}
