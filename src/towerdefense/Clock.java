package towerdefense;

public class Clock {
	static long currenTime;
	static long beginTime;
	public static void delay(int delayTime) {
		beginTime=System.currentTimeMillis();
		currenTime=beginTime;
		while(currenTime-beginTime<delayTime) {
			currenTime=System.currentTimeMillis();
		}
		beginTime=currenTime;
	}
}
