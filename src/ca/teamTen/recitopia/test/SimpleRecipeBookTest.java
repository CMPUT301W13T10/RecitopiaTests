package ca.teamTen.recitopia.test;

import ca.teamTen.recitopia.RecipeBook;
import ca.teamTen.recitopia.SimpleRecipeBook;

public class SimpleRecipeBookTest extends RecipeBookTest {

	@Override
	protected RecipeBook createRecipeBook() {
		return new SimpleRecipeBook();
	}
	
	public void testPersistence() {
		// TODO: create recipe book here with memory-based IOFactory, test save/load.
	}
}
