package org.java.controller;

import java.util.List;

import org.java.db.pojo.Ingredient;
import org.java.db.pojo.Pizza;
import org.java.db.serv.IngredientService;
import org.java.db.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
public class IngredientController {

	@Autowired
	private PizzaService pizzaService;

	@Autowired
	private IngredientService ingredientService;

	@GetMapping("/ingredients")
	public String ingredientList(Model model) {

		List<Ingredient> ingredients = ingredientService.findAll();

		model.addAttribute("ingredientsList", ingredients);

		return "index-ingredients-list";
	}

	@GetMapping("/ingredient/create")
	public String createIngredient(Model model) {

		Ingredient ingr = new Ingredient();

		model.addAttribute("ingredient", ingr);
		return "ingredient-form";
	}

	@PostMapping("/ingredient/create")
	public String saveIngredient(Model model, @Valid @ModelAttribute Ingredient ingredient,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("ingredient", ingredient);
			return "ingredient-form";
		}

		Ingredient ingr = new Ingredient(ingredient.getName());

		ingredientService.save(ingr);

		return "redirect:/ingredients";

	}

	@PostMapping("/ingredients/delete/{id}")
	public String deleteInfredient(Model model, @PathVariable int id, RedirectAttributes redirectAtr) {

		Ingredient ingr = ingredientService.findById(id);

		List<Pizza> pizzas = ingr.getPizzas();

		if (pizzas.size() > 0) {
			for (Pizza pizza : pizzas) {

				pizza.getIngredients().remove(ingr);
			}
		}

		ingredientService.delete(ingr);

		redirectAtr.addFlashAttribute("ingredientDeleted", ingr);

		return "redirect:/ingredients";

	}

}
