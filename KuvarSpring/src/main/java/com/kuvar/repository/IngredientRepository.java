package com.kuvar.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import model.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {
	
	public Ingredient findByName(String name);

}
