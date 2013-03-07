package ca.teamTen.recitopia.test;

import java.util.Collection;

import ca.teamTen.recitopia.Fridge;
import junit.framework.TestCase;

/*
 * ca.teamTen.recitopia.Fridge unit tests.
 * 
 * Doesn't test saving/loading from disk.
 */
public class FridgeTests extends TestCase {
	private Fridge fridge;

	protected void setUp() throws Exception
	{
		super.setUp();
		
		fridge = new Fridge();
	}
	
	protected void tearDown() throws Exception
	{
		super.tearDown();
	}
	
	public void testAddSingleIngredient()
	{
		int initialCount = fridge.countIngredients();
		assertEquals(fridge.addIngredient("spiky melon"), true);
		assertEquals(fridge.countIngredients(), initialCount + 1);

		assertEquals(hasIngredient("spiky melon"), true);
	}
	
	public void testRemoveIngredient()
	{
		assertEquals(fridge.addIngredient("spiky melon"), true);
		int count = fridge.countIngredients();
		fridge.removeIngredient("spiky melon");
		assertEquals(fridge.countIngredients(), count - 1);	
		assertEquals(hasIngredient("spiky melon"), false);
	}
	
	public void testAddDuplicateIngredient()
	{
		int initialCount = fridge.countIngredients();
		String ingredient = "spiky melon";
		assertEquals(fridge.addIngredient(ingredient), true);
		assertEquals(fridge.countIngredients(), initialCount + 1);
		assertEquals(fridge.addIngredient(ingredient), false);
		assertEquals(fridge.countIngredients(), initialCount + 1);
	}
	
	private boolean hasIngredient(String ingredient)
	{
		Collection<String> ingredients = fridge.getIngredients();
		for (String i : ingredients) {
			if (i.equals(ingredient)) {
				return true;
			}
		}
		
		return false;
	}
}
