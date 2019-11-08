package towerdefense.gameEntity.enemy;

import towerdefense.GameField;
import towerdefense.Player;

public class TankerEnemy extends Enemy{
	 public TankerEnemy(Player player, GameField gameField) {
		 super(150, 40, 0.8f, 15,  70, "res\\GameEntity\\Enemy\\TankerEnemy.png");
		 this.player=player;
		 this.gameField=gameField;
	 }
		public static String getPath() {
			return "res\\GameEntity\\Enemy\\TankerEnemy.png";
		}
}
