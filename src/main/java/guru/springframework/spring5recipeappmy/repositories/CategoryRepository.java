package guru.springframework.spring5recipeappmy.repositories;

import guru.springframework.spring5recipeappmy.domain.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long> {

    Optional<Category> findByDescription(String description);
}
