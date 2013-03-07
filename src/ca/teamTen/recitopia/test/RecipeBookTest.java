package ca.teamTen.recitopia.test;

import java.util.ArrayList;
import java.util.Arrays;

import ca.teamTen.recitopia.Recipe;
import ca.teamTen.recitopia.RecipeBook;
import junit.framework.TestCase;

/**
 * abstract RecipeBook junit tests
 *
 * TODO: test what happens when adding another recipe with the same
 * author and title.
 */
public abstract class RecipeBookTest extends TestCase
{

	protected abstract RecipeBook createRecipeBook();
	
	protected RecipeBook recipeBook;
	
	/**
	 * Stores recipes that have been added with addTestData();
	 */
	protected ArrayList<Recipe> defaultRecipes;
	
	protected void setUp() throws Exception
	{
		super.setUp();
		defaultRecipes = new ArrayList<Recipe>();
		generateDefaultRecipes();
		recipeBook = createRecipeBook();
		addTestData();
	}

	protected void tearDown() throws Exception
	{
		super.tearDown();
	}
	
	public void testQueryByTitle()
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
	
	public void testUpdatesRecipes()
	{
		addTestData();
		Recipe oldRecipe = defaultRecipes.get(0);
		String newInstructions = "these are not the same instructions";
		Recipe modifiedRecipe = new Recipe(oldRecipe.getRecipeName(),
				oldRecipe.showIngredients(), newInstructions,
				oldRecipe.showAuthor());
		
		recipeBook.addRecipe(modifiedRecipe);
		
		Recipe[] results = recipeBook.query(null, modifiedRecipe.getRecipeName(),
				modifiedRecipe.getRecipeName());
		assertEquals(results.length, 1);
		assertEquals(results[0].showCookingInstructions(), newInstructions);
		
	}
	
	protected void generateDefaultRecipes()
	{
		defaultRecipes.add(new Recipe("Spiky Melon Salad",
			new ArrayList<String>(Arrays.asList("spiky melon", "lettuce", "cucumber")),
			"Cube the melon, chop the lettuce and cumbers. Mix in bowl and enjoy",
			"alex@test.com"));
		defaultRecipes.add(new Recipe("Spiky Melon Soup",
			new ArrayList<String>(Arrays.asList("spiky melon", "cream", "spices")),
			"mix, heat and enjoy",
			"zhexin@test.com"));
		defaultRecipes.add(new Recipe("Spiky Melon Shake",
			new ArrayList<String>(Arrays.asList("spiky melon", "cream", "sugar")),
			"mix, blend and enjoy",
			"osipovas@test.com"));
		defaultRecipes.add(new Recipe("Spiky Melon Fries",
			new ArrayList<String>(Arrays.asList("spiky melon", "salt", "cooking oil")),
			"chop, fry, eat",
			"zou@test.com"));
	}
	
	protected void addTestData()
	{		
		for (Recipe recipe: defaultRecipes) {
			recipeBook.addRecipe(recipe);
		}
	}
}
