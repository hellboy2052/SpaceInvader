package test;

import static org.junit.Assert.*;
import java.awt.Color;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import Invader.bullet;
import Invader.player;
import Invader.enemy;

public class TestCollide extends TestPrepare {
	
	@Before
	public void BeforeRun() throws IOException {
		player = new player(x, y_player);
		enemy = new enemy(x, y_enemy);
		
				
	}


	
	@Test
	public void TestUpdate() throws IOException {
		bullet b = new bullet(5, 10, x, 50, Color.YELLOW, "none");
		assertFalse(player.checkCollision(b.getBound()));
		assertFalse(enemy.checkCollision(b.getBound()));
		assertFalse(player.checkCollision(enemy.getBound()));
		assertFalse(enemy.checkCollision(player.getBound()));
		
		//Test Bullet Collision with player
		
		b = new bullet(5, 10, 218, 245, Color.YELLOW, "none");
		player = new player(210, 250);
		assertTrue(player.checkCollision(b.getBound()));
		
		
		b = new bullet(5, 10, 218, 249, Color.YELLOW, "none");
		player = new player(210, 250);
		assertTrue(player.checkCollision(b.getBound()));
		
		
		b = new bullet(5, 10, 300, 367, Color.YELLOW, "none");
		player = new player(290, 370);
		assertTrue(player.checkCollision(b.getBound()));
		
		
		b = new bullet(5, 10, 85, 349, Color.YELLOW, "none");
		player = new player(70, 350);
		assertTrue(player.checkCollision(b.getBound()));
		
		
		b = new bullet(5, 10, 85, 343, Color.YELLOW, "none");
		player = new player(70, 350);
		assertTrue(player.checkCollision(b.getBound()));
		
		
		//Test Bullet Collision with Enemy
		
		
		b = new bullet(5, 10, 118, 110, Color.YELLOW, "none");
		enemy = new enemy(114, 93);
		assertTrue(enemy.checkCollision(b.getBound()));
		
		b = new bullet(5, 10, 218, 230, Color.YELLOW, "none");
		enemy = new enemy(213, 217);
		assertTrue(enemy.checkCollision(b.getBound()));
		
		b = new bullet(5, 10, 398, 250, Color.YELLOW, "none");
		enemy = new enemy(399, 238);
		assertTrue(enemy.checkCollision(b.getBound()));
		
		
		b = new bullet(5, 10, 298, 290, Color.YELLOW, "none");
		enemy = new enemy(291, 277);
		assertTrue(enemy.checkCollision(b.getBound()));
		
		
		b = new bullet(5, 10, 178, 380, Color.YELLOW, "none");
		enemy = new enemy(171, 362);
		assertTrue(enemy.checkCollision(b.getBound()));
		
		
		b = new bullet(5, 10, 158, 20, Color.YELLOW, "none");
		enemy = new enemy(158, 2);
		assertTrue(enemy.checkCollision(b.getBound()));
		
		
		//Test collision between player and enemy
		
		player = new player(230, 110);
		enemy = new enemy(221, 101);
		assertTrue(enemy.checkCollision(player.getBound()));
		
		player = new player(290, 150);
		enemy = new enemy(294, 157);
		assertTrue(enemy.checkCollision(player.getBound()));
		
		
		player = new player(310, 150);
		enemy = new enemy(299, 142);
		assertTrue(enemy.checkCollision(player.getBound()));
		
		
		player = new player(350, 150);
		enemy = new enemy(373, 161);
		assertTrue(enemy.checkCollision(player.getBound()));
		
		
		player = new player(370, 150);
		enemy = new enemy(365, 173);
		assertTrue(enemy.checkCollision(player.getBound()));
	}
}
