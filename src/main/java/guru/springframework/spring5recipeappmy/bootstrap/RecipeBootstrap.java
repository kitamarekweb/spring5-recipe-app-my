package guru.springframework.spring5recipeappmy.bootstrap;

import guru.springframework.spring5recipeappmy.domain.*;
import guru.springframework.spring5recipeappmy.repositories.CategoryRepository;
import guru.springframework.spring5recipeappmy.repositories.RecipeRepository;
import guru.springframework.spring5recipeappmy.repositories.UnitOfMeasureReposiotory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureReposiotory unitOfMeasureReposiotory;

    public RecipeBootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureReposiotory unitOfMeasureReposiotory) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureReposiotory = unitOfMeasureReposiotory;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(getRecipes());
        log.debug("Loading bootstrap data");
    }

    private List<Recipe> getRecipes() {
        List<Recipe> recipes = new ArrayList<>(2);

        //get UOMs
        Optional<UnitOfMeasure> eachUomOptional = unitOfMeasureReposiotory.findByDescription("Each");
        if (!eachUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> tableSpoonUomOptional = unitOfMeasureReposiotory.findByDescription("Tablespoon");
        if (!tableSpoonUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> teaSpoonUomOptional = unitOfMeasureReposiotory.findByDescription("Teaspoon");
        if (!teaSpoonUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> dashUomOptional = unitOfMeasureReposiotory.findByDescription("Dash");
        if (!dashUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> pintUomOptional = unitOfMeasureReposiotory.findByDescription("Pint");
        if (!pintUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> cupsUomOptional = unitOfMeasureReposiotory.findByDescription("Cup");
        if (!cupsUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        //get optionals UOMs
        UnitOfMeasure eachUom = eachUomOptional.get();
        UnitOfMeasure tableSpoonUom = tableSpoonUomOptional.get();
        UnitOfMeasure teaSpoonUom = teaSpoonUomOptional.get();
        UnitOfMeasure pintUom = pintUomOptional.get();
        UnitOfMeasure cupsUom = cupsUomOptional.get();
        UnitOfMeasure dashUom = dashUomOptional.get();

        //get Categories
        Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");
        if (!americanCategoryOptional.isPresent()) {
            throw new RuntimeException("Expected Category Not Found");
        }

        Optional<Category> italianCategoryOptional = categoryRepository.findByDescription("Italian");
        if (!italianCategoryOptional.isPresent()) {
            throw new RuntimeException("Expected Category Not Found");
        }

        //get optionals cat
        Category americanCategory = americanCategoryOptional.get();
        Category italianCategory = italianCategoryOptional.get();

        //Yammy Guac
        Recipe guacRecipe = new Recipe();
        guacRecipe.setDescription("Perfect Guacamole");
        guacRecipe.setPrepTime(20);
        guacRecipe.setCookTime(0);
        guacRecipe.setDifficulty(Difficulty.EASY);
        guacRecipe.setDirections("1. sfasfasfsd fdf sf sfasds sdf" +
                "sadfasdf" +
                "\n" +
                "2. asdf" +
                "\n" +
                "3. sadfagsg");

        Notes guacNotes = new Notes();
        guacNotes.setRecipeNotes("sfafasg as asfa" +
                "\n" +
                "\n" +
                "" +
                "asdfasdfasdf");

//        guacNotes.setRecipe(guacRecipe); //bo przeniesione dzialanie do metody setNotes w Recipe.java
        guacRecipe.setNotes(guacNotes);

        //podmiana na metode: z guacRecipe.getIngredients().add na guacRecipe.addIngredient
        guacRecipe.addIngredient(new Ingredient("ripe avocados", new BigDecimal(2), eachUom));
        guacRecipe.addIngredient(new Ingredient("fresh lime juice or lemon juice", new BigDecimal(3), tableSpoonUom));
        guacRecipe.addIngredient(new Ingredient("frshly grated black paper", new BigDecimal(2), dashUom));

        guacRecipe.getCategories().add(americanCategory);
        guacRecipe.getCategories().add(italianCategory);

        //add to return list
        recipes.add(guacRecipe);

        return recipes;
    }
}
