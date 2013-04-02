package ca.teamTen.recitopia.test;


import java.util.ArrayList;
import java.util.Arrays;

import com.google.gson.Gson;

import android.util.Log;

import ca.teamTen.recitopia.models.Recipe;
import junit.framework.TestCase;


/*
 * Recipe class unit tests
 */
public class RecipeTests extends TestCase
{

	// data used in the recipe under test
	private Recipe recipe;
	private String name = "spiky melon salad";
	private String ingredients[] = {"spiky melon", "spices", "salad dresing"};
	private String instructions = "make a salad";
	private String author = "Clark Kent";
	
	/*
	 * Create a recipe with our test data before each test.
	 */
	protected void setUp() throws Exception
	{
		super.setUp();
		recipe = new Recipe(name, new ArrayList<String>(Arrays.asList(ingredients)), instructions, author);
	}

	protected void tearDown() throws Exception
	{
		super.tearDown();
	}
	
	/*
	 * Test that the constructor for GSON (Recipe(boolean isPublished))
	 * works as expected.
	 */
	public void testPublishingConstructor()
	{
		recipe = new Recipe(true);
		assertTrue(recipe.publishRecipe());
		
		recipe = new Recipe(false);
		assertFalse(recipe.publishRecipe());
	}
	
	/*
	 * Test getters give data matching the constructor.
	 */
	public void testGetters() {
		assertEquals(recipe.getRecipeName(), name);
		assertEquals(recipe.getAuthor(), author);
		assertEquals(recipe.getCookingInstructions(), instructions);

        // test ingredients
        ArrayList<String> resultIngredients = recipe.getIngredients();
        assertEquals(resultIngredients.size(), ingredients.length);
        for (int i = 0; i < resultIngredients.size(); i++) {
            assertEquals(resultIngredients.get(i), ingredients[i]);
        }
	}
	
	/*
	 * Test the equalData() method.
	 */
	public void testEquals() {
		// recipe should equal itself
		assertTrue(recipe.equalData(recipe));
		
		// recipe should equal another instance with the same data
		Recipe other = new Recipe(name, new ArrayList<String>(Arrays.asList(ingredients)), instructions, author);
		assertTrue(other.equalData(recipe));
		
		// recipe should not equal another instance with a different name
		other = new Recipe("wrong " + name, new ArrayList<String>(Arrays.asList(ingredients)), instructions, author);
		assertTrue(!other.equalData(recipe));
		
		// recipe should not equal if ingredients are different
		other = new Recipe(name, null, instructions, author);
		assertTrue(!other.equalData(recipe));
		
		// recipe should not equal if instructions are different
		other = new Recipe(name, new ArrayList<String>(Arrays.asList(ingredients)), "wrong " + instructions, author);
		assertTrue(!other.equalData(recipe));
		
		// recipe should not equal if author is different
		other = new Recipe(name, new ArrayList<String>(Arrays.asList(ingredients)), instructions, "wrong " + author);
		assertTrue(!other.equalData(recipe));
	}

	/*
	 * Test that appropriate info is included in the result of
	 * toString().
	 */
	public void testToString() {
		assertTrue(recipe.toString().contains(name));
		assertTrue(recipe.toString().contains(author));
		assertTrue(recipe.toString().contains(instructions));
		
		Log.v("TOSTRING", recipe.toString());
		
		//Test for containing each ingredient
		for(String s : new ArrayList<String>(Arrays.asList(ingredients)))
			assertTrue(recipe.toString().contains(s));
	}
	
	/*
	 * Test that recipe -> json -> recipe is equal with itself
	 */
	public void testJSONRoundtrip() {
		Gson gson = new Gson();
		Recipe result = gson.fromJson(gson.toJson(recipe), Recipe.class);
		assertTrue(recipe.equalData(result));
	}
}
