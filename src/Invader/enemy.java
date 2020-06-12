package Invader;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class enemy {
	int height, width;
	Image enemyImg;
	int x,y;
	public int enemySpeed;
	boolean moveleft;
	boolean moveright;
	double bulletspawn;
	double tick;
	

	
	
	public enemy( int x, int y) throws IOException {
		super();
		
		getMassFromImage("assets/invader.gif");
		this.enemyImg = new ImageIcon("assets/invader.gif").getImage();
		this.x = x;
		this.y = y;
		this.enemySpeed = 2;
		this.moveleft = false;
		this.moveright = true;
		this.bulletspawn = 0.09;
		this.tick = 1;
	}
	
	public int getX() {
		
		return x;
	}
	protected void setX(int x) {
		this.x = x;
	}
	public int getY() {
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

	protected int getHeight() {
		return height;
	}

	protected void setHeight(int height) {
		this.height = height;
	}

	protected int getWidth() {
		return width;
	}

	protected void setWidth(int width) {
		this.width = width;
	}

	protected Image getEnemyImg() {
		return enemyImg;
	}

	protected void setEnemyImg(Image enemyImg) {
		this.enemyImg = enemyImg;
	}
	
	public void getMassFromImage(String filename) throws IOException {
		URL fileurl = new File(filename).toURI().toURL();
		BufferedImage Bi = ImageIO.read(fileurl.openStream());
		
		this.width = Bi.getWidth();
		this.height = Bi.getHeight();
		
	}
	
	public Rectangle getBound() {
		return new Rectangle(this.x, this.y, this.width, this.height);
	}

	public void move() {
		setY(y + enemySpeed);
	}
	
	public boolean checkCollision(Rectangle rb) {
        Rectangle r1 = getBound();
        Rectangle r2 = rb;
        if (r1.intersects(r2)){
        	return true;
        }else {
        	return false;
        }
               
}
	
	
}
