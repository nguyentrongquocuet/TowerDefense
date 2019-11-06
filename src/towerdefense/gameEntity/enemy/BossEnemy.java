package towerdefense.gameEntity.enemy;

public class BossEnemy extends Enemy{
	public BossEnemy() {
		super(4, 300, 30, 8, 30, 200, "res\\GameEntity\\Enemy\\BossEnemy.png");
	}
	public static String getPath() {
		return "res\\GameEntity\\Enemy\\BossEnemy.png";
	}
}
