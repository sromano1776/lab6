package pokerBase;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import exceptions.DeckException;
import exceptions.HandException;
import pokerEnums.eCardNo;
import pokerEnums.eHandStrength;
import pokerEnums.eRank;
import pokerEnums.eSuit;

public class HandTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	
	@Test
	public void TestWild() {
		
		HandScore hs = new HandScore();
		ArrayList<Card> FourOfAKind = new ArrayList<Card>();
		FourOfAKind.add(new Card(eSuit.CLUBS,eRank.ACE,0));
		FourOfAKind.add(new Card(eSuit.CLUBS,eRank.ACE,0));
		FourOfAKind.add(new Card(eSuit.JOKER,eRank.JOKER,0));		
		FourOfAKind.add(new Card(eSuit.CLUBS,eRank.ACE,0));
		FourOfAKind.add(new Card(eSuit.CLUBS,eRank.KING,0));
		Collections.sort(FourOfAKind);
		Hand h = new Hand();
		h = SetHand(FourOfAKind,h);
		
		ArrayList<Hand> hands = new ArrayList<Hand>();
		hands.add(h);
		hands = Hand.ExplodeHands(h);
		
		assertEquals(hands.size(),13);
		
		try {
			h = Hand.Evaluate(h);
			
		} catch (HandException e) {			
			e.printStackTrace();
			fail("TestFourOfAKindEval failed");
		}
		
		
		
		
	
		boolean bActualIsHandFourOfAKind = Hand.isHandFourOfAKind(h, hs);
		boolean bExpectedIsHandFourOfAKind = true;
		
		//	Did this evaluate to Four of a Kind?
		assertEquals(bActualIsHandFourOfAKind,bExpectedIsHandFourOfAKind);		
		//	Was the four of a kind an Ace?
		assertEquals(hs.getHiHand(),eRank.ACE.getiRankNbr());		
		//	FOAK has one kicker.  Was it a Club?
		assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteSuit(), eSuit.CLUBS);
		//	FOAK has one kicker.  Was it a King?		
		assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteRank(), eRank.KING);
	}
	
	
	private Hand SetHand(ArrayList<Card> setCards, Hand h)
	{
		Object t = null;
		
		try {
			//	Load the Class into 'c'
			Class<?> c = Class.forName("pokerBase.Hand");
			//	Create a new instance 't' from the no-arg Deck constructor
			t = c.newInstance();
			//	Load 'msetCardsInHand' with the 'Draw' method (no args);
			Method msetCardsInHand = c.getDeclaredMethod("setCardsInHand", new Class[]{ArrayList.class});
			//	Change the visibility of 'setCardsInHand' to true *Good Grief!*
			msetCardsInHand.setAccessible(true);
			//	invoke 'msetCardsInHand'
			Object oDraw = msetCardsInHand.invoke(t, setCards);
			
			
		} catch (ClassNotFoundException x) {
			x.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		h = (Hand)t;
		return h;
		
	}
	/**
	 * This test checks to see if a HandException is throw if the hand only has two cards.
	 * @throws Exception
	 */
	@Test(expected = HandException.class)
	public void TestEvalShortHand() throws Exception {
		
		Hand h = new Hand();
		
		ArrayList<Card> ShortHand = new ArrayList<Card>();
		ShortHand.add(new Card(eSuit.CLUBS,eRank.ACE,0));
		ShortHand.add(new Card(eSuit.CLUBS,eRank.ACE,0));

		h = SetHand(ShortHand,h);
		
		h = Hand.Evaluate(h);
	}	
			
	@Test
	public void TestFourOfAKind1() {
		
		HandScore hs = new HandScore();
		ArrayList<Card> FourOfAKind = new ArrayList<Card>();
		FourOfAKind.add(new Card(eSuit.CLUBS,eRank.ACE,0));
		FourOfAKind.add(new Card(eSuit.CLUBS,eRank.ACE,0));
		FourOfAKind.add(new Card(eSuit.CLUBS,eRank.ACE,0));		
		FourOfAKind.add(new Card(eSuit.CLUBS,eRank.ACE,0));
		FourOfAKind.add(new Card(eSuit.CLUBS,eRank.KING,0));
		Collections.sort(FourOfAKind);
		Hand h = new Hand();
		h = SetHand(FourOfAKind,h);
		
		boolean bActualIsHandFourOfAKind = Hand.isHandFourOfAKind(h, hs);
		boolean bExpectedIsHandFourOfAKind = true;
		
		//	Did this evaluate to Four of a Kind?
		assertEquals(bActualIsHandFourOfAKind,bExpectedIsHandFourOfAKind);		
		//	Was the four of a kind an Ace?
		assertEquals(hs.getHiHand(),eRank.ACE.getiRankNbr());		
		//	FOAK has one kicker.  Was it a Club?
		assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteSuit(), eSuit.CLUBS);
		//	FOAK has one kicker.  Was it a King?		
		assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteRank(), eRank.KING);
	}
	
	@Test
	public void TestFourOfAKindEval1() {
		
		ArrayList<Card> FourOfAKind = new ArrayList<Card>();
		FourOfAKind.add(new Card(eSuit.CLUBS,eRank.ACE,0));
		FourOfAKind.add(new Card(eSuit.CLUBS,eRank.ACE,0));
		FourOfAKind.add(new Card(eSuit.CLUBS,eRank.ACE,0));
		FourOfAKind.add(new Card(eSuit.CLUBS,eRank.ACE,0));
		FourOfAKind.add(new Card(eSuit.CLUBS,eRank.KING,0));
		Collections.sort(FourOfAKind);
		Hand h = new Hand();
		h = SetHand(FourOfAKind,h);
		
		try {
			h = Hand.Evaluate(h);
			
		} catch (HandException e) {			
			e.printStackTrace();
			fail("TestFourOfAKindEval failed");
		}
		HandScore hs = h.getHandScore();
		int iActualIsHandFourOfAKind = hs.getHandStrength();
		int iExpectedIsHandFourOfAKind = eHandStrength.FourOfAKind.getHandStrength();
		
		//	Did this evaluate to Four of a Kind?
		assertEquals(iActualIsHandFourOfAKind,iExpectedIsHandFourOfAKind);		
		//	Was the four of a kind an Ace?
		assertEquals(hs.getHiHand(),eRank.ACE.getiRankNbr());		
		//	FOAK has one kicker.  Was it a Club?
		assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteSuit(), eSuit.CLUBS);
		//	FOAK has one kicker.  Was it a King?		
		assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteRank(), eRank.KING);
	}	

	@Test
	public void TestFourOfAKind2() {
		
		HandScore hs = new HandScore();
		ArrayList<Card> FourOfAKind = new ArrayList<Card>();
		FourOfAKind.add(new Card(eSuit.CLUBS,eRank.KING,0));
		FourOfAKind.add(new Card(eSuit.CLUBS,eRank.KING,0));
		FourOfAKind.add(new Card(eSuit.CLUBS,eRank.KING,0));		
		FourOfAKind.add(new Card(eSuit.CLUBS,eRank.KING,0));
		FourOfAKind.add(new Card(eSuit.CLUBS,eRank.ACE,0));
		Collections.sort(FourOfAKind);
		Hand h = new Hand();
		h = SetHand(FourOfAKind,h);
		
		boolean bActualIsHandFourOfAKind = Hand.isHandFourOfAKind(h, hs);
		boolean bExpectedIsHandFourOfAKind = true;
		
		//	Did this evaluate to Four of a Kind?
		assertEquals(bActualIsHandFourOfAKind,bExpectedIsHandFourOfAKind);		
		//	Was the four of a kind an Ace?
		assertEquals(hs.getHiHand(),eRank.KING.getiRankNbr());		
		//	FOAK has one kicker.  Was it a Club?
		assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteSuit(), eSuit.CLUBS);
		//	FOAK has one kicker.  Was it a King?		
		assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteRank(), eRank.ACE);
	}
	
	@Test
	public void TestFourOfAKindEval2() {
		
		ArrayList<Card> FourOfAKind = new ArrayList<Card>();
		FourOfAKind.add(new Card(eSuit.CLUBS,eRank.KING,0));
		FourOfAKind.add(new Card(eSuit.CLUBS,eRank.KING,0));
		FourOfAKind.add(new Card(eSuit.CLUBS,eRank.KING,0));		
		FourOfAKind.add(new Card(eSuit.CLUBS,eRank.KING,0));
		FourOfAKind.add(new Card(eSuit.CLUBS,eRank.ACE,0));
		
		Hand h = new Hand();
		h = SetHand(FourOfAKind,h);
		
		try {
			h = Hand.Evaluate(h);
		} catch (HandException e) {			
			e.printStackTrace();
			fail("TestFourOfAKindEval failed");
		}
		HandScore hs = h.getHandScore();
		int iActualIsHandFourOfAKind = hs.getHandStrength();
		int iExpectedIsHandFourOfAKind = eHandStrength.FourOfAKind.getHandStrength();
		
		//	Did this evaluate to Four of a Kind?
		assertEquals(iActualIsHandFourOfAKind,iExpectedIsHandFourOfAKind);		
		//	Was the four of a kind an Ace?
		assertEquals(hs.getHiHand(),eRank.KING.getiRankNbr());		
		//	FOAK has one kicker.  Was it a Club?
		assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteSuit(), eSuit.CLUBS);
		//	FOAK has one kicker.  Was it a King?		
		assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteRank(), eRank.ACE);
	}	
	@Test
	public void TestFiveOfAKind() {
		
		HandScore hs = new HandScore();
		ArrayList<Card> FiveOfAKind = new ArrayList<Card>();
		FiveOfAKind.add(new Card(eSuit.CLUBS,eRank.ACE,0));
		FiveOfAKind.add(new Card(eSuit.CLUBS,eRank.ACE,0));
		FiveOfAKind.add(new Card(eSuit.CLUBS,eRank.ACE,0));		
		FiveOfAKind.add(new Card(eSuit.CLUBS,eRank.ACE,0));
		FiveOfAKind.add(new Card(eSuit.CLUBS,eRank.ACE,0));
		
		Hand h = new Hand();
		h = SetHand(FiveOfAKind,h);
		
		boolean bActualIsHandFivefAKind = Hand.isHandFiveOfAKind(h, hs);
		boolean bExpectedIsHandFiveOfAKind = true;
		
		//	Did this evaluate to Five of a Kind?
		assertEquals(bActualIsHandFivefAKind,bExpectedIsHandFiveOfAKind);		
		//	Was the four of a kind an Ace?
		assertEquals(hs.getHiHand(),eRank.ACE.getiRankNbr());		
	}
	
	
	@Test
	public void TestFiveOfAKindEval() {
		
		HandScore hs = new HandScore();
		ArrayList<Card> FiveOfAKind = new ArrayList<Card>();
		FiveOfAKind.add(new Card(eSuit.CLUBS,eRank.ACE,0));
		FiveOfAKind.add(new Card(eSuit.CLUBS,eRank.ACE,0));
		FiveOfAKind.add(new Card(eSuit.CLUBS,eRank.ACE,0));		
		FiveOfAKind.add(new Card(eSuit.CLUBS,eRank.ACE,0));
		FiveOfAKind.add(new Card(eSuit.CLUBS,eRank.ACE,0));
		
		Hand h = new Hand();
		h = SetHand(FiveOfAKind,h);
		
	
		try {
			h = Hand.Evaluate(h);
		} catch (HandException e) {			
			e.printStackTrace();
			fail("TestFourOfAKindEval failed");
		}		
		
		
		
		
		int iActualIsHandFivefAKind = h.getHandScore().getHandStrength();
		int iExpectedIsHandFiveOfAKind = eHandStrength.FiveOfAKind.getHandStrength();
		
		//	Did this evaluate to Five of a Kind?
		assertEquals(iActualIsHandFivefAKind,iExpectedIsHandFiveOfAKind);		
		//	Was the four of a kind an Ace?
		assertEquals(h.getHandScore().getHiHand(),eRank.ACE.getiRankNbr());		
	}
	
	
	@Test
	public void TestStraight1() {
		
		HandScore hs = new HandScore();
		ArrayList<Card> Straight = new ArrayList<Card>();
		Straight.add(new Card(eSuit.CLUBS,eRank.FIVE,0));
		Straight.add(new Card(eSuit.CLUBS,eRank.SIX,0));
		Straight.add(new Card(eSuit.CLUBS,eRank.SEVEN,0));		
		Straight.add(new Card(eSuit.CLUBS,eRank.EIGHT,0));
		Straight.add(new Card(eSuit.CLUBS,eRank.NINE,0));
		Collections.sort(Straight);
		Hand h = new Hand();
		h = SetHand(Straight,h);
		
		boolean bActualIsHandStraight = Hand.isHandStraight(h, hs);
		boolean bExpectedIsHandStraight = true;
		
		//	Did this evaluate to Straight?
		assertEquals(bActualIsHandStraight,bExpectedIsHandStraight);		
		//	Was the high card a nine?
		assertEquals(hs.getHiHand(),eRank.NINE.getiRankNbr());		
	}
		
	@Test
	public void TestStraightEval1() {
		
		HandScore hs = new HandScore();
		ArrayList<Card> Straight = new ArrayList<Card>();
		Straight.add(new Card(eSuit.CLUBS,eRank.FIVE,0));
		Straight.add(new Card(eSuit.CLUBS,eRank.SIX,0));
		Straight.add(new Card(eSuit.CLUBS,eRank.SEVEN,0));		
		Straight.add(new Card(eSuit.CLUBS,eRank.EIGHT,0));
		Straight.add(new Card(eSuit.DIAMONDS,eRank.NINE,0));
		Collections.sort(Straight);
		Hand h = new Hand();
		h = SetHand(Straight,h);
		
		try {
			h = Hand.Evaluate(h);
		} catch (HandException e) {			
			e.printStackTrace();
			fail("TestStraightEval failed");
		}		

		int iActualIsHandStraight = h.getHandScore().getHandStrength();
		int iExpectedIsHandStraight = eHandStrength.Straight.getHandStrength();
		
		//	Did this evaluate to Straight?
		assertEquals(iActualIsHandStraight,iExpectedIsHandStraight);		
		//	Was the high card a nine?
		assertEquals(h.getHandScore().getHiHand(),eRank.NINE.getiRankNbr());			
	}
	
	
	@Test
	public void TestStraight2() {
		
		HandScore hs = new HandScore();
		ArrayList<Card> Straight = new ArrayList<Card>();
		Straight.add(new Card(eSuit.CLUBS,eRank.ACE,0));
		Straight.add(new Card(eSuit.CLUBS,eRank.TWO,0));
		Straight.add(new Card(eSuit.CLUBS,eRank.THREE,0));		
		Straight.add(new Card(eSuit.CLUBS,eRank.FOUR,0));
		Straight.add(new Card(eSuit.DIAMONDS,eRank.FIVE,0));
		Collections.sort(Straight);
		Hand h = new Hand();
		h = SetHand(Straight,h);
		
		boolean bActualIsHandStraight = Hand.isHandStraight(h, hs);
		boolean bExpectedIsHandStraight = true;
		
		assertEquals(bActualIsHandStraight,bExpectedIsHandStraight);		
		assertEquals(hs.getHiHand(),eRank.FIVE.getiRankNbr());		
	}
	
	@Test
	public void TestStraightEval2() {
		
		HandScore hs = new HandScore();
		ArrayList<Card> Straight = new ArrayList<Card>();
		Straight.add(new Card(eSuit.CLUBS,eRank.ACE,0));
		Straight.add(new Card(eSuit.CLUBS,eRank.TWO,0));
		Straight.add(new Card(eSuit.CLUBS,eRank.THREE,0));		
		Straight.add(new Card(eSuit.CLUBS,eRank.FOUR,0));
		Straight.add(new Card(eSuit.DIAMONDS,eRank.FIVE,0));
		Collections.sort(Straight);
		Hand h = new Hand();
		h = SetHand(Straight,h);
		
		try {
			h = Hand.Evaluate(h);
		} catch (HandException e) {			
			e.printStackTrace();
			fail("TestStraightEval failed");
		}		

		int iActualIsHandStraight = h.getHandScore().getHandStrength();
		int iExpectedIsHandStraight = eHandStrength.Straight.getHandStrength();
		
		assertEquals(iActualIsHandStraight,iExpectedIsHandStraight);		
		assertEquals(h.getHandScore().getHiHand(),eRank.FIVE.getiRankNbr());			
	}
	
	@Test
	public void TestStraight3() {
		
		HandScore hs = new HandScore();
		ArrayList<Card> Straight = new ArrayList<Card>();
		Straight.add(new Card(eSuit.CLUBS,eRank.ACE,0));
		Straight.add(new Card(eSuit.CLUBS,eRank.KING,0));
		Straight.add(new Card(eSuit.CLUBS,eRank.JACK,0));		
		Straight.add(new Card(eSuit.CLUBS,eRank.QUEEN,0));
		Straight.add(new Card(eSuit.DIAMONDS,eRank.TEN,0));
		Collections.sort(Straight);
		Hand h = new Hand();
		h = SetHand(Straight,h);
		
		boolean bActualIsHandStraight = Hand.isHandStraight(h, hs);
		boolean bExpectedIsHandStraight = true;
		
		//	Did this evaluate to Four of a Kind?
		assertEquals(bActualIsHandStraight,bExpectedIsHandStraight);		
		//	Was the four of a kind an Nine?
		assertEquals(hs.getHiHand(),eRank.ACE.getiRankNbr());		
	}
	@Test
	public void TestStraightEval3() {
		
		HandScore hs = new HandScore();
		ArrayList<Card> Straight = new ArrayList<Card>();
		Straight.add(new Card(eSuit.CLUBS,eRank.ACE,0));
		Straight.add(new Card(eSuit.CLUBS,eRank.KING,0));
		Straight.add(new Card(eSuit.CLUBS,eRank.JACK,0));		
		Straight.add(new Card(eSuit.CLUBS,eRank.QUEEN,0));
		Straight.add(new Card(eSuit.DIAMONDS,eRank.TEN,0));
		Collections.sort(Straight);
		Hand h = new Hand();
		h = SetHand(Straight,h);
		
		try {
			h = Hand.Evaluate(h);
		} catch (HandException e) {			
			e.printStackTrace();
			fail("TestStraightEval failed");
		}		

		int iActualIsHandStraight = h.getHandScore().getHandStrength();
		int iExpectedIsHandStraight = eHandStrength.Straight.getHandStrength();
		
		assertEquals(iActualIsHandStraight,iExpectedIsHandStraight);		
		assertEquals(h.getHandScore().getHiHand(),eRank.ACE.getiRankNbr());			
	}
	
	
	@Test
	public void TestFlush() {
		
		HandScore hs = new HandScore();
		ArrayList<Card> Flush = new ArrayList<Card>();
		Flush.add(new Card(eSuit.CLUBS,eRank.FIVE,0));
		Flush.add(new Card(eSuit.CLUBS,eRank.SIX,0));
		Flush.add(new Card(eSuit.CLUBS,eRank.SEVEN,0));		
		Flush.add(new Card(eSuit.CLUBS,eRank.EIGHT,0));
		Flush.add(new Card(eSuit.CLUBS,eRank.NINE,0));
		Collections.sort(Flush);
		Hand h = new Hand();
		h = SetHand(Flush,h);
		
		boolean bActualIsHandFlush = Hand.isHandFlush(h, hs);
		boolean bExpectedIsFlush = true;
		
		assertEquals(bActualIsHandFlush,bExpectedIsFlush);		
		assertEquals(hs.getHiHand(),eRank.NINE.getiRankNbr());		
	}
	
	@Test
	public void TestFlushEval() {
		
		HandScore hs = new HandScore();
		ArrayList<Card> Flush = new ArrayList<Card>();
		Flush.add(new Card(eSuit.CLUBS,eRank.ACE,0));
		Flush.add(new Card(eSuit.CLUBS,eRank.KING,0));
		Flush.add(new Card(eSuit.CLUBS,eRank.JACK,0));		
		Flush.add(new Card(eSuit.CLUBS,eRank.QUEEN,0));
		Flush.add(new Card(eSuit.CLUBS,eRank.FOUR,0));
		Collections.sort(Flush);
		Hand h = new Hand();
		h = SetHand(Flush,h);
		
		try {
			h = Hand.Evaluate(h);
		} catch (HandException e) {			
			e.printStackTrace();
			fail("TestStraightEval failed");
		}		

		int iActualIsHandFlush = h.getHandScore().getHandStrength();
		int iExpectedIsHandFlush = eHandStrength.Flush.getHandStrength();
		
		assertEquals(iActualIsHandFlush,iExpectedIsHandFlush);		
		assertEquals(h.getHandScore().getHiHand(),eRank.ACE.getiRankNbr());			
	}	
	
	
	@Test
	public void TestThreeOfAKind1() {
		
		HandScore hs = new HandScore();
		ArrayList<Card> ThreeOfAKind = new ArrayList<Card>();
		ThreeOfAKind.add(new Card(eSuit.CLUBS,eRank.FIVE,0));
		ThreeOfAKind.add(new Card(eSuit.CLUBS,eRank.FIVE,0));
		ThreeOfAKind.add(new Card(eSuit.CLUBS,eRank.FIVE,0));		
		ThreeOfAKind.add(new Card(eSuit.DIAMONDS,eRank.EIGHT,0));
		ThreeOfAKind.add(new Card(eSuit.DIAMONDS,eRank.NINE,0));
		Collections.sort(ThreeOfAKind);
		Hand h = new Hand();
		h = SetHand(ThreeOfAKind,h);
		
		boolean bActualIsThreeOfAKind = Hand.isHandThreeOfAKind(h, hs);
		boolean bExpectedIsThreeOfAKind = true;
		
		assertEquals(bActualIsThreeOfAKind,bExpectedIsThreeOfAKind);		
		assertEquals(hs.getHiHand(),eRank.FIVE.getiRankNbr());	
				
		assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteRank(), eRank.NINE);
		assertEquals(hs.getKickers().get(eCardNo.SecondCard.getCardNo()).geteRank(), eRank.EIGHT);
	}	
	
	@Test
	public void TestThreeOfAKind2() {
		
		HandScore hs = new HandScore();
		ArrayList<Card> ThreeOfAKind = new ArrayList<Card>();
		ThreeOfAKind.add(new Card(eSuit.CLUBS,eRank.TWO,0));
		ThreeOfAKind.add(new Card(eSuit.CLUBS,eRank.FIVE,0));
		ThreeOfAKind.add(new Card(eSuit.CLUBS,eRank.FIVE,0));		
		ThreeOfAKind.add(new Card(eSuit.DIAMONDS,eRank.FIVE,0));
		ThreeOfAKind.add(new Card(eSuit.DIAMONDS,eRank.NINE,0));
		Collections.sort(ThreeOfAKind);
		Hand h = new Hand();
		h = SetHand(ThreeOfAKind,h);
		
		boolean bActualIsThreeOfAKind = Hand.isHandThreeOfAKind(h, hs);
		boolean bExpectedIsThreeOfAKind = true;
		
		assertEquals(bActualIsThreeOfAKind,bExpectedIsThreeOfAKind);		
		assertEquals(hs.getHiHand(),eRank.FIVE.getiRankNbr());		
		
		assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteRank(), eRank.NINE);
		assertEquals(hs.getKickers().get(eCardNo.SecondCard.getCardNo()).geteRank(), eRank.TWO);
	}		
	
	@Test
	public void TestThreeOfAKind3() {
		
		HandScore hs = new HandScore();
		ArrayList<Card> ThreeOfAKind = new ArrayList<Card>();
		ThreeOfAKind.add(new Card(eSuit.CLUBS,eRank.TWO,0));
		ThreeOfAKind.add(new Card(eSuit.CLUBS,eRank.FIVE,0));
		ThreeOfAKind.add(new Card(eSuit.CLUBS,eRank.FIVE,0));		
		ThreeOfAKind.add(new Card(eSuit.DIAMONDS,eRank.FIVE,0));
		ThreeOfAKind.add(new Card(eSuit.DIAMONDS,eRank.THREE,0));
		Collections.sort(ThreeOfAKind);
		Hand h = new Hand();
		h = SetHand(ThreeOfAKind,h);
		
		boolean bActualIsThreeOfAKind = Hand.isHandThreeOfAKind(h, hs);
		boolean bExpectedIsThreeOfAKind = true;
		
		assertEquals(bActualIsThreeOfAKind,bExpectedIsThreeOfAKind);		
		assertEquals(hs.getHiHand(),eRank.FIVE.getiRankNbr());		
		
		assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteRank(), eRank.THREE);
		assertEquals(hs.getKickers().get(eCardNo.SecondCard.getCardNo()).geteRank(), eRank.TWO);
	}	
	
	@Test
	public void TestThreeOfAKindEval() {
		
		HandScore hs = new HandScore();
		ArrayList<Card> ThreeOfAKind = new ArrayList<Card>();
		ThreeOfAKind.add(new Card(eSuit.CLUBS,eRank.TWO,0));
		ThreeOfAKind.add(new Card(eSuit.CLUBS,eRank.FIVE,0));
		ThreeOfAKind.add(new Card(eSuit.CLUBS,eRank.FIVE,0));		
		ThreeOfAKind.add(new Card(eSuit.DIAMONDS,eRank.FIVE,0));
		ThreeOfAKind.add(new Card(eSuit.DIAMONDS,eRank.THREE,0));
		Collections.sort(ThreeOfAKind);
		Hand h = new Hand();
		h = SetHand(ThreeOfAKind,h);
		
		try {
			h = Hand.Evaluate(h);
		} catch (HandException e) {			
			e.printStackTrace();
			fail("TestStraightEval failed");
		}		

		int iActualIsHandThreeOfAKind = h.getHandScore().getHandStrength();
		int iExpectedIsHandThreeOfAKind = eHandStrength.ThreeOfAKind.getHandStrength();
		
		assertEquals(iActualIsHandThreeOfAKind,iExpectedIsHandThreeOfAKind);		
		assertEquals(h.getHandScore().getHiHand(),eRank.FIVE.getiRankNbr());		
		
		assertEquals(h.getHandScore().getKickers().get(eCardNo.FirstCard.getCardNo()).geteRank(), eRank.THREE);
		assertEquals(h.getHandScore().getKickers().get(eCardNo.SecondCard.getCardNo()).geteRank(), eRank.TWO);		
	}	
	
	
	
	@Test
	public void TestTwoPair1() {
		
		HandScore hs = new HandScore();
		ArrayList<Card> TwoPair = new ArrayList<Card>();
		TwoPair.add(new Card(eSuit.CLUBS,eRank.TWO,0));
		TwoPair.add(new Card(eSuit.CLUBS,eRank.TWO,0));
		TwoPair.add(new Card(eSuit.CLUBS,eRank.FIVE,0));		
		TwoPair.add(new Card(eSuit.DIAMONDS,eRank.FIVE,0));
		TwoPair.add(new Card(eSuit.DIAMONDS,eRank.THREE,0));
		Collections.sort(TwoPair);
		Hand h = new Hand();
		h = SetHand(TwoPair,h);
		
		boolean bActualIsTwoPair = Hand.isHandTwoPair(h, hs);
		boolean bExpectedIsTwoPair = true;
		
		assertEquals(bActualIsTwoPair,bExpectedIsTwoPair);		
		assertEquals(hs.getHiHand(),eRank.FIVE.getiRankNbr());	
		
		assertEquals(hs.getLoHand(),eRank.TWO.getiRankNbr());
		
		assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteRank(), eRank.THREE);

	}		
	
	
	@Test
	public void TestTwoPair2() {
		
		HandScore hs = new HandScore();
		ArrayList<Card> TwoPair = new ArrayList<Card>();
		TwoPair.add(new Card(eSuit.CLUBS,eRank.TWO,0));
		TwoPair.add(new Card(eSuit.CLUBS,eRank.THREE,0));
		TwoPair.add(new Card(eSuit.CLUBS,eRank.FIVE,0));		
		TwoPair.add(new Card(eSuit.DIAMONDS,eRank.FIVE,0));
		TwoPair.add(new Card(eSuit.DIAMONDS,eRank.THREE,0));
		Collections.sort(TwoPair);
		Hand h = new Hand();
		h = SetHand(TwoPair,h);
		
		boolean bActualIsTwoPair = Hand.isHandTwoPair(h, hs);
		boolean bExpectedIsTwoPair = true;
		
		assertEquals(bActualIsTwoPair,bExpectedIsTwoPair);		
		assertEquals(hs.getHiHand(),eRank.FIVE.getiRankNbr());	
		
		assertEquals(hs.getLoHand(),eRank.THREE.getiRankNbr());
		
		assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteRank(), eRank.TWO);

	}		
	
	
	@Test
	public void TestTwoPair3() {
		
		HandScore hs = new HandScore();
		ArrayList<Card> TwoPair = new ArrayList<Card>();
		TwoPair.add(new Card(eSuit.CLUBS,eRank.TWO,0));
		TwoPair.add(new Card(eSuit.CLUBS,eRank.TWO,0));
		TwoPair.add(new Card(eSuit.CLUBS,eRank.FIVE,0));		
		TwoPair.add(new Card(eSuit.DIAMONDS,eRank.FIVE,0));
		TwoPair.add(new Card(eSuit.DIAMONDS,eRank.KING,0));
		Collections.sort(TwoPair);
		Hand h = new Hand();
		h = SetHand(TwoPair,h);
		
		boolean bActualIsTwoPair = Hand.isHandTwoPair(h, hs);
		boolean bExpectedIsTwoPair = true;
		
		assertEquals(bActualIsTwoPair,bExpectedIsTwoPair);		
		assertEquals(hs.getHiHand(),eRank.FIVE.getiRankNbr());	
		
		assertEquals(hs.getLoHand(),eRank.TWO.getiRankNbr());
		
		assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteRank(), eRank.KING);

	}	
	
	
	
	
	@Test
	public void TestTwoPairEval() {
		
		HandScore hs = new HandScore();
		ArrayList<Card> TwoPair = new ArrayList<Card>();
		TwoPair.add(new Card(eSuit.CLUBS,eRank.TWO,0));
		TwoPair.add(new Card(eSuit.CLUBS,eRank.TWO,0));
		TwoPair.add(new Card(eSuit.CLUBS,eRank.FIVE,0));		
		TwoPair.add(new Card(eSuit.DIAMONDS,eRank.FIVE,0));
		TwoPair.add(new Card(eSuit.DIAMONDS,eRank.KING,0));
		Collections.sort(TwoPair);
		Hand h = new Hand();
		h = SetHand(TwoPair,h);
		
		try {
			h = Hand.Evaluate(h);
		} catch (HandException e) {			
			e.printStackTrace();
			fail("TestStraightEval failed");
		}		

		int iActualIsHandTwoPair = h.getHandScore().getHandStrength();
		int iExpectedIsHandTwoPair = eHandStrength.TwoPair.getHandStrength();
		
		assertEquals(iActualIsHandTwoPair,iExpectedIsHandTwoPair);		
		assertEquals(h.getHandScore().getHiHand(),eRank.FIVE.getiRankNbr());		
		
		
		assertEquals(h.getHandScore().getLoHand(),eRank.TWO.getiRankNbr());
		
		assertEquals(h.getHandScore().getKickers().get(eCardNo.FirstCard.getCardNo()).geteRank(), eRank.KING);
		
		
		
	}	
	
	
	
	
	@Test
	public void TestPair1() {
		
		HandScore hs = new HandScore();
		ArrayList<Card> Pair = new ArrayList<Card>();
		Pair.add(new Card(eSuit.CLUBS,eRank.TWO,0));
		Pair.add(new Card(eSuit.CLUBS,eRank.TWO,0));
		Pair.add(new Card(eSuit.CLUBS,eRank.FIVE,0));		
		Pair.add(new Card(eSuit.DIAMONDS,eRank.SIX,0));
		Pair.add(new Card(eSuit.DIAMONDS,eRank.THREE,0));
		Collections.sort(Pair);
		Hand h = new Hand();
		h = SetHand(Pair,h);
		
		boolean bActualIsPair = Hand.isHandPair(h, hs);
		boolean bExpectedIsPair = true;
		
		assertEquals(bExpectedIsPair,bActualIsPair);		
		assertEquals(hs.getHiHand(),eRank.TWO.getiRankNbr());	
		
		assertEquals(hs.getLoHand(),0);
		
		assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteRank(), eRank.SIX);
		assertEquals(hs.getKickers().get(eCardNo.SecondCard.getCardNo()).geteRank(), eRank.FIVE);
		assertEquals(hs.getKickers().get(eCardNo.ThirdCard.getCardNo()).geteRank(), eRank.THREE);

	}	
	
	
	
	
	@Test
	public void TestPair2() {
		
		HandScore hs = new HandScore();
		ArrayList<Card> Pair = new ArrayList<Card>();
		Pair.add(new Card(eSuit.CLUBS,eRank.TWO,0));
		Pair.add(new Card(eSuit.CLUBS,eRank.THREE,0));
		Pair.add(new Card(eSuit.CLUBS,eRank.FIVE,0));		
		Pair.add(new Card(eSuit.DIAMONDS,eRank.SIX,0));
		Pair.add(new Card(eSuit.DIAMONDS,eRank.THREE,0));
		Collections.sort(Pair);
		Hand h = new Hand();
		h = SetHand(Pair,h);
		
		boolean bActualIsPair = Hand.isHandPair(h, hs);
		boolean bExpectedIsPair = true;
		
		assertEquals(bExpectedIsPair,bActualIsPair);		
		assertEquals(hs.getHiHand(),eRank.THREE.getiRankNbr());	
		
		assertEquals(hs.getLoHand(),0);
		
		assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteRank(), eRank.SIX);
		assertEquals(hs.getKickers().get(eCardNo.SecondCard.getCardNo()).geteRank(), eRank.FIVE);
		assertEquals(hs.getKickers().get(eCardNo.ThirdCard.getCardNo()).geteRank(), eRank.TWO);

	}	
	
	
	@Test
	public void TestPair3() {
		
		HandScore hs = new HandScore();
		ArrayList<Card> Pair = new ArrayList<Card>();
		Pair.add(new Card(eSuit.CLUBS,eRank.TWO,0));
		Pair.add(new Card(eSuit.CLUBS,eRank.THREE,0));
		Pair.add(new Card(eSuit.CLUBS,eRank.FIVE,0));		
		Pair.add(new Card(eSuit.DIAMONDS,eRank.FIVE,0));
		Pair.add(new Card(eSuit.DIAMONDS,eRank.SEVEN,0));
		Collections.sort(Pair);
		Hand h = new Hand();
		h = SetHand(Pair,h);
		
		boolean bActualIsPair = Hand.isHandPair(h, hs);
		boolean bExpectedIsPair = true;
		
		assertEquals(bExpectedIsPair,bActualIsPair);		
		assertEquals(hs.getHiHand(),eRank.FIVE.getiRankNbr());	
		
		assertEquals(hs.getLoHand(),0);
		
		assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteRank(), eRank.SEVEN);
		assertEquals(hs.getKickers().get(eCardNo.SecondCard.getCardNo()).geteRank(), eRank.THREE);
		assertEquals(hs.getKickers().get(eCardNo.ThirdCard.getCardNo()).geteRank(), eRank.TWO);

	}	
	
	@Test
	public void TestPair4() {
		
		HandScore hs = new HandScore();
		ArrayList<Card> Pair = new ArrayList<Card>();
		Pair.add(new Card(eSuit.CLUBS,eRank.TWO,0));
		Pair.add(new Card(eSuit.CLUBS,eRank.THREE,0));
		Pair.add(new Card(eSuit.CLUBS,eRank.FIVE,0));		
		Pair.add(new Card(eSuit.DIAMONDS,eRank.SEVEN,0));
		Pair.add(new Card(eSuit.DIAMONDS,eRank.SEVEN,0));
		Collections.sort(Pair);
		Hand h = new Hand();
		h = SetHand(Pair,h);
		
		boolean bActualIsPair = Hand.isHandPair(h, hs);
		boolean bExpectedIsPair = true;
		
		assertEquals(bExpectedIsPair,bActualIsPair);		
		assertEquals(hs.getHiHand(),eRank.SEVEN.getiRankNbr());	
		
		assertEquals(hs.getLoHand(),0);
		
		assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteRank(), eRank.FIVE);
		assertEquals(hs.getKickers().get(eCardNo.SecondCard.getCardNo()).geteRank(), eRank.THREE);
		assertEquals(hs.getKickers().get(eCardNo.ThirdCard.getCardNo()).geteRank(), eRank.TWO);

	}	
	
	@Test
	public void TestPairEval() {
		
		HandScore hs = new HandScore();
		ArrayList<Card> Pair = new ArrayList<Card>();
		Pair.add(new Card(eSuit.CLUBS,eRank.TWO,0));
		Pair.add(new Card(eSuit.CLUBS,eRank.THREE,0));
		Pair.add(new Card(eSuit.CLUBS,eRank.FIVE,0));		
		Pair.add(new Card(eSuit.DIAMONDS,eRank.SEVEN,0));
		Pair.add(new Card(eSuit.DIAMONDS,eRank.SEVEN,0));
		Collections.sort(Pair);
		Hand h = new Hand();
		h = SetHand(Pair,h);
		
		try {
			h = Hand.Evaluate(h);
		} catch (HandException e) {			
			e.printStackTrace();
			fail("TestStraightEval failed");
		}		

		int iActualIsHandPair = h.getHandScore().getHandStrength();
		int iExpectedIsHandPair = eHandStrength.Pair.getHandStrength();
		
		assertEquals(iActualIsHandPair,iExpectedIsHandPair);		
		assertEquals(h.getHandScore().getHiHand(),eRank.SEVEN.getiRankNbr());		
		
		
		assertEquals(h.getHandScore().getLoHand(),0);

		assertEquals(h.getHandScore().getKickers().get(eCardNo.FirstCard.getCardNo()).geteRank(), eRank.FIVE);
		assertEquals(h.getHandScore().getKickers().get(eCardNo.SecondCard.getCardNo()).geteRank(), eRank.THREE);
		assertEquals(h.getHandScore().getKickers().get(eCardNo.ThirdCard.getCardNo()).geteRank(), eRank.TWO);
		
	}	
	
	
	@Test
	public void TestHighCard() {
		
		HandScore hs = new HandScore();
		ArrayList<Card> HighCard = new ArrayList<Card>();
		HighCard.add(new Card(eSuit.CLUBS,eRank.TWO,0));
		HighCard.add(new Card(eSuit.CLUBS,eRank.THREE,0));
		HighCard.add(new Card(eSuit.CLUBS,eRank.FIVE,0));		
		HighCard.add(new Card(eSuit.DIAMONDS,eRank.SEVEN,0));
		HighCard.add(new Card(eSuit.DIAMONDS,eRank.KING,0));
		Collections.sort(HighCard);
		Hand h = new Hand();
		h = SetHand(HighCard,h);
		
		boolean bActualIsHighCard = Hand.isHandHighCard(h, hs);
		boolean bExpectedIsHighCard = true;
		
		assertEquals(bExpectedIsHighCard,bActualIsHighCard);		
		
		assertEquals(hs.getHiHand(),eRank.KING.getiRankNbr());	
		
		assertEquals(hs.getLoHand(),0);
		
		assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteRank(), eRank.SEVEN);
		assertEquals(hs.getKickers().get(eCardNo.SecondCard.getCardNo()).geteRank(), eRank.FIVE);
		assertEquals(hs.getKickers().get(eCardNo.ThirdCard.getCardNo()).geteRank(), eRank.THREE);
		assertEquals(hs.getKickers().get(eCardNo.FourthCard.getCardNo()).geteRank(), eRank.TWO);

	}	
	
	@Test
	public void TestHighCardEval() {
		
		HandScore hs = new HandScore();
		ArrayList<Card> HighCard = new ArrayList<Card>();
		HighCard.add(new Card(eSuit.CLUBS,eRank.TWO,0));
		HighCard.add(new Card(eSuit.CLUBS,eRank.THREE,0));
		HighCard.add(new Card(eSuit.CLUBS,eRank.FIVE,0));		
		HighCard.add(new Card(eSuit.DIAMONDS,eRank.SEVEN,0));
		HighCard.add(new Card(eSuit.DIAMONDS,eRank.KING,0));
		Collections.sort(HighCard);
		Hand h = new Hand();
		h = SetHand(HighCard,h);
		
		try {
			h = Hand.Evaluate(h);
		} catch (HandException e) {			
			e.printStackTrace();
			fail("TestStraightEval failed");
		}		

		int iActualIsHandHighCard = h.getHandScore().getHandStrength();
		int iExpectedIsHandHighCard = eHandStrength.HighCard.getHandStrength();
		
		assertEquals(iActualIsHandHighCard,iExpectedIsHandHighCard);		
		assertEquals(h.getHandScore().getHiHand(),eRank.KING.getiRankNbr());		
		
		assertEquals(h.getHandScore().getLoHand(),0);

		assertEquals(h.getHandScore().getKickers().get(eCardNo.FirstCard.getCardNo()).geteRank(), eRank.SEVEN);
		assertEquals(h.getHandScore().getKickers().get(eCardNo.SecondCard.getCardNo()).geteRank(), eRank.FIVE);
		assertEquals(h.getHandScore().getKickers().get(eCardNo.ThirdCard.getCardNo()).geteRank(), eRank.THREE);
		assertEquals(h.getHandScore().getKickers().get(eCardNo.FourthCard.getCardNo()).geteRank(), eRank.TWO);
		
	}		
	
	@Test
	public void TestRoyalFlush() {
		
		HandScore hs = new HandScore();
		ArrayList<Card> RoyalFlush = new ArrayList<Card>();
		RoyalFlush.add(new Card(eSuit.CLUBS,eRank.TEN,0));
		RoyalFlush.add(new Card(eSuit.CLUBS,eRank.JACK,0));
		RoyalFlush.add(new Card(eSuit.CLUBS,eRank.QUEEN,0));		
		RoyalFlush.add(new Card(eSuit.CLUBS,eRank.KING,0));
		RoyalFlush.add(new Card(eSuit.CLUBS,eRank.ACE,0));
		Collections.sort(RoyalFlush);
		Hand h = new Hand();
		h = SetHand(RoyalFlush,h);
		
		boolean bActualIsRoyalFlush = Hand.isHandRoyalFlush(h, hs);
		boolean bExpectedIsRoyalFlush = true;
		
		assertEquals(bExpectedIsRoyalFlush,bActualIsRoyalFlush);		
		
		assertEquals(hs.getHiHand(),eRank.ACE.getiRankNbr());	
	}
	
	@Test
	public void TestRoyalFlushEval() {
		
		HandScore hs = new HandScore();
		ArrayList<Card> RoyalFlush = new ArrayList<Card>();
		RoyalFlush.add(new Card(eSuit.CLUBS,eRank.TEN,0));
		RoyalFlush.add(new Card(eSuit.CLUBS,eRank.JACK,0));
		RoyalFlush.add(new Card(eSuit.CLUBS,eRank.QUEEN,0));		
		RoyalFlush.add(new Card(eSuit.CLUBS,eRank.KING,0));
		RoyalFlush.add(new Card(eSuit.CLUBS,eRank.ACE,0));
		Collections.sort(RoyalFlush);
		Hand h = new Hand();
		h = SetHand(RoyalFlush,h);
		
		try {
			h = Hand.Evaluate(h);
		} catch (HandException e) {			
			e.printStackTrace();
			fail("TestStraightEval failed");
		}		

		int iActualIsHandRoyalFlush = h.getHandScore().getHandStrength();
		int iExpectedIsHandRoyalFlush = eHandStrength.RoyalFlush.getHandStrength();
		
		assertEquals(iActualIsHandRoyalFlush,iExpectedIsHandRoyalFlush);		
		assertEquals(h.getHandScore().getHiHand(),eRank.ACE.getiRankNbr());		
	}
	
	@Test
	public void TestStraightFlush() {
		
		HandScore hs = new HandScore();
		ArrayList<Card> StraightFlush = new ArrayList<Card>();
		StraightFlush.add(new Card(eSuit.CLUBS,eRank.TEN,0));
		StraightFlush.add(new Card(eSuit.CLUBS,eRank.JACK,0));
		StraightFlush.add(new Card(eSuit.CLUBS,eRank.QUEEN,0));		
		StraightFlush.add(new Card(eSuit.CLUBS,eRank.KING,0));
		StraightFlush.add(new Card(eSuit.CLUBS,eRank.NINE,0));
		Collections.sort(StraightFlush);
		Hand h = new Hand();
		h = SetHand(StraightFlush,h);
		
		boolean bActualIsStraightFlush = Hand.isHandStraightFlush(h, hs);
		boolean bExpectedIsStraightFlush = true;
		
		assertEquals(bExpectedIsStraightFlush,bActualIsStraightFlush);		
		
		assertEquals(hs.getHiHand(),eRank.KING.getiRankNbr());	
	}	
	
	@Test
	public void TestStraightFlushEval() {
		
		HandScore hs = new HandScore();
		ArrayList<Card> StraightFlush = new ArrayList<Card>();
		StraightFlush.add(new Card(eSuit.CLUBS,eRank.TEN,0));
		StraightFlush.add(new Card(eSuit.CLUBS,eRank.JACK,0));
		StraightFlush.add(new Card(eSuit.CLUBS,eRank.QUEEN,0));		
		StraightFlush.add(new Card(eSuit.CLUBS,eRank.KING,0));
		StraightFlush.add(new Card(eSuit.CLUBS,eRank.NINE,0));
		Collections.sort(StraightFlush);
		Hand h = new Hand();
		h = SetHand(StraightFlush,h);
		
		try {
			h = Hand.Evaluate(h);
		} catch (HandException e) {			
			e.printStackTrace();
			fail("TestStraightEval failed");
		}		

		int iActualIsHandStraightFlush = h.getHandScore().getHandStrength();
		int iExpectedIsHandStraightFlush = eHandStrength.StraightFlush.getHandStrength();
		
		assertEquals(iActualIsHandStraightFlush,iExpectedIsHandStraightFlush);		
		assertEquals(h.getHandScore().getHiHand(),eRank.KING.getiRankNbr());		
	}	
	
	@Test
	public void TestFullHouse1() {
		
		HandScore hs = new HandScore();
		ArrayList<Card> FullHouse = new ArrayList<Card>();
		FullHouse.add(new Card(eSuit.CLUBS,eRank.TEN,0));
		FullHouse.add(new Card(eSuit.CLUBS,eRank.TEN,0));
		FullHouse.add(new Card(eSuit.CLUBS,eRank.TEN,0));		
		FullHouse.add(new Card(eSuit.CLUBS,eRank.KING,0));
		FullHouse.add(new Card(eSuit.CLUBS,eRank.KING,0));
		Collections.sort(FullHouse);
		Hand h = new Hand();
		h = SetHand(FullHouse,h);
		
		boolean bActualIsFullHouse = Hand.isHandFullHouse(h, hs);
		boolean bExpectedIsFullHouse = true;
		
		//	Did this evaluate
		assertEquals(bExpectedIsFullHouse,bActualIsFullHouse);		
		//	Test Hi Hand
		
		assertEquals(hs.getHiHand(),eRank.TEN.getiRankNbr());	
		
		assertEquals(hs.getLoHand(),eRank.KING.getiRankNbr());
	}	
	
	@Test
	public void TestFullHouse2() {
		
		HandScore hs = new HandScore();
		ArrayList<Card> FullHouse = new ArrayList<Card>();
		FullHouse.add(new Card(eSuit.CLUBS,eRank.TEN,0));
		FullHouse.add(new Card(eSuit.CLUBS,eRank.TEN,0));
		FullHouse.add(new Card(eSuit.CLUBS,eRank.KING,0));		
		FullHouse.add(new Card(eSuit.CLUBS,eRank.KING,0));
		FullHouse.add(new Card(eSuit.CLUBS,eRank.KING,0));
		Collections.sort(FullHouse);
		Hand h = new Hand();
		h = SetHand(FullHouse,h);
		
		boolean bActualIsFullHouse = Hand.isHandFullHouse(h, hs);
		boolean bExpectedIsFullHouse = true;
		
		assertEquals(bExpectedIsFullHouse,bActualIsFullHouse);		
		
		assertEquals(hs.getHiHand(),eRank.KING.getiRankNbr());	
		
		assertEquals(hs.getLoHand(),eRank.TEN.getiRankNbr());
	}		
	
	@Test
	public void TestFullHouseEval() {
		
		HandScore hs = new HandScore();
		ArrayList<Card> FullHouse = new ArrayList<Card>();
		FullHouse.add(new Card(eSuit.CLUBS,eRank.TEN,0));
		FullHouse.add(new Card(eSuit.CLUBS,eRank.TEN,0));
		FullHouse.add(new Card(eSuit.CLUBS,eRank.KING,0));		
		FullHouse.add(new Card(eSuit.CLUBS,eRank.KING,0));
		FullHouse.add(new Card(eSuit.CLUBS,eRank.KING,0));
		Collections.sort(FullHouse);
		Hand h = new Hand();
		h = SetHand(FullHouse,h);
		
		try {
			h = Hand.Evaluate(h);
		} catch (HandException e) {			
			e.printStackTrace();
			fail("TestStraightEval failed");
		}		

		int iActualIsHandFullHouse = h.getHandScore().getHandStrength();
		int iExpectedIsHandFullHouse = eHandStrength.FullHouse.getHandStrength();
		
		assertEquals(iActualIsHandFullHouse,iExpectedIsHandFullHouse);		
		assertEquals(h.getHandScore().getHiHand(),eRank.KING.getiRankNbr());		
		assertEquals(h.getHandScore().getLoHand(),eRank.TEN.getiRankNbr());
	}		
	
	
	
	@Test
	public void TestHandSort1() {
		
		ArrayList<Card> FullHouse = new ArrayList<Card>();
		FullHouse.add(new Card(eSuit.CLUBS,eRank.TEN,0));
		FullHouse.add(new Card(eSuit.CLUBS,eRank.TEN,0));
		FullHouse.add(new Card(eSuit.CLUBS,eRank.KING,0));		
		FullHouse.add(new Card(eSuit.CLUBS,eRank.KING,0));
		FullHouse.add(new Card(eSuit.CLUBS,eRank.KING,0));
		Collections.sort(FullHouse);
		Hand h1 = new Hand();
		h1 = SetHand(FullHouse,h1);
		
		try {
			h1 = Hand.Evaluate(h1);
		} catch (HandException e) {			
			e.printStackTrace();
			fail("TestStraightEval failed");
		}		

		ArrayList<Card> StraightFlush = new ArrayList<Card>();
		StraightFlush.add(new Card(eSuit.CLUBS,eRank.NINE,0));
		StraightFlush.add(new Card(eSuit.CLUBS,eRank.TEN,0));
		StraightFlush.add(new Card(eSuit.CLUBS,eRank.JACK,0));		
		StraightFlush.add(new Card(eSuit.CLUBS,eRank.QUEEN,0));
		StraightFlush.add(new Card(eSuit.CLUBS,eRank.KING,0));
		Collections.sort(StraightFlush);
		Hand h2 = new Hand();
		h2 = SetHand(StraightFlush,h2);
		
		try {
			h2 = Hand.Evaluate(h2);
		} catch (HandException e) {			
			e.printStackTrace();
			fail("TestStraightEval failed");
		}	
		
		
		ArrayList<Hand> hands = new ArrayList<Hand>();
		hands.add(h1);
		hands.add(h2);
		
		Collections.sort(hands, Hand.HandRank);

		int iActualIsHandFullHouse = hands.get(0).getHandScore().getHandStrength();
		int iExpectedIsHandFullHouse = eHandStrength.StraightFlush.getHandStrength();
		
		assertEquals(iActualIsHandFullHouse,iExpectedIsHandFullHouse);	
		
	}
	
	@Test
	public void TestHandSort2() {
		
		ArrayList<Card> FullHouse = new ArrayList<Card>();
		FullHouse.add(new Card(eSuit.CLUBS,eRank.TEN,0));
		FullHouse.add(new Card(eSuit.CLUBS,eRank.TEN,0));
		FullHouse.add(new Card(eSuit.CLUBS,eRank.KING,0));		
		FullHouse.add(new Card(eSuit.CLUBS,eRank.KING,0));
		FullHouse.add(new Card(eSuit.CLUBS,eRank.KING,0));
		Collections.sort(FullHouse);
		Hand h1 = new Hand();
		h1 = SetHand(FullHouse,h1);
		
		try {
			h1 = Hand.Evaluate(h1);
		} catch (HandException e) {			
			e.printStackTrace();
			fail("TestStraightEval failed");
		}		

		ArrayList<Card> Straight = new ArrayList<Card>();
		Straight.add(new Card(eSuit.CLUBS,eRank.NINE,0));
		Straight.add(new Card(eSuit.CLUBS,eRank.TEN,0));
		Straight.add(new Card(eSuit.CLUBS,eRank.JACK,0));		
		Straight.add(new Card(eSuit.SPADES,eRank.QUEEN,0));
		Straight.add(new Card(eSuit.CLUBS,eRank.KING,0));
		Collections.sort(Straight);
		Hand h2 = new Hand();
		h2 = SetHand(Straight,h2);
		
		try {
			h2 = Hand.Evaluate(h2);
		} catch (HandException e) {			
			e.printStackTrace();
			fail("TestStraightEval failed");
		}	
		
		ArrayList<Card> FullHouse2 = new ArrayList<Card>();
		FullHouse2.add(new Card(eSuit.CLUBS,eRank.TEN,0));
		FullHouse2.add(new Card(eSuit.CLUBS,eRank.TEN,0));
		FullHouse2.add(new Card(eSuit.CLUBS,eRank.TEN,0));		
		FullHouse2.add(new Card(eSuit.CLUBS,eRank.KING,0));
		FullHouse2.add(new Card(eSuit.CLUBS,eRank.KING,0));
		Collections.sort(FullHouse2);
		Hand h3 = new Hand();
		h3 = SetHand(FullHouse2,h3);
		
		try {
			h3 = Hand.Evaluate(h3);
		} catch (HandException e) {			
			e.printStackTrace();
			fail("TestStraightEval failed");
		}			
		
		
		ArrayList<Hand> hands = new ArrayList<Hand>();
		hands.add(h1);
		hands.add(h2);
		hands.add(h3);
		
		Collections.sort(hands, Hand.HandRank);

		int iActualIsHandFullHouse = hands.get(0).getHandScore().getHandStrength();
		int iExpectedIsHandFullHouse = eHandStrength.FullHouse.getHandStrength();
		
		assertEquals(iActualIsHandFullHouse,iExpectedIsHandFullHouse);	

		assertEquals(eRank.KING.getiRankNbr(), hands.get(0).getHandScore().getHiHand());	
		
		
	}	
	
	
	@Test
	public void TestHandSortTons() {
	
		/*
		 * Create a thousand random hands... I want to see if the sort / comparitor for hand works.
		 */
		int iCreateHands = 1000;
		ArrayList<Hand> hands = new ArrayList<Hand>();
		for (int a = 0; a < iCreateHands;a++)
		{
			Deck d = new Deck();			
			Hand h = new Hand();
			
			for (int b= 0;b<5;b++)
			{
				try {
					h.Draw(d);
				} catch (DeckException e) {				
					e.printStackTrace();
					fail("TestHandSortTons failed");				
				}	
			}
			try {
				h = Hand.Evaluate(h);
			} catch (HandException e) {
				e.printStackTrace();
				fail("TestHandSortTons failed");	
			}
			hands.add(h);
		}
		
		assertTrue(hands.size() == 1000);
		
		Collections.sort(hands, Hand.HandRank);
		
		for (Hand h : hands)
		{
			System.out.print(h.getHandScore().getHandStrength());
			System.out.print(' ');
			System.out.print(h.getHandScore().getHiHand());
			System.out.print(' ');
			System.out.print(h.getHandScore().getLoHand());
			System.out.print(' ');
			
			for (Card k: h.getHandScore().getKickers())
			{
				System.out.print(k.geteRank().toString());
				System.out.print(' ');
			}
			System.out.println(' ');
		}
		
	}
}
