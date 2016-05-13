package poker.app.model;

import java.io.Serializable;
import pokerBase.Hand;

public class PokerGameState implements Serializable {
        
    public final static int DEAL = 0;  
    public final static int BET_OR_FOLD = 1; 
    public final static int RAISE_SEE_OR_FOLD_ROUND_1 = 2;   
                                                             
    public final static int RAISE_CALL_OR_FOLD_ROUND_2 = 3;  
                                                             
    public final static int DRAW = 4;  
    
    public final static int WAIT_FOR_DEAL = 5;  
    public final static int WAIT_FOR_BET = 6;   
    public final static int WAIT_FOR_DRAW = 7;  
    
    //-------------------------------------------------------------
    
    
    public int status;         
    public final Hand hand;   
    
    public int money;         
    public int opponentMoney;  
    public int pot;            
    
    public int amountToSee;     

    public PokerGameState(Hand hand, 
    		int status, 
    		int money, 
    		int opponentMoney, 
    		int pot) 
    {
        this(hand,status,money,opponentMoney,pot,0);
    }

    /**
     * Create a PokerGameState object with specified values for all public variables in this class.
     */
    public PokerGameState(Hand hand, 
    		int status, 
    		int money, 
    		int opponentMoney, 
    		int pot, 
    		int amountToSee) {
        this.hand = hand;
        this.status = status;
        this.money = money;
        this.opponentMoney = opponentMoney;
        this.pot = pot;
        this.amountToSee = amountToSee;
    }

	public void applyMessage(int playerID, Object message) {

		
	}
	
    void startFirstGame() {
      
    }
}
