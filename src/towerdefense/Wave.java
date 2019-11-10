package towerdefense;

import java.util.ArrayList;
import java.util.Random;

public class Wave {
	private int boss;
	private Level level;
	private int normal;
	private int small;
	private int tanker;
	int waveNumber;
	private int numberOfEnemies;// khong co boss
	ArrayList<Integer> id;

	public Wave(Level lv) {
		level = lv;
		numberOfEnemies=level.startEnemies;
		waveNumber=1;
		id = new ArrayList<Integer>();
	}
	
	public void buildWaveEnemy() {
		numberOfEnemies=level.startEnemies+((waveNumber-1)*level.enemiesPerWaveUp);
		System.out.println("number of nenemies"+ numberOfEnemies);
		Random rd= new Random();
		if(numberOfEnemies>=8) boss=1;
		small= (int)(numberOfEnemies*(rd.nextInt(1)+1)/4);
		tanker= (int)((rd.nextInt(2)+1) * numberOfEnemies /5);
		normal= numberOfEnemies-tanker-small-boss;
		for(int i=0; i<numberOfEnemies; i++) {
			if(i<small) {
				id.add(1);
				continue;
			}
			if(i>=small&& i<small+normal) {
				id.add(2); 
				continue;
			}
			if(i>=small+normal&&i<small+normal+tanker) {
				id.add(3);
				continue;
			}else {id.add(4); continue;}
			}
		System.out.println("WAVE BUILD COMPLETE, WE HAVE "+id.size()+"  "+boss+ " "+normal+ " "+tanker+" "+small);

	}}