package ca.teamTen.recitopia.test;

import java.util.ArrayList;
import java.util.Arrays;

import ca.teamTen.recitopia.Recipe;
import ca.teamTen.recitopia.RecipeBook;
import junit.framework.TestCase;


public abstract class RecipeBookTest extends TestCase
{

	protected abstract RecipeBook createRecipeBook();
	
	protected RecipeBook recipeBook;
	
	protected void setUp() throws Exception
	{
		super.setUp();
		recipeBook = createRecipeBook();
	}

	protected void tearDown() throws Exception
	{
		super.tearDown();
	}
	
	protected void testQueryByTitle()
	{
		addTestData();
		assertEquals(recipeBook.query(null, "Spiky", null).length, 2);
		assertEquals(recipeBook.query(null, "Soup", null).length, 1);

		// search should be case-insensitive
		assertEquals(recipeBook.query(null, "spiky", null).length, 2);

		// don't search user field
		assertEquals(recipeBook.query(null, "alex", null).length, 0);
		
		// don't search ingredients field
		assertEquals(recipeBook.query(null, "lettuce", null).length, 0);
	}
	
	protected void addTestData()
	{
		recipeBook.addRecipe(new Recipe("Spiky Melon Salad",
			new ArrayList<String>(Arrays.asList("spiky melon", "lettuce", "cucumber")),
			"Cube the melon, chop the lettuce and cumbers. Mix in bowl and enjoy",
			"alex@test.com"));
		recipeBook.addRecipe(new Recipe("Spiky Melon Soup",
			new ArrayList<String>(Arrays.asList("spiky melon", "cream", "spices")),
			"mix, heat and enjoy",
			"zhexin@test.com"));
	}
}
