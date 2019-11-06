package towerdefense;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

public class Wave {
	int boss;
	Level level;
	int normal;
	int small;
	int tanker;
	int waveNumber;
	int numberOfEnemies;// khong co boss
	ArrayList<Integer> id;

	public Wave(Level lv) {
		level = lv;
		numberOfEnemies=level.startEnemies;
		waveNumber=1;
		id = new ArrayList<Integer>();
	}
	public void buildWaveEnemy() {
		numberOfEnemies=level.startEnemies+((waveNumber-1)*level.enemiesPerWaveUp);
		Random rd= new Random();
		if(this.numberOfEnemies>=10) this.boss=1;
		this.small= (int)(this.numberOfEnemies*(rd.nextInt(1)+1)/4);
		this.tanker= (int)((rd.nextInt(2)+1) * this.numberOfEnemies /5);
		this.normal= numberOfEnemies-this.tanker-this.small-this.boss;
		for(int i=0; i<this.numberOfEnemies; i++) {
			if(i<this.small) {
				this.id.add(1);
				continue;
			}
			if(i>=this.small&& i<this.small+this.normal) {
				this.id.add(2); 
				continue;
			}
			if(i>=this.small+this.normal&&i<this.small+this.normal+this.tanker) {
				this.id.add(3);
				continue;
			}else {this.id.add(4); continue;}
			//enemies[i].pos=new GameMaps().spawnerPosition;
			}
		System.out.println("WAVE BUILD COMPLETE, WE HAVE "+this.boss+ " "+this.normal+ " "+this.tanker+" "+this.small);

	}}