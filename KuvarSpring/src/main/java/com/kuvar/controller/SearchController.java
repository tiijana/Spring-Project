package com.kuvar.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kuvar.repository.CategoryRepository;
import com.kuvar.repository.ContainRepository;
import com.kuvar.repository.IngredientRepository;
import com.kuvar.repository.RecipeRepository;

import model.Category;
import model.Ingredient;
import model.Picture;
import model.Recipe;
import model.User;

@Controller
@RequestMapping(value = "/searchController")
public class SearchController {
	
	@Autowired
	CategoryRepository cr;
	@Autowired
	IngredientRepository ir;
	@Autowired
	RecipeRepository rr;
	@Autowired
	ContainRepository conr;
	
	@RequestMapping(value = "allCategoriesAndIngredients", method = RequestMethod.GET) 
	public String getAllCategories(HttpServletRequest request) {
		List<Category> categories = cr.findAll();
		request.getSession().setAttribute("categories", categories);
		List<Ingredient> ingredients = ir.findAll();
		request.getSession().setAttribute("ingredients", ingredients);
		return "/searchRecipe";
	}
	
	@RequestMapping(value = "searchByIngredient", method = RequestMethod.GET)
	public String searchByIngredient(HttpServletRequest request, Integer selectedIngredient) {
		List<Recipe> recipes = rr.getRecipesByIngredient(selectedIngredient);
		request.getSession().setAttribute("recipesByIngredient", recipes);
		return "/searchRecipe";
	}
	
	@RequestMapping(value = "searchByCategory", method = RequestMethod.GET)
	public String searchByCategory(HttpServletRequest request, Integer selectedCategory) {
		List<Recipe> recipes = rr.getRecipesByCategory(selectedCategory);
		request.getSession().setAttribute("recipesByCategory", recipes);
		return "/searchRecipe";
	}
	
	@RequestMapping(value = "getRecipeContent", method = RequestMethod.GET)
	public String getRecipeContent(HttpServletRequest request, Integer idRec) {
		Recipe recipe = rr.findById(idRec).get();
		List<Ingredient> ingredients = conr.getIngredientsForRecipe(recipe.getIdRecipe());
		List<Picture> pictures = recipe.getPictures();
		User user = recipe.getUser();
		request.getSession().setAttribute("recipeIngredients", ingredients);;
		request.getSession().setAttribute("recipePictures", pictures);
		request.getSession().setAttribute("recipeCon", recipe);
		request.getSession().setAttribute("userInfo", user);
		return "/users/recipeContent";
	}

}
