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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import javax.swing.JButton;



public class board extends JFrame implements ActionListener,KeyListener, MouseListener {
	public Timer timer;
	private JPanel contentPane;
	private JButton btnStart;
	private JLabel lblScore;
	private JLabel lblLv;
	private JPanel panel;
	private int score = 0, level = 1, wave_length = 5, currentwave = 0;
	private drawing game;
	private player player;
	private ArrayList<enemy> Enemies = new ArrayList<enemy>();
	private ArrayList<bullet> Bullets = new ArrayList<bullet>();
	private int playerSpeed = 20;
	private int bulletSpeed = 20;
	private Random random = new Random();
	private URL laser;
	private URL explosion;
	private String action;
	private java.applet.AudioClip laserClip;
	private java.applet.AudioClip ExplosionClip;
	private Boolean left = false, right = false;
	
	
	
	public board() throws IOException {
		addMouseListener(this);
		addKeyListener(this);
		timer = new Timer(50, this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(0, 0, 600, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(56, 0, 480, 480);
		panel.setLayout(new BorderLayout(0, 0));
		
		game = new drawing();
		game.setLayout(null);
		panel.add(game);
		player = new player(230, 430);
		
		
		laser = new File("assets/laser.wav").toURI().toURL();
		laserClip = java.applet.Applet.newAudioClip(laser);
		explosion = new File("assets/explosion.wav").toURI().toURL();
		ExplosionClip = java.applet.Applet.newAudioClip(explosion);
		
		lblScore = new JLabel("Score: 0");
		lblScore.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblScore.setHorizontalAlignment(SwingConstants.CENTER);
		lblScore.setBounds(10, 491, 67, 31);
		
		lblLv = new JLabel("Level: " + level);
		lblLv.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblLv.setHorizontalAlignment(SwingConstants.CENTER);
		lblLv.setBounds(10, 529, 67, 31);
		
		contentPane.add(lblScore);
		contentPane.add(lblLv);
		
		contentPane.add(panel);
		
		btnStart = new JButton("Start");
		btnStart.addActionListener(this);
		btnStart.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnStart.setBounds(240, 510, 89, 23);
		contentPane.add(btnStart);
		setLocationRelativeTo(null);
		setVisible(true);
		SpawnEnemy(wave_length);

	}
	
	public static void main(String[] args) throws IOException {
		new board();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getActionCommand() != null) {
			if(e.getActionCommand().equalsIgnoreCase("start")) {
				requestFocusInWindow();
				btnStart.setVisible(false);
				timer.start();
			}if(e.getActionCommand().equalsIgnoreCase("restart")) {
				try {
					Reset();
					btnStart.setVisible(false);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		}
		if(timer.isRunning()) {
			process();
			repaint();
		}
	}
	
	public void process() {
		
		updateLvl();
		game.setPlayer(player);
		game.setEnemy(Enemies);
		game.setBullets(Bullets);
		player.move();
		moveEnemy();
		EnemyShoot();
		
		
		if(Bullets != null) {
			for(int i = 0; i < Bullets.size(); i++) {
				Bullets.get(i).move();
				
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
							if(Bullets.get(i).checkCollision(Enemies.get(i1).getBound())) {
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
						if(Bullets.get(i).checkCollision(player.getBound())) {
							ExplosionClip.play();
							Bullets.remove(i);
							player.health-= 20;
							
							break;
						}
					}
				}	
			}
			
			for(int i = 0; i < Enemies.size(); i++) {
				if(Enemies.get(i).checkCollision(player.getBound())) {
					System.out.println("player y: " + player.getY() + " player x: " + player.getX());
					System.out.println("enemy y: " + Enemies.get(i).getY() + " enemy x: " + Enemies.get(i).getX());
					ExplosionClip.play();
					Enemies.remove(i);
					player.health-= 20;
					score += 10;
					lblScore.setText("Score: " + score);
					break;
				}
			}
			
			if(player.checkHealth()) {
				btnStart.setVisible(true);
				btnStart.setText("Restart");
				btnStart.requestFocus();
				timer.stop();
			}
			
		
		
		
		
		
		
	}
	
	public void Reset() throws IOException {
		score = 0;
		level = 0;
		wave_length = 5;
		currentwave = 0;
		player = new player(230, 430);
		Enemies.removeAll(Enemies);
		Bullets.removeAll(Bullets);
		lblScore.setText("Score: 0" );
		lblLv.setText("Level: 0");
		SpawnEnemy(wave_length);
		requestFocusInWindow();
		timer.start();
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
			e.move();
			if(e.y > 460) {
				e.x = getRandomIntegerBetweenRange(20, 400);
				e.y = getRandomIntegerBetweenRange(-20, -100);
			}
		}
	}
	
	public void updateLvl() {
		if(Enemies.size() == 0) {
			level += 1;
			wave_length += 5;
			lblLv.setText("Level: " + level);
			try {
				SpawnEnemy(wave_length);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void SpawnEnemy(int length) throws IOException {
		for(int i = 0; i < length; i++) {
			Enemies.add(new enemy(getRandomIntegerBetweenRange(20, 420), getRandomIntegerBetweenRange(-20, -100)));
			currentwave += 1;
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
	
//	public void movePlayer(player p) {
//		if(p.isMoveleft()) {
//
//			if(player.getX() > 10) {
//				int i = 0;
//				while(i != playerSpeed) {
//					player.setX(player.getX() - 1);
//					i += 1;
//				}
//			}
//		}
//		if(p.isMoveright()) {
//			if(player.getX() < 450) {
//				int i = 0;
//				while(i != playerSpeed) {
//					player.setX(player.getX() + 1);
//					i += 1;
//				}
//			}
//		}
//		
//		if(p.isMovedown()) {
//			if(player.getY() < 430) {
//				int i = 0;
//				while(i != playerSpeed) {
//					player.setY(player.getY() + 1);
//					i += 1;
//				}
//			}
//		}
//		
//		if(p.isMoveup()) {
//			if(player.getY() > 10) {
//				int i = 0;
//				while(i != playerSpeed) {
//					player.setY(player.getY() - 1);
//					i += 1;
//				}
//			}
//		}
//	}
	
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

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
//		e.getComponent().requestFocusInWindow();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
