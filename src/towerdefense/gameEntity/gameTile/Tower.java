package towerdefense.gameEntity.gameTile;



import java.awt.Graphics;
import static towerdefense.Clock.*;
import javax.swing.ImageIcon;


import towerdefense.GameField;
import towerdefense.Position;
import towerdefense.gameEntity.Bullet;
import towerdefense.gameEntity.enemy.Enemy;


public abstract class Tower extends GameTile{
	float lastShoot=0f;
	private GameField gameField;
	private int shootSpeed;
	private int damage;
	private int bulletSpeed;
	private int range;
	private Bullet bullet;
	private int cost;
	public Tower(int damage, int bulletSpeed, int shootSpeed, int range, int cost, String path, String path2, GameField gameField) {
		this.shootSpeed=shootSpeed;
		this.gameField=gameField;
		this.cost=cost;
		this.damage=damage;
		this.bulletSpeed=bulletSpeed;
		this.range=range;
		this.bullet= new Bullet(bulletSpeed, shootSpeed, damage, range, path2);
		this.texturePath=path;
	}
	
	public Bullet getBullet() {
		return bullet;
	}

	
	public void checkEnemy() {
		
		if(getTotalTime()-lastShoot>=shootSpeed*deltaDelay()) {
		for(Enemy enemy: gameField.enemiesList) {
			if(pos.distance(enemy.pos)<range && enemy.active && enemy.alive) {
				lastShoot=getTotalTime();
				bullet.enemy=enemy;
				bullet.readyToFire=true;
				bullet.active=true;
				if(bullet.pos.distance(pos)>range) bullet.active=false;
				if(bullet.active==false) { bullet.pos.x = pos.x; bullet.pos.y=pos.y; bullet.enemy=null;}
				return;
			}
		}}
		
		bullet.move();
	}
		
	public void draw(Graphics g) {
		g.drawImage(new ImageIcon(texturePath).getImage(), (int)pos.x-20, (int)pos.y-20, null);
		if(bullet.active) g.drawImage(new ImageIcon(bullet.texturePath).getImage(), (int)bullet.pos.x-6, (int)bullet.pos.y-6, null);
	}
}
