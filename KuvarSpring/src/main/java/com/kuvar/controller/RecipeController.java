package com.kuvar.controller;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kuvar.repository.CategoryRepository;
import com.kuvar.repository.ContainRepository;
import com.kuvar.repository.FavouriteCategoryRepository;
import com.kuvar.repository.IngredientRepository;
import com.kuvar.repository.PictureRepository;
import com.kuvar.repository.RecipeRepository;
import com.kuvar.repository.UserRepository;

import model.Category;
import model.Contain;
import model.Favourite_category;
import model.Ingredient;
import model.Picture;
import model.Recipe;
import model.User;

@Controller
@RequestMapping(value = "/recipeController")
public class RecipeController {

	@Autowired
	RecipeRepository rr;

	@Autowired
	CategoryRepository cr;

	@Autowired
	PictureRepository pr;

	@Autowired
	UserRepository ur;

	@Autowired
	IngredientRepository ir;

	@Autowired
	ContainRepository conr;
	
	@Autowired
	FavouriteCategoryRepository fcr;

	@RequestMapping(value = "getCategoriesAndIngredients", method = RequestMethod.GET)
	public String getCategories(HttpServletRequest request) {
		List<Category> categories = cr.findAll();
		List<Ingredient> ingredients = ir.findAll();
		request.getSession().setAttribute("allCategories", categories);
		request.getSession().setAttribute("allIngredients", ingredients);

		return "/users/addNewRecipe";
	}

	@RequestMapping(value = "saveNewRecipe", method = RequestMethod.POST)
	public String saveNewRecipe(Principal p, HttpServletRequest request, String name, String description,
			Integer selectedCategory, String path) {
		String username = p.getName();
		User user = ur.findByUsername(username);
		Date date = new Date(); // contain current date
		Category category = cr.findById(selectedCategory).get();
		Recipe recipe = new Recipe();
		if (user != null) {
			recipe.setName(name);
			recipe.setDescription(description);
			recipe.setCategory(category);
			recipe.setDate(date);
			recipe.setUser(user);
			rr.save(recipe);
		}

		request.getSession().setAttribute("addedRecipe", recipe);
		return "/users/addNewRecipe";
	}
	
	@RequestMapping(value = "addPictureForRecipe", method = RequestMethod.POST)
	public String addPictureForRecipe(HttpServletRequest request, String path) {
		Recipe recipe = (Recipe) request.getSession().getAttribute("addedRecipe");
		Picture picture = new Picture();
		picture.setPath(path);
		picture.setRecipe(recipe);
		pr.save(picture);
		return "/users/addNewRecipe";
	}

	@RequestMapping(value = "addIngredients", method = RequestMethod.POST)
	public String addIngredients(HttpServletRequest request, String name, String amount, Integer selectedIngredient) {
		Recipe forRecipe = (Recipe) request.getSession().getAttribute("addedRecipe");
		if (selectedIngredient != null) {
			Ingredient existIng = ir.findById(selectedIngredient).get();
			Contain contain = new Contain();
			contain.setAmount(amount);
			contain.setIngredient(existIng);
			contain.setRecipe(forRecipe);
			conr.save(contain);
		} else {
			Ingredient ingr = new Ingredient();
			ingr.setName(name);
			Ingredient ingredient = ir.save(ingr);
			Contain contain = new Contain();
			contain.setAmount(amount);
			contain.setIngredient(ingredient);
			contain.setRecipe(forRecipe);
			conr.save(contain);
		}

		return "/users/addNewRecipe";
	}
	
	@RequestMapping(value = "allCategories", method = RequestMethod.GET) // iskoristimo je i kod allRecipes
	public String getAllCategories(HttpServletRequest request) {
		List<Category> categories = cr.findAll();
		request.getSession().setAttribute("categories", categories);
		return "/searchRecipe";
	}
	
	@RequestMapping(value = "searchByIngredientAndCategory", method = RequestMethod.GET)
	public String searchByIngredientAndCategory(HttpServletRequest request, Integer selectedCategory, String name) {
		Ingredient ingredient = ir.findByName(name);
		List<Recipe> recipes = rr.getRecipesByCategoryAndIngredient(ingredient.getIdIngredient(), selectedCategory);	
		request.getSession().setAttribute("recipesByCatAndIng", recipes);
		return "/searchRecipe";
	}
	
	
//	@RequestMapping(value = "getRecipeContent", method = RequestMethod.GET)
//	public String getRecipeContent(HttpServletRequest request, Integer idRec) {
//		Recipe recipe = rr.findById(idRec).get();
//		List<Picture> pictures = recipe.getPictures();
//		request.getSession().setAttribute("recipePictures", pictures);
//		
//		return "/users/recipeContent";
//	}
	
	@RequestMapping(value = "getAllRecipesCategories", method = RequestMethod.GET)
	public String getAllRecipes(HttpServletRequest request) {
		List<Category> catgs = cr.findAll();
		request.getSession().setAttribute("allCategories", catgs);
		return "/users/allRecipes";
	}
	
	@RequestMapping(value = "showRecipesForCategory", method = RequestMethod.GET)
	public String showRecipesForCategory(HttpServletRequest request, Integer categoryId) {
		Category category = cr.findById(categoryId).get();
		List<Recipe> recipes = rr.findByCategory(category);
		request.getSession().setAttribute("recipesForCategory", recipes);
		return "/users/allRecipes";
	}
	
	@RequestMapping(value = "showUsersFavouriteCategories", method = RequestMethod.GET)
	public String showUsersFavouriteCategories(Principal p, HttpServletRequest request, Integer recipeID) {
		String username = p.getName();
		User user = ur.findByUsername(username);
		List<Favourite_category> favs = fcr.findByUser(user);
		request.getSession().setAttribute("favsCategories", favs);
		Recipe recipe = rr.findById(recipeID).get();
		request.getSession().setAttribute("recipeForFavs", recipe);
		return "/users/addRecipeToFavourite";
	}
	
	@RequestMapping(value = "addToFavouriteCategory", method = RequestMethod.POST)
	public String addToFavouriteCategory(HttpServletRequest request, Integer selectedFavCategory) {
		Recipe recipe = (Recipe) request.getSession().getAttribute("recipeForFavs");
		Favourite_category favC = fcr.findById(selectedFavCategory).get();
		favC.getRecipes().add(recipe);
		System.out.println(favC.getRecipes().size());
		recipe.getFavouriteCategories().add(favC);
		System.out.println(recipe.getFavouriteCategories().size());
		fcr.saveAndFlush(favC);
		rr.saveAndFlush(recipe);
		return "/users/allRecipes";
	}
	
	
	

}
