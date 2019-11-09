package towerdefense.gameEntity.enemy;

import static towerdefense.Clock.*;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;

import towerdefense.GameField;
import towerdefense.GameMaps;
import towerdefense.Player;
import towerdefense.Position;
import towerdefense.gameEntity.GameEntity;

public abstract class Enemy implements GameEntity {
	GameField gameField;
	Player player;
	boolean firstTime=true;
	GameMaps gameMaps;
	private int health;
	public int realTimeHealth;
	private int armor;
	private float speed;
	public int reward;
	public boolean active;
	public int damage;
	public Position pos;
	public boolean alive;
	public String texturePath;
	private int[] deltaPos= new int[2];
	private float distance=0f;
	private int mPosX;
	private int mPosY;
	
	////////////////////////////////////////////////////////////////////////////////////////////////
	public Enemy() {
	}

	public Enemy(int health, int armor, float speed, int damage, int reward, String texturePath) {
		alive = false;
		active = false;
		gameMaps = new GameMaps(gameField);
		this.health = health;
		this.realTimeHealth=this.health;
		this.armor = armor;
		this.speed = speed;
		this.damage = damage;
		this.reward = reward;
		this.texturePath = texturePath;
		pos = gameMaps.spawnerPosition;
		mPosX=(int)gameMaps.getSpawnerMatrixPosition().x;
		mPosY=(int)gameMaps.getSpawnerMatrixPosition().y;
	}

	
	public int getReward() {
		return reward;
	}

	public boolean isActive() {
		return active;
	}

	public boolean isAlive() {
		return alive;
	}

	public int getAmor() {
		return armor;
	}

	public void update() {
		if(Math.abs((int)distance)>=40) {
			distance=0;
			mPosX+=deltaPos[1];
			mPosY+=deltaPos[0];
			if(!canMove(mPosX+deltaPos[1], mPosY+deltaPos[0])) { pos.x=mPosY*40+70; pos.y=mPosX*40+70; findRoad();}
		}
		
		if (mPosX==gameMaps.targetMatrixPosition.x&& mPosY==gameMaps.targetMatrixPosition.y) {
			alive = false;
			player.health-=damage;
		}
		if(realTimeHealth<=0) {realTimeHealth=0; alive=false; gameField.player.coin+=reward;}
	}

	public void setPos_1(Position pos) {
		this.pos = pos;
	}

	public void setPos(float x, float y) {
		pos.setPosition(x, y);
	}

	public String getTexturePath() {
		return texturePath;
	}

	private boolean canMove(int x, int y) {
		if(x<13&&x>=0&&y<26&&y>=0) {
			if(gameField.gameMaps.map[x][y]>0) return true;
			}
		return false;
	}

	private void move() {
		if(!firstTime) {
			pos.x+=deltaPos[0]*deltaMove()*speed/15;
			pos.y+=deltaPos[1]*deltaMove()*speed/15;
			distance+=deltaPos[0]*deltaMove()*speed/15+deltaPos[1]*deltaMove()*speed/15;
			} else {
			findRoad(); firstTime=false;
			distance=0;
			}	
	}

	private void findRoad() {
		//i:, j:vi tri trong mang
		if(canMove(mPosX, mPosY-1)) {
			//left
			if(!(deltaPos[0]==1&&deltaPos[1]==0)) {
			deltaPos[0]=-1;
			deltaPos[1]=0; return;
			}
		}
		
		if(canMove(mPosX, mPosY+1)) {
			//right
			if(!(deltaPos[0]==-1&&deltaPos[1]==0)) {
				deltaPos[0]=1;
				deltaPos[1]=0; return;}
		}
		
		if(canMove(mPosX-1, mPosY)) {
			//up
			if(!(deltaPos[0]==0&&deltaPos[1]==1)) {
				deltaPos[0]=0;
				deltaPos[1]=-1; return;}
		}
		
		if(canMove(mPosX+1, mPosY)) {
			//down
			if(!(deltaPos[0]==0&&deltaPos[1]==-1)) {
				deltaPos[0]=0;
				deltaPos[1]=1; return;}
		}
		
		deltaPos[0]=0;
		deltaPos[1]=0;
	}



	public void run() {
		if (active && alive) {
			move();
			update();
		}
	}
	
	
	
	public void draw(Graphics g) {
		g.drawImage(new ImageIcon(texturePath).getImage(), (int)pos.x-20, (int)pos.y-20, null);
		g.setColor(Color.RED);
		g.fillRect((int)pos.x-20, (int)pos.y-28, 40*realTimeHealth/health, 5);
	}
}
