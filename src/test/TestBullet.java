package test;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import Invader.bullet;

public class TestBullet extends TestPrepare{
	
	@Before
	public void BeforeRun() {
		player_bullet = new bullet(5, 10, x, y_player, Color.YELLOW, "player");
		player_bullet.setMoveUp(true);
		enemy_bullet = new bullet(5, 10, x, y_enemy, Color.RED, "enemy");
		enemy_bullet.setMoveDown(true);
				
	}
	
	@Test
	public void testUpdate() {
		final int num_update = 6;
		
		//Test Bullet Movement
		for(int i = 0; i < num_update; i++) {
			player_bullet.move();
		}
		
		for(int i = 0; i < num_update; i++) {
			enemy_bullet.move();
		}
		
		// X
		assertEquals(x, player_bullet.getX());
		assertEquals(x, enemy_bullet.getX());
		// Y
		assertEquals(y_player - num_update * player_bullet.bulletSpeed, player_bullet.getY());
		assertEquals(y_enemy + num_update * enemy_bullet.bulletSpeed, enemy_bullet.getY());
	}
}
