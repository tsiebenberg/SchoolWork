package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestBlood.class, TestGameMap.class, TestGoop.class,
		TestHunter.class, TestPit.class, TestRoom.class, TestSlime.class,
		TestWumpus.class, TestHuntTheWumpusModel.class })
public class AllTests {

}
