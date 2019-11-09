//show update game
//GameField
package towerdefense;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.sun.xml.internal.ws.api.config.management.policy.ManagementAssertion.Setting;

import towerdefense.gameEntity.enemy.Enemy;
import towerdefense.gameEntity.gameTile.Mountain;
import towerdefense.gameEntity.gameTile.MachineGunTower;
import towerdefense.gameEntity.gameTile.Rock;
import towerdefense.gameEntity.gameTile.NormalTower;
import towerdefense.gameEntity.gameTile.Road;
import towerdefense.gameEntity.gameTile.SniperTower;
import towerdefense.gameEntity.gameTile.Spawner;
import towerdefense.gameEntity.gameTile.Target;

public class Screen extends JPanel implements Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// khai bao
	private boolean started=false;
	private int fps = 0;
	public boolean isRunning = false;
	public int status=0;// status=1:ingame, 0: pause
	Frame frame;
	private User user;
	private Level level;
	private GameField gameField;
	private Thread thread = new Thread(this);
	private int onHand = 0;// trang thai giu thap
	private int mousePosX = 0;
	private int mousePosY = 0;
	Color warning = new Color(255, 16, 0, 90);
	Color exepting = new Color(64, 64, 64, 64);
	Image winImage= new ImageIcon("res\\Background\\WinStage.jpg").getImage();
	Image lose= new ImageIcon("res\\Background\\Lose.jpg").getImage();
	Image levelSelect= new ImageIcon("res\\Background\\WinStage.jpg").getImage();
	Image pauseMenu= new ImageIcon("res\\Background\\PauseMenu.jpg").getImage();
	
	// ham
	public Screen(Frame frame) {
		this.frame = frame;
		MouseHandler mouseHandler = new MouseHandler(this);
		KeyHandler keyHandler=new KeyHandler(this);
		frame.addKeyListener(keyHandler);
		addKeyListener(keyHandler);
		addMouseListener(mouseHandler);
		addMouseMotionListener(mouseHandler);
		setVisible(true);
		thread.start();
	}

	private void draw(Graphics g, int x, int y, int range, String texturePath, Color color) {
		g.drawImage(new ImageIcon(texturePath).getImage(), x - 20, y - 20, null);
		g.setColor(color);
		g.fillOval(x - range, y - range, range*2, range*2);
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void paintComponent(Graphics g) {
		g.clearRect(0, 0, frame.getWidth(), frame.getHeight());
		g.setColor(Color.red);
		/*
		 * switch(status ) {
		 * 
		 * case 0: start game scence startScense
		 * case 1: in game 
		 * sase 2: pause game: included setting: level(sound / maybe)...
		 * 
		 * 
		 * 
		 * 
		 * }
		 */
		
		
		if(status ==1) inGame(g);
		if(status ==0) startScene(g);
		if(status ==2) winScense(g);
		if(status ==3) loseScense(g);
		if(status ==4) pauseGameMenu(g);
		if(status ==5) Setting(g);
		

	}





////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private void pauseGameMenu(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(pauseMenu, 0, 0, null);
		
	}
	
	private void Setting(Graphics g) {
		// TODO Auto-generated method stub
		
	}
	
	private void winScense(Graphics g) {
		// TODO Auto-generated method stub
		// win, next stage
		g.drawImage(winImage, 0, 0, null);
	}
	
	private void loseScense(Graphics g) {
		//thua 
		g.drawImage(lose, 0, 0, null);
	}
	
	private void startScene(Graphics g) {
		if(level==null) status=5;
		else {
		g.setColor(Color.yellow);
		g.fillRect(0, 0, frame.getWidth(), frame.getHeight());
		g.drawImage(new ImageIcon("res\\Background\\MainMenuBackground.jpg").getImage(), 0, 0, null);
		g.setColor(Color.red);
		g.drawString("PRESS SPACE OR CLICK TO PLAY", 600, 600);
	}}
		
	private void inGame(Graphics g) {
		if (status == 1) {
			// Background
			g.drawImage(new ImageIcon("res\\Background\\MainMenuBackground.jpg").getImage(), 0, 0, null);

			g.setColor(Color.YELLOW);
			g.drawRect(0, 0, 40, 40);// o chua fps
			g.setColor(Color.RED);
			// ve map
			for (int i = 0; i < 26; i++) {
				for (int j = 0; j < 13; j++) {
					g.drawImage(new ImageIcon(Mountain.getPath()).getImage(), i * 40 + 50, j * 40 + 50, null);
					switch (gameField.gameMaps.map[j][i]) {
					case -1: g.drawImage(new ImageIcon("res\\GameEntity\\GameTile\\Tower\\Destroyed.png").getImage(), i * 40 + 50, j * 40 + 50, null);
						break;
					case 1:
						g.drawImage(Road.getImage(), i * 40 + 50, j * 40 + 50, null);
						break;
					case 0:
						g.drawImage(Mountain.getImage(), i * 40 + 50, j * 40 + 50, null);
						break;
					case -2:
						g.drawImage(Rock.getImage(), i * 40 + 50, j * 40 + 50, null);
						break;
					}
				}
			}
			// ve quai
			for (Enemy enemy:gameField.getEnemiesList()) {
				if(enemy.active&&enemy.alive) {
					enemy.draw(g);
				}
			}
			//ve spawner va target
			g.drawImage(new ImageIcon(Spawner.getPath()).getImage(), (int)gameField.gameMaps.getSpawnerPosition().x - 32,
					(int)gameField.gameMaps.getSpawnerPosition().y - 32, null);
			g.drawImage(new ImageIcon(Target.getPath()).getImage(), (int)gameField.gameMaps.getTargetPosition().x - 32,
					(int)gameField.gameMaps.getTargetPosition().y - 32, null);
			

			// g.setColor(null); ve thap
			for(int i=0; i<gameField.gameMaps.getTowerSize(); i++) {
				if(gameField.gameMaps.tower[i]!=null)
				gameField.gameMaps.tower[i].draw(g);
			}
		
			/*ve filll
			 * g.drawImage(new ImageIcon("res\\Background\\Fill.png").getImage(), 0, 0,
			 * null);
			 */
			
			//display speedUp
			g.setColor(Color.YELLOW);
			g.drawRect(810, 600, 120, 80);
			if(Clock.speedUp!=4) {
				g.drawImage(new ImageIcon("res\\Background\\SpeedUp.png").getImage(), 810, 600, null);
			} else g.drawImage(new ImageIcon("res\\Background\\nonSpeedUp.png").getImage(), 810, 600, null);
			
			//display tower interview
			
			// display towerslist
			g.setColor(Color.YELLOW);
			for (int i = 200; i < 310; i += 40) {
				g.drawRect(i, 600, 40, 40);
				if (i == 200) {
					g.drawImage(NormalTower.getImage(), i, 600, null);
				} else if (i == 250) {
					g.drawImage(MachineGunTower.getImage(), i, 600, null);
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
				switch (onHand) {
				case 1:
					if (isAble(mousePosX, mousePosY))
						draw(g, mousePosX, mousePosY, NormalTower.getRange(), NormalTower.getPath(), exepting);
					else
						draw(g, mousePosX, mousePosY, NormalTower.getRange(), NormalTower.getPath(), warning);
					break;
				case 2:
					if (isAble(mousePosX, mousePosY))
						draw(g, mousePosX, mousePosY, MachineGunTower.getRange(), MachineGunTower.getPath(), exepting);
					else
						draw(g, mousePosX, mousePosY, MachineGunTower.getRange(), MachineGunTower.getPath(), warning);
					break;
				case 3:
					if (isAble(mousePosX, mousePosY))
						draw(g, mousePosX, mousePosY, SniperTower.getRange(), SniperTower.getPath(), exepting);
					else
						draw(g, mousePosX, mousePosY, SniperTower.getRange(), SniperTower.getPath(), warning);
					break;
				}
			}
			//
			if(onHand!=0) {
				g.setColor(Color.YELLOW);
				g.drawRect(1130, 50, 160, 160);
				g.setFont(new Font("TimesRoman", Font.BOLD, 20));
				switch(onHand) {
				case 1: 
					g.drawString("NORMAL TOWER", 1130, 250);
					g.drawImage(NormalTower.getInterview(), 1130, 50, null);
					g.drawString("Cost "+ NormalTower.getCost(), 1130, 290);
					g.drawString("Damage "+ NormalTower.getDamage(), 1130, 330);
					g.drawString("Range "+ NormalTower.getRange(), 1130, 370);
					g.drawString("Shoot Speed "+ NormalTower.getShootSpeed(),1130 , 410);
					
					break; 
				case 2: g.drawString("MACHINE GUN TOWER", 1130, 250);
				g.drawImage(MachineGunTower.getInterview(), 1130, 50, null);
				g.drawString("Cost "+ MachineGunTower.getCost(), 1130, 290);
				g.drawString("Damage "+ MachineGunTower.getDamage(), 1130, 330);
				g.drawString("Range "+ MachineGunTower.getRange(), 1130, 370);
				g.drawString("Shoot Speed "+ MachineGunTower.getShootSpeed(),1130 , 410);
					break;
				case 3: g.drawString("SNIPER TOWER", 1130, 250);
				g.drawImage(SniperTower.getInterview(), 1130, 50, null);
				g.drawString("Cost "+ SniperTower.getCost(), 1130, 290);
				g.drawString("Damage "+ SniperTower.getDamage(), 1130, 330);
				g.drawString("Range "+ SniperTower.getRange(), 1130, 370);
				g.drawString("Shoot Speed "+ SniperTower.getShootSpeed(),1130 , 410);
					break;
				}
				
			}
			
		g.setColor(Color.YELLOW);
		g.drawString(fps + "", 10, 20);
		}
	}
	
	// vao man hinh cho(maybe menu)
	public void loadGame() {
		isRunning = true;
		level = new Level(1);
		user = new User(this);
		user.creatPlayer();
		gameField = new GameField(level, user.player);
	}

	// bat dau choi
	public void startGame(User user) {
		if(started==false) {
			status = 1;
			started=true;
			}

	}

	public void run() {
		System.out.print("RUNNING!\n");
		long beginTimes = System.currentTimeMillis();
		int frames = 0;
		loadGame();
		//System.out.println(frame.getWidth() + frame.getHeight());
		while (isRunning) {
			Clock.update();
			repaint();
			update();
			frames++;
			if (System.currentTimeMillis() - beginTimes >= 1000) {
				fps = frames;
				frames = 0;
				beginTimes = System.currentTimeMillis();
			}
			  try { Thread.sleep(2); } catch (InterruptedException e) {
			  e.printStackTrace(); }
		}
		System.out.print("STOP");
		System.exit(0);
	}

	public void update() {
		if(status==1) {
			if(Clock.getTotalTime()-gameField.getTimeLastWave()>= level.newWaveSpeed*Clock.deltaDelay()) {
			if(Clock.getTotalTime()-gameField.getTimeLastSpawn()>=level.spawnSpeed*Clock.deltaDelay()) gameField.setSpawning(true);
			else gameField.setSpawning(false);
			} else gameField.setSpawning(false);
			gameField.spawn();
			for(Enemy e:gameField.getEnemiesList()) {
				e.run();
			}
			
			for(int i=0; i<gameField.gameMaps.getTowerSize(); i++) {
				if(gameField.gameMaps.tower[i]!=null)gameField.gameMaps.tower[i].checkEnemy();
			}
		}
		if(gameField.isWinStage()) status=2;
		if(user.player.health<=0) status=3;
	}
	
	public void destroyTower() {
		if(status ==1) {
		if(gameField.gameMaps.towerMap[(mousePosY-50)/40][(mousePosX-50)/40]>0) {
			gameField.gameMaps.towerMap[(mousePosY-50)/40][(mousePosX-50)/40]=-1;
			gameField.gameMaps.map[(mousePosY-50)/40][(mousePosX-50)/40]=-1;
			gameField.gameMaps.changePos.setPosition((mousePosY - 50) / 40,(mousePosX - 50) / 40);
			gameField.gameMaps.change=true;
			}}
	}
	
	public boolean isAble(int x, int y) {
		int indexX = (x - 50) / 40;
		int indexY = (y - 50) / 40;
		if (x > 50 && x < 1090 && y > 50 && y < 570) {
			if (gameField.gameMaps.towerMap[indexY][indexX] > 0) {
				return false;
			}
			if (gameField.gameMaps.map[indexY][indexX] != 0) {
				if(gameField.gameMaps.map[indexY][indexX] == -1) return true;
				return false;
			}
			
		} else
			return false;
		return true;
	}

	public void placeTower(int x, int y, int onHand) {
		if (isAble(x, y)&&status==1) {
			gameField.gameMaps.towerMap[(int) (y - 50) / 40][(int) (x - 50) / 40] = onHand;
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
			
			gameField.gameMaps.change=true;
			gameField.gameMaps.changePos.setPosition((y - 50) / 40,(x - 50) / 40);
		}
	}

	public class MouseEffect {
		boolean mouseDown = false;

		// xu ly keo tha
		public void moved(MouseEvent e) {
			mousePosX = e.getX();
			mousePosY = e.getY();
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
			
			if(status==1&&mousePosX>=810&& mousePosX<=930) {
				if(mousePosY>=600&&mousePosY<=680) {
					Clock.speedUp();
				}
			}
			
			Effect(e);
		}

		public void choose() {
			//bat dau tu 0
			if(Math.abs(mousePosX-668)<=125) {
				int indexY= (mousePosY-210)/90;
				if(indexY>=5) return;
				else {
					switch (status) {
					case 0:
						switch (indexY) {
						case 0: //new game
							
						break;
						case 1://load game
							break;
						case 2://options
							break;
						case 4:
							break;//exit
						}
						break;
					case 2:
						switch (indexY) {
						case 0: //
							break;
						case 1://continue nextstage
							break;
						case 2://
							break;
						case 4:
							break;//return main menu
						}
						break;
					case 3:
						switch (indexY) {
						case 0: //
							break;
						case 1://replay
							break;
						case 2://
							break;
						case 4:
							break;//to main menu
						}
						break;
					case 4:
						switch (indexY) {
						case 0: //continue
							break;
						case 1://
							break;
						case 2://
							break;
						case 4:
							break;//to main menu
						}
						break;
					case 5:
					}
				}
			}
			
		}
	}

	public class GameStatus {
		public void exitGame() {
			isRunning = false;
		}

		public void srtGame() {
			startGame(user);
		}

		public void destroy() {
			destroyTower();
		}
	}
}
