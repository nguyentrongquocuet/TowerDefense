package towerdefense.gameEntity.enemy;

import towerdefense.Player;

public class TankerEnemy extends Enemy{
	 public TankerEnemy(Player player) {
		 super(3, 150, 40, 6, 15,  70, "res\\GameEntity\\Enemy\\TankerEnemy.png");
		 this.player=player;
	 }
		public static String getPath() {
			return "res\\GameEntity\\Enemy\\TankerEnemy.png";
		}
}
