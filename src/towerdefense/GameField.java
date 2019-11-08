package towerdefense;

import java.util.ArrayList;
import towerdefense.gameEntity.enemy.BossEnemy;
import towerdefense.gameEntity.enemy.Enemy;
import towerdefense.gameEntity.enemy.NormalEnemy;
import towerdefense.gameEntity.enemy.SmallerEnemy;
import towerdefense.gameEntity.enemy.TankerEnemy;

public class GameField {
	float timeLastWave;
	float timeLastSpawn;
	Player player;
	boolean endStage;
	int frame;
	boolean endWave;
	DelayFrame delayFrame;
	public GameMaps gameMaps;
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
	public ArrayList<Enemy> enemiesList;
	public GameField(Level lv,Player player) {
		this.player=player;
		index=0;
		frame=0;
		delayFrame=new DelayFrame();
		stageNumber=0;
		enemiesList= new ArrayList<Enemy>();
		level=lv;
		stage=new GameStage(lv);
		gameMaps= new GameMaps(this);
		newWave= new Wave(lv);
		pivok=level.startEnemies;
		nextStage();
	}
	
	public void spawn() {
			if(spawning && index<enemiesList.size()) {
				timeLastSpawn=Clock.getTotalTime();
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
			case 1: enemiesList.add(new SmallerEnemy(player, this)); break;
			case 2: enemiesList.add(new NormalEnemy(player, this)); break;
			case 3: enemiesList.add(new TankerEnemy(player, this)); break;
			case 4: enemiesList.add(new BossEnemy(player, this)); break;
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
		gameMaps.buildTowerMap();
		if(index==pivok) {endWave=true; waveNumber++; pivok+=level.startEnemies+(waveNumber-1)*level.enemiesPerWaveUp; timeLastWave =Clock.getTotalTime();}
		else endWave=false;
		if(endWave && index==enemiesList.size()) {
			endStage= true; 
			System.out.println("ENDSTAGE");
			}
		winStage(); 
		if(winStage) { System.out.println("WIN STAGE "+ stageNumber); nextStage();}
	}
	public void winStage() {
		if(endStage==true) {
			winStage=true;
		for(Enemy e: enemiesList) {
			if(e.alive) {winStage =false; return;}
		}} else winStage=false;
	}
}
