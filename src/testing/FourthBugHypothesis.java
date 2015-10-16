package testing;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import classes.Dice;
import classes.DiceValue;
import classes.Game;

public class FourthBugHypothesis {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void firstHypothesis() {
	//	Dice d1 = new Dice();
	//	Dice d2 = new Dice();
	//	Dice d3 = new Dice();

	//	Game game = new Game(d1, d2, d3);


		//Play 100 rounds to see if the die faces ever change
		for (int i = 0; i < 100; i++) {
			Dice d1 = new Dice();
			Dice d2 = new Dice();
			Dice d3 = new Dice();        
			Game game = new Game(d1, d2, d3);
			List<DiceValue> cdv = game.getDiceValues(); //inject DiceValues to use dice faces
			cdv = game.getDiceValues(); //get the values of the three dice
			System.out.printf("Rolled %s, %s, %s\n",
					d1, d2, d3); //print out the dice faces on the console
		}
	}

	@Test
	public void test() {
		int rolls = 1000;
		String type;
		int clubs = 0;
		int spade = 0;
		int hearts = 0;
		int diamonds = 0;
		int anchor = 0;
		int crown = 0;
		for (int i = 0; i < rolls; i++) {
			type = DiceValue.getRandom().toString();
			if (type == "CLUB") {
				clubs++;
			}
			if (type == "SPADE") {
				spade++;
			}
			if (type == "HEART") {
				hearts++;
			}
			if (type == "DIAMOND") {
				diamonds++;
			}
			if (type == "ANCHOR") {
				anchor++;
			}
			if (type == "CROWN") {
				crown++;
			}
		}

		System.out.println("Clubs: " + clubs + "\nSpades: " + spade + "\nHearts: " + hearts + "\nDiamonds: " + diamonds + "\nAnchors: " + anchor + "\nCrowns: " + crown);
	}

}
