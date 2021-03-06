package guru.springframework.spring5recipeappmy.controllers;

import guru.springframework.spring5recipeappmy.domain.Category;
import guru.springframework.spring5recipeappmy.domain.UnitOfMeasure;
import guru.springframework.spring5recipeappmy.repositories.CategoryRepository;
import guru.springframework.spring5recipeappmy.repositories.UnitOfMeasureReposiotory;
import guru.springframework.spring5recipeappmy.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Slf4j
@Controller
public class IndexController {

//    private CategoryRepository categoryRepository;
//    private UnitOfMeasureReposiotory unitOfMeasureReposiotory;
//
//    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureReposiotory unitOfMeasureReposiotory) {
//        this.categoryRepository = categoryRepository;
//        this.unitOfMeasureReposiotory = unitOfMeasureReposiotory;
//    }

    public final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"","/", "/index"})
    public String getIndexPage(Model model) {
        log.debug("Getting index page");

//        Optional<Category> categoryOptional = categoryRepository.findByDescription("American");
//        Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureReposiotory.findByDescription("Teaspoon");
//        System.out.println("Cat Id is: " + categoryOptional.get().getId());
//        System.out.println("UOM Id is: " + unitOfMeasureOptional.get().getId());

        model.addAttribute("recipes", recipeService.getRecipes());

        return "index";
    }
}
