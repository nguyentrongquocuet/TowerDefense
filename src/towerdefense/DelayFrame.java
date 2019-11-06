package towerdefense;

public class DelayFrame {
	public int begin=0;
	public int current=0;
	//public boolean delayed=false;
	public boolean delayed(int n) {
		if(current-begin>=n) {
			current=0;
			return true;
		}else {current++; return false;}
	}
}
