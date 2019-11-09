package towerdefense;

public class Clock{
    static long  lastFrame;
     static float totalTimeFromBegin;
    static float lastDelta=0f;
    static boolean firstTime=true;
    static boolean firstTimeDelay=true;
    static float pivok;
    static int speedUp=1;
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
    
    public static float deltaMove() {
    	return lastDelta*speedUp;
    }
    
    public static float deltaDelay() {
    	return lastDelta/speedUp;
    }
    
    public static float getTotalTime() {
        return totalTimeFromBegin;
    }
    
    public static void speedUp() {
    	speedUp=speedUp%4+1;
    	System.out.println("speedup  "+ speedUp);
    }
}
