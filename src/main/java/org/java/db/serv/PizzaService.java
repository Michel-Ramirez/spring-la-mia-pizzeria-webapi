package org.java.db.serv;

import java.util.List;
import java.util.Optional;

import org.java.db.pojo.Ingredient;
import org.java.db.pojo.Pizza;
import org.java.db.repo.IngredientRepository;
import org.java.db.repo.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PizzaService {

	@Autowired
	private PizzaRepository pizzaRepository;

	@Autowired
	private IngredientRepository ingredientRepository;

	public List<Pizza> findAll() {
		return pizzaRepository.findAll();
	}

	public Optional<Pizza> findById(int id) {
		return pizzaRepository.findById(id);
	}

	public List<Pizza> findByPizzaNameOrIngredient(String value) {
		return pizzaRepository.findPizzasByPizzaNameOrIngredientName(value);
	}

	public List<Ingredient> getIngredientsInPizza(int id) {
		return ingredientRepository.findIngredientsByPizzaId(id);
	}

	public void save(Pizza pizza) {
		pizzaRepository.save(pizza);
	}

	public void delete(Pizza pizza) {

		pizzaRepository.delete(pizza);
	}

}
