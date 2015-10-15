package testing;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import classes.Dice;
import classes.DiceValue;
import classes.Game;
import classes.Player;

public class TestSecondBug {
	private int winnings;
	private String name;
	private int balance;
	private int limit;
	private Player player;
	private int bet;
	private Dice diceOne;
	private Dice diceTwo;
	private Dice diceThree;
	private Game game;

	@Before
	public void setUp() throws Exception {
		this.winnings = 0;
		this.name = "Angela";
		this.balance = 25;
		this.bet = 5;
		this.limit = 0;

		this.diceOne = Mockito.mock(Dice.class);
		this.diceTwo = Mockito.mock(Dice.class);
		this.diceThree = Mockito.mock(Dice.class);
		this.game = new Game(diceOne, diceTwo, diceThree); //inject the mock so die with the appropriate face can be used
	}

	@After
	public void tearDown() throws Exception {
		this.winnings = 0;
		this.name = null;
		this.balance = 0;
		this.limit = 0;
		this.player = null;
		this.bet = 0;

		this.diceOne = null;
		this.diceTwo = null;
		this.diceThree = null;        
	}

	/**
	 * Recreates second bug using simplification and limited dependencies
	 * Uses same code used in Main class, with Mocks instead of actual classes when possible
	 */
	@Test
	public void testBugReplication(){
		List<DiceValue> diceValues = game.getDiceValues();
		player = new Player(name, balance); //create a player object from injection
		player.setLimit(limit); //set the limit from injection (limit used in bugged program same limit used in test)

		Mockito.when(diceOne.getValue()).thenReturn(DiceValue.HEART); //top face on first die is a heart
		Mockito.when(diceTwo.getValue()).thenReturn(DiceValue.SPADE); //top face on second die is a spade
		Mockito.when(diceThree.getValue()).thenReturn(DiceValue.SPADE); //top face on third die is a spade

		System.out.println(String.format("\n\n%s starts with balance %d, limit %d\n", 
				player.getName(), player.getBalance(), player.getLimit())); //Give output to user

		int turn = 0; //Begin counting at turn 0

		while (player.balanceExceedsLimitBy(bet) && player.getBalance() < 200 ) { //play game until place exceeds their limit or balance goes above 200
			turn++;
			// pick anchor face
			DiceValue face = DiceValue.ANCHOR;

			System.out.printf("Turn %d: %s bet %d on %s\n", 
					turn, player.getName(), bet, face); 
			//Play the game
			int winnings = game.playRound(player, face, bet);
			diceValues = game.getDiceValues(); 
			//Display output to the user
			System.out.printf("Rolled %s, %s, %s\n",
					diceValues.get(0), diceValues.get(1), diceValues.get(2)); //get the values of the three dice

			System.out.printf("%s lost, balance now %d\n\n",
					player.getName(), player.getBalance()); //get the players name, how much they won, and thir current balance
		} //while

		System.out.print(String.format("%d turns later.\nEnd Game: ", turn));
		System.out.println(String.format("%s now has balance %d\n", player.getName(), player.getBalance()));
	}

}
