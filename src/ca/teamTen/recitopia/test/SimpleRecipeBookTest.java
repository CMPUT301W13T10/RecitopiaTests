package ca.teamTen.recitopia.test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import ca.teamTen.recitopia.Recipe;
import ca.teamTen.recitopia.RecipeBook;
import ca.teamTen.recitopia.SimpleRecipeBook;
import ca.teamTen.recitopia.IOFactory;

public class SimpleRecipeBookTest extends RecipeBookTest {

	@Override
	protected RecipeBook createRecipeBook() {
		return new SimpleRecipeBook();
	}
	
	// create recipe book with memory-based IOFactory to test save/load.
	public void testPersistence() {
		recipeBook = new SimpleRecipeBook(new BufferIOFactory());
		addTestData();
		
		// save and load data
		recipeBook.save();
		((SimpleRecipeBook)recipeBook).load();
		
		// now make sure all of the test data recipes are present
		Recipe[] recipes = recipeBook.query("");
		for (Recipe recipe: defaultRecipes) {
			assertTrue(queryResultContains(recipes, recipe));
		}
	}
	
	/*
	 * SimpleRecipeBook.IOFactory that reads/writes to memory.
	 */
	class BufferIOFactory implements IOFactory {
		private ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		
		@Override
		public InputStream getInputStream() throws IOException {
			return new ByteArrayInputStream(outStream.toByteArray());
		}

		@Override
		public OutputStream getOutputStream() throws IOException {
			return outStream;
		}
		
	}
}
