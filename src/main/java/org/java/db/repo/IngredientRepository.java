package org.java.db.repo;

import java.util.List;

import org.java.db.pojo.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {
	@Query("SELECT i FROM Ingredient i JOIN i.pizzas p WHERE p.id = :pizzaId")
	List<Ingredient> findIngredientsByPizzaId(@Param("pizzaId") int pizzaId);
}
