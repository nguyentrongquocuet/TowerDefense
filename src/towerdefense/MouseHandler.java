package towerdefense;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseHandler implements MouseListener, MouseMotionListener {
	public Screen screen;
	public Screen.MouseEffect mouseEffect;

	public MouseHandler(Screen screen) {
		this.screen = screen;
		this.mouseEffect = this.screen.new MouseEffect();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		//this.mouseEffect.moved(e);
		//this.mouseEffect.down(e);
		mouseEffect.choose();
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		this.mouseEffect.down(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		//this.mouseEffect.down(e);
		this.mouseEffect.moved(e);
		//mouseEffect.choose();
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		this.mouseEffect.moved(e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		this.mouseEffect.moved(e);
	}

}
