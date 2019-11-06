package towerdefense;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


public class GameStage {
	int numberOfWave;
	int stageNumber;
	int waveNumber;
	Wave wave;
	Level level;
	ArrayList<Integer> stageEnemies;
	public GameStage(Level lv) {
		stageEnemies=new ArrayList<Integer>();
		level=lv;
		wave= new Wave(lv);
		stageNumber=1;
		waveNumber=1;
	}
	public void nextStage() {
		stageNumber++;
		waveNumber=1;
	}
	
	public void buildStageEnemy() {
		numberOfWave=stageNumber;
		for(int i=waveNumber; i<=numberOfWave; i++) {
			wave.waveNumber=i;
			wave.buildWaveEnemy();
			for(int j=0; j<wave.id.size(); j++) {
				stageEnemies.add(wave.id.get(j));
			}
			wave.id.clear();
			System.out.println("WAVE LIST NOW EMPTY "+ wave.id.isEmpty());
		}

	}
}
