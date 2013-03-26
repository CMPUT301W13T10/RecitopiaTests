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

	/*
	 * Create a new fridge before each test
	 */
	protected void setUp() throws Exception
	{
		super.setUp();
		fridge = new Fridge();
	}
	
	protected void tearDown() throws Exception
	{
		super.tearDown();
	}
	
	/*
	 * Test that adding a single ingredient works.
	 */
	public void testAddSingleIngredient()
	{
		int initialCount = fridge.countIngredients();
		assertEquals(fridge.addIngredient("spiky melon"), true);
		assertEquals(fridge.countIngredients(), initialCount + 1);

		assertEquals(hasIngredient("spiky melon"), true);
	}
	
	/*
	 * Test that ingredients can be removed.
	 */
	public void testRemoveIngredient()
	{
		assertEquals(fridge.addIngredient("spiky melon"), true);
		int count = fridge.countIngredients();
		fridge.removeIngredient("spiky melon");
		assertEquals(fridge.countIngredients(), count - 1);	
		assertEquals(hasIngredient("spiky melon"), false);
	}
	
	/*
	 * Test that duplicate ingredients are removed.
	 */
	public void testAddDuplicateIngredient()
	{
		int initialCount = fridge.countIngredients();
		String ingredient = "spiky melon";
		assertEquals(fridge.addIngredient(ingredient), true);
		assertEquals(fridge.countIngredients(), initialCount + 1);
		assertEquals(fridge.addIngredient(ingredient), false);
		assertEquals(fridge.countIngredients(), initialCount + 1);
	}
	
	/*
	 * Utility method to check whether the fridge has an ingredient
	 */
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
