package towerdefense;

public class DelayFrame {
	//public int begin=0;
	public int current=0;
	//public boolean delayed=false;
	public int delay;
	public boolean delayed() {
		if(current==delay) {
			current=0;
			System.out.println();
			return true;
		} else {current++; return false;}
	}
}
