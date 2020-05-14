package Invader;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class drawing extends JPanel {
	
	private player player;
	private ArrayList<enemy> Enemy;
	private ArrayList<bullet> Bullets;
	private ImageIcon bg_img = new ImageIcon("assets/background-black.png");
	public drawing()  {
		
	}
	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		super.paintComponent(g);
		
		
        g2d.drawImage(bg_img.getImage(), 0, 0, 480, 480, null);
//		g2d.drawImage(img, x, y, width, height, observer)
//		g.clearRect(0, 0, 480, 480);
//    	g.setColor(Color.BLACK);
//    	g.fillRect(0, 0, 480, 480);
    	
    	
    	if(player != null) {
    		g2d.drawImage(player.getPlayerimg(),player.getX(), player.getY(), player.getWidth(), player.getHeight(), null);
    		g2d.setColor(Color.RED);
    		g2d.fillRect(player.getX() - 4, player.getY()+28, 35, 5);
    		g2d.setColor(Color.GREEN);
    		g2d.fillRect(player.getX() - 4, player.getY()+28, player.giveWidthByHealth(35), 5);
//    		g.setColor(Color.blue);
//    		g.fillRect(player.getX(), player.getY(), player.getSize(), player.getSize());
    	}
    	

	    if(Enemy != null) {
		    for(enemy e : Enemy) {
//		    	g.setColor(Color.red);
//		    	g.fillRect(Enemy.get(i).getX(), Enemy.get(i).getY(), Enemy.get(i).getSize(), Enemy.get(i).getSize());
		   		g2d.drawImage(e.getEnemyImg(), e.getX(),e.getY(),e.getWidth(),e.getHeight(), null);
		   	}
	    }
	    
	    if(Bullets != null) {
	    	for(bullet b : Bullets) {
	    		g2d.setColor(b.getBulletcolor());
	    		g2d.fillRect(b.getX(), b.getY(), b.getWidth(), b.height);
	    	}
	    }

    	
	}
	
	public player setPlayer(player p) {
		return this.player = p;
	}
	
	public ArrayList<enemy> setEnemy(ArrayList<enemy> e) {
		return this.Enemy = e;
	}
	
	public ArrayList<bullet> setBullets(ArrayList<bullet> b) {
		return this.Bullets = b;
	}
	
	

}
