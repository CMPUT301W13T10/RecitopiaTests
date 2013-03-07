package ca.teamTen.recitopia.test;

import ca.teamTen.recitopia.CloudRecipeBook;
import ca.teamTen.recitopia.RecipeBook;


public class CloudRecipeBookTest extends RecipeBookTest
{

	@Override
	protected RecipeBook createRecipeBook()
	{
		return new CloudRecipeBook();
	}

}
