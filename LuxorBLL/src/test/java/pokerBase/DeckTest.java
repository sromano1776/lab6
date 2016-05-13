/**
 * 
 */
package pokerBase;

import static org.junit.Assert.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import exceptions.DeckException;
import exceptions.HandException;
import pokerEnums.eHandStrength;

/**
 * @author Bert.Gibbons
 *
 */
public class DeckTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test to check to see if the .draw() method actually returns a Card
	 * @throws DeckException
	 */
	@Test
	public void DeckDrawIsCard() throws DeckException {
		Deck d = new Deck();
		Object o = d.Draw();

		if (!(o instanceof Card)) {
			fail("Object drawn from deck isn't card");
		}
	}

	/**
	 * Test to check to see if an overdraw (draw more cards than in deck)
	 * throws the right exception
	 * @throws Exception 
	 * @throws DeckException
	 */

	@Test(expected = DeckException.class)
	public void DeckOverDraw() throws Exception {
		Deck d = new Deck();
		Card c = null;
		for (int i = 0; i < 100; i++) {
			c = d.Draw();		
		}
	}

	/**
	 * I can't call 'GetDeckSize' directly- the method is private! I'm calling
	 * GetDeckSize using Java Reflections
	 * 
	 * All those crazy 'catch' blocks... using reflections can potentially throw
	 * a bunch of errors... method not found, instance not found, etc The
	 * 'catch' blocks are required to catch any of these generated exceptions
	 */
	@Test
	public void NoramlDeckSizeTest() {
		int iExpectedValue = 51;
		int iActualValue;

		try {
			//	Load the Class into 'c'
			Class<?> c = Class.forName("pokerBase.Deck");
			//	Create a new instance 't' from the no-arg Deck constructor
			Object t = c.newInstance();
			//	Load 'mDraw' with the 'Draw' method (no args);
			Method mDraw = c.getDeclaredMethod("Draw", null);
			//	Load 'mGetDeckSize' with the 'GetDeckSize' method
			Method mGetDeckSize = c.getDeclaredMethod("GetDeckSize", null);
			//	Change the visibilty of 'GetDeckSize' to true *Good Grief!*
			mGetDeckSize.setAccessible(true);

			//	invoke 'Draw'
			Object oDraw = mDraw.invoke(t, null);
			
			//	invoke 'GetDeckSize'
			Object oGetDeckSize = mGetDeckSize.invoke(t, null);

			iActualValue = ((Integer) oGetDeckSize).intValue();

			assertEquals(iExpectedValue, iActualValue);
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
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void OneJokerDeckSizeTest() {
		int iNbrOfJokers = 1;
		int iExpectedValue = 52;
		int iActualValue;

		try {
			Class<?> c = Class.forName("pokerBase.Deck");
			Constructor<?> cons = c.getConstructor(int.class);
			Object t = cons.newInstance(iNbrOfJokers);

			Method mDraw = c.getDeclaredMethod("Draw", null);
			Method mGetDeckSize = c.getDeclaredMethod("GetDeckSize", null);
			mGetDeckSize.setAccessible(true);

			Object oDraw = mDraw.invoke(t, null);
			Object oGetDeckSize = mGetDeckSize.invoke(t, null);

			iActualValue = ((Integer) oGetDeckSize).intValue();

			assertEquals(iExpectedValue, iActualValue);
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
	}	
}
