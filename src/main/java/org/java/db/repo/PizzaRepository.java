package org.java.db.repo;

import java.util.List;

import org.java.db.pojo.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PizzaRepository extends JpaRepository<Pizza, Integer> {

	List<Pizza> findByNameContainingIgnoreCase(String value);

	@Query("SELECT DISTINCT p FROM Pizza p LEFT JOIN p.ingredients i WHERE p.name LIKE %:searchQuery% OR i.name LIKE %:searchQuery%")
	List<Pizza> findPizzasByPizzaNameOrIngredientName(@Param("searchQuery") String searchQuery);

}
