package org.java.restcontroller;

import java.util.List;
import java.util.Optional;

import org.java.db.pojo.Ingredient;
import org.java.db.pojo.Offert;
import org.java.db.pojo.Pizza;
import org.java.db.serv.IngredientService;
import org.java.db.serv.OffertService;
import org.java.db.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/v1.0/pizzas")
public class PizzaRestController {

	@Autowired
	PizzaService pizzaService;

	@Autowired
	OffertService offertService;

	@Autowired
	IngredientService ingredientService;

	// INVIA UN JSON CON TUTTE LE PIZZE E LE SUE RELAZIONI ALLA INDEX
	@GetMapping
	public ResponseEntity<List<Pizza>> getIndex(@RequestParam(required = false) String query) {

		List<Pizza> pizzas = query != null ? pizzaService.findByPizzaNameOrIngredient(query) : pizzaService.findAll();

		return new ResponseEntity<>(pizzas, HttpStatus.OK);
	}

	// DETTAGLIO DELLA PIZZA, LA ROTTA E' FORNITA DA REQUESTMAPPIN INIZIALE, NEL GET
	// MAPPING SPECIFICO SOLO IL PARAMETRO, IN QUESTO CASO ID
	@GetMapping("{id}")
	public ResponseEntity<Pizza> getPizza(@PathVariable int id) {

		Pizza pizza = pizzaService.findById(id).get();

		if (pizza == null)
			return new ResponseEntity<>(pizza, HttpStatus.NOT_FOUND);

		return new ResponseEntity<>(pizza, HttpStatus.OK);

	}

	// API CREAZIONE DELLA PIZZA
	@PostMapping("pizza/create")
	public ResponseEntity<Pizza> createPizza(@RequestBody Pizza pizza) {

		try {
			pizzaService.save(pizza);
			return new ResponseEntity<>(pizza, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(pizza, HttpStatus.BAD_REQUEST);
		}
	}

	// MODIFICA DELLE PIZZA
	@PutMapping("pizza/edit/{id}")
	public ResponseEntity<Pizza> editPizza(@PathVariable int id, @RequestBody Pizza newPizza) {

		Pizza pizza = pizzaService.findById(id).get();

		try {
			pizza.setName(newPizza.getName());
			pizza.setDescription(newPizza.getDescription());
			pizza.setPrice(newPizza.getPrice());
			pizza.setIngredients(newPizza.getIngredients());

			pizzaService.save(pizza);
			return new ResponseEntity<>(pizza, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(pizza, HttpStatus.BAD_REQUEST);
		}
	}

	// ELIMINAZIONE DI UNA PIZZA
	@DeleteMapping("/pizza/delete/{id}")
	public ResponseEntity<Pizza> deletePizza(@PathVariable int id) {

		Optional<Pizza> pizzaOpt = pizzaService.findById(id);

		if (pizzaOpt.isEmpty())
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

		Pizza pizza = pizzaOpt.get();

		pizzaService.delete(pizza);
		return new ResponseEntity<>(HttpStatus.OK);

	}

	// API CREAZIONE DELL'OFFERTA (ID = ID DELLA PIZZA ALLA QUALE ASSOCIARE
	// L'OFFERTA)
	@PostMapping("/offert/create/{id}")
	public ResponseEntity<Offert> createOffert(@RequestBody Offert offert, @PathVariable int id) {

		Pizza pizza = pizzaService.findById(id).get();

		try {
			Offert of = new Offert(offert.getTitle(), offert.getStartDateOffert(), offert.getEndDateOffert(), pizza);
			offertService.save(of);
			return new ResponseEntity<>(of, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(offert, HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/offert/delete/{id}")
	public ResponseEntity<Offert> deleteOffert(@PathVariable int id) {

		Offert of = offertService.findById(id);

		if (of == null)
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

		offertService.delete(of);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	// CREAZIONE DI INGREDIENTI
	@PostMapping("/ingredient/create")
	public ResponseEntity<Ingredient> createIngredient(@RequestBody Ingredient ingredient) {

		ingredientService.save(ingredient);
		return new ResponseEntity<>(ingredient, HttpStatus.OK);

	}

	// ELIMINAZIONE INGREDIENTI
	@DeleteMapping("/ingredient/delete/{id}")
	public ResponseEntity<Ingredient> deleteIngresient(@PathVariable int id) {

		Ingredient ing = ingredientService.findById(id);

		if (ing == null)
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

		List<Pizza> pizzas = ing.getPizzas();

		if (pizzas.size() > 0) {
			for (Pizza pizza : pizzas) {

				pizza.getIngredients().remove(ing);
			}
		}

		ingredientService.delete(ing);
		return new ResponseEntity<>(HttpStatus.OK);

	}

}
