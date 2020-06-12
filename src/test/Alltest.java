package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@Suite.SuiteClasses(value = { 
		TestBullet.class,
		TestShip.class,
		TestCollide.class
		})
public class Alltest {

}
