package towerdefense;

import java.util.ArrayList;
import towerdefense.gameEntity.enemy.BossEnemy;
import towerdefense.gameEntity.enemy.Enemy;
import towerdefense.gameEntity.enemy.NormalEnemy;
import towerdefense.gameEntity.enemy.SmallerEnemy;
import towerdefense.gameEntity.enemy.TankerEnemy;

public class GameField {
	boolean endStage;
	int frame;
	boolean endWave;
	DelayFrame delayFrame;
	GameMaps gameMaps;
	boolean spawning=true;
	int index;
	int cur=0;
	boolean winStage;
	int stageNumber;
	Level level;
	Wave newWave;
	int waveNumber;
	GameStage stage;
	int pivok;
	ArrayList<Enemy> enemiesList;
	public GameField(Level lv) {
		index=0;
		frame=0;
		delayFrame=new DelayFrame();
		stageNumber=0;
		enemiesList= new ArrayList<Enemy>();
		level=lv;
		stage=new GameStage(lv);
		gameMaps= new GameMaps();
		newWave= new Wave(lv);
		pivok=level.startEnemies;
		nextStage();
		//buildGameEnemy();
	}
	
	public void spawn() {
			if(spawning && index<enemiesList.size()) {
				System.out.println("LIST"+enemiesList.size()+" SPWANING");
				enemiesList.get(index).active=true;
				enemiesList.get(index).alive=true;
				index++;
			}
			update();
	}
	
	public void buildGameEnemy() {
		for(int i=0; i< stage.stageEnemies.size(); i++) {
			switch (stage.stageEnemies.get(i)) {
			case 1: enemiesList.add(new SmallerEnemy()); break;
			case 2: enemiesList.add(new NormalEnemy()); break;
			case 3: enemiesList.add(new TankerEnemy()); break;
			case 4: enemiesList.add(new BossEnemy()); break;
			}
		}
	}

	public void nextStage() {
		System.out.println("winnnstaff" + winStage);
		winStage=false;
		endStage=false;
		endWave=true;
		index=0;
		waveNumber=1;
		spawning=true;
		//endStage=false;
		//winStage=false;
		enemiesList.clear();
		pivok=level.startEnemies;
		stageNumber++;
		stage.stageNumber=stageNumber;
		stage.buildStageEnemy();
		buildGameEnemy();
		System.out.println("STAGE NUMBER" + stage.stageNumber);
	}
	public void update() {
		//System.out.println("index"+index);
		if(index==pivok) {endWave=true; waveNumber++; pivok+=level.startEnemies+(waveNumber-1)*level.enemiesPerWaveUp; System.out.println("NEW PIVOK "+pivok +"index"+index);}
		else endWave=false;
		//System.out.println("EndWave"+endWave);
		if(endWave && index==enemiesList.size()) {
			endStage= true; 
			System.out.println("ENDSTAGE");}
		winStage(); 
		//System.out.println("Endstage"+endStage);
		//System.out.println("winstage"+winStage);
		if(winStage) { System.out.println("WIN STAGE "+ stageNumber); nextStage();}
		System.out.println("IS END STAGE" + endStage);
	}
	public void winStage() {
		if(endStage==true) {
			winStage=true;
		for(Enemy e: enemiesList) {
			if(e.alive) {winStage =false; return;}
		}} else winStage=false;
		
	}
}
