package ca.teamTen.recitopia.test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import ca.teamTen.recitopia.CloudRecipeBook;
import ca.teamTen.recitopia.Recipe;
import ca.teamTen.recitopia.RecipeBook;


/**
 * Tests CloudRecipeBook.
 * 
 * This is really an integration test, not a unit test, as it talks to the DB.
 * 
 * Before running this test, you should probably clear the database by running
 * 
 * curl -XDELETE http://cmput301.softwareprocess.es:8080/cmput301w13t10/recipe/
 */
public class CloudRecipeBookTest extends RecipeBookTest
{

	@Override
	protected RecipeBook createRecipeBook() {
		return new CloudRecipeBook();
	}

	public void testRecipeURL() throws UnsupportedEncodingException {
		CloudRecipeBook cloudBook = (CloudRecipeBook)recipeBook;
		
		Recipe recipe = defaultRecipes.get(0);
		String url = cloudBook.buildRecipeURL(recipe);
		
		assertFalse(url.contains(" "));
		assertTrue(url.contains(URLEncoder.encode(recipe.getRecipeName(), "UTF-8")));
		assertTrue(url.contains(URLEncoder.encode(recipe.showAuthor(), "UTF-8")));
	}
}
