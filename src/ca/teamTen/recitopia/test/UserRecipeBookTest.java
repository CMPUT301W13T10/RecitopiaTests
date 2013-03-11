package ca.teamTen.recitopia.test;

import ca.teamTen.recitopia.RecipeBook;
import ca.teamTen.recitopia.UserRecipeBook;

public class UserRecipeBookTest extends RecipeBookTest {

	@Override
	protected RecipeBook createRecipeBook() {
		return new UserRecipeBook();
	}

}
