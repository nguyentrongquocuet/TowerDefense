package towerdefense;

public class Level {
	 int level;
	 int spawnSpeed;
	 int enemiesPerWaveUp;
	 int numberWaveUp;
	 int newWaveSpeed;
	 int startEnemies;

	// int waveUp;//so wave tang sau moi stage
	public Level(int l) {
		switch (l) {
		case 1:
			startEnemies = 5;
			enemiesPerWaveUp = 3;
			spawnSpeed = 600;
			numberWaveUp = 1;
			newWaveSpeed =3000;
			break;
		case 2:
			startEnemies = 7;
			enemiesPerWaveUp = 4;
			spawnSpeed = 500;
			numberWaveUp = 2;
			newWaveSpeed = 7500;
			break;
		case 3:
			startEnemies = 9;
			enemiesPerWaveUp = 5;
			spawnSpeed = 400;
			numberWaveUp = 2;
			newWaveSpeed = 7500;
			break;
		}
	}

}
