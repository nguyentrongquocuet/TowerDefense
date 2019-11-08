package towerdefense;

public class Clock{
    static long  lastFrame;
     static float totalTimeFromBegin;
    static float lastDelta=0f;
    static boolean firstTime=true;
    static boolean firstTimeDelay=true;
    static float pivok;

    public static float getDelta() {
        long delta= (int)(System.nanoTime()-lastFrame);
        lastFrame=System.nanoTime();
        return delta*0.000001f;
    }

    public static void update() {
        if(firstTime) firstTime=false;
        else {
            lastDelta=getDelta();
            totalTimeFromBegin+=lastDelta;
            }
    }
    public static float delta() {
    	return lastDelta;
    }
    public static float getTotalTime() {
        return totalTimeFromBegin;
    }
    
    public static boolean delay(float x) {
    	if(firstTimeDelay) {
    		firstTimeDelay=false;
    		pivok=totalTimeFromBegin;
    	} else {
    		if(totalTimeFromBegin-pivok>x) {pivok= totalTimeFromBegin; return true;}
    	}
    	return false;
    }
}
