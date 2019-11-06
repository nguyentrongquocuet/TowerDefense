//show update game
//GameField
package towerdefense;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import sun.nio.cs.ext.TIS_620;
import towerdefense.gameEntity.enemy.Enemy;
import towerdefense.gameEntity.gameTile.Grass;
import towerdefense.gameEntity.gameTile.MachineGunTower;
import towerdefense.gameEntity.gameTile.Mountain;
import towerdefense.gameEntity.gameTile.NormalTower;
import towerdefense.gameEntity.gameTile.Road;
import towerdefense.gameEntity.gameTile.SniperTower;
import towerdefense.gameEntity.gameTile.Spawner;
import towerdefense.gameEntity.gameTile.Target;
import towerdefense.gameEntity.gameTile.Tower;

public class Screen extends JPanel implements Runnable {
	// khai bao
	boolean started=false;
	private int fps = 0;
	int delay;
	public boolean isRunning = false;
	public int status;// status=0:ingame, 1: pause
	Frame frame;
	boolean checked = false;
	User user;
	Level level;
	// GameMaps gameMaps;
	// GameStage stage;
	GameField gameField;
	// public Level level;
	public Thread thread = new Thread(this);
	int onHand = 0;// trang thai giu thap
	public int mousePosX = 0;
	public int mousePosY = 0;
	Color warning = new Color(255, 16, 0, 90);
	Color exepting = new Color(64, 64, 64, 64);

	// ham
	public Screen(Frame frame) {
		this.frame = frame;
		MouseHandler mouseHandler = new MouseHandler(this);
		frame.addKeyListener(new KeyHandler(this));
		addMouseListener(mouseHandler);
		addMouseMotionListener(mouseHandler);
		thread.start();
	}

	public void draw(Graphics g, int x, int y, Tower tower, Color color) {
		g.drawImage(new ImageIcon(tower.texturePath).getImage(), x - 20, y - 20, null);
		g.setColor(color);
		g.fillOval(x - tower.range / 2, y - tower.range / 2, tower.range, tower.range);
	}

