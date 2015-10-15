package testing;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.mockito.Mockito;

import classes.Dice;
import classes.DiceValue;
import classes.Game;
import classes.Player;

public class testFirstBug {
	
	private Player player  = null;

	private static final int BET = 5;
	
	private String name;
    private int balance;
    private int limit;
    
    private Dice diceOne;
    private Dice diceTwo;
    private Dice diceThree;
    
    private Game game;

	@Before
	public void setUp() throws Exception {

		this.name = "Angela";
	    	this.balance = 100;
	    	this.limit = 0;

	    	this. player = Mockito.mock(Player.class);
	        
	        this.diceOne = Mockito.mock(Dice.class);
	        this.diceTwo = Mockito.mock(Dice.class);
	        this.diceThree = Mockito.mock(Dice.class);
	        //Inject the Game class so the game can be played like the bugged game
	        this.game = new Game(diceOne, diceTwo, diceThree);
	}

	@After
	public void tearDown() throws Exception {
		this.name = null;
	    	this.balance = 0;
	    	this.limit = 0;
	        this.player = null;
	  
	        this.diceOne = null;
	        this.diceTwo = null;
	        this.diceThree = null;
	}

    @Test
    /**
     * Recreates the bug which appears to cause the players balance to not update.
     * Bug appears to be caused in main class, so recreate the bug using similar code used in the main class
     */
    public void recreateBug(){
	List<DiceValue> diceValues = game.getDiceValues();
        player = new Player(name, balance); 
        player.setLimit(limit);
        int bet = 5;

	Mockito.when(diceOne.getValue()).thenReturn(DiceValue.HEART); //First dice has its face se to show a heart
	Mockito.when(diceTwo.getValue()).thenReturn(DiceValue.SPADE); //Second dice has its face set to show a Spade
	Mockito.when(diceThree.getValue()).thenReturn(DiceValue.ANCHOR); //Third dice has its face set to show an Anchor

        System.out.println(String.format("\n\n%s starts with balance %d, limit %d\n", 
        		player.getName(), player.getBalance(), player.getLimit()));

        int turn = 0;
        
        if (!player.balanceExceedsLimitBy(bet) && !(player.getBalance() < 200)) { 
        	return; 
        	}; //end the game if the players balance goes outside the limit (ie, balance goes outside 0-200 range
        
        while (turn < 10) { //play game for 10 rounds
            turn++;                   
            // pick anchor symbol
            DiceValue pick = DiceValue.ANCHOR;
           
            System.out.printf("Turn %d: %s bet %d on %s\n", 
        	    turn, player.getName(), bet, pick); 
            // Play the game
            int winnings = game.playRound(player, pick, BET);
            diceValues = game.getDiceValues();
            // Display the output to the user
            System.out.printf("Rolled %s, %s, %s\n",
        	    diceValues.get(0), diceValues.get(1), diceValues.get(2));
            
            System.out.printf("%s won %d, balance now %d\n\n",
        	    player.getName(), winnings, player.getBalance());
        } //while
    }
}
