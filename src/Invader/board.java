package Invader;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;



public class board extends JFrame implements ActionListener,KeyListener {
	public Timer timer;
	private JPanel contentPane;
	private JLabel lblScore;
	private int score = 0;
	private drawing game;
	private player player;
	private ArrayList<enemy> Enemies = new ArrayList<enemy>();
	private ArrayList<bullet> Bullets = new ArrayList<bullet>();
	private int playerSpeed = 20;
	private int bulletSpeed = 20;
	private Random random = new Random();
	private URL laser;
	private URL explosion;
	private java.applet.AudioClip laserClip;
	private java.applet.AudioClip ExplosionClip;
	private Boolean left = false, right = false;
	
	
	
	public board() throws IOException {
		addKeyListener(this);
		timer = new Timer(80, this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(0, 0, 600, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(56, 0, 480, 480);
		panel.setLayout(new BorderLayout(0, 0));
		
		game = new drawing();
		game.setLayout(null);
		panel.add(game);
		player = new player("assets/player.gif", 230, 430);
		int x = 50;
		int y = 20;
		for(int i = 0; i < 18; i++) {
			Enemies.add(new enemy("assets/invader.gif", x, y, 2));
			x += 50;
			if(x == 350) {
				x = 50;
				y+= 40;
			}
		}
		
		laser = new File("assets/laser.wav").toURI().toURL();
		laserClip = java.applet.Applet.newAudioClip(laser);
		explosion = new File("assets/explosion.wav").toURI().toURL();
		ExplosionClip = java.applet.Applet.newAudioClip(explosion);
		
		lblScore = new JLabel("Score: 0");
		lblScore.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblScore.setHorizontalAlignment(SwingConstants.CENTER);
		lblScore.setBounds(10, 491, 67, 31);
		contentPane.add(lblScore);
		
		contentPane.add(panel);
		setLocationRelativeTo(null);
		setVisible(true);
		timer.start();

	}
	
	public static void main(String[] args) throws IOException {
		new board();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		process();
		repaint();
	}
	
	public void process() {
		game.setPlayer(player);
		game.setEnemy(Enemies);
		game.setBullets(Bullets);
		movePlayer(player);
		moveEnemy();
		EnemyShoot();
		
		
		if(Bullets != null) {
			for(int i = 0; i < Bullets.size(); i++) {
				moveBullet(Bullets.get(i));
				
				if(Bullets.get(i).getY()<20) {
					Bullets.remove(i);
				}else if(Bullets.get(i).getY() > 460) {
					Bullets.remove(i);
				}
				
			}

			
		}
//		
			if(Bullets != null) {
				for(int i = 0; i < Bullets.size(); i++) {
					if(Bullets.get(i).getBulletStatus().equalsIgnoreCase("player")) {
						for(int i1 = 0; i1 < Enemies.size(); i1++) {
							if(checkCollision(Bullets.get(i).getBound(), Enemies.get(i1).getBound())) {
								ExplosionClip.play();
								score += 10;
								lblScore.setText("Score: " + score);
								Bullets.remove(i);
								Enemies.remove(i1);
								break;	
					  		}
						}
					}
				}	
			}
			if(Bullets != null) {
				for(int i = 0; i < Bullets.size(); i++) {
					if(Bullets.get(i).getBulletStatus().equalsIgnoreCase("enemy")) {
						if(checkCollision(Bullets.get(i).getBound(), player.getBound())) {
							timer.stop();
							break;
						}
					}
				}	
			}
			
		
		
		
		
		
		
		
		
	}
	public void EnemyShoot() {
		for(enemy e : Enemies) {
			e.setTick(e.getTick() + e.getBulletspawn());
			if(e.getTick() > 1) {
				if(Math.random() < 0.3) {
					fireBullet(e);
					
				}
			}
			
			if(e.getTick() > 1) {
				e.setTick(0);
			}
		}
		
	}
	
	public void moveEnemy() {
		for(enemy e : Enemies) {
			if(e.isMoveleft()) {
				e.setX(e.getX()-e.getEnemySpeed());
			}
			
			if(e.isMoveright()) {
				e.setX(e.getX()+e.getEnemySpeed());
				
			}
			
		}
		for(enemy e : Enemies) {
			if(e.getX() > 450) {
				for(int i = 0; i < Enemies.size(); i++) {
					Enemies.get(i).setMoveleft(true);
					Enemies.get(i).setMoveright(false);
				}
			}
			
			if(e.getX() < 5) {
				for(int i = 0; i < Enemies.size(); i++) {
					Enemies.get(i).setMoveright(true);
					Enemies.get(i).setMoveleft(false);
				}
			}
		}
		
			

	
	}
	
	
	public void moveBullet(bullet b) {
			if(b.isMoveUp()) {
				int i = 0;
				while(i != b.bulletSpeed) {
					b.setY(b.getY() - 1);
					i += 1;
				}
				
			}
			
			if(b.isMoveDown()) {
				int i = 0;
				while(i != b.bulletSpeed) {
					b.setY(b.getY() + 1);
					i += 1;
				}
				
			}
		
	}
	
	public void fireBullet(player p) {	
		laserClip.play();
		bullet bullet = new bullet(5, 10, p.getX()+8, p.getY(), Color.YELLOW, "player");
		bullet.setMoveUp(true);
		Bullets.add(bullet);
	}
	
	public void fireBullet(enemy e) {
		bullet bullet = new bullet(5, 10, e.getX()+8, e.getY() + 10, Color.RED, "enemy");
		bullet.setMoveDown(true);
		Bullets.add(bullet);

	}
	
	public void movePlayer(player p) {
		if(p.isMoveleft()) {

			if(player.getX() > 10) {
				int i = 0;
				while(i != playerSpeed) {
					player.setX(player.getX() - 1);
					i += 1;
				}
			}
		}
		if(p.isMoveright()) {
			if(player.getX() < 450) {
				int i = 0;
				while(i != playerSpeed) {
					player.setX(player.getX() + 1);
					i += 1;
				}
			}
		}
		
		if(p.isMovedown()) {
			if(player.getY() < 450) {
				int i = 0;
				while(i != playerSpeed) {
					player.setY(player.getY() + 1);
					i += 1;
				}
			}
		}
		
		if(p.isMoveup()) {
			if(player.getY() > 10) {
				int i = 0;
				while(i != playerSpeed) {
					player.setY(player.getY() - 1);
					i += 1;
				}
			}
		}
	}
	
//	public boolean checkCollision(bullet Bullet, enemy e) {
//		int distance = (int) Math.sqrt( Math.pow(Bullet.getX() - e.getX(),2) + Math.pow( Bullet.getY() - e.getY(),2));
//
//		if(distance <= 12) {
//			return true;
//		}else {
//			return false;
//		}
//	
//	}
//	
//	public boolean checkCollision(bullet Bullet, player p) {
//		int distance = (int) Math.sqrt( Math.pow(Bullet.getX() - p.getX(),2) + Math.pow( Bullet.getY() - p.getY(),2));
//
//		if(distance < 10) {
//			System.out.println(distance);
//			return true;
//		}else {
//			return false;
//		}
//	
//	}
	public boolean checkCollision(Rectangle rb, Rectangle ro) {
        Rectangle r1 = rb;
        Rectangle r2 = ro;
        if (r1.intersects(r2)){
        	return true;
        }else {
        	return false;
        }
               
}
	
//	public void Collide() {
//		int offset_X = 0;
//		int offset_Y = 0;
//		int b1 = 0;
//		for(bullet b : Bullets) {
//			offset_X = b.getX() - Enemies.get(0).getX();
//			offset_Y = b.getY() - Enemies.get(0).getY();
//			b1 = b.getX();
//		}
////		if(offset_Y < 20) {
////			System.out.println("x :" + offset_X + " y :" + offset_Y);
////			System.out.println("enemy x: " + Enemies.get(0).getX() + " bullet x: " + b1);
////		}
//	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_A) {
			player.setMoveleft(true);
		}
		
		if(key == KeyEvent.VK_D) {
			player.setMoveright(true);
		}
		
		if(key == KeyEvent.VK_W) {
			player.setMoveup(true);
		}
		
		if(key == KeyEvent.VK_S) {
			player.setMovedown(true);
		}
		
		if(key == KeyEvent.VK_SPACE) {
			fireBullet(player);
		}
		
		
		
		
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_A) {
			player.setMoveleft(false);
		}
		
		if(key == KeyEvent.VK_D) {
			player.setMoveright(false);
		}
		
		if(key == KeyEvent.VK_W) {
			player.setMoveup(false);
		}
		
		if(key == KeyEvent.VK_S) {
			player.setMovedown(false);
		}
	}
	
	public static int getRandomIntegerBetweenRange(double min, double max){
	    return (int) ((Math.random()*((max-min)+1))+min);
	}

}
