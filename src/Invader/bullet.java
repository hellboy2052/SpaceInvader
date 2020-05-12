package Invader;

import java.awt.Color;

public class bullet {

	int width,height ,x ,y,bulletSpeed;
	Color bulletcolor;
	boolean moveUp, moveDown;
	String bulletStatus;	
	public bullet(int width, int height, int x, int y, Color bulletcolor, String bulletStatus) {
		super();
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
		this.bulletcolor = bulletcolor;
		this.moveUp = false;
		this.moveDown = false;
		this.bulletSpeed = 10;
		this.bulletStatus = bulletStatus;
	}

	protected int getWidth() {
		return width;
	}

	protected void setWidth(int width) {
		this.width = width;
	}

	protected int getHeight() {
		return height;
	}

	protected void setHeight(int height) {
		this.height = height;
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

	protected Color getBulletcolor() {
		return bulletcolor;
	}

	protected void setBulletcolor(Color bulletcolor) {
		this.bulletcolor = bulletcolor;
	}

	protected boolean isMoveUp() {
		return moveUp;
	}

	protected void setMoveUp(boolean moveUp) {
		this.moveUp = moveUp;
	}

	protected boolean isMoveDown() {
		return moveDown;
	}

	protected void setMoveDown(boolean moveDown) {
		this.moveDown = moveDown;
	}

	protected int getBulletSpeed() {
		return bulletSpeed;
	}

	protected void setBulletSpeed(int bulletSpeed) {
		this.bulletSpeed = bulletSpeed;
	}

	protected String getBulletStatus() {
		return bulletStatus;
	}

	protected void setBulletStatus(String bulletStatus) {
		this.bulletStatus = bulletStatus;
	}
	
	
	
	
	
}
