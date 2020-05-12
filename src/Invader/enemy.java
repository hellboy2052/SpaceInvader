package Invader;

import java.util.ArrayList;

public class enemy {
	int size;
	int x,y;
	int enemySpeed;
	boolean moveleft;
	boolean moveright;
	double bulletspawn;
	double tick;
	

	
	
	public enemy(int size, int x, int y, int enemySpeed) {
		super();
		this.size = size;
		this.x = x;
		this.y = y;
		this.enemySpeed = enemySpeed;
		this.moveleft = false;
		this.moveright = true;
		this.bulletspawn = 0.05;
		this.tick = 1;
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
	
	protected int getEnemySpeed() {
		return enemySpeed;
	}

	protected void setEnemySpeed(int enemySpeed) {
		this.enemySpeed = enemySpeed;
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
	protected double getBulletspawn() {
		return bulletspawn;
	}
	protected void setBulletspawn(double bulletspawn) {
		this.bulletspawn = bulletspawn;
	}
	protected double getTick() {
		return tick;
	}
	protected void setTick(double tick) {
		this.tick = tick;
	}

	
	
	
}
