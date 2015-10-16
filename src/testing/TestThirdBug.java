package testing;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import classes.Dice;
import classes.DiceValue;
import classes.Game;
import classes.Player;

public class TestThirdBug {
	private Player player  = null;
	private static final int BALANCE = 100;

	@Before
	public void setUp() throws Exception {
		player = new Player("Angela", BALANCE); //create a player object	
	}

	@After
	public void tearDown() throws Exception {
		player = null;
	}

	@Test
	public final void test ()  {
		Game game = new Game(new Dice(), new Dice(), new Dice());
		int loseCount = 0;
		int winCount = 0;

		//Play game for 10000 rounds
		int total = 10000;
		for (int i = 0; i < total; i++) {
			player = new Player ("Angela", BALANCE); //create player object
			DiceValue pick = DiceValue.getRandom (); //randomly pick dice values

			int winnings = game.playRound (player, pick, 5);
			List<DiceValue> diceValues = game.getDiceValues ();

			if (winnings > 0) {
				int matches = Collections.frequency (diceValues, pick);
				if (matches >= 1)
					winCount++;
			}

			else
				loseCount++;
		}

		//Make sure the losses and wins total the amount of total rolls
		assertEquals (total, winCount + loseCount);

		//Print results out to the user
		System.out.print("With " + winCount + " wins and " + loseCount + " losses, player had ratio " + winCount/(total*1.0));
	}
}
