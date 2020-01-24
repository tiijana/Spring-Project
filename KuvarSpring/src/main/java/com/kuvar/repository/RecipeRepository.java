package com.kuvar.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import model.Category;
import model.Recipe;
import model.User;

public interface RecipeRepository extends JpaRepository<Recipe, Integer> {
	
	public List<Recipe> findByDate(Date date);
	
	public List<Recipe> findByUser(User user);
	
	@Query("select r from Recipe r inner join r.contains c where c.ingredient.idIngredient = :idIngr and r.category.idCategory = :idCat")
	public List<Recipe> getRecipesByCategoryAndIngredient(@Param("idIngr") Integer idIngredient, @Param("idCat") Integer idCategory);
	
	public List<Recipe> findByCategory(Category category);
	

}
