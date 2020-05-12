package Invader;

import java.util.ArrayList;

public class player {
	int size;
	int x,y;
	boolean moveleft;
	boolean moveright;
	
	public player(int size, int x, int y) {
		super();
		this.size = size;
		this.x = x;
		this.y = y;
		this.moveleft = false;
		this.moveright = false;
	}
	protected int getSize() {
		return size;
	}
	protected void setSize(int size) {
		this.size = size;
	}
	protected int getX() {
		return x;
	}
	protected void setX(int x) {
		this.x = x;
	}
	protected int getY() {
		return y;
	}
	protected void setY(int y) {
		this.y = y;
	}
	protected boolean isMoveleft() {
		return moveleft;
	}
	protected void setMoveleft(boolean moveleft) {
		this.moveleft = moveleft;
	}
	protected boolean isMoveright() {
		return moveright;
	}
	protected void setMoveright(boolean moveright) {
		this.moveright = moveright;
	}
	
	
}
