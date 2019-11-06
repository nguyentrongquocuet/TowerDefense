package towerdefense.gameEntity.enemy;

public class NormalEnemy extends Enemy{
	public NormalEnemy() {
		super(1,100, 20, 10, 15, 30, "res\\GameEntity\\Enemy\\NormalEnemy.png");
	}
	public void setPos(int x, int y) {
		super.setPos(x, y);
	}
	public void setTexturePath(String s) {
		this.texturePath=s;
	}
	public static String getPath() {
		return "res\\GameEntity\\Enemy\\NormalEnemy.png";
	}
}
