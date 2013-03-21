package ca.teamTen.recitopia.test;


import java.util.ArrayList;
import java.util.Arrays;

import com.google.gson.Gson;

import android.util.Log;

import ca.teamTen.recitopia.Photo;
import ca.teamTen.recitopia.Recipe;
import junit.framework.TestCase;


/*
 * todo: add more comments
 */
public class RecipeTests extends TestCase
{

	private Recipe recipe;
	private String name = "spiky melon salad";
	private String ingredients[] = {"spiky melon", "spices", "salad dresing"};
	private String instructions = "make a salad";
	private String author = "Clark Kent";
	
	protected void setUp() throws Exception
	{
		super.setUp();

		recipe = new Recipe(name, new ArrayList<String>(Arrays.asList(ingredients)), instructions, author);
	}

	protected void tearDown() throws Exception
	{
		super.tearDown();
	}
	
	public void testAddPhoto() {
		assertEquals(recipe.getPhotos().length, 0);
		recipe.addPhotos(new Photo());
		assertEquals(recipe.getPhotos().length, 1);
	}
	
	public void testGetters() {
		assertEquals(recipe.getRecipeName(), name);
		assertEquals(recipe.showAuthor(), author);
		assertEquals(recipe.showCookingInstructions(), instructions);

        // test ingredients
        ArrayList<String> resultIngredients = recipe.showIngredients();
        assertEquals(resultIngredients.size(), ingredients.length);
        for (int i = 0; i < resultIngredients.size(); i++) {
            assertEquals(resultIngredients.get(i), ingredients[i]);
        }
	}
	
	public void testEquals() {
		assertTrue(recipe.equalData(recipe));
		
		Recipe other = new Recipe(name, new ArrayList<String>(Arrays.asList(ingredients)), instructions, author);
		assertTrue(other.equalData(recipe));
		
		other = new Recipe("wrong " + name, new ArrayList<String>(Arrays.asList(ingredients)), instructions, author);
		assertTrue(!other.equalData(recipe));
		
		other = new Recipe(name, null, instructions, author);
		assertTrue(!other.equalData(recipe));
		
		other = new Recipe(name, new ArrayList<String>(Arrays.asList(ingredients)), "wrong " + instructions, author);
		assertTrue(!other.equalData(recipe));
		
		other = new Recipe(name, new ArrayList<String>(Arrays.asList(ingredients)), instructions, "wrong " + author);
		assertTrue(!other.equalData(recipe));
	}

	public void testToString() {
		assertTrue(recipe.toString().contains(name));
		assertTrue(recipe.toString().contains(author));
		assertTrue(recipe.toString().contains(instructions));
		
		Log.v("TOSTRING", recipe.toString());
		
		//Test for containing each ingredient
		for(String s : new ArrayList<String>(Arrays.asList(ingredients)))
			assertTrue(recipe.toString().contains(s));
	}
	
	public void testJSONRoundtrip() {
		Gson gson = new Gson();
		Recipe result = gson.fromJson(gson.toJson(recipe), Recipe.class);
		assertTrue(recipe.equalData(result));
	}
}
