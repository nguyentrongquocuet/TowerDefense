package towerdefense;

public class Position {
	public float x;
	public float y;
	public Position(float x, float y) {
		this.x = x;
		this.y = y;
	}
	public Position() {}
	public void setPosition(float x, float y) {
		this.x=x;
		this.y=y;
	}

	public boolean equals(Position pos2) {
		return ((int)(pos2.x-x)==0&&(int)(pos2.y-y)==0);
	}
	
	public float distance(Position pos2) {
		return (float)Math.sqrt((x-pos2.x)*(x-pos2.x)+(y-pos2.y)*(y-pos2.y));
	}
}
