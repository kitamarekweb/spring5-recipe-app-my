package guru.springframework.spring5recipeappmy.services;

import guru.springframework.spring5recipeappmy.domain.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();
}
