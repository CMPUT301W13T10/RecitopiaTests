package ca.teamTen.recitopia.test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Random;

import ca.teamTen.recitopia.CloudRecipeBook;
import ca.teamTen.recitopia.CacheRecipeBook;
import ca.teamTen.recitopia.Photo;
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
	private CacheRecipeBook cache;
	
	@Override
	protected RecipeBook createRecipeBook() {
		cache = new CacheRecipeBook(defaultRecipes.size() + 5);
		return new CloudRecipeBook(cache);
	}

	/*
	 * Test that all results from a search in the CloudRecipeBook
	 * should appear in the cache as well.
	 */
	public void testCaching() {
		addTestData();
		
		Recipe[] cloudResults = recipeBook.query("spiky");
		Recipe[] cacheResults = cache.query("spiky");
		
		assertEquals(cacheResults.length, cloudResults.length);
		for (Recipe recipe: cloudResults) {
			assertTrue(queryResultContains(cacheResults, recipe));
		}
	}
	
	/*
	 * Test that serializing/deserializing photos works
	 */
	public void testPhotoSerialization(){
		byte[] fakePhoto = generateFakePhoto();
		defaultRecipes.get(0).addPhoto(new Photo(fakePhoto));
		Recipe recipe = defaultRecipes.get(0);
		CloudRecipeBook cloudbook = (CloudRecipeBook)recipeBook;
		String recipeAsJSON = cloudbook.recipeToJson(recipe);
		
		Recipe recipe2 = cloudbook.recipeFromJson(recipeAsJSON);
		byte[] fakePhoto2 = recipe2.getPhotos()[0].getByteImage();
		
		assertTrue(Arrays.equals(fakePhoto, fakePhoto2));
		assertTrue(fakePhoto.length == fakePhoto2.length);
		
	}
	
	/*
	 * Test that CloudRecipeBook ignores Recipe.isPublished
	 */
	public void testIsPublishedIgnored() {
		// isPublished should not be added to the JSON
		Recipe recipe = defaultRecipes.get(0);
		CloudRecipeBook cloudBook = (CloudRecipeBook)recipeBook;
		assertFalse(cloudBook.recipeToJson(recipe).contains("isPublished"));
		
		// after deserialization, isPublished should always be true
		Recipe roundTripped = cloudBook.recipeFromJson(cloudBook.recipeToJson(recipe));
		assertTrue(roundTripped.publishRecipe());
	}
	
	public void testQueryResultsArePublished() {
		addTestData();
		Recipe[] recipes = recipeBook.query("spiky");
		for (Recipe recipe: recipes) {
			assertTrue(recipe.publishRecipe());
		}
	}
	
	/*
	 * Test that the recipe urls are based on the recipe name and author,
	 * since this pair should be unique among recipes.
	 */
	public void testRecipeURL() throws UnsupportedEncodingException {
		CloudRecipeBook cloudBook = (CloudRecipeBook)recipeBook;
		
		Recipe recipe = defaultRecipes.get(0);
		String url = cloudBook.buildRecipeURL(recipe);
		
		assertFalse(url.contains(" "));
		assertTrue(url.contains(URLEncoder.encode(recipe.getRecipeName(), "UTF-8")));
		assertTrue(url.contains(URLEncoder.encode(recipe.getAuthor(), "UTF-8")));
	}
	
	/*
	 * Generates a fake Photo
	 */
	protected byte[] generateFakePhoto(){
		byte[] fakePhoto = new byte[10000];
		Random random = new Random();
		random.nextBytes(fakePhoto);
		return fakePhoto;
	}
}
