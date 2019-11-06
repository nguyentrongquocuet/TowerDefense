package towerdefense.gameEntity.gameTile;
import towerdefense.gameEntity.Bullet;

public class MachineGunTower extends Tower{
	public MachineGunTower() {
		super(2, 35, 120, 100, new Bullet(30, 30, 50), "res\\GameEntity\\GameTile\\Tower\\MachineGun.png");
	}
	public static String getPath() {
		return "res\\GameEntity\\GameTile\\Tower\\MachineGun.png";
	}
	public static int getCost() {
		return 100;
	}
}
