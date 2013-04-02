package ca.teamTen.recitopia.test;

import java.util.ArrayList;
import java.util.Arrays;

import ca.teamTen.recitopia.models.CacheRecipeBook;
import ca.teamTen.recitopia.models.Recipe;
import ca.teamTen.recitopia.models.RecipeBook;

/**
 * jUnit tests for LocalCache RecipeBook.
 * 
 * Test conformance to RecipeBook interface as well as
 */
public class CacheRecipeBookTest extends RecipeBookTest
{	
	@Override
	protected RecipeBook createRecipeBook()
	{
		return new CacheRecipeBook(defaultRecipes.size());
	}

	/*
	 * Test that adding another recipe beyond the test data
	 * bumps out one of the old recipes.
	 */
	public void testSizeLimit() {
		addTestData();
		int recipeCount = defaultRecipes.size();
		
		Recipe newRecipe = new Recipe("A completely new recipe",
				new ArrayList<String>(Arrays.asList("spiky melon", "salt", "cooking oil")),
				"chop, fry, eat",
				"localCacheTest@test.com");
		
		recipeBook.addRecipe(newRecipe);
		
		for (int i = 1; i <= defaultRecipes.size(); i++) {
			Recipe recipe = defaultRecipes.get(recipeCount - i);
			
			if (i >= recipeCount) {
				assertEquals(recipeBook.query(recipe.getAuthor()).length, 0);
				// these recipes should not be present
			} else {
				assertEquals(recipeBook.query(recipe.getAuthor()).length, 1);
				// these recipes should be present
			}
		}
		
		assertEquals(recipeBook.query(newRecipe.getAuthor()).length, 1);
			// the new recipe should be present
	}
	
}
