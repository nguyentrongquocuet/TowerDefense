package towerdefense;

import javax.swing.*;

public class Frame extends JFrame {
    /**
	 * 
	 */
	public static void main(String[] args) {
        new Frame();}
    public Frame(){
    	new JFrame();
    	//this.setSize(1300, 740);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	this.setExtendedState(MAXIMIZED_BOTH);
        this.setUndecorated(true);
        this.setResizable(false);
        this.setTitle("TowerDefense");
        //this.setLocationRelativeTo(null);
        Screen screen= new Screen(this);
       
        this.add(screen);
        this.setVisible(true);
        
        

}
}
