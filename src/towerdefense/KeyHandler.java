package towerdefense;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//xu ly phim
public class KeyHandler implements KeyListener {

	private Screen screen;
	private Screen.GameStatus gameStatus;

	public KeyHandler(Screen screen) {
		this.screen = screen;
		this.gameStatus = this.screen.new GameStatus();
	}

	public void keyReleased(KeyEvent e) {

	}

	public void keyTyped(KeyEvent e) {

	}

	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		System.out.print("KEYCODE: " + keyCode + "\n");
		if (keyCode == 32) {
			this.gameStatus.srtGame();
		}
		if (keyCode == 27) {
			this.gameStatus.exitGame();
		}
		if(keyCode==71) {
			gameStatus.destroy();
		}
	}

}