	public void paintComponent(Graphics g) {
		g.clearRect(0, 0, frame.getWidth(), frame.getHeight());
		g.setColor(Color.red);
		if (status == 0) {
			g.setColor(Color.yellow);
			g.fillRect(0, 0, frame.getWidth(), frame.getHeight());
			g.drawImage(new ImageIcon("res\\Background\\MainMenuBackground.jpg").getImage(), 0, 0, null);
			g.setColor(Color.red);
			g.drawString("PRESS SPACE OR CLICK TO PLAY", 600, 600);

		} else if (status == 1) {
			// Background

			g.drawImage(new ImageIcon("res\\Background\\MainMenuBackground.jpg").getImage(), 0, 0, null);

			g.setColor(Color.YELLOW);
			g.drawRect(0, 0, 40, 40);// o chua fps
			g.setColor(Color.RED);
			// ve map
			for (int i = 0; i < 26; i++) {
				for (int j = 0; j < 13; j++) {
					g.drawImage(new ImageIcon(Grass.getPath()).getImage(), i * 40 + 50, j * 40 + 50, null);
					switch (gameField.gameMaps.map[j][i]) {
					case 1:
						g.drawImage(new ImageIcon(Road.getPath()).getImage(), i * 40 + 50, j * 40 + 50, null);
						break;
					case 0:
						g.drawImage(new ImageIcon(Grass.getPath()).getImage(), i * 40 + 50, j * 40 + 50, null);
						break;
					case -2:
						g.drawImage(new ImageIcon(Mountain.getPath()).getImage(), i * 40 + 50, j * 40 + 50, null);
						break;
					}
				}
			}
			// ve quai
			for (Enemy enemy:gameField.enemiesList) {
				if(enemy.active&&enemy.alive) {
					g.drawImage(new ImageIcon(enemy.texturePath).getImage(), enemy.pos.x, enemy.pos.y, null);
					//g.drawRect(enemy.pos.x, enemy.pos.y-8, 40, 5);
					g.setColor(Color.RED);
					g.fillRect(enemy.pos.x, enemy.pos.y-8, 40*enemy.realTimeHealth/enemy.health, 5);
					
				}
			}
			g.drawImage(new ImageIcon(Spawner.getPath()).getImage(), gameField.gameMaps.spawnerPosition.x - 12,
					gameField.gameMaps.spawnerPosition.y - 12, null);
			g.drawImage(new ImageIcon(Target.getPath()).getImage(), gameField.gameMaps.targetPosition.x - 12,
					gameField.gameMaps.targetPosition.y - 12, null);
			

			// g.setColor(null); ve thap
			for (int i = 0; i < 26; i++) {
				for (int j = 0; j < 13; j++) {
					switch (gameField.gameMaps.tower[j][i]) {
					case 0:
						break;
					case 1:
						draw(g, i * 40 + 70, j * 40 + 70, new NormalTower(), exepting);
						break;
					case 2:
						draw(g, i * 40 + 70, j * 40 + 70, new MachineGunTower(), exepting);
						break;
					case 3:
						draw(g, i * 40 + 70, j * 40 + 70, new SniperTower(), exepting);
						break;
					}
				}
			}

			g.drawImage(new ImageIcon("res\\Background\\Fill.png").getImage(), 0, 0, null);

			// display towerslist
			g.setColor(Color.YELLOW);
			for (int i = 200; i < 310; i += 40) {
				g.drawRect(i, 600, 40, 40);
				if (i == 200) {
					g.drawImage(new ImageIcon(NormalTower.getPath()).getImage(), i, 600, null);
				} else if (i == 250) {
					g.drawImage(new ImageIcon(MachineGunTower.getPath()).getImage(), i, 600, null);
				} else
					g.drawImage(new ImageIcon(SniperTower.getPath()).getImage(), i, 600, null);
				i += 10;
			}
			// display health and coin
			g.setColor(Color.YELLOW);
			g.drawRect(50, 600, 120, 40);
			g.drawRect(50, 640, 120, 40);
			g.drawString("Health: " + user.player.health, 70, 625);
			g.drawString("Coin: " + user.player.coin, 70, 665);

			// thap di chuyen cung chuot
			if (onHand != 0) {
				// draw(g, mousePosX, mousePosY,new Tower);
//				g.drawOval(mousePosX-20, mousePosY-45, 40, 40);
				switch (onHand) {
				case 1:
					if (isAble(mousePosX, mousePosY))
						draw(g, mousePosX, mousePosY, new NormalTower(), exepting);
					else
						draw(g, mousePosX, mousePosY, new NormalTower(), warning);
					break;
				case 2:
					if (isAble(mousePosX, mousePosY))
						draw(g, mousePosX, mousePosY, new MachineGunTower(), exepting);
					else
						draw(g, mousePosX, mousePosY, new MachineGunTower(), warning);
					break;
				case 3:
					if (isAble(mousePosX, mousePosY))
						draw(g, mousePosX, mousePosY, new SniperTower(), exepting);
					else
						draw(g, mousePosX, mousePosY, new SniperTower(), warning);
					break;
				}
			}
		}
		g.setColor(Color.YELLOW);
		g.drawString(fps + "", 10, 20);
	}

	// vao man hinh cho(maybe menu)
	public void loadGame() {
		isRunning = true;
		level = new Level(1);
		gameField = new GameField(level);
		user = new User(this);
	}

	// bat dau choi
	public void startGame(User user) {
		if(started==false) {
			user.creatPlayer();
			status = 1;
			started=true;
			delay=level.spawnSpeed;
			}

		// gameField= new GameField(level);
	}

