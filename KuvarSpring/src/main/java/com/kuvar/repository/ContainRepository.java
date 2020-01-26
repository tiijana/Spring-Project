package com.kuvar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import model.Contain;
import model.Ingredient;

public interface ContainRepository extends JpaRepository<Contain, Integer>{
	
	@Query("select c.ingredient from Contain c where c.recipe.idRecipe = :idR")
	public List<Ingredient> getIngredientsForRecipe(@Param("idR") Integer idRecipe);

}
