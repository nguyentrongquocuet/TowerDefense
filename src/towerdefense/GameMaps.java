package towerdefense;

import java.io.BufferedReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import towerdefense.gameEntity.gameTile.MachineGunTower;
import towerdefense.gameEntity.gameTile.NormalTower;
import towerdefense.gameEntity.gameTile.SniperTower;
import towerdefense.gameEntity.gameTile.Tower;

public class GameMaps {
	GameField gameField;
	int towerSize=0;
	public int[][] map;
	public int[][] towerMap;
	public Tower[] tower;
	public Position spawnerPosition;
	public Position targetPosition;
	public Position spawnerMatrixPosition;
	public Position targetMatrixPosition;
	public Position road;

	public GameMaps(GameField gameField) {
		this.gameField=gameField;
		map = new int[13][26];
		towerMap = new int[13][26];
		tower= new Tower[13*26];
		loadData("res\\Config\\map.txt", map);
		loadData("res\\Config\\tower.txt", towerMap);
		buildTowerMap();
		findSpecialPosition();
	}
	
	public boolean loadData(String filePath, int[][] arr) {
		Path path = Paths.get(filePath);
		Charset charset = Charset.forName("US-ASCII");
		try (BufferedReader reader = Files.newBufferedReader(path, charset)) {
			String line = null;
			int count = 0;
			while ((line = reader.readLine()) != null) {
				String k[] = line.split(" ");
				if (k.length == 1) {
					arr = new int[Integer.parseInt(k[0])][Integer.parseInt(k[0])];
				} else {
					for (int i = 0; i < k.length; i++) {
						arr[count][i] = Integer.parseInt(k[i]);
					}
					count++;
				}
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	public void findSpecialPosition() {
		for (int i = 0; i < 13; i++) {
			for (int j = 0; j < 26; j++) {
				if (this.map[i][j] == 3) {
					spawnerMatrixPosition = new Position(i, j);
					spawnerPosition = new Position(j * 40 + 70, i * 40 + 70);
				} else if (this.map[i][j] == 2) {
					targetMatrixPosition = new Position(i, j);
					targetPosition = new Position(j * 40 + 70, i * 40 + 70);
				}
			}
		}
	}
	
	public void buildTowerMap() {
		towerSize=0;
		for (int i = 0; i < 13; i++) {
			for (int j = 0; j < 26; j++) {
				switch (towerMap[i][j]) {
				default : tower[towerSize]=null; break;
				case 1:
					tower[towerSize]=new NormalTower(new Position(j*40+70, i*40+70), gameField); towerSize++;
					break;
				case 2:
					tower[towerSize]=new MachineGunTower(new Position(j*40+70, i*40+70), gameField); towerSize++;
					break;
				case 3:
					tower[towerSize]=new SniperTower(new Position(j*40+70, i*40+70), gameField); towerSize++;
					break;
				}
			}
		}
	}
	
}
