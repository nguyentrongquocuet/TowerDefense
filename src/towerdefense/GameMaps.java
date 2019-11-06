package towerdefense;

import java.io.BufferedReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GameMaps {
	public int[][] map;
	public int[][] tower;
	public Position spawnerPosition;
	public Position targetPosition;
	public Position spawnerMatrixPosition;
	public Position targetMatrixPosition;

	public GameMaps() {
		map = new int[13][26];
		tower = new int[13][26];
		this.loadData("res\\Config\\map.txt", map);
		this.loadData("res\\Config\\tower.txt", tower);
		for (int i = 0; i < 13; i++) {
			for (int j = 0; j < 26; j++) {
				if (this.map[i][j] == 3) {
					spawnerMatrixPosition = new Position(i, j);
					spawnerPosition = new Position(j * 40 + 50, i * 40 + 50);
				} else if (this.map[i][j] == 2) {
					targetMatrixPosition = new Position(i, j);
					targetPosition = new Position(j * 40 + 50, i * 40 + 50);
				}
			}
		}
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
}
