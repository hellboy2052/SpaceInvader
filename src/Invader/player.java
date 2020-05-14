package Invader;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class player {
	int height, width;
	Image playerimg;
	int x,y;
	boolean moveleft;
	boolean moveright;
	boolean moveup;
	boolean movedown;
	double health;
	double max_health;
	
	public player(String filename, int x, int y) throws IOException {
		super();
		this.x = x;
		this.y = y;
		getMassFromImage(filename);
		this.playerimg = new ImageIcon(filename).getImage();
		this.moveleft = false;
		this.moveright = false;
		this.movedown = false;
		this.moveup = false;
		this.health = 100;
		this.max_health = this.health;
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
	protected boolean isMoveup() {
		return moveup;
	}
	protected void setMoveup(boolean moveup) {
		this.moveup = moveup;
	}
	protected boolean isMovedown() {
		return movedown;
	}
	protected void setMovedown(boolean movedown) {
		this.movedown = movedown;
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
	
	protected Image getPlayerimg() {
		return playerimg;
	}
	protected void setPlayerimg(Image playerimg) {
		this.playerimg = playerimg;
	}
	
	
	protected double getHealth() {
		return health;
	}
	protected void setHealth(double health) {
		this.health = health;
	}
	protected double getMax_health() {
		return max_health;
	}
	protected void setMax_health(double max_health) {
		this.max_health = max_health;
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
	
	public int giveWidthByHealth(int width) {
		return (int) (width * (this.health/this.max_health));
	}
	
	public boolean checkHealth() {
		if(this.health == 0) {
			return true;
		}else {
			return false;
		}
	}
	
	
}