	public void run() {
		System.out.print("RUNNING!\n");
		long beginTimes = System.currentTimeMillis();
		int frames = 0;
		loadGame();
		// System.out.println(gameField.stage.wave.id[4]);
		System.out.println(frame.getWidth() + frame.getHeight());
		// System.out.println(gameField.enemies[1].texturePath);
		while (isRunning) {
			repaint();
			update();
			frames++;
			if (System.currentTimeMillis() - beginTimes >= 1000) {
				fps = frames;
				frames = 0;
				beginTimes = System.currentTimeMillis();
			}

			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.print("STOP");
		System.exit(0);
	}

	public void update() {
		if(status==1) {
			//System.out.println(gameField.winStage);
			if(gameField.endWave==true) {delay=level.newWaveSpeed;}
			if(delay<=0) {gameField.spawning=true; delay=level.spawnSpeed;} else {
				delay--; gameField.spawning=false;
			}
			gameField.spawn();
			for(Enemy e:gameField.enemiesList) {
				if(e.active) e.run();
			}
		}
	}
	
	public boolean isAble(int x, int y) {
		int indexX = (x - 50) / 40;
		int indexY = (y - 50) / 40;
		if (x > 50 && x < 1090 && y > 50 && y < 570) {
			if (gameField.gameMaps.map[indexY][indexX] != 0) {
				return false;
			}
			if (gameField.gameMaps.tower[indexY][indexX] != 0) {
				return false;
			}
		} else
			return false;
		return true;
	}

	public void placeTower(int x, int y, int onHand) {
		if (isAble(x, y)) {
			gameField.gameMaps.tower[(int) (y - 50) / 40][(int) (x - 50) / 40] = onHand;
			switch (onHand) {
			case 1:
				user.player.coin -= NormalTower.getCost();
				System.out.println("YOU BOUGHT NORMAL TOWER, YOUR COIN: " + user.player.coin);
				break;
			case 2:
				user.player.coin -= MachineGunTower.getCost();
				System.out.println("YOU BOUGHT MACHINE GUN TOWER, YOUR COIN: " + user.player.coin);
				break;
			case 3:
				user.player.coin -= SniperTower.getCost();
				System.out.println("YOU BOUGHT SNIPER TOWER, YOUR COIN: " + user.player.coin);
				break;
			}
		}
	}

	public class MouseEffect {
		boolean mouseDown = false;

		// xu ly keo tha
		public void moved(MouseEvent e) {
			mousePosX = e.getX();
			mousePosY = e.getY();
			// System.out.println(mousePosX+" "+mousePosY);
			if (onHand != 0) {
				System.out.println("indexX " + (mousePosX - 50) / 40 + " indexY " + (mousePosY - 50) / 40
						+ isAble(mousePosX, mousePosY));
			}
		}

		// xu ly keo tha
		public void Effect(MouseEvent e) {
			if (status == 1)// ingame
			{
				if (mouseDown && onHand == 0) {

					if (e.getY() >= 600 & e.getY() <= 640) {
						if (e.getX() >= 200 && e.getX() <= 240) {
							if (user.player.coin >= NormalTower.getCost()) {
								onHand = 1;
							} else
								System.out.println("NOT ENOUGH MONEY!");
						}
						if (e.getX() >= 250 && e.getX() <= 290) {
							if (user.player.coin >= MachineGunTower.getCost()) {
								onHand = 2;
							} else
								System.out.println("NOT ENOUGH MONEY!");
						}
						if (e.getX() >= 300 && e.getX() <= 340) {
							if (user.player.coin >= SniperTower.getCost()) {
								onHand = 3;// dang giu de xay thap
							} else
								System.out.println("NOT ENOUGH MONEY!");
						}

					}
				}
			} else
				startGame(user);
		}

		// nha chuot, xay xong nha
		public void down(MouseEvent e) {
			mouseDown = true;
			if (status == 1 && onHand != 0) {
				placeTower(e.getX(), e.getY(), onHand);
				onHand = 0;
			}
			Effect(e);
		}
	}

	public class GameStatus {
		public void exitGame() {
			isRunning = false;
		}

		public void srtGame() {
			startGame(user);
		}
	}
}
