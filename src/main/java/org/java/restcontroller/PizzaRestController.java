package org.java.restcontroller;

import java.util.List;

import org.java.db.pojo.Pizza;
import org.java.db.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/v1.0/pizzas")
public class PizzaRestController {

	@Autowired
	PizzaService pizzaService;

	// INVIA UN JSON CON TUTTE LE PIZZE E LE SUE RELAZIONI ALLA INDEX
	@GetMapping
	public ResponseEntity<List<Pizza>> getIndex() {

		List<Pizza> pizzas = pizzaService.findAll();

		return new ResponseEntity<>(pizzas, HttpStatus.OK);
	}

	// DETTAGLIO DELLA PIZZA, LA ROTTA E' FORNITA DA REQUESTMAPPIN INIZIALE, NEL GET
	// MAPPING SPECIFICO SOLO IL PARAMETRO, IN QUESTO CASO ID
	@GetMapping("{id}")
	public ResponseEntity<Pizza> getPizza(@PathVariable int id) {

		Pizza pizza = pizzaService.findById(id);

		if (pizza == null)
			return new ResponseEntity<>(pizza, HttpStatus.NOT_FOUND);

		return new ResponseEntity<>(pizza, HttpStatus.OK);

	}

	@PostMapping
	public ResponseEntity<Pizza> create(@RequestBody Pizza pizza) {

		pizzaService.save(pizza);

		return new ResponseEntity<>(pizza, HttpStatus.OK);
	}

}
