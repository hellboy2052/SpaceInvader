package Invader;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class drawing extends JPanel {
	
	private player player;
	private ArrayList<enemy> Enemy;
	private ArrayList<bullet> Bullets;
	private ImageIcon player_img = new ImageIcon("assets/player.gif");
	private ImageIcon invader_img = new ImageIcon("assets/invader.gif");
	private ImageIcon bg_img = new ImageIcon("assets/background-black.png");
	public drawing()  {
		
	}
	@Override
	public void paint(Graphics g) {
		super.paintComponent(g);
		

        g.drawImage(bg_img.getImage(), 0, 0, 480, 480, null);
		
//		g.clearRect(0, 0, 480, 480);
//    	g.setColor(Color.BLACK);
//    	g.fillRect(0, 0, 480, 480);
    	
    	
    	if(player != null) {
    		player_img.paintIcon(this, g, player.getX(), player.getY());
//    		g.setColor(Color.blue);
//    		g.fillRect(player.getX(), player.getY(), player.getSize(), player.getSize());
    	}
    	

	    if(Enemy != null) {
		    for(int i = 0; i < Enemy.size(); i++) {
//		    	g.setColor(Color.red);
//		    	g.fillRect(Enemy.get(i).getX(), Enemy.get(i).getY(), Enemy.get(i).getSize(), Enemy.get(i).getSize());
		   		invader_img.paintIcon(this, g, Enemy.get(i).getX(), Enemy.get(i).getY());
		   	}
	    }
	    
	    if(Bullets != null) {
	    	for(bullet b : Bullets) {
	    		g.setColor(b.getBulletcolor());
		    	g.fillRect(b.x, b.getY(), b.getWidth(), b.getHeight());
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
