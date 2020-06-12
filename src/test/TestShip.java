package test;

import static org.junit.Assert.assertEquals;

import java.awt.Color;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import Invader.bullet;
import Invader.player;
import Invader.enemy;

public class TestShip extends TestPrepare {
	@Before
	public void BeforeRun() throws IOException {
		player = new player(x, y_player);
		enemy = new enemy(x, y_enemy);
				
	}
	
	@Test
	public void testUpdate() throws IOException {
		int current_x, current_y;
		final int num_update = 6;
		
		//Test Ship Movement
		
		//Enemy
		for(int i = 0; i < num_update; i++) {
			enemy.move();
		}
		assertEquals(x, enemy.getX());
		assertEquals(y_enemy + num_update * enemy.enemySpeed, enemy.getY());
		
		//Player
		
		
		//left
		player.setMoveleft(true);
		for(int i = 0; i < num_update; i++) {
			player.move();
		}
		player.setMoveleft(false);
		assertEquals(x - num_update * player.playerSpeed , player.getX());
		assertEquals(y_player, player.getY());
		current_x = x - num_update * player.playerSpeed ;
		
		
		//Right
		player.setMoveright(true);
		for(int i = 0; i < num_update; i++) {
			player.move();
		}
		player.setMoveright(false);
		assertEquals(current_x + num_update * player.playerSpeed , player.getX());
		assertEquals(y_player, player.getY());
		current_x = current_x + num_update * player.playerSpeed ;
		assertEquals(x, current_x); // Return to the x coordinate that player spawn in
		
		
		//Up
		player.setMoveup(true);
		for(int i = 0; i < num_update; i++) {
			player.move();
		}
		player.setMoveup(false);
		assertEquals(current_x, player.getX());
		assertEquals(y_player - num_update * player.playerSpeed , player.getY());
		current_y = y_player - num_update * player.playerSpeed;
		
		
		//Down
		player.setMovedown(true);
		for(int i = 0; i < num_update; i++) {
			player.move();
		}
		player.setMovedown(false);
		assertEquals(current_x, player.getX());
		assertEquals(current_y + num_update * player.playerSpeed , player.getY());
		current_y = current_y + num_update * player.playerSpeed;
		assertEquals(y_player, current_y); // Return to the y coordinate that player spawn in
		
		

		
		
		
		
	}
}
