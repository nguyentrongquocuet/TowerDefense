package towerdefense.gameEntity.enemy;

public class TankerEnemy extends Enemy{
	 public TankerEnemy() {
		 super(3, 150, 40, 6, 15,  70, "res\\GameEntity\\Enemy\\TankerEnemy.png");
	 }
		public static String getPath() {
			return "res\\GameEntity\\Enemy\\TankerEnemy.png";
		}
}
