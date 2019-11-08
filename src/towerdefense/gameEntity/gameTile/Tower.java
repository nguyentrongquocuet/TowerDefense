package towerdefense.gameEntity.gameTile;



import java.awt.Graphics;

import javax.swing.ImageIcon;

import towerdefense.GameField;
import towerdefense.Position;
import towerdefense.gameEntity.Bullet;
import towerdefense.gameEntity.enemy.Enemy;


public abstract class Tower extends GameTile{
	public GameField gameField;
	public int shootSpeed;
	public int damage;
	public int bulletSpeed;
	public int range;
	public Bullet bullet;
	public int cost;
	public boolean firstTime=true;
	public float currentTime;
	public Tower(int damage, int bulletSpeed, int shootSpeed, int range, int cost, String path, String path2, GameField gameField) {
		this.shootSpeed=shootSpeed;
		this.gameField=gameField;
		this.cost=cost;
		this.damage=damage;
		this.bulletSpeed=bulletSpeed;
		this.range=range;
		this.bullet=new Bullet(bulletSpeed, shootSpeed, damage, range, path2);
		this.texturePath=path;
	}
	public double distane(Position pos1, Position pos2) {
		return Math.sqrt((pos1.x-pos2.x)*(pos1.x-pos2.x)+ (pos1.y-pos2.y)*(pos1.y-pos2.y));
	}
	public void checkEnemy() {
		for(Enemy enemy: gameField.enemiesList) {
			if(distane(enemy.pos, pos)<range/2 && enemy.active && enemy.alive) {
				//bullet.active=true;
				bullet.enemy=enemy;
				bullet.move();
				if(bullet.active==false) {bullet.pos.x = pos.x; bullet.pos.y=pos.y;}
				return;
			}
		}
}
		
	public void draw(Graphics g) {
		g.drawImage(new ImageIcon(texturePath).getImage(), (int)pos.x-20, (int)pos.y-20, null);
		if(bullet.active) g.drawImage(new ImageIcon(bullet.texturePath).getImage(), (int)bullet.pos.x, (int)bullet.pos.y, null);
	}
}
