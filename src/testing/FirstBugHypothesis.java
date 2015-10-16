package testing;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import classes.Player;

public class FirstBugHypothesis {
	Player player;
	String name;
	int balance;
	int limit;
	int bet;

	@Before
	public void setUp() throws Exception {
		name = "Angela";
		balance = 100;
		limit = 0;
		bet = 5;
	}

	@After
	public void tearDown() throws Exception {
	}

	  
    @Test
    //Checks to see if the correct bet is taken from the players balance
    //Test passes if the original balance(balance) minus the players current balance (player.getBalance()) are equal to the bet
    public void testWinnings() {
    	this.player = new Player(name, balance);
    	int bet = 5;
    	player.takeBet(bet);
    	assertEquals(bet, balance - player.getBalance());
    }
    
    @Test
    //Checks to see if the correct winnings is given back to the player
    //Test passes if the players current balance is equal to the old balance plus the bet
    public void testBet() {
    	this.player = new Player(name, balance);
    	int bet = 5;
    	player.takeBet(bet);
    	int betBalance = player.getBalance();
    	player.receiveWinnings(bet);
    	assertEquals(balance, betBalance + bet);
    }
    
    /*
     * Test the Player object accumulates credits correctly
     */
    @Test
    public void testHypothesisTwo(){
	this.player = new Player(name, balance);
	player.setLimit(0);
	
	// Check that the object has been initiated correctly
	System.out.println("Balance before bet: " + player.getBalance());
	player.takeBet(bet);
	System.out.println("The player bet " + bet);
	System.out.println("Balance after bet: " + player.getBalance());
	System.out.println("Player wins! They get " + bet);
	player.receiveWinnings(bet);
	System.out.println("Balance after bet " + player.getBalance());
	assertTrue(player.getBalance()==this.balance);
      
    } 
    
}
