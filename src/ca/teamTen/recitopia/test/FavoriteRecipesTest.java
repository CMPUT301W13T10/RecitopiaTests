package ca.teamTen.recitopia.test;

import ca.teamTen.recitopia.FavoriteRecipe;
import ca.teamTen.recitopia.RecipeBook;

public class FavoriteRecipesTest extends RecipeBookTest {

	@Override
	protected RecipeBook createRecipeBook() {
		return new FavoriteRecipe();
	}
}
