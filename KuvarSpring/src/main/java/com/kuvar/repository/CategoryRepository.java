package com.kuvar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{
	
}
