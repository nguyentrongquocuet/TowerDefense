package towerdefense;

import java.util.ArrayList;


public class GameStage {
	private int numberOfWave;
	int stageNumber;
	//private int waveNumber;
	private Wave wave;
	Level level;
	ArrayList<Integer> stageEnemies;
	public GameStage(Level lv) {
		stageEnemies=new ArrayList<Integer>();
		level=lv;
		wave= new Wave(lv);
		//stageNumber=1;
		//waveNumber=1;
	}

	public void buildStageEnemy() {
		numberOfWave=stageNumber;
		System.out.println("stage number"+ stageNumber+ "number of wave"+ numberOfWave);
		for(int i=1; i<=numberOfWave; i++) {
			wave.waveNumber=i;
			wave.buildWaveEnemy();
			for(int j=0; j<wave.id.size(); j++) {
				stageEnemies.add(wave.id.get(j));
			}
			wave.id.clear();
		}

	}
}
