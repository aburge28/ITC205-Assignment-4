package testing;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import classes.Dice;
import classes.DiceValue;
import classes.Game;

public class TestFourthBug {
	Dice diceOne;
	Dice diceTwo;
	Dice diceThree;
	Game game;

	@Before
	public void setUp() throws Exception {
		game = Mockito.mock(Game.class); //mock Game class to get rid of any dependencies
	}

	@After
	public void tearDown() throws Exception {
		game = null;
	}

	@Test
	public void recreateBug() {
		Dice d1 = new Dice();
		Dice d2 = new Dice();
		Dice d3 = new Dice();

		Game game = new Game(d1, d2, d3);
		List<DiceValue> cdv = game.getDiceValues(); //inject DiceValues to use dice faces

		//Play 100 rounds to see if the die faces ever change
		//The dice faces shouldn't change over 100 rounds ot match the Main class
		for (int i = 0; i < 100; i++) {
			cdv = game.getDiceValues(); //get the values of the three dice
			System.out.printf("Rolled %s, %s, %s\n",
					 cdv.get(0), cdv.get(1), cdv.get(2)); //print out the dice faces on the console
		} 
	} 
}
