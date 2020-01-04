package guru.springframework.spring5recipeappmy.repositories;

import guru.springframework.spring5recipeappmy.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
